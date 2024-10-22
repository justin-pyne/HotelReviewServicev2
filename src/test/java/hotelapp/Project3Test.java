package hotelapp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A Test class for Project 3.
 * Note: Passing these tests does not mean your code is correct; students are responsible for
 * testing their code.
 *
 */
public class Project3Test {

	private static final int RUNS = 10;
	private static final int THREADS = 4;

	@Test
	@Timeout(value = TestUtils.TIMEOUT1, unit = TimeUnit.MILLISECONDS)
	void testOneHotel() {
		// No reviews, reading one hotel from hotels1.json.
		// Asks the program to output hotels to the file "studentOutput"
		// Compares studentOutput with expectedOutputHotels1
		String testName = "testOneHotel";
		String[] args = {
				"-hotels", "dataset" + File.separator + "hotelsTiny" + File.separator + "hotel1.json",
				"-output", "output" + File.separator + "studentOutputHotels1"
		};
		Path expected = Paths.get(TestUtils.OUTPUT_DIR + File.separator + "expectedOutputHotels1"); // instructor's output
		Path actual = Paths.get(args[3]);
		//System.out.println(expected + " " + actual);
		runTestAndCompare(args, actual, expected, testName);
	}

	@Test
	@Timeout(value = TestUtils.TIMEOUT1, unit = TimeUnit.MILLISECONDS)
	public void testThreeReviewsSameHotel() {
		// reading one hotel from hotels1.json.
		// reading three reviews from reviewsTiny folder
		// Asks the program to output hotel & review data to file "studentOutputHotel1Reviews3"
		// Compares studentOutputHotel1Reviews3 with expectedOutputHotel1Reviews3".
		String testName = "testThreeReviewsSameHotel";
		String[] args = {"-hotels", "dataset" + File.separator + "hotelsTiny" + File.separator + "hotel1.json",
				"-reviews", "dataset" + File.separator + "reviewsTiny",
				"-output", "output" + File.separator + "studentOutputHotel1Reviews3"};
		Path expected = Paths.get( "output" + File.separator  + "expectedOutputHotel1Reviews3"); // instructor's output
		Path actual = Paths.get(args[5]);
		runTestAndCompare(args, actual, expected, testName);
	}

	@Test
	@Timeout(value = TestUtils.TIMEOUT1, unit = TimeUnit.MILLISECONDS)
	public void testConcurrentBuildSmallSet() {
		String testName = "testConcurrentBuildSmallSet";
		String[] args = {"-hotels", "dataset" + File.separator + "hotels" + File.separator + "hotels.json",
				"-reviews", "dataset" + File.separator + "reviews",
				"-output", "output" + File.separator + "studentOutputReviewsSet", "-threads", "3"};
		Path expected = Paths.get( "output" + File.separator  + "expectedOutputReviewsSet"); // instructor's output
		Path actual = Paths.get(args[5]);
		runTestAndCompare(args, actual, expected, testName);
	}

	@Test
	@Timeout(value = TestUtils.TIMEOUT, unit = TimeUnit.MILLISECONDS)
	public void testConcurrentBuildLargerSetSeveralThreads() {
		String testName = "testConcurrentBuildLargerSetSeveralThreads";
		String[] args = {"-hotels", "dataset" + File.separator + "hotels" + File.separator + "hotels.json",
				"-reviews", "dataset" + File.separator + "reviewsLargeSet",
				"-output", "output" + File.separator + "studentOutputLargeSet", "-threads", "3"};
		Path expected = Paths.get( "output" + File.separator  + "expectedOutputLargeSet"); // instructor's output
		Path actual = Paths.get(args[5]);
		runTestAndCompare(args, actual, expected, testName);
	}

	@Test
	@Timeout(value = TestUtils.TIMEOUT, unit = TimeUnit.MILLISECONDS)
	public void testBuildLargerSetOneThread() {
		String testName = "testBuildLargerSetOneThread";
		String[] args = {"-hotels", "dataset" + File.separator + "hotels" + File.separator + "hotels.json",
				"-reviews", "dataset" + File.separator + "reviewsLargeSet",
				"-output", "output" + File.separator + "studentOutputLargeSet", "-threads", "1"};
		Path expected = Paths.get( "output" + File.separator  + "expectedOutputLargeSet"); // instructor's output
		Path actual = Paths.get(args[5]);
		runTestAndCompare(args, actual, expected, testName);

	}
	
	/**
	 * Tests the application output multiple times, to make sure the
	 * results are always consistent.
	 */
	@Test
	@Timeout(value = TestUtils.TIMEOUT * RUNS, unit = TimeUnit.MILLISECONDS)
	public void testHotelDataConsistency() {
		
		for (int i = 0; i < RUNS; i++) {
			testConcurrentBuildLargerSetSeveralThreads();
		}
	}

