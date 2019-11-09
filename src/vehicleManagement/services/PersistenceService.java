package vehicleManagement.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.TypeDeserializerBase;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import vehicleManagement.GlobalVars;
import vehicleManagement.data.rental.Rental;
import vehicleManagement.data.vehicle.*;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

public class PersistenceService {
    File vehicleFile;
    File rentalFile;

    public PersistenceService() {
        vehicleFile = new File("vehicleFile.json");
        rentalFile = new File("rentalFile.json");
    }
    public void readFile() {
        try {
            BufferedReader vehicleFileReader = new BufferedReader(new FileReader(vehicleFile));
            String vehicleJson = vehicleFileReader.lines().collect(Collectors.joining(System.lineSeparator()));
            vehicleFileReader.close();
            BufferedReader rentalFileReader = new BufferedReader(new FileReader(rentalFile));
            String rentalJson = rentalFileReader.lines().collect(Collectors.joining(System.lineSeparator()));
            rentalFileReader.close();


            VehicleDeserializer vehicleDeserializer = new VehicleDeserializer("type");
            vehicleDeserializer.registerVehicleType("Car", Car.class);
            vehicleDeserializer.registerVehicleType("Motorcycle", Motorcycle.class);
            vehicleDeserializer.registerVehicleType("Transporter", Transporter.class);

            Gson gsonVehicle = new GsonBuilder()
                    .registerTypeAdapter(Vehicle.class, vehicleDeserializer)
                    .create();
            Gson gsonRental = new Gson();

            List<Vehicle> vehicleList = gsonVehicle.fromJson(vehicleJson, new TypeToken<ArrayList<Vehicle>>() {}.getType());
            List<Rental> rentalList = gsonVehicle.fromJson(rentalJson, new TypeToken<ArrayList<Rental>>() {}.getType());

            //vehicleList.forEach(vehicle -> System.out.println(vehicle.getIdNumber()));

            GlobalVars.vService.vehicleList.addAll(vehicleList);
            GlobalVars.rService.rentalList.addAll(rentalList);

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Loaded File");
    }

    public void writeFile() {
        Gson gson = new Gson();
        List<Vehicle> vehicleList = new ArrayList<Vehicle>(GlobalVars.vService.vehicleList);
        List<Rental> rentalList = new ArrayList<Rental>(GlobalVars.rService.rentalList);
        String vehicleJson = gson.toJson(vehicleList);
        String rentalJson = gson.toJson(rentalList);
        try {
            BufferedWriter vehicleFileWriter = new BufferedWriter(new FileWriter(vehicleFile));
            vehicleFileWriter.write(vehicleJson);
            vehicleFileWriter.close();

            BufferedWriter rentalFileWriter = new BufferedWriter(new FileWriter(rentalFile));
            rentalFileWriter.write(rentalJson);
            rentalFileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Saved File");
    }
}
