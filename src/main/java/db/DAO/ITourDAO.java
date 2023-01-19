package db.DAO;

import db.models.Tour;

public interface ITourDAO extends IBaseDAO<Tour> {
    Tour getEntityByName(String db_name);
}
