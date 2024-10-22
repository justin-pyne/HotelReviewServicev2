package hotelapp.Controller;

import hotelapp.Model.Hotel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HotelController {
    /**
     * This class serves as a helper to store the map of all hotelIds to the appropriate
     * hotel objects, and providing hotel lookup by hotelId
     */

    private Map<String, Hotel> hotelMap = new HashMap<>();

    /**
     * Constructor for HotelManager, which takes a list of hotels and converts it to a map
     * Key: hotelId
     * Value: hotel object
     * @param hotels, list of all hotels
     */
    public HotelController(List<Hotel> hotels){
        for (Hotel hotel : hotels){
            hotelMap.put(hotel.getHotelId(), hotel);
        }
    }

    /**
     * Looks up hotel by the hotelId and displays information
     * @param hotelId, the hotelId we want to search
     * @return String, a string representation of the hotel's information
     */
    public String findHotel(String hotelId){
        if (!hotelMap.containsKey(hotelId)){
            throw new IllegalArgumentException();
        }
        return(hotelMap.get(hotelId).toString());
    }
}
