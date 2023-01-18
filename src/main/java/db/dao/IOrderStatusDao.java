package db.dao;


import db.models.OrderStatus;

import java.util.List;

public interface IOrderStatusDao extends IBaseDao<OrderStatus>{
    List<OrderStatus> getAllOrderStatus();
    OrderStatus getOrderStatusById(int id);
}
