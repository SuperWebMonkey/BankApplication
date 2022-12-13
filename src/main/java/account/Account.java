package account;
import Interfaces.IAccount;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Account implements IAccount {
    private double balance = 0;
    private String accountNumber;
    private static int count = 0;
    private String accountType;
    private final static Logger LOGGER = LogManager.getLogger(Account.class);


    public Account(double balance, String accountNumber){
        setBalance(balance);
        setAccountNumber(accountNumber);
        count++;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setAccountNumber(String accountNumber){
        this.accountNumber = accountNumber;
    }

    public void setAccountType(String accountType){
        this.accountType = accountType;
    }
    public double getBalance() {
        return this.balance;
    }
    public String getAccountNumber(){
        return accountNumber;
    }

    public String getAccountType(){
        return accountType;
    }

    public void withdraw(double transfer) {
        if (transfer < balance) {
            balance = balance - transfer;
        } else {
            LOGGER.info("The transfer is greater than the balance.");
        }
    }

    public void deposit(double transfer) {
        if (transfer < 0) {
            LOGGER.info("invalid amount");
        } else {
            balance = balance + transfer;
        }
    }

    public int getCount(){
        return count;
    }

    public void printTotalAccounts(){
        LOGGER.info("You have " + count + " accounts.");
    }

    public boolean equals(Object o){
        if (o == this)
            return true;
        if (!(o instanceof Account))
            return false;
        Account other = (Account)o;
        boolean accountNumberEquals = (this.accountNumber == null && other.accountNumber == null)
                || (this.accountNumber != null && this.accountNumber.equals(other.accountNumber));
        return this.balance == other.balance;
    }

    public int hashCode() {
        int result = 17;
        if (accountNumber != null) {
            result = 31 * result + accountNumber.hashCode();
        }

        return result;
    }

    public String toString() {
        return "Your balance is " + this.balance +
                "\nyour account number is " + this.accountNumber;
    }
}
