package db.DAO;

import db.models.Customer;

public interface ICustomerDAO extends IBaseDAO<Customer> {
    Customer getEntityByFirstName(String first_name);
    Customer getEntityByLastName(String last_name);
    Customer getEntityByPhone(String db_phone);
}
