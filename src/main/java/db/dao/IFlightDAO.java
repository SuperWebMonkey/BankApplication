package db.dao;

import db.models.Flight;

public interface IFlightDAO extends IBaseDAO<Flight> {
    Flight getFlightByPrice(double price);
}
