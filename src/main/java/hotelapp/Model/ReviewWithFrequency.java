package hotelapp.Model;

import java.time.LocalDate;

/**
 * A model class storing a Review that stores the frequency of a particular word in the review
 */
public class ReviewWithFrequency implements Comparable<ReviewWithFrequency> {
    private final Review review;
    private final int frequency;

    public ReviewWithFrequency(Review review, int frequency) {
        this.review = review;
        this.frequency = frequency;
    }

    /**
     * Getter for date of Review
     * @return LocalDate date associated with this review
     */
    public LocalDate getReviewDate() {
        return review.getDate();
    }

    /**
     * Getter for reviewId
     * @return String reviewId associated with this review
     */
    public String getReviewId() {
        return review.getReviewId();
    }

    /**
     * Getter for frequency
     * @return frequency of the word associated with this ReviewWithFrequency
     */
    public int getFrequency() {
        return frequency;
    }

    /**
     * Comparable compareTo implementation
     * @param o the object to be compared.
     * @return int for comparison
     */
    @Override
    public int compareTo(ReviewWithFrequency o) {
        int comp = Integer.compare(o.getFrequency(), this.getFrequency());
        if (comp != 0) {
            return comp;
        }
        int dateComp = o.getReviewDate().compareTo(this.getReviewDate());
        if (dateComp != 0) {
            return dateComp;
        }
        return this.getReviewId().compareTo(o.getReviewId());
    }

    /**
     * toString override
     * @return A String representation of this ReviewWithFrequency object
     */
    @Override
    public String toString() {
        String content = review.toString();
        content = content.replaceFirst("--------------------" + System.lineSeparator(), "");


        return "--------------------" + System.lineSeparator() +
                frequency + System.lineSeparator() +
                content;
    }
}
