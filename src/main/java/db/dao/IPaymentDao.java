package db.dao;

import db.models.Payments;

import java.util.List;

public interface IPaymentDao extends IBaseDao<Payments>{
    List<Payments> getAllPayments();
    Payments getPaymentsById(int id);
}
