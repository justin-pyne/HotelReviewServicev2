package hotelapp;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;

public class Review {
    /**
     * A class that represents a review
     */

    //Instance Variables
    private final String hotelId;
    private final String reviewId;

    @SerializedName(value = "ratingOverall")
    private final double rating;

    private final String title;
    private final String reviewText;
    private final String userNickname;

    @SerializedName(value = "reviewSubmissionDate")
    private final LocalDate submissionDate;


    /**
     * Constructor for the review object containing all necessary info about the review.
     * @param hotelId, id of the hotel
     * @param reviewId, id of the review
     * @param rating, numerical rating from the review
     * @param title, title of the review
     * @param reviewText, text body of the review
     * @param userNickname, user nickname who submitted the review
     * @param submissionDate, date review was written
     */
    public Review(String hotelId, String reviewId, double rating, String title, String reviewText, String userNickname, String submissionDate) {
        this.hotelId = hotelId;
        this.reviewId = reviewId;
        this.rating = rating;
        this.title = title;
        this.reviewText = reviewText;
        this.userNickname = userNickname;
        this.submissionDate = LocalDate.parse(submissionDate);
    }

    /**
     * Getter for the hotelId
     * @return String, the hotelId for this review
     */
    public String getHotelId(){
        return this.hotelId;
    }

    /**
     * Getter for submissionDate
     * @return LocalDate, a localdate representing the submission date
     */
    public LocalDate getSubmissionDate(){
        return this.submissionDate;
    }

    /**
     * Getter for reviewId
     * @return String, the reviewId of this review
     */
    public String getReviewId(){
        return this.reviewId;
    }

    /**
     * Getter for review text
     * @return reviewText String
     */
    public String getReviewText(){
        return this.reviewText;
    }

    /**
     * Override for the toString method
     * @return String, string representation of the review
     */
    @Override
    public String toString() {
        return "hotelId = " + hotelId + System.lineSeparator() +
                "reviewId = " + reviewId + System.lineSeparator() +
                "averageRating = " + rating + System.lineSeparator() +
                "title = " + title + System.lineSeparator() +
                "reviewText = " + reviewText + System.lineSeparator() +
                "userNickname = " + userNickname + System.lineSeparator() +
                "submissionDate = " + submissionDate + System.lineSeparator() +
                "--------------------" + System.lineSeparator();
    }
}