	@Test
	public void testFindHotelQuery() {
		HotelReviewService service  = new HotelReviewService();
		String[] args = {"-hotels", "dataset" + File.separator + "hotels" + File.separator + "hotels.json",
				"-reviews", "dataset" + File.separator + "reviews",
				"-threads", "3"};

		service.loadData(args); // run loadData in HotelReviewService with these arguments
		String actual = service.processQuery("findHotel 10323");
		String expected = "Hilton Garden Inn San Francisco/Oakland Bay Bridge: 10323" + System.lineSeparator() +
				"1800 Powell Street" + System.lineSeparator() +
				"Emeryville, CA";
		assertEquals(expected, actual.trim(), "processQuery - findHotel10323 returned incorrect result.");
	}

	@Test
	public void testFindReviewsQuery() {
		HotelReviewService service  = new HotelReviewService();
		String[] args = {"-hotels", "dataset" + File.separator + "hotels" + File.separator + "hotels.json",
				"-reviews", "dataset" + File.separator + "reviews",
				"-threads", "3"};

		service.loadData(args);
		String res = service.processQuery("findReviews 150946");
		assertNotNull(res);
		System.out.println(res);
		StringBuilder sb = new StringBuilder();
		sb.append("--------------------" + System.lineSeparator());
		sb.append("Review by Leah on 2016-06-24" + System.lineSeparator() +
				"Rating: 4" + System.lineSeparator() +
				"ReviewId: 576d839399fac30b8311b38b" + System.lineSeparator() +
				"New hotel close to Union Square. Great location!" + System.lineSeparator() +
				"Got checked in early with a room upgrade. Good breakfast great service with a great location. " + System.lineSeparator() +
				"--------------------" + System.lineSeparator() +
				"Review by Dr Phil on 2016-06-06" + System.lineSeparator() +
				"Rating: 5" + System.lineSeparator() +
				"ReviewId: 5754befb630b760b7cb60b8b" + System.lineSeparator() +
				"Highly recommend a stay at this hotel." + System.lineSeparator() +
				"The staff was nice. The hotel was clean. Breakfast was great each morning. Close to shopping, food, transportation, and touring." + System.lineSeparator() +
				"--------------------" + System.lineSeparator() +
				"Review by jJoe on 2016-05-15" + System.lineSeparator() +
				"Rating: 5" + System.lineSeparator() +
				"ReviewId: 573885f6fd62b709fce5ba67" + System.lineSeparator() +
				"Nice hotel convenient location " + System.lineSeparator() +
				"This was a very nice hotel in the heart of" + System.lineSeparator() +
				"Union Square. Staff was very friendly and facility very clean. Room was a little small but well appointed." + System.lineSeparator() +
				"--------------------" + System.lineSeparator() +
				"Review by Anonymous on 2016-03-20" + System.lineSeparator() +
				"Rating: 4" + System.lineSeparator() +
				"ReviewId: 56ef0e2e6ae639097a90ca90" + System.lineSeparator() +
				"" + System.lineSeparator() +
				"I liked the hotel, close to everything." + System.lineSeparator() +
				"The neighborhood isnt so pretty though and noisy but this hotel is the best in the area." + System.lineSeparator() +
				"--------------------" + System.lineSeparator() +
				"Review by Jason on 2016-03-19" + System.lineSeparator() +
				"Rating: 3" + System.lineSeparator() +
				"ReviewId: 56eda2c9b800f7097acb584b" + System.lineSeparator() +
				"Tiny new hotel tucked away inside an old building" + System.lineSeparator() +
				"I stayed here during GDC 2016. The hotel had just opened - it was so new that they didn't yet have permanent signage, and I had trouble finding the place because I wasn't looking for the temporary signage inside the window. The continental breakfast included hot eggs and meat, although the quality was just barely enjoyable. Overall it was clean and new, but no-frills. Perfect for a trip where you want a clean and comfortable room downtown, but won't be spending much time in the hotel." + System.lineSeparator() +
				"--------------------" + System.lineSeparator() +
				"Review by Darrin on 2016-02-23" + System.lineSeparator() +
				"Rating: 3" + System.lineSeparator() +
				"ReviewId: 56cc561903df4a09818ffadc" + System.lineSeparator() +
				"Beware of double charging your credit card" + System.lineSeparator() +
				"Paid Expedia for hotel + flight.  A few days after checking out I noticed a charge for $628.64 from Holiday Inn Express.  I was double charged for the room.  I called Expedia and they said it was not their fault and to call the hotwl.  Called Holiday Inn Express Union Square and spoke with Marcelo who told me that someone made a mistake and it should have been charged to third party \"Expedia\", but only th manager Raj can fix it.  He said he would email Raj to fix it and call me back.  Raj never has called so I filled a dispute with my credit card company.  Neither Expedia nor Holiday Inn Express seem to want to fix the problem...stressful to the customer and poor customer service!!");// + System.lineSeparator() +
		res = res.replace("\r" + System.lineSeparator(), System.lineSeparator());
		String expected = sb.toString();
		assertEquals(expected.trim(), res.trim());
	}

