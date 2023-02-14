package db.patterns;

import db.dao.IBaseDAO;
import db.dao.mysql.*;

public class JDBCDAOFactory implements AbstractFactory {
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
            case "OrderStatus":
                return new OrderStatusDAO();
            case "Payment":
                return new PaymentDAO();
            case "PaymentType":
                return new PaymentTypeDAO();
            case "Staff":
                return new StaffDAO();
            case "Tour":
                return new TourDAO();
            default:
                throw new UnknownDatabaseTypeException("Unknown model provided.");
        }
    }
}
