package hotelapp.Controller;

import hotelapp.Model.Review;

import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ThreadSafeReviewController extends ReviewController{
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
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
    }

    @Override
    protected void initialize(List<Review> reviews) {
        lock.writeLock().lock();
        try {
            super.initialize(reviews);
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public String findReviews(String hotelId) {
        try {
            lock.readLock().lock();
            return super.findReviews(hotelId);
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public String findWord(String word) {
       try {
           lock.readLock().lock();
           return super.findWord(word);
       } finally {
          lock.readLock().unlock();
       }
    }
}
