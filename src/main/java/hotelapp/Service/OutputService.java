package hotelapp.Service;

import hotelapp.Controller.HotelController;
import hotelapp.Controller.ThreadSafeReviewController;
import hotelapp.Model.Hotel;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Class to handle writing Hotel and Review information into files
 */
public class OutputService {

    /**
     * A method to write Hotel and Review information to a desired file
     * @param output File location to write to
     * @param hotelController hotelController containing the Hotels in hotelMap
     * @param reviewController reviewController containing the Reviews in reviewMap and the invertedIndex
     */
    public void writeToFile(String output, HotelController hotelController, ThreadSafeReviewController reviewController) {
        try(PrintWriter pw = new PrintWriter(new FileWriter(output))) {
            for (Hotel hotel : hotelController.getHotels()){
                pw.println();
                pw.println("********************");
                pw.println(hotel.toString());
                String reviewOutput = reviewController.findReviews(hotel.getHotelId());
                if (reviewOutput != null && !reviewOutput.isEmpty()) {
                    pw.print(reviewOutput);
                }
            }
        } catch(IOException e) {
            System.out.println(e);
        }
    }
}





