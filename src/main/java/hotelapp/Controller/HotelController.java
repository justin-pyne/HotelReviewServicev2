package hotelapp.Controller;

import hotelapp.Model.Hotel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HotelController {
    private Map<String, Hotel> hotelMap = new HashMap<>();


    public HotelController(List<Hotel> hotels) {
        initialize(hotels);
    }

    protected void initialize(List<Hotel> hotels) {
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
}
