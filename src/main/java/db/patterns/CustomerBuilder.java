package db.patterns;

import db.models.Customer;

public class CustomerBuilder {
    private int customerId;
    private String firstName;
    private String lastName;
    private String phone;

    public CustomerBuilder customerId(int customerId) {
        this.customerId = customerId;
        return this;
    }

    public CustomerBuilder firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public CustomerBuilder lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public CustomerBuilder phone(String phone) {
        this.phone = phone;
        return this;
    }

    public Customer build() {
        return new Customer(customerId, firstName, lastName, phone);
    }
}
