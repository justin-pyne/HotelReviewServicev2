package hotelapp.Controller;

import hotelapp.Model.Review;

import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ThreadSafeReviewController extends ReviewController{
    private ReentrantReadWriteLock lock;
    /**
     * Constructor for review manager, converts list of reviews to
     * Map of reviewsByHotel:
     * Key : hotelId
     * Value : List of reviews for that hotel
     * &
     * Map of reviewsByFrequency (invertedIndex):
     * Key: Word
     * Value: Sorted treeset of reviews by frequency of word
     *
     * @param reviews list of all reviews
     */
    public ThreadSafeReviewController(List<Review> reviews) {
        super(reviews);
        lock = new ReentrantReadWriteLock();
    }

    @Override
    public String findReviews(String hotelId) {
        return super.findReviews(hotelId);
    }

    @Override
    public String findWord(String word) {
        return super.findWord(word);
    }
}