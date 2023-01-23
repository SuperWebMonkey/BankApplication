package db.dao;

import db.models.Hotel;

public interface IHotelDAO extends IBaseDAO<Hotel> {
    Hotel getHotelByName(String db_name);

    Hotel getHotelByPrice(double db_price);
}
