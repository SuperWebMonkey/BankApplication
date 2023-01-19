package db.DAO;

import db.models.Order;

public interface IOrderDAO extends IBaseDAO<Order> {
    Order getEntityByPayment(int db_payment);
}
