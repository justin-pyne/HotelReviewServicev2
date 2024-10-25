package hotelapp.Controller;

import hotelapp.Model.Hotel;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


/**
 * A class that stores a Map of Hotel objects, and provides methods to look up Hotels.
 */
public class HotelController {
    private Map<String, Hotel> hotelMap = new TreeMap<>();

    public HotelController(List<Hotel> hotels) {
        for (Hotel hotel : hotels) {
            hotelMap.put(hotel.getHotelId(), hotel);
        }
    }

    /**
     * A method to look up a Hotel object by ID
     * @param hotelId the hotelId to look up
     * @return the Hotel object with the provided hotelId
     */
    public String findHotel(String hotelId){
        if(!hotelMap.containsKey(hotelId)) {
            throw new IllegalArgumentException();
        }
        return hotelMap.get(hotelId).toString();
    }

    /**
     * A method to get all the Hotels stored in the map
     * @return A Collection of Hotels from the map
     */
    public Collection<Hotel> getHotels() {
        return hotelMap.values();
    }
}
