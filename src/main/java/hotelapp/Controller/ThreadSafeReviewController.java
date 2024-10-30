package hotelapp.Controller;

import hotelapp.Model.Review;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * A thread safe version of the ReviewController
 */
public class ThreadSafeReviewController extends ReviewController{
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public ThreadSafeReviewController() {}

    /**
     * Thread safe override for addReviews, add a List of Reviews to reviewMap
     * @param newReviews A list of Review objects to be added
     */
    @Override
    public void addReviews(List<Review> newReviews) {
        lock.writeLock().lock();
        try {
            super.addReviews(new ArrayList<>(newReviews));
        } finally {
            lock.writeLock().unlock();
        }
    }

    /**
     * Thread safe override for findReviews, looks up reviews for a Hotel by hotelId
     * @param hotelId the desired hotelId to fetch the Reviews for
     * @return A String representation of all Reviews for the desired Hotel
     */
    @Override
    public String findReviews(String hotelId) {
        lock.readLock().lock();
        try {
            return super.findReviews(hotelId);
        } finally {
            lock.readLock().unlock();
        }
    }

    /**
     * Thread safe override for findWord, looks up frequency of a word in all Reviews.
     * @param word the word to look up in the Reviews
     * @return A String representation of all Reviews and the frequency for the desired word
     */
    @Override
    public String findWord(String word) {
        lock.readLock().lock();
        try {
           return super.findWord(word);
       } finally {
          lock.readLock().unlock();
       }
    }
}
