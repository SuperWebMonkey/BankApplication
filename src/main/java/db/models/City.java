package db.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "city")
@XmlType(propOrder = {"cityId", "cityName", "countryId"})
public class City {
    @JsonProperty("id")
    private int cityId;
    @JsonProperty("name")
    private String cityName;
    @JsonProperty("countryId")
    private int countryId;

    public City() {
    }

    public City(int cityId, String cityName, int countryId) {
        this.cityId = cityId;
        this.cityName = cityName;
        this.countryId = countryId;
    }

    public int getCityId() {
        return cityId;
    }

    @XmlElement(name = "cityId")
    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    @XmlElement(name = "cityName")
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getCountryId() {
        return countryId;
    }

    @XmlElement(name = "countryId")
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public String toString() {
        return this.cityId + " " + this.cityName + " " + this.countryId;
    }
}
