package db.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import db.DBMain;
import db.models.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JacksonMain {
    private static final Logger LOGGER = LogManager.getLogger(DBMain.class);

    public static void main(String[] args){
        List<AirlineCompany> airlineCompanies = new ArrayList<>();
        List<City> cities = new ArrayList<>();
        List<Country> countries = new ArrayList<>();
        List<Customer> customers = new ArrayList<>();
        List<Hotel> hotels = new ArrayList<>();

        AirlineCompany ac1 = new AirlineCompany(1, "Delta");
        airlineCompanies.add(ac1);
        AirlineCompany ac2 = new AirlineCompany(2, "Alpha");
        airlineCompanies.add(ac2);
        AirlineCompany ac3 = new AirlineCompany(3, "Beta");
        airlineCompanies.add(ac2);
        airlineCompanies.add(ac3);

        City city1 = new City(1, "San Bernardino", 1);
        cities.add(city1);
        City city2 = new City(2, "San Francisco", 1);
        cities.add(city2);
        City city3 = new City(3, "Bakersfield", 1);
        cities.add(city3);

        Country country1 = new Country(1, "United Kingdom");
        countries.add(country1);
        Country country2 = new Country(2, "United States");
        countries.add(country2);
        Country country3 = new Country(3, "Germany");

        Customer customer1 = new Customer(1,"Larry","Page","420-829-1011");
        customers.add(customer1);
        Customer customer2 = new Customer(2, "Bob", "Page", "808-254-5289");
        customers.add(customer2);
        Customer customer3 = new Customer(3, "Will", "Adams", "545-802-9999");
        customers.add(customer3);

        Hotel hotel1 = new Hotel(1, "Holiday Inn", 100, 1);
        hotels.add(hotel1);
        Hotel hotel2 = new Hotel(2, "Flamenco", 50, 1);
        hotels.add(hotel2);
        Hotel hotel3 = new Hotel(3, "Hilton Hotel", 70, 1);
        hotels.add(hotel3);

        TravelAgency travelAgency = new TravelAgency(airlineCompanies, cities, countries, customers, hotels);

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            objectMapper.writeValue(new File(System.getProperty("user.dir") +"/src/main/resources/db-parse.json"), travelAgency);
        } catch (IOException e) {
            LOGGER.error(e);
        }

        TravelAgency ta2 = null;

        try {
            ta2 = objectMapper.readValue(new File(System.getProperty("user.dir") +"/src/main/resources/db-parse.json"), TravelAgency.class);
        } catch (IOException e) {
            LOGGER.error(e);
        }

        LOGGER.info(ta2);
    }
}
