package account;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class Bank {
    private ArrayList<Account> accountList = new ArrayList<Account>();
    private final int MAX_ACCOUNTS = 7;
    private final static Logger LOGGER = LogManager.getLogger(Bank.class);

    public Bank(ArrayList<Account> accountList) {
        this.accountList = accountList;
    }

    public void openAccount() {
        Scanner input = new Scanner(System.in);

        LOGGER.info("Opening an account");
        LOGGER.info("Please enter your balance:");
        double balance = input.nextDouble();

        LOGGER.info("Please enter your account number:");
        String accountNumber = input.nextLine();

        Account account = new Account(balance, accountNumber);
        accountList.add(account);
    }

    public void closeAccount(int index) {
        LOGGER.info("You are closing account " + index);
        accountList.remove(index);
    }

    public boolean isAccountLimit() {
        if (accountList.size() <= MAX_ACCOUNTS) {
            return false;
        }
        return true;
    }
}
