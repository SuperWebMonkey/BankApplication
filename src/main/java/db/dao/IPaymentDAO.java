package db.dao;

import db.models.Payment;

public interface IPaymentDAO extends IBaseDAO<Payment> {
    Payment getPaymentByAmount(double amount);
}
