package creditcard;
import account.Account;

public class DebitCard extends Card{
    private static double balance;

    public DebitCard(String cardNumber, String expirationDate, String name, double debt, double balance, String type){
        super(cardNumber, expirationDate, name, type, debt);
        setBalance(balance);
    }

    public void setBalance(double balance){
        this.balance = balance;
    }

    public double getBalance(){
        return balance;
    }

    public void use(double amount){
        this.balance -= amount;

        if (this.balance >= 0) {
            System.out.println("Balance is " + this.balance);
            System.out.println("Debit card debt is " + 0);
        } else {
            System.out.println("Debit card debt is " + this.balance);
        }
    }

    public double getDebt(){
        int debt = 0;
        if (balance >= debt){
        } else {
            double leftover = balance - debt;
            debt += (-leftover);
        }
        return debt;
    }

    public void increaseBalance(double amount){
        if (amount < 0){
            System.out.println("You entered a negative value. Please choose again.");
        } else {
            balance += amount;
        }
    }

    public void withdrawFromAccount(double amount, Account account){
        account.withdraw(amount);
    }

}
