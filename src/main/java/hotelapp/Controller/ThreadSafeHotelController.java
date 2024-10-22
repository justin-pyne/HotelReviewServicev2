package hotelapp.Controller;

import hotelapp.Model.Hotel;

import java.util.List;

public class ThreadSafeHotelController extends HotelController{
    /**
     * Constructor for HotelManager, which takes a list of hotels and converts it to a map
     * Key: hotelId
     * Value: hotel object
     *
     * @param hotels
     */
    public ThreadSafeHotelController(List<Hotel> hotels) {
        super(hotels);
    }
}
