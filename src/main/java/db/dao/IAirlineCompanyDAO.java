package db.dao;

import db.models.AirlineCompany;

public interface IAirlineCompanyDAO extends IBaseDAO<AirlineCompany> {
    AirlineCompany getAirlineCompanyByName(String name);
}
