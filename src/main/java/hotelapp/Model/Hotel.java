package hotelapp.Model;


/**
 * A model class representing a Hotel
 */
public class Hotel implements Comparable<Hotel>{

    private final String name;
    private final String hotelId;
    private final String longitude;
    private final String latitude;
    private final String address;
    private final String city;
    private final String state;


    public Hotel(String name, String hotelId, String longitude, String latitude, String address, String city, String state) {
        this.name = name;
        this.hotelId = hotelId;
        this.longitude = longitude;
        this.latitude = latitude;
        this.address = address;
        this.city = city;
        this.state = state;
    }

    /**
     * Getter for hotelId
     * @return hotelId String
     */
    public String getHotelId() {
        return hotelId;
    }

    /**
     * Comparable compareTo implementation
     * @param o the object to be compared.
     * @return an int for the comparison
     */
    @Override
    public int compareTo(Hotel o) {
        return this.hotelId.compareTo(o.hotelId);
    }

    /**
     * A toString override
     * @return A String representation of this Hotel
     */
    @Override
    public String toString() {
        return  name + ": " + hotelId + System.lineSeparator() +
                address + System.lineSeparator() +
                city + ", " + state;
    }

}
