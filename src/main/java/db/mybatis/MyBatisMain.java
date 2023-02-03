package db.mybatis;

import db.models.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class MyBatisMain {
    private static final Logger LOGGER = LogManager.getLogger(MyBatisMain.class);

    public static void main(String[] args) {
        AirlineCompanyDAO airlineCompanyDAO = new AirlineCompanyDAO();
        CityDAO cityDAO = new CityDAO();
        CountryDAO countryDAO = new CountryDAO();
        CustomerDAO customerDAO = new CustomerDAO();
        DrivingCompanyDAO dcDAO = new DrivingCompanyDAO();

        // Insert
//         AirlineCompany airlineCompany1 = new AirlineCompany(2, "Beta");
//         airlineCompanyDAO.createEntity(airlineCompany1);
//         City city1 = new City(4,"Pomona", 1);
//         cityDAO.createEntity(city1);
//         Country country1 = new Country(9, "Brazil");
//         countryDAO.createEntity(country1);
//         Customer customer1 = new Customer(4, "Bob", "Billard", "789-914-0880");
//         customerDAO.createEntity(customer1);

        // Remove
//         airlineCompanyDAO.removeEntity(2);
//         cityDAO.removeEntity(4);
//         countryDAO.removeEntity(9);

        // Update
        AirlineCompany updateAC = new AirlineCompany(1, "Alpha");
        airlineCompanyDAO.updateEntity(updateAC);
        City updateCity = new City(2, "Monterey Park", 1);
        cityDAO.updateEntity(updateCity);
        Country updateCountry = new Country(3, "Chile");
        countryDAO.updateEntity(updateCountry);
        Customer updateCustomer = new Customer(1, "Max", "Madd", "678-458-1101");
        customerDAO.updateEntity(updateCustomer);

        // Get
        AirlineCompany airlineCompany2 = airlineCompanyDAO.getEntityById(1);
        City city2 = cityDAO.getEntityById(2);
        Country country2 = countryDAO.getEntityById(4);
        Customer customer2 = customerDAO.getEntityById(1);
        LOGGER.info(airlineCompany2);
        LOGGER.info(city2);
        LOGGER.info(country2);
        LOGGER.info(customer2);

        // Get All
        List<AirlineCompany> airlineCompanies = airlineCompanyDAO.getAllEntities();
        LOGGER.info(airlineCompanies);
        List<City> cities = cityDAO.getAllEntities();
        LOGGER.info(cities);
        List<Country> countries = countryDAO.getAllEntities();
        LOGGER.info(countries);
        List<Customer> customers = customerDAO.getAllEntities();
        LOGGER.info(customers);

        // Get By
        AirlineCompany airlineCompany3 = airlineCompanyDAO.getAirlineCompanyByName("Alpha");
        LOGGER.info(airlineCompany3);
        City city3 = cityDAO.getCityByName("San Bernardino");
        LOGGER.info(city3);
        Country country3 = countryDAO.getCountryByName("Canada");
        LOGGER.info(country3);
        Customer customer = customerDAO.getCustomerByPhone("801-222-4004");
    }
}
