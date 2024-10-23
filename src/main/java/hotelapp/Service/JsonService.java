package hotelapp.Service;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import hotelapp.Model.Hotel;
import hotelapp.Model.Review;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;

public class JsonService {
    private ExecutorService executor;
    private Phaser phaser = new Phaser();
    private String output;

    public JsonService(int numThreads, String output) {
        this.output = output;
    }

    public List<Hotel> parseHotel(String filePath) {
        List<Hotel> hotels = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            JsonParser parser = new JsonParser();

            JsonObject obj = (JsonObject) parser.parse(br);
            JsonArray hotelArray = obj.getAsJsonArray("sr");
            for (JsonElement ele : hotelArray) {
                JsonObject hotelObj = ele.getAsJsonObject();

                String name = hotelObj.get("f").getAsString();
                String id = hotelObj.get("id").getAsString();
                String ad = hotelObj.get("ad").getAsString();
                String city = hotelObj.get("ci").getAsString();

                JsonObject ll = hotelObj.getAsJsonObject("ll");
                String lat = ll.get("lat").getAsString();
                String lon = ll.get("lng").getAsString();

                Hotel hotel = new Hotel(name, id, lat, lon, ad, city);
                hotels.add(hotel);
            }


        } catch (IOException e) {
            System.out.println(e);
        }
        return hotels;
    }

    public List<Review> parseReviews(String filePath) {

    }


}
