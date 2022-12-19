package person;

import account.*;

import java.util.*;

public class Customer extends Person {
    private List<Account> account;

    public Customer(String name, String emailAddress, List<Account> account) {
        super(name, emailAddress);
        setAccountNumber(account);
    }

    public void setAccountNumber(List<Account> accountNumber) {
        this.account = account;
    }

    public List<Account> getAccount() {
        return this.account;
    }

}
