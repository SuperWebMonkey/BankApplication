package db.DAO;

import db.models.Flight;

public interface IFlightDAO extends IBaseDAO<Flight> {
    Flight getEntityPrice(double db_price);
}
