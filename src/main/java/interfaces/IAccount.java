package interfaces;

public interface IAccount {
    public void withdraw(double transfer);
    public void deposit(double transfer);
    public int getCount();
    public void printTotalAccounts();
}
