package hotelapp.Controller;

import hotelapp.Model.Hotel;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class HotelController {
    private Map<String, Hotel> hotelMap = new TreeMap<>();


    public HotelController(List<Hotel> hotels) {
        for (Hotel hotel : hotels) {
            hotelMap.put(hotel.getHotelId(), hotel);
        }
    }

    public String findHotel(String hotelId){
        if(!hotelMap.containsKey(hotelId)) {
            throw new IllegalArgumentException();
        }
        return hotelMap.get(hotelId).toString();
    }

    public Collection<Hotel> getHotels() {
        return hotelMap.values();
    }
}
