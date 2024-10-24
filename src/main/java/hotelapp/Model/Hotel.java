package hotelapp.Model;

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

    public String getHotelId() {
        return hotelId;
    }

    @Override
    public String toString() {
        return  name + ": " + hotelId + System.lineSeparator() +
                address + System.lineSeparator() +
                city + ", " + state;
    }

    @Override
    public int compareTo(Hotel o) {
        return this.hotelId.compareTo(o.hotelId);
    }
}
