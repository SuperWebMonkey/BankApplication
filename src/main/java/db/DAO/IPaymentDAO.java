package db.DAO;

import db.models.Payment;

public interface IPaymentDAO extends IBaseDAO<Payment> {
    Payment getEntityByAmount(double db_amount);
}
