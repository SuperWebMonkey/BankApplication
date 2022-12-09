package account;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public final class Transaction {
    private List<Account> accountsList = new ArrayList<Account>();
    private final static Logger LOGGER = LogManager.getLogger(Transaction.class);

    public Transaction(List<Account> accountsList){
        this.accountsList = accountsList;
    }

    public void transact(Account a, Account b, double cash){
        a.withdraw(cash);
        LOGGER.info("\nYour balance is the first account is " + a.getBalance());
        b.deposit(cash);
        LOGGER.info("Your balance for the second account is " + b.getBalance() + "\n");
    }

    public void printAccounts(){
        if(accountsList.size() == 0){
            LOGGER.info("You have no accounts");
        } else {
            for (int i = 0; i < accountsList.size(); i++){
                LOGGER.info("Account " + i);
            }
        }
    }
}
