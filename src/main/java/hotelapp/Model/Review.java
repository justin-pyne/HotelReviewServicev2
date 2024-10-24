package hotelapp.Model;

import java.time.LocalDate;

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

    public String getHotelId() {
        return hotelId;
    }

    public String getReviewText() {
        return reviewText;
    }

    public String getReviewId() {
        return reviewId;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "--------------------" + System.lineSeparator() +
                "Review by " + userName + " on " + date + System.lineSeparator() +
                "Rating: " + Math.round(rating) + System.lineSeparator() +
                "ReviewId: " + reviewId + System.lineSeparator() +
                reviewTitle + System.lineSeparator() +
                reviewText + System.lineSeparator();


//        + hotelId + System.lineSeparator() +
//                "reviewId = " + reviewId + System.lineSeparator() +
//                "averageRating = " + rating + System.lineSeparator() +
//                "title = " + reviewTitle + System.lineSeparator() +
//                "reviewText = " + reviewText + System.lineSeparator() +
//                "userNickname = " + userName + System.lineSeparator() +
//                "submissionDate = " + date + System.lineSeparator() +
//                "--------------------" + System.lineSeparator();
    }

    @Override
    public int compareTo(Review o) {
        int comp = this.date.compareTo(o.date);
        if(comp == 0) {
            return this.reviewId.compareTo(o.reviewId);
        }
        return comp;
    }
}
