package db.dao;

import db.models.City;

public interface ICityDAO extends IBaseDAO<City> {
    City getCityByName(String name);
}
