package db.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "hotel")
@XmlType(propOrder = {"hotelId", "hotelName", "price", "cityId"})
public class Hotel {
    @JsonProperty("id")
    private int hotelId;
    @JsonProperty("name")
    private String hotelName;
    @JsonProperty("price")
    private double price;
    @JsonProperty("cityId")
    private int cityId;

    public Hotel() {
    }

    public Hotel(int hotelId, String hotelName, double price, int cityId) {
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.price = price;
        this.cityId = cityId;
    }

    public int getHotelId() {
        return hotelId;
    }

    @XmlElement(name = "hotelId")
    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    @XmlElement(name = "hotelName")
    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public double getPrice() {
        return price;
    }

    @XmlElement(name = "price")
    public void setPrice(double price) {
        this.price = price;
    }

    public int getCityId() {
        return cityId;
    }

    @XmlElement(name = "cityId")
    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String toString() {
        return this.hotelId + " " + this.hotelName + " " + this.price + " " + this.cityId;
    }
}
