package db.dao;

import db.models.Tour;

public interface ITourDAO extends IBaseDAO<Tour> {
    Tour getTourByName(String db_name);
}
