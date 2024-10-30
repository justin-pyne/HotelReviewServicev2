package hotelapp.Controller;

import hotelapp.Model.Review;
import hotelapp.Model.ReviewWithFrequency;

import java.util.*;


/**
 * A class that stores a reviewMap linking Reviews to Hotels, and an invertexIndex for word lookup in Reviews.
 */
public class ReviewController {
    private final Map<String, List<Review>> reviewMap = new TreeMap<>();
    private final Map<String, TreeSet<ReviewWithFrequency>> invertedIndex = new HashMap<>();

    public ReviewController() {}

    /**
     * Adds reviews to the reviewMap
     * @param newReviews A list of Review objects to be added
     */
    public void addReviews(List<Review> newReviews) {
        for (Review review : newReviews) {
            if (!reviewMap.containsKey(review.getHotelId())) {
                reviewMap.put(review.getHotelId(), new ArrayList<>());
            }
            reviewMap.get(review.getHotelId()).add(review);
            invertedIndexReview(review);
        }
    }

    /**
     * Looks up all Reviews for a Hotel by hotelId
     * @param hotelId the desired hotelId to fetch the Reviews for
     * @return A String with all Reviews for the provided Hotel
     */
    public String findReviews(String hotelId){
        if (!reviewMap.containsKey(hotelId)) {
            return null;
        }

        List<Review> reviews = reviewMap.get(hotelId);

        reviews.sort((r1, r2) -> {
           int comp = r2.getDate().compareTo(r1.getDate());
           if (comp == 0) {
               return r1.getReviewId().compareTo(r2.getReviewId());
           }
           return comp;
        });

        StringBuilder result = new StringBuilder();

        for (Review review : reviews) {
            result.append(review.toString());
        }
        return result.toString();
    }

    /**
     * Looks up all Reviews with the provided word, sorted in descending order.
     * @param word the word to look up in the Reviews
     * @return String representation of all the Reviews containing the word, and the frequency of the word
     */
    public String findWord(String word){
        word = word.toLowerCase();
        if (!invertedIndex.containsKey(word)) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        for (ReviewWithFrequency reviewWithFrequency : invertedIndex.get(word)) {
            result.append(reviewWithFrequency.toString());
        }
        return result.toString();
    }

    /**
     * Private helper to add the words in a Review to the invertedIndex map
     * @param review The desired review to be processed to the invertedIndex
     */
    private void invertedIndexReview(Review review) {
        String[] words = review.getReviewText().toLowerCase().split("[,;!\\. ]");

        Map<String, Integer> wordCount = new HashMap<>();
        for (String word : words) {
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }

        for (String word : wordCount.keySet()) {
            int frequency = wordCount.get(word);
            ReviewWithFrequency reviewWithFrequency = new ReviewWithFrequency(review, frequency);

            invertedIndex.computeIfAbsent(word, v -> new TreeSet<>()).add(reviewWithFrequency);
        }
    }

}
