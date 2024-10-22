package hotelapp.Controller;

import hotelapp.Model.Hotel;

import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ThreadSafeHotelController extends HotelController{
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    /**
     * Constructor for HotelManager, which takes a list of hotels and converts it to a map
     * Key: hotelId
     * Value: hotel object
     *
     * @param hotels
     */
    public ThreadSafeHotelController(List<Hotel> hotels) {
        super(hotels);
        lock = new ReentrantReadWriteLock();
    }

    @Override
    public String findHotel(String hotelId) {
        try {
            lock.readLock().lock();
            return super.findHotel(hotelId);
        } finally {
            lock.readLock().unlock();
        }
    }
}
