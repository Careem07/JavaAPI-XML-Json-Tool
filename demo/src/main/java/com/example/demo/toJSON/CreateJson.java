package com.example.demo.toJSON;

import com.example.demo.Restaurant;
import jakarta.json.*;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;

import java.io.*;
import java.util.List;

public class CreateJson {

    public void createJsonFromTable(List<Restaurant> restaurantList) throws IOException {
//        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder(restaurantList);
//
////        JsonReader jsonReader = Json.createReader(new ObjectInputStream( restaurantList));
//        JsonObjectBuilder resultBuilder = Json.createObjectBuilder();
//        resultBuilder.add("persons", arrayBuilder.build());
//        JsonObject result = resultBuilder.build();
//        System.out.println(result);
        Jsonb jsonb = JsonbBuilder.create();
        String result = jsonb.toJson(restaurantList);
        try(FileWriter fileWriter = new FileWriter("output.json")){
            fileWriter.write(result);
        }


    }


}
