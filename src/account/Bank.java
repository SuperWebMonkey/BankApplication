package account;
import java.util.*;

public class Bank {
    private ArrayList<Account> accountList = new ArrayList<Account>();
    private final int MAX_ACCOUNTS = 7;

    public Bank(ArrayList<Account> accountList){
        this.accountList = accountList;
    }

    public void openAccount(){
        Scanner input = new Scanner(System.in);

        System.out.println("Opening an account");
        System.out.println("Please enter your balance:");
        double balance = input.nextDouble();

        System.out.println("Please enter your account number:");
        String accountNumber = input.nextLine();

        Account account = new Account(balance, accountNumber);
        accountList.add(account);
    }

    public void closeAccount(int index){
        System.out.println("You are closing account " + index);
        accountList.remove(index);
    }

    public boolean isAccountLimit(){
        if(accountList.size() <= MAX_ACCOUNTS){
            return false;
        }
        return true;
    }
}
