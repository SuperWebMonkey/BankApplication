package person;
import account.*;
import java.util.*;

public class Customer extends Person {
    private ArrayList<Account> account;

    public Customer(String name, String emailAddress, ArrayList<Account> account) {
        super(name, emailAddress);
        setAccountNumber(account);
    }

    public void setAccountNumber(ArrayList<Account> accountNumber){
        this.account = account;
    }

    public List<Account> getAccount() {
        return this.account;
    }

}
