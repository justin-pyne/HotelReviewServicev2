package hotelapp.Model;

import java.time.LocalDate;

/**
 * A model class representing a Review
 */
public class Review implements Comparable<Review>{
    private final String hotelId;
    private final String reviewId;
    private final double rating;
    private final String reviewTitle;
    private final String reviewText;
    private final String userName;
    private final LocalDate date;

    public Review(String hotelId, String reviewId, double rating, String reviewTitle, String reviewText, String userName, String date) {
        this.hotelId = hotelId;
        this.reviewId = reviewId;
        this.rating = rating;
        this.reviewTitle = reviewTitle;
        this.reviewText = reviewText;
        this.userName = userName;
        this.date = LocalDate.parse(date);

    }

    /**
     * Getter for hotelId associated with this review
     * @return hotelId String
     */
    public String getHotelId() {
        return hotelId;
    }

    /**
     * Getter for the reviewText
     * @return String of reviewText
     */
    public String getReviewText() {
        return reviewText;
    }

    /**
     * Getter for reviewId
     * @return The reviewId String
     */
    public String getReviewId() {
        return reviewId;
    }

    /**
     * Getter for date of the review
     * @return LocalDate date of the review
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * toString override
     * @return A String representation of the Review object
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
         sb.append("--------------------").append(System.lineSeparator());
         sb.append("Review by ").append(userName).append(" on ").append(date).append(System.lineSeparator());
         sb.append("Rating: ").append(Math.round(rating)).append(System.lineSeparator());
         sb.append("ReviewId: ").append(reviewId).append(System.lineSeparator());
         sb.append(reviewTitle).append(System.lineSeparator());
         sb.append(reviewText).append(System.lineSeparator());
         return sb.toString();
    }

    /**
     * Comparable compareTo implementation
     * @param o the object to be compared.
     * @return int for comparison
     */
    @Override
    public int compareTo(Review o) {
        int comp = this.date.compareTo(o.date);
        if(comp == 0) {
            return this.reviewId.compareTo(o.reviewId);
        }
        return comp;
    }
}
