package com.example.geocodegoogle;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
@SpringBootApplication
public class GeocodegoogleApplication {
    private static final String GOOGLE_API_KEY = "add_your_api_key";
    //----------------------REPLACE HERE BY THE ADDRESS YOU WANT TO SEARCH  --------------------------------------------------------
    private static final String ADDRESS = "add your address";


    public static void main(String[] args) throws IOException, InterruptedException, ApiException {
        SpringApplication.run(GeocodegoogleApplication.class, args);
        GeoApiContext context = new GeoApiContext.Builder().apiKey(GOOGLE_API_KEY).build();
        GeocodingResult[] results =  GeocodingApi.geocode(context,ADDRESS).await();
        var houseNumber = "";
        var street = "";
        for (var x : results[0].addressComponents) {
            for (var y : x.types){
                if (y.toString() == "street_number")
                    houseNumber = x.longName;
                if (y.toString() == "street_address" || y.toString() == "route" || y.toString() == "intersection" )
                    street = x.longName;
            }
        }
        System.out.println();
        System.out.println("Entire address provided: " + ADDRESS);
        System.out.println("Street of the address provided: " + street);
        System.out.println("Number of the address provided: " + houseNumber);
    }

}
