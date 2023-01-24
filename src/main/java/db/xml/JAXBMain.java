package db.xml;

import db.models.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class JAXBMain {
    private static final Logger LOGGER = LogManager.getLogger(JAXBMain.class);

    public static void main(String[] args) {
        List<AirlineCompany> airlineCompanies = new ArrayList<>();
        AirlineCompany ac1 = new AirlineCompany(1, "Delta");
        airlineCompanies.add(ac1);
        AirlineCompany ac2 = new AirlineCompany(2, "Alpha");
        airlineCompanies.add(ac2);

        List<City> cities = new ArrayList<>();
        City city1 = new City(1, "Los Angeles", 1);
        cities.add(city1);
        City city2 = new City(2, "San Diego", 1);
        cities.add(city2);

        List<Country> countries = new ArrayList<>();
        Country country1 = new Country(1, "United States");
        countries.add(country1);
        Country country2 = new Country(2, "Brazil");
        countries.add(country2);

        List<Customer> customers = new ArrayList<>();
        Customer customer1 = new Customer(1,"Larry","Page","420-829-1011");
        customers.add(customer1);
        Customer customer2 = new Customer(2, "Bob", "Page", "808-254-5289");
        customers.add(customer2);

        List<Hotel> hotels = new ArrayList<>();
        Hotel hotel1 = new Hotel(1, "Caesar Palace", 100, 1);
        hotels.add(hotel1);
        Hotel hotel2 = new Hotel(2, "Circus Circus", 50, 1);
        hotels.add(hotel2);

        TravelAgency travelAgency = new TravelAgency(airlineCompanies, cities, countries, customers, hotels);

        try {
            JAXBContext context = JAXBContext.newInstance(TravelAgency.class, AirlineCompany.class, City.class,
                    Country.class, Customer.class, Hotel.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(travelAgency, new File(System.getProperty("user.dir") +
                    "/src/main/resources/travel_agency_output.xml"));
            LOGGER.info("Done");
        } catch(JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    public static TravelAgency unmarshall() {
        TravelAgency travelAgency = new TravelAgency();
        try {
            JAXBContext context = JAXBContext.newInstance(TravelAgency.class, AirlineCompany.class, City.class,
                    Country.class, Customer.class, Hotel.class);
            travelAgency = (TravelAgency) context.createUnmarshaller()
                    .unmarshal(new FileReader(System.getProperty("user.dir") +
                            "/src/main/resources/travel_agency_output.xml"));
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return travelAgency;
    }
}


