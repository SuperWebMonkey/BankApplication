package creditcard;

import account.Account;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DebitCard extends Card {
    private static double balance;
    private final static Logger LOGGER = LogManager.getLogger(DebitCard.class);

    public DebitCard(String cardNumber, String expirationDate, String name, double debt, double balance, String type) {
        super(cardNumber, expirationDate, name, type, debt);
        setBalance(balance);
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void use(double amount) {
        this.balance -= amount;

        if (this.balance >= 0) {
            LOGGER.info("Balance is " + this.balance);
            LOGGER.info("Debit card debt is " + 0);
        } else {
            LOGGER.info("Debit card debt is " + this.balance);
        }
    }

    public double getDebt() {
        int debt = 0;
        if (balance >= debt) {
        } else {
            double leftover = balance - debt;
            debt += (-leftover);
        }
        return debt;
    }

    public void increaseBalance(double amount) {
        if (amount < 0) {
            LOGGER.info("You entered a negative value. Please choose again.");
        } else {
            balance += amount;
        }
    }

    public void withdrawFromAccount(double amount, Account account) {
        account.withdraw(amount);
    }

}
