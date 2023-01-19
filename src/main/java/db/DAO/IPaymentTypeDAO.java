package db.DAO;

import db.models.PaymentType;

public interface IPaymentTypeDAO extends IBaseDAO<PaymentType> {
    PaymentType getEntityByName(String db_name);
}
