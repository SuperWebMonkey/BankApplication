package db.models;

public class Hotels {
    private int hotelId;
    private String hotelName;
    private double price;
    private int cityId;

    public Hotels(int hotelId, String hotelName, double price, int cityId) {
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.price = price;
        this.cityId = cityId;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }
}
