package hotelapp.Model;

public class ReviewWithFrequency implements Comparable<ReviewWithFrequency>{
    private final Review review;
    private final int frequency;


    /**
     * Constructor for review w/ frequency objects
     * @param review review object
     * @param frequency frequency
     */
    public ReviewWithFrequency(Review review, int frequency) {
        this.review = review;
        this.frequency = frequency;
    }

    /**
     * Getter for review
     * @return review, the Review object stored in this ReviewWithFrequency object
     */
    public Review getReview(){
        return this.review;
    }

    /**
     * Getter for frequency int
     * @return frequency, # of occurrences of a word
     */
    public int getFrequency(){
        return this.frequency;
    }

    /**
     * compareTo Override for Comparable
     * @param o the object to be compared.
     * @return int, represents the result of the comparison
     */
    @Override
    public int compareTo(ReviewWithFrequency o) {
        int frequencyCompare = Integer.compare(o.getFrequency(), this.getFrequency());
        if (frequencyCompare != 0) {
            return frequencyCompare;
        }
        int dateCompare = o.getReview().getSubmissionDate().compareTo(this.getReview().getSubmissionDate());
        if (dateCompare != 0) {
            return dateCompare;
        }
        return this.getReview().getReviewId().compareTo(o.getReview().getReviewId());
    }

    /**
     * toString override that defaults to review toString
     * @return String, review toString
     */
    @Override
    public String toString() {
        return frequency + System.lineSeparator() + review.toString();
    }
}
