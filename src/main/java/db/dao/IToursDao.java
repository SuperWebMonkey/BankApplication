package db.dao;

import db.models.Tours;

import java.util.List;

public interface IToursDao extends IBaseDao<Tours> {
    List<Tours> getAllTours();
    Tours getToursById(int id);
}
