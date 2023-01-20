package db.dao;

import db.models.Order;

public interface IOrderDAO extends IBaseDAO<Order> {
    Order getOrderByPayment(int db_payment);
}
