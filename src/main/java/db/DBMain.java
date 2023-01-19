package db;

import db.DAO.mysql.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import db.models.*;

import java.util.List;

public class DBMain {

    private static final Logger LOGGER = LogManager.getLogger(DBMain.class);

    public static void main(String[] args) {

//        CustomersDao customersDao = new CustomersDao();
//        List<Customers> customerList = customersDao.getAllEntities();
//        LOGGER.info(customerList);
//
//        Customers customer = customersDao.getEntityById(1);
//        LOGGER.info(customer);
//
//        Customers customer2 = new Customers(27, "Mary", "Keller", "223-334-4450");
//        customersDao.insertCustomer(customer2);
//
//        // Delete Statement
//        customersDao.removeCustomer(27);
//
//        // Update Statement - does not work
//        Customers customer3 = new Customers(1, "Max", "Peterson", "757-180-8001");
//        customersDao.updateEntity(customer3);
//
//        StaffDao staffDao = new StaffDao();
//        List<Staff> staffList = staffDao.getAllEntities();
//        LOGGER.info(staffList);
//
//        AirlineCompaniesDao acDao = new AirlineCompaniesDao();
//        List<AirlineCompanies> acList = acDao.getAllEntities();
//        LOGGER.info(acList);
//
//        CitiesDao cityDao = new CitiesDao();
//        List<Cities> cityList = cityDao.getAllEntities();
//        LOGGER.info(cityList);

        CountryDAO countryDao = new CountryDAO();
        List<Country> countryList = countryDao.getAllEntities();
        LOGGER.info(countryList);
    }
}