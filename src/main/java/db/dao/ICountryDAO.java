package db.dao;

import db.models.Country;

public interface ICountryDAO extends IBaseDAO<Country> {
    Country getCountryByName(String name);
}
