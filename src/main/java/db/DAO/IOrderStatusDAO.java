package db.dao;


import db.models.OrderStatus;

public interface IOrderStatusDAO extends IBaseDAO<OrderStatus> {
    OrderStatus getOrderStatusByName(String db_name);
}
