package hotelapp.Controller;

import hotelapp.Model.Review;
import hotelapp.Model.ReviewWithFrequency;

import java.util.*;

/**
 * A class that stores Maps of reviews and reviews with word frequency, and
 * provides review and word lookup functionality
 */
public class ReviewController {
    private Map<String, List<Review>> reviewsByHotel = new HashMap<>();
    private Map<String, TreeSet<ReviewWithFrequency>> invertedIndex = new HashMap<>();

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
    public ReviewController(List<Review> reviews){
        for (Review review : reviews){
            this.reviewsByHotel.computeIfAbsent(review.getHotelId(), v -> new ArrayList<>()).add(review);
            invertedIndexReview(review);
        }
    }

    /**
     * Puts ReviewWithFrequency objects in TreeSet for Words in provided Review
     * @param review Review to index words in
     */
    public void invertedIndexReview(Review review){
        String[] words = review.getReviewText().toLowerCase().split("[\\s\\p{Punct}&&[^-]]+");

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

    /**
     * Looks up all reviews by hotelId and returns in sorted order
     * @param hotelId, the hotelId to look up
     * @return String, a string with information from all reviews matching the hotelId
     */
    public String findReviews (String hotelId){
        if (!reviewsByHotel.containsKey(hotelId)){
            throw new IllegalArgumentException();
        }

        List<Review> reviews = reviewsByHotel.get(hotelId);

        reviews.sort((r1, r2) -> {
            int dateCompare = r2.getSubmissionDate().compareTo(r1.getSubmissionDate());
            if (dateCompare != 0) {
                return dateCompare;
            }
            return r1.getReviewId().compareTo(r2.getReviewId());
        });
        StringBuilder result = new StringBuilder();
        for (Review review : reviews){
            result.append(review.toString());
        }
        return result.toString();
    }

    /**
     * Searches reviews for word, then returns all reviews containing
     * based on frequency of the word then the review id.
     * @param word, word to find frequency of
     * @return list of all reviews with word, sorted by frequency, then review id
     */
    public String findWord(String word){
        word = word.toLowerCase();
        if (!invertedIndex.containsKey(word)){
            return "";
        }
        StringBuilder result = new StringBuilder();
        for (ReviewWithFrequency reviewWithFrequency : invertedIndex.get(word)){
            result.append(reviewWithFrequency.toString());
        }
        return result.toString();
    }
}
