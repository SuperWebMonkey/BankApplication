package db.models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "customer")
@XmlType(propOrder = {"customerId", "firstName", "lastName", "phone"})
public class Customer {
    private int customerId;
    private String firstName;
    private String lastName;
    private String phone;

    public Customer() {
    }

    public Customer(int customerId, String firstName, String lastName, String phone) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }

    public int getCustomerId() {
        return this.customerId;
    }

    @XmlElement(name = "customerId")
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return this.firstName;
    }

    @XmlElement(name = "firstName")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    @XmlElement(name = "lastName")
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return this.phone;
    }

    @XmlElement(name = "phone")
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String toString() {
        return this.customerId + " " + this.firstName + " " + this.lastName + " " + this.phone;
    }
}
