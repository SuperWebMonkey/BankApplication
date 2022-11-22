package account;
import person.*;

public class JointAccount extends Account {
    private String[] names;

    public JointAccount(double balance, String accountNumber) {
        super(balance, accountNumber);
    }

    public void SetNames(String[] names) {
        this.names = names;
    }

    public String[] getCustomers() {
        return this.names;
    }
}
