package hotelapp.Service;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import hotelapp.Controller.ThreadSafeReviewController;
import hotelapp.Model.Hotel;
import hotelapp.Model.Review;

import java.io.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;

public class JsonService {
    private ExecutorService poolManager;
    private Phaser phaser = new Phaser();
    private JsonParser parser = new JsonParser();
    private ThreadSafeReviewController reviewController;

    public JsonService(int numThreads, ThreadSafeReviewController reviewController) {
        this.poolManager = Executors.newFixedThreadPool(numThreads);
        this.reviewController = reviewController;
    }

    class ReviewWorker implements Runnable {
        private File file;

        public ReviewWorker(File file) {
            this.file = file;
        }

        @Override
        public void run() {
            List<Review> localReviews = new ArrayList<>();

            try(BufferedReader br = new BufferedReader(new FileReader(file))) {
                JsonObject obj = (JsonObject)parser.parse(br);
                JsonObject reviewDetails = obj.getAsJsonObject("reviewDetails");
                JsonObject reviewCollection = reviewDetails.getAsJsonObject("reviewCollection");
                JsonArray reviewArray = reviewCollection.getAsJsonArray("review");

                for (JsonElement ele : reviewArray) {
                    JsonObject reviewObj = ele.getAsJsonObject();

                    String hotelId = reviewObj.get("hotelId").getAsString();
                    String reviewId = reviewObj.get("reviewId").getAsString();
                    int rating = reviewObj.get("ratingOverall").getAsInt();
                    String userNickname = reviewObj.get("userNickname").getAsString();
                    String title = reviewObj.get("title").getAsString();
                    String reviewText = reviewObj.get("reviewText").getAsString();
                    String date = reviewObj.get("reviewSubmissionDate").getAsString();
                    if (userNickname.equals("")) {
                        userNickname = "Anonymous";
                    }

                    Review review = new Review(hotelId, reviewId, rating, title, reviewText, userNickname, date);
                    localReviews.add(review);
                }
                reviewController.addReviews(localReviews);
            } catch(IOException e) {
                System.out.println(e);
            } finally {
                phaser.arriveAndDeregister();
            }
        }
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
                String state = hotelObj.get("pr").getAsString();

                JsonObject ll = hotelObj.getAsJsonObject("ll");
                String lat = ll.get("lat").getAsString();
                String lon = ll.get("lng").getAsString();

                Hotel hotel = new Hotel(name, id, lat, lon, ad, city, state);
                hotels.add(hotel);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return hotels;
    }

    public void parseReviews(String filePath) {
        traverseReviewDirectory(filePath);
        waitToFinish();
    }

    private void traverseReviewDirectory(String filePath) {
        Path p = Paths.get(filePath);
        try(DirectoryStream<Path> pathsInDir = Files.newDirectoryStream(p)) {
            for(Path path : pathsInDir) {
                if(Files.isDirectory(path)) {
                    traverseReviewDirectory(path.toString());
                } else if (path.toString().endsWith(".json")) {
                    poolManager.submit(new ReviewWorker(path.toFile()));
                    phaser.register();
                }
            }
        } catch(IOException e) {
            System.out.println(e);
        }
    }

    private void waitToFinish() {
        phaser.awaitAdvance(phaser.getPhase());
        poolManager.shutdown();
    }
}
