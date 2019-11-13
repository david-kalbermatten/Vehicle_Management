package vehicleManagement.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;
import vehicleManagement.GlobalVars;
import vehicleManagement.data.DataWrapper;
import vehicleManagement.data.rental.Rental;
import vehicleManagement.data.vehicle.*;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

public class PersistenceService {
    File dataFile;
    ObjectMapper objectMapper;

    public PersistenceService() {
        dataFile = new File("data.json");
        objectMapper = new ObjectMapper();
        PolymorphicTypeValidator ptv =
                BasicPolymorphicTypeValidator.builder()
                        .allowIfSubType(Vehicle.class)
                        .build();
        objectMapper.activateDefaultTyping(ptv);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNRESOLVED_OBJECT_IDS, false);
        objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true);
    }
    public void readFile() {
        if(dataFile.exists()) {
            try {
                DataWrapper dataWrapper = objectMapper.readValue(dataFile, DataWrapper.class);
                GlobalVars.vService.vehicleList.addAll(dataWrapper.vehicleList);
                dataWrapper.rentalList.forEach(rental -> {
                    GlobalVars.vService.vehicleList.forEach(vehicle -> {
                        if(rental.getVehicleID() == vehicle.getId()) {
                            rental.setVehicle(vehicle);
                        }
                    });
                    rental.setVehicleID(-1);
                });
                GlobalVars.vService.vehicleList.forEach(v -> v.setId(-1));
                GlobalVars.rService.rentalList.addAll(dataWrapper.rentalList);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Loaded File");
        } else {
            System.out.println("File not found");
        }
    }

    public void writeFile() {
        List<Vehicle> vehicleList = new ArrayList<>(GlobalVars.vService.vehicleList);
        for (int i = 0; i < vehicleList.size(); i++) {
            vehicleList.get(i).setId(i);
        }
        List<Rental> rentalList = new ArrayList<>(GlobalVars.rService.rentalList);
        rentalList.forEach(rental -> {
            rental.setVehicleID(rental.getVehicle().getId());
            rental.setVehicle(null);
        });

        DataWrapper dataWrapper = new DataWrapper();
        dataWrapper.vehicleList = new ArrayList<>(vehicleList);
        dataWrapper.rentalList = new ArrayList<>(rentalList);

        String dataJson = null;
        try {
            dataJson = objectMapper.writeValueAsString(dataWrapper);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            System.out.println("Parsing to JSON failed!");
        }
        if(dataJson != null) {
            try {
                BufferedWriter dataWriter = new BufferedWriter(new FileWriter(dataFile));
                dataWriter.write(dataJson);
                dataWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Writing to File failed!");
            }
        }
        System.out.println("Saved File");
    }
}