	@Test
	public void testFindByWordQuery() {
		HotelReviewService service  = new HotelReviewService();
		String[] args = {"-hotels", "dataset" + File.separator + "hotels" + File.separator + "hotels.json",
				"-reviews", "dataset" + File.separator + "reviews",
				"-threads", "3"};

		service.loadData(args);
		String res = service.processQuery("findWord dog");
		assertNotNull(res);
		// System.out.println(res);
		StringBuilder sb = new StringBuilder();
		sb.append("--------------------" + System.lineSeparator());
		sb.append("4" + System.lineSeparator() +
				"Review by Anonymous on 2016-06-02" + System.lineSeparator() +
				"Rating: 3" + System.lineSeparator() +
				"ReviewId: 574fa3a6021cc109df10d39c" + System.lineSeparator() +
				"Great concept poor execution. " + System.lineSeparator() +
				"My normal go to when traveling to San Francisco is the Mark Hopkins. This time I wanted to try something new a bit more hip then old school. The staff is amazing, kind, knowledgable and overall good people. The room not so great, office chair super dirty as pictured below the vents were super dusty. The only reason I looked was because my allergies were so agitated it had to be something in the room.  I travel with my hypoallergenic dog when we asked for a dog bed it came covered in dog hair. Mind you my dog doesn't shed and she refused to lay on that bed. Lastly No room service only breakfastI guess thats where it counts but was disappointing. " + System.lineSeparator() +
				"--------------------" + System.lineSeparator() +
				"2" + System.lineSeparator() +
				"Review by Shannon on 2016-01-21" + System.lineSeparator() +
				"Rating: 5" + System.lineSeparator() +
				"ReviewId: 56a1184aeb9755099412f0b7" + System.lineSeparator() +
				"Wonderful experience!" + System.lineSeparator() +
				"Great stay!  From our initial experience of arrival and dropping off our car to the valet (a very friendly gentlemen welcomed us and took care of the car quickly), to a pleasant gentlemen at the desk who answered all of our questions & checked us in quickly (we did have reservations for our 3 day stay), to other hotel staff who were all smiles and acknowledged us as we arrived and got our luggage to the elevator.  Beautiful room, great views of the waterfront and arriving and departing flight at SFO." + System.lineSeparator() +
				"Surprisingly quiet, giving the proximity to the airport.  Clean, spacious, and well-appointed comfy room.   Our experiences at Hangar Steak for both dinner and breakfast were wonderful.  Expertly prepared steaks (tried the filet.  Best I've EVER eaten). My wife had the free-range chicken with root veggies.  She thought it was AMAZING.  Local produce was used, and it's SO fresh!!   The breakfast buffet was also beyond awesome.   Also, we appreciated having a dog relief station right out back for our service dog, complete with baggies.  All staff members ROCK!  Awesome folks, truly!  Marriot just GETS the concept of hospitality. We'll be staying here again very soon!" + System.lineSeparator() +
				"--------------------" + System.lineSeparator() +
				"2" + System.lineSeparator() +
				"Review by Anonymous on 2015-10-19" + System.lineSeparator() +
				"Rating: 1" + System.lineSeparator() +
				"ReviewId: 562548ec47aaef094a82737d" + System.lineSeparator() +
				"Don't book this hotel" + System.lineSeparator() +
				"There were some dog wastes in front of our room door, and we did not know where were they come from. We asked front desk to help for cleaning up around 10 pm, but no one came to clean up until morning. The most awful thing is that they CHARGED us $75 for the dog wastes when we checked out. TERRIBLE!" + System.lineSeparator() +
				"--------------------" + System.lineSeparator() +
				"1" + System.lineSeparator() +
				"Review by kirsten on 2016-08-08" + System.lineSeparator() +
				"Rating: 5" + System.lineSeparator() +
				"ReviewId: 57a8c6e9e40c4a0b28fde3fa" + System.lineSeparator() +
				"Nice room, nice beds, nice staff" + System.lineSeparator() +
				"Very nice front desk staff.  Gave me upgrade room after I was placed in a room next to a dog whining.  " + System.lineSeparator() +
				"--------------------" + System.lineSeparator() +
				"1" + System.lineSeparator() +
				"Review by Susan on 2016-07-23" + System.lineSeparator() +
				"Rating: 4" + System.lineSeparator() +
				"ReviewId: 5793d5a53a803a0b288f2525" + System.lineSeparator() +
				"Great for me and my dog!" + System.lineSeparator() +
				"Lovely, pet friendly hotel. The only complaint is the parking structure is the only place to park in the area, and they charge $25 for an overnight stay (I arrived at 11pm and left by 10am). I didn't ask what 2 nights would have cost..." + System.lineSeparator() +
				"Also, the tub/shower had nicks that weren't repaired, but being on the dog floor, I didn't mind." + System.lineSeparator() +
				"Everyone was friendly and helpful." + System.lineSeparator() +
				"--------------------" + System.lineSeparator() +
				"1" + System.lineSeparator() +
				"Review by Traveler on 2016-07-14" + System.lineSeparator() +
				"Rating: 3" + System.lineSeparator() +
				"ReviewId: 5786d9852c54c70b28dbd121" + System.lineSeparator() +
				"La Quinta Inn near SFO" + System.lineSeparator() +
				"Mid-level hotel in significant need of upgrading.  Staff was friendly and helpful, and the free shuttle to/from SFO was very useful.  However, room was tiny and cramped and the walls were paper thin.  Had a barking dog in the next room and thank god it went to sleep late at night.  Not the worst hotel experience, but not the best." + System.lineSeparator() +
				"--------------------" + System.lineSeparator() +
				"1" + System.lineSeparator() +
				"Review by dance mom on 2016-04-22" + System.lineSeparator() +
				"Rating: 2" + System.lineSeparator() +
				"ReviewId: 571980dbaf750809dae2c418" + System.lineSeparator() +
				"Up for a dance competition " + System.lineSeparator() +
				"Hmnnn well the first room we didn't even get to the door as there was a loud barking dog next door , nope! Then the 2nd room only had one bed when we booked 2 queens for 4 of us. The third room was fine but really disappointed there wasn't even a cofee maker. The guy working was very nice. Trash outside when we first walked in was overflowing. Overall it was OK.  Would pay a bit more for something a bit nicer with full breakfast and a coffee pot! " + System.lineSeparator() +
				"--------------------" + System.lineSeparator() +
				"1" + System.lineSeparator() +
				"Review by Anonymous on 2016-02-01" + System.lineSeparator() +
				"Rating: 5" + System.lineSeparator() +
				"ReviewId: 56afe64303bdc90980cd1c23" + System.lineSeparator() +
				"Great for family and including the pets" + System.lineSeparator() +
				"Stay here yearly for the Cow Palace dog show and is very comfortable and convenient. Close to nearby shopping and BJ's , Red Lobster and many more places to eat. Breakfast is good and for the price a great deal." + System.lineSeparator() +
				"--------------------" + System.lineSeparator() +
				"1" + System.lineSeparator() +
				"Review by RAMIL on 2013-08-06" + System.lineSeparator() +
				"Rating: 4" + System.lineSeparator() +
				"ReviewId: 55827028" + System.lineSeparator() +
				"Nice Hotel close to highway." + System.lineSeparator() +
				"Overall the hotel was very satisfactory. It was close to the airport (10 minutes away) and to At&t Park (20 minutes). It was a dog friendly hotel so we brought our mutt. It has a nice walking trail by the water." +
				System.lineSeparator());
		res = res.replace("\r\n", System.lineSeparator());
		String expected = sb.toString();
		assertEquals(expected.trim(), res.trim());
	}

	/**
	 * Helper method, used to run the program and compare intructor's output with student's output
	 * @param args
	 * @param actual
	 * @param expected
	 * @param testName
	 */
	private void runTestAndCompare(String[] args, Path actual, Path expected, String testName) {
		try {
			Files.deleteIfExists(actual);
		}
		catch (IOException e) {
			System.out.println("Could not delete old output file: " + e);
		}
		HotelReviewService service  = new HotelReviewService();
		service.loadData(args); // run loadData in HotelReviewService with these arguments

		//  compare output files
		int count = 0;
		try {
			count = TestUtils.checkFiles(expected, actual); // compares two files line by line
		} catch (IOException e) {
			fail(String.format("%n" + "Test Case: %s%n" + " File check failed: %s%n", testName, e.getMessage()));
		}

		if (count <= 0) {
			fail(String.format("%n" + "Test Case: %s%n" + " Mismatched Line: %d%n", testName, -count));
		}
	}

}