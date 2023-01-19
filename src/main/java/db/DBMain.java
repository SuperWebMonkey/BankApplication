package db;

import db.DAO.mysql.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import db.models.*;

import java.util.List;

public class DBMain {

    private static final Logger LOGGER = LogManager.getLogger(DBMain.class);

    public static void main(String[] args) {
        AirlineCompanyDAO acDao = new AirlineCompanyDAO();
        List<AirlineCompany> acList = acDao.getAllEntities();
        LOGGER.info(acList);

        CityDAO cityDao = new CityDAO();
        List<City> cityList = cityDao.getAllEntities();
        LOGGER.info(cityList);

        CountryDAO countryDao = new CountryDAO();
        List<Country> countryList = countryDao.getAllEntities();
        LOGGER.info(countryList);

        CustomerDAO customerDAO = new CustomerDAO();
        List<Customer> customerList = customerDAO.getAllEntities();
        LOGGER.info(customerList);

        DrivingCompanyDAO dcDAO = new DrivingCompanyDAO();
        List<DrivingCompany> dcList = dcDAO.getAllEntities();
        LOGGER.info(dcList);

        FlightDAO flightDAO = new FlightDAO();
        List<Flight> flightList = flightDAO.getAllEntities();
        LOGGER.info(flightList);

        HotelDAO hotelDAO = new HotelDAO();
        List<Hotel> hotelList = hotelDAO.getAllEntities();
        LOGGER.info(hotelList);

        OrderDAO orderDAO = new OrderDAO();
        List<Order> orderList = orderDAO.getAllEntities();
        LOGGER.info(orderList);

        OrderStatusDAO osDAO = new OrderStatusDAO();
        List<OrderStatus> osList = osDAO.getAllEntities();
        LOGGER.info(osList);

        PaymentDAO paymentDAO = new PaymentDAO();
        List<Payment> paymentList = paymentDAO.getAllEntities();
        LOGGER.info(paymentList);

        PaymentTypeDAO ptdao = new PaymentTypeDAO();
        List<PaymentType> ptList = ptdao.getAllEntities();
        LOGGER.info(ptList);

        StaffDAO staffDao = new StaffDAO();
        List<Staff> staffList = staffDao.getAllEntities();
        LOGGER.info(staffList);

        TourDAO tourDAO = new TourDAO();
        List<Tour> tourList = tourDAO.getAllEntities();
        LOGGER.info(tourList);
    }
}