package db.dao;

import db.models.DrivingCompany;

public interface IDrivingCompanyDAO extends IBaseDAO<DrivingCompany> {
    DrivingCompany getDrivingCompanyByPrice(double db_price);
}
