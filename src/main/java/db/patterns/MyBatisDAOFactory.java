package db.patterns;

import db.dao.IBaseDAO;
import db.dao.mysql.*;
import db.mybatis.AirlineCompanyDAO;

public class MyBatisDAOFactory implements AbstractFactory {
    public IBaseDAO createDAO(String model) {
        switch (model) {
            case "AirlineCompany":
                return new AirlineCompanyDAO();
            case "City":
                return new CityDAO();
            case "Country":
                return new CountryDAO();
            case "Customer":
                return new CustomerDAO();
            case "DrivingCompany":
                return new DrivingCompanyDAO();
            case "Flight":
                return new FlightDAO();
            case "Hotel":
                return new HotelDAO();
            case "Order":
                return new OrderDAO();
            default:
                throw new UnknownDatabaseTypeException("Unknown model provided.");
        }
    }
}
