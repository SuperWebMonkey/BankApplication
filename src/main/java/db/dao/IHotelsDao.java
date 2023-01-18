package db.dao;

import db.models.Hotels;

import java.util.List;

public interface IHotelsDao extends IBaseDao<Hotels>{
    List<Hotels> getAllHotels();
    Hotels getHotelsById(int id);
}
