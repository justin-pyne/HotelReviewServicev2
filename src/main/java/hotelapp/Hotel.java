package hotelapp;

import com.google.gson.annotations.SerializedName;

public class Hotel {
    /**
     * A class that represents a hotel
     */

    //Instance Variables
    @SerializedName(value = "f")
    private final String name;

    private final String hotelId;
    private final String lat;
    private final String lng;

    @SerializedName(value = "ad")
    private final String address;

    @SerializedName(value = "ci")
    private final String city;

    /**
     * Constructor for a hotel object
     * @param name, name of the hotel
     * @param hotelId, id of the hotel
     * @param latitude, latitude of the hotel
     * @param longitude, longitude of the hotel
     * @param address, street address of the hotel
     * @param city, city of the hotel
     */
    public Hotel(String name, String hotelId, String latitude, String longitude, String address, String city) {
        this.name = name;
        this.hotelId = hotelId;
        this.lat = latitude;
        this.lng = longitude;
        this.address = address;
        this.city = city;
    }

    /**
     * A method that retrieves the hotelId
     * @return hotelId, the hotelId of this hotel
     */
    public String getHotelId(){
        return this.hotelId;
    }

    /**
     * Override for toString
     * @return String, String representation of the hotel
     */
    @Override
    public String toString() {
        return "hotelName = " + name + System.lineSeparator() +
                "hotelId = " + hotelId + System.lineSeparator() +
                "latitude = " + lat + System.lineSeparator() +
                "longitude = " + lng + System.lineSeparator() +
                "address = " + address + ", " + city + System.lineSeparator();
    }
}
