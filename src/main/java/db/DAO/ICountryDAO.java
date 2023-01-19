package db.DAO;

import db.models.Country;

public interface ICountryDAO extends IBaseDAO<Country> {
    Country getEntityByName(String name);
}
