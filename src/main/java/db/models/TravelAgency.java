package db.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "travel_agency")
@XmlType(propOrder = {"acList", "cityList", "countryList", "customerList", "hotelList"})
public class TravelAgency {
    @JsonProperty("AirlineCompanies")
    private List<AirlineCompany> acList = new ArrayList<>();
    @JsonProperty("cities")
    private List<City> cityList = new ArrayList<>();
    @JsonProperty("countries")
    private List<Country> countryList = new ArrayList<>();
    @JsonProperty("customers")
    private List<Customer> customerList = new ArrayList<>();
    @JsonProperty("hotels")
    private List<Hotel> hotelList = new ArrayList<>();

    public TravelAgency() {}

    public TravelAgency(List<AirlineCompany> acList, List<City> cityList, List<Country> countryList, List<Customer> customerList, List<Hotel> hotelList) {
        this.acList = acList;
        this.cityList = cityList;
        this.countryList = countryList;
        this.customerList = customerList;
        this.hotelList = hotelList;
    }

    public List<AirlineCompany> getAcList() {
        return acList;
    }

    @XmlElementWrapper(name = "airlineCompanies")
    @XmlElement(name = "airlineCompany", type = AirlineCompany.class)
    public void setAcList(List<AirlineCompany> acList) {
        this.acList = acList;
    }

    public List<City> getCityList() {
        return cityList;
    }

    @XmlElementWrapper(name = "cities")
    @XmlElement(name = "city", type = City.class)
    public void setCityList(List<City> cityList) {
        this.cityList = cityList;
    }

    public List<Country> getCountryList() {
        return countryList;
    }

    @XmlElementWrapper(name = "countries")
    @XmlElement(name = "country", type = Country.class)
    public void setCountryList(List<Country> countryList) {
        this.countryList = countryList;
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    @XmlElementWrapper(name = "customers")
    @XmlElement(name = "customer", type = Customer.class)
    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }

    public List<Hotel> getHotelList() {
        return hotelList;
    }

    @XmlElementWrapper(name = "hotels")
    @XmlElement(name = "hotel", type = Hotel.class)
    public void setHotelList(List<Hotel> hotelList) {
        this.hotelList = hotelList;
    }

    public String toString() {
        return "Travel Agency{" +
                "acList=" + acList +
                ", cityList=" + cityList +
                ", countryList=" + countryList +
                ", customerList=" + customerList +
                ", hotelList=" + hotelList +
                "}";
    }
}
