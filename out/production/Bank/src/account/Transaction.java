package account;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public final class Transaction {
    private ArrayList<Account> accountsList = new ArrayList<Account>();
    private static Logger logger = LogManager.getLogger(Transaction.class);

    public Transaction(ArrayList<Account> accountsList){
        this.accountsList = accountsList;
    }

    public void transact(Account a, Account b, double cash){
        a.withdraw(cash);
        logger.info("\nYour balance is the first account is " + a.getBalance());
        b.deposit(cash);
        logger.info("Your balance for the second account is " + b.getBalance() + "\n");
    }

    public void printAccounts(){
        if(accountsList.size() == 0){
            logger.info("You have no accounts");
        } else {
            for (int i = 0; i < accountsList.size(); i++){
                logger.info("Account " + i);
            }
        }
    }
}
