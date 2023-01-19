package db.DAO;


import db.models.OrderStatus;

public interface IOrderStatusDAO extends IBaseDAO<OrderStatus> {
    OrderStatus getEntityByName(String db_name);
}
