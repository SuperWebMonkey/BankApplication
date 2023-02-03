package db.dao;

import db.models.Hotel;

public interface IHotelDAO extends IBaseDAO<Hotel> {
    Hotel getHotelByName(String name);

    Hotel getHotelByPrice(double price);
}
