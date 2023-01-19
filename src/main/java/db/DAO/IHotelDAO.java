package db.DAO;

import db.models.Hotel;

public interface IHotelDAO extends IBaseDAO<Hotel> {
    Hotel getEntityByName(String db_name);
    Hotel getEntityByPrice(double db_price);
}
