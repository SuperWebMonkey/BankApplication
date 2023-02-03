package db.dao;

import db.models.Customer;

public interface ICustomerDAO extends IBaseDAO<Customer> {
    Customer getCustomerByFirstName(String first_name);

    Customer getCustomerByLastName(String last_name);

    Customer getCustomerByPhone(String phone);
}
