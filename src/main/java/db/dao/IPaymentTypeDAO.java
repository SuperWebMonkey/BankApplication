package db.dao;

import db.models.PaymentType;

public interface IPaymentTypeDAO extends IBaseDAO<PaymentType> {
    PaymentType getPaymentTypeByName(String db_name);
}
