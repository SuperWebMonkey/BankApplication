package db;

import db.dao.mysql.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import db.models.*;

import java.util.List;

public class DBMain {

    private static final Logger LOGGER = LogManager.getLogger(DBMain.class);

    public static void main(String[] args) {
        CityDAO cityDao = new CityDAO();
        List<City> cityList = cityDao.getAllEntities();
        LOGGER.info(cityList);

        CountryDAO countryDao = new CountryDAO();
        List<Country> countryList = countryDao.getAllEntities();
        LOGGER.info(countryList);

        City city = new City(3, "San Francisco", 1);
        cityDao.createEntity(city);
        Country country = new Country(8, "France");
        countryDao.createEntity(country);

        City city2 = new City(3, "San Bernardino", 1);
        cityDao.updateEntity(city2);
        Country country2 = new Country(7, "Great Britain");
        countryDao.updateEntity(country2);

        cityDao.removeEntity(2);
        countryDao.removeEntity(5);

        City city3 = cityDao.getCityByName("Los Angeles");
        LOGGER.info(city3);
        Country country3 = countryDao.getCountryByName("United States");
        LOGGER.info(country3);
    }
}