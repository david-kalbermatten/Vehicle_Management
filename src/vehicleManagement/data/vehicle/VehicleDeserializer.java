package vehicleManagement.data.vehicle;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class VehicleDeserializer implements JsonDeserializer<Vehicle> {
    private String vehicleTypeElementName;
    private Gson gson;
    private Map<String, Class<? extends Vehicle>> vehicleTypeRegistry;
 
    public VehicleDeserializer(String vehicleTypeElementName) {
        this.vehicleTypeElementName = vehicleTypeElementName;
        this.gson = new Gson();
        this.vehicleTypeRegistry = new HashMap<>();
    }
 
    public void registerVehicleType(String vehicleTypeName, Class<? extends Vehicle> vehicleType) {
        vehicleTypeRegistry.put(vehicleTypeName, vehicleType);
    }
 
    public Vehicle deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
        JsonObject vehicleObject = json.getAsJsonObject();
        JsonElement vehicleTypeElement = vehicleObject.get(vehicleTypeElementName);
 
        Class<? extends Vehicle> vehicleType = vehicleTypeRegistry.get(vehicleTypeElement.getAsString());
        return gson.fromJson(vehicleObject, vehicleType);
    }
}