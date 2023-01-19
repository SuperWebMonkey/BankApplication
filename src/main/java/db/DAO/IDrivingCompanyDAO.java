package db.DAO;

import db.models.DrivingCompany;

public interface IDrivingCompanyDAO extends IBaseDAO<DrivingCompany> {
    DrivingCompany getEntityByPrice(double db_price);
}
