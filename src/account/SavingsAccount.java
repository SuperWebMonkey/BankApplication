package account;

public class SavingsAccount extends Account {
    public SavingsAccount(double balance, String accountNumber) {
        super(balance, accountNumber);
    }

    public String toString(){
        return "Savings Account";
    }
}
