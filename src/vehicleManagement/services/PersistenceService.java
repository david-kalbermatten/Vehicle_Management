package vehicleManagement.services;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;
import vehicleManagement.GlobalVars;
import vehicleManagement.data.rental.Rental;
import vehicleManagement.data.vehicle.*;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

public class PersistenceService {
    File vehicleFile;
    File rentalFile;
    ObjectMapper objectMapper;

    public PersistenceService() {
        vehicleFile = new File("vehicleFile.json");
        rentalFile = new File("rentalFile.json");
        objectMapper = new ObjectMapper();
        PolymorphicTypeValidator ptv =
                BasicPolymorphicTypeValidator.builder()
                        .allowIfSubType(Vehicle.class)
                        .build();
        objectMapper.activateDefaultTyping(ptv);
    }
    public void readFile() {
        try {
            List<Vehicle> vehicleList = objectMapper.readValue(vehicleFile, new TypeReference<ArrayList<Vehicle>>() {
                @Override
                public Type getType() {
                    return super.getType();
                }
            });
            List<Rental> rentalList = objectMapper.readValue(rentalFile, new TypeReference<ArrayList<Rental>>() {
                @Override
                public Type getType() {
                    return super.getType();
                }
            });
            GlobalVars.vService.vehicleList.addAll(vehicleList);
            GlobalVars.rService.rentalList.addAll(rentalList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Loaded File");
    }

    public void writeFile() {
        List<Vehicle> vehicleList = new ArrayList<>(GlobalVars.vService.vehicleList);
        List<Rental> rentalList = new ArrayList<>(GlobalVars.rService.rentalList);

        String vehicleJson = null;
        String rentalJson = null;
        try {
            vehicleJson = objectMapper.writeValueAsString(vehicleList);
            rentalJson = objectMapper.writeValueAsString(rentalList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            System.out.println("Parsing to JSON failed!");
        }
        if(vehicleJson != null && rentalJson != null) {
            try {
                BufferedWriter vehicleFileWriter = new BufferedWriter(new FileWriter(vehicleFile));
                BufferedWriter rentalFileWriter = new BufferedWriter(new FileWriter(rentalFile));
                vehicleFileWriter.write(vehicleJson);
                vehicleFileWriter.close();
                rentalFileWriter.write(rentalJson);
                rentalFileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Writing to File failed!");
            }
        }
        System.out.println("Saved File");
    }
}
