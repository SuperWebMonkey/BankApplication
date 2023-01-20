package db.dao;

import db.models.Flight;

public interface IFlightDAO extends IBaseDAO<Flight> {
    Flight getFlightPrice(double db_price);
}
