package account;
import java.util.ArrayList;

public final class Transaction {
    private ArrayList<Account> accountsList = new ArrayList<Account>();

    public Transaction(ArrayList<Account> accountsList){
        this.accountsList = accountsList;
    }

    public void transact(Account a, Account b, double cash){
        a.withdraw(cash);
        System.out.println("\nYour balance is the first account is " + a.getBalance());
        b.deposit(cash);
        System.out.println("Your balance for the second account is " + b.getBalance() + "\n");
    }

    public void printAccounts(){
        if(accountsList.size() == 0){
            System.out.println("You have no accounts");
        } else {
            for (int i = 0; i < accountsList.size(); i++){
                System.out.println("Account " + i);
            }
        }
    }
}
