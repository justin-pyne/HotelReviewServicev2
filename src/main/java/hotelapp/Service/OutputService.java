package hotelapp.Service;

import hotelapp.Controller.HotelController;
import hotelapp.Controller.ThreadSafeReviewController;
import hotelapp.Model.Hotel;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class OutputService {
    public void writeToFile(String output, HotelController hotelController, ThreadSafeReviewController reviewController) {
        try(PrintWriter pw = new PrintWriter(new FileWriter(output))) {
            for (Hotel hotel : hotelController.getHotels()){
                pw.println();
                pw.println("********************");
                pw.println(hotel.toString());
                String reviewOutput = reviewController.findReviews(hotel.getHotelId());
                if (reviewOutput != null) {
                    pw.println(reviewOutput);
                }
            }
        } catch(IOException e) {
            System.out.println(e);
        }
    }
}





