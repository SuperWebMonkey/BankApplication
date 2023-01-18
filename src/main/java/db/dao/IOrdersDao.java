package db.dao;

import db.models.Orders;

import java.util.List;

public interface IOrdersDao extends IBaseDao<Orders>{
    List<Orders> getAllOrders();
    Orders getOrdersById(int id);
}
