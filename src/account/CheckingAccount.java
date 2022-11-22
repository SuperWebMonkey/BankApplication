package account;

public class CheckingAccount extends Account {

    public CheckingAccount(double balance, String accountNumber) {
        super(balance, accountNumber);
    }

    public String toString() {
        return "Checking Account";
    }
}
