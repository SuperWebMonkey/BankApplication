package db.DAO;

import db.models.AirlineCompany;

public interface IBaseAirlineCompanyDAO extends IBaseDAO<AirlineCompany> {
    AirlineCompany getEntityByName(String name);
}
