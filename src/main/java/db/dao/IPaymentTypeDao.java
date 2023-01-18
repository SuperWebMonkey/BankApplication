package db.dao;

import db.models.PaymentTypes;

import java.util.List;

public interface IPaymentTypeDao extends IBaseDao<PaymentTypes>{
    List<PaymentTypes> getAllPaymentTypes();
    PaymentTypes getPaymentTypesById(int id);
}
