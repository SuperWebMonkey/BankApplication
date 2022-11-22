package account;

public abstract class Account {
    private double balance = 0;
    private String accountNumber;

    public Account(double balance, String accountNumber){
        setBalance(balance);
        setAccountNumber(accountNumber);
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setAccountNumber(String accountNumber){
        this.accountNumber = accountNumber;
    }
    public double getBalance() {
        return this.balance;
    }
    public void withdraw(double transfer) {
        if (transfer < balance) {
            balance = balance - transfer;
        } else {
            System.out.println("The transfer is greater than the balance.");
        }
    }

    public void deposit(double transfer) {
        if (transfer < 0) {
            System.out.println("invalid amount");
        } else {
            balance = balance + transfer;
        }
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

    public final int hashCode() {
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
