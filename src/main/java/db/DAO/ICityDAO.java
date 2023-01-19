package db.DAO;

import db.models.City;

public interface ICityDAO extends IBaseDAO<City> {
    City getEntityByName(String name);
}
