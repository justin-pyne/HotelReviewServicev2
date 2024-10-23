package hotelapp;

import hotelapp.Controller.HotelController;
import hotelapp.Controller.ThreadSafeReviewController;
import hotelapp.Model.Hotel;
import hotelapp.Model.Review;
import hotelapp.Service.JsonService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** The driver class for project 3.
 * The main function should be able to take the following command line arguments
 * -hotels hotelFile -reviews reviewsDirectory -threads numThreads -output filepath
 * (only -hotels followed by the hotel file is required):
 * and read general information about the hotels from the hotelFile (a JSON file),
 * as well as concurrently read hotel reviews from the json files in reviewsDirectory.
 * The data should be loaded into data structures that allow efficient search.
 * If the -output flag is provided, the results should be output into the given file.
 * See pdf description of the project for the requirements.
 * You are expected to add other classes and methods from project 1 to this project,
 * and take instructor's / TA's feedback from a code review of project 1 into account.
 */
public class HotelReviewService {
    private ThreadSafeReviewController reviewController;
    private HotelController hotelController;
    /**
     * Parse given arguments that contain paths to the hotel file and the reviews folder,
     * and load hotel and review data into the corresponding data structures.
     * Do not store data structures (maps) in this class and do not do the actual parsing in this class,
     * think of a better design that includes multiple classes / packages, so that this class can
     * delegate work to other classes.
     * @param args  Arguments can be given in the following format where -reviews, -threads, -output are optional:
     *  -hotels pathToHotelFile -reviews pathToReviewsFolder -threads n -output outputFilePath
     *   or in a different order.
     */
    public void loadData(String[] args) {
        Map<String, String> argsMap = new HashMap<>();

        if (args.length == 0) {
            System.out.println("Missing arguments.");
            return;
        }

        for (int i = 0; i < args.length; i++) {
            if (args[i].startsWith("-")) {
                if (i + 1 < args.length && !args[i + 1].startsWith("-")) {
                    argsMap.put(args[i], args[i + 1]);
                    i++;
                } else {
                    System.out.println("Unexpected or missing value for flag:" + args[i]);
                    return;
                }
            } else {
                System.out.println("Unexpected argument: " + args[i]);
                return;
            }
        }
        JsonService js = new JsonService(argsMap.get("-output"));
        if (argsMap.containsKey("-hotels")){
            List<Hotel> hotels = js.parseHotel(argsMap.get("-hotels"));
            this.hotelController = new HotelController(hotels);
        }

        if (argsMap.containsKey("-reviews")){
            List<Review> reviews = js.parseReviews(argsMap.get("-reviews"), Integer.getInteger(argsMap.get("-threads")));
            this.reviewController = new ThreadSafeReviewController(reviews);
        }
    }

    /**
     * Process a given query and return the result as a string
     * @param query in one of the following formats:
     *              findHotel hotelId
     *              findReviews hotelId
     *              findWord word
     * @return String, the result of the query
     */
    public String processQuery(String query) {
        // FILL IN CODE:

        return "";
    }

    public static void main(String[] args) {
        // args can be specified in Run/Edit Configuration/Program Arguments
        HotelReviewService service = new HotelReviewService();
        try {
            service.loadData(args); // pass arguments to loadData
            // System.out.println(service.processQuery("findHotel 10323"));
            // System.out.println(service.processQuery("findReviews 25622"));
            System.out.println(service.processQuery("findWord dog"));
        }
        catch(Exception e) {
            System.out.println("Could not load data or process a query.");
        }
    }
}
