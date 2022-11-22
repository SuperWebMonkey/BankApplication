import creditcard.*;
import person.*;
import account.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        // Transferring between accounts
        ArrayList<Account> accountList = new ArrayList<Account>();
        Account account = new CheckingAccount(5000, "123456");
        System.out.println(account);
        accountList.add(account);
        Account account2 = new SavingsAccount(2500, "123457");
        accountList.add(account2);
        System.out.println(account2);

        System.out.println(account.equals(account2));

        transfer(account, account2, 500);

        printAccounts(accountList);

        // Creating a customer class
        Person sam = new Customer("Sam", "sam@yahoo.com", accountList);
        System.out.println(sam);

        // Credit card
        CreditCard masters = new Masters("123456","06/27", "Sam", 0);
        CreditCard visa = new Visa("321567", "08/26", "Sam", 0);
        CreditCard americanExpress = new AmericanExpress("123456", "07/28", "Sam", 0);
        masters.use(30);
        visa.use(50);
        americanExpress.use(80);

        menu(accountList);
    }
    public static void transfer(Account a, Account b, double transfer) {
        a.withdraw(transfer);
        System.out.println(a.getBalance());
        b.deposit(transfer);
        System.out.println(b.getBalance());
    }
    public static void showMenu() {
        System.out.println("This is the menu: ");
        System.out.println("0) to quit the program");
        System.out.println("1) to withdraw");
        System.out.println("2) to deposit");
        System.out.println("3) Create an account");
        System.out.println("4) Transfer between accounts");
        System.out.println("5) Show accounts and their balance");
    }

    public static void menu(ArrayList<Account> arrayList) {
        Scanner scan = new Scanner(System.in);
        int input;

        do {
            showMenu();
            input = scan.nextInt();

            switch (input) {
                case 0:
                    break;
                case 1:
                    printAccounts(arrayList);
                    System.out.println("Choose one of your accounts:");
                    int choice = scan.nextInt();

                    System.out.println("Select how much you want to withdraw:");
                    double withdraw = scan.nextDouble();

                    arrayList.get(choice).withdraw(withdraw);

                    System.out.println(arrayList.get(choice).getBalance());

                    break;
                case 2:
                    printAccounts(arrayList);
                    System.out.println("Choose one of your accounts:");
                    int choice2 = scan.nextInt();

                    System.out.println("Select how much you want to deposit:");
                    double deposit = scan.nextDouble();

                    arrayList.get(choice2).deposit(deposit);

                    System.out.println(arrayList.get(choice2).getBalance());

                    break;
                case 3:
                    System.out.println("Enter the amount:");
                    double amount = scan.nextDouble();

                    scan.nextLine();

                    System.out.println("Enter your account number:");
                    String accountNumber= scan.nextLine();

                    createAccount(amount, accountNumber, arrayList);

                    break;
                case 4:
                    System.out.println("Choose 2 accounts");
                    System.out.println("First account:");
                    int select = scan.nextInt();
                    System.out.println("Second account:");
                    int select2 = scan.nextInt();
                    System.out.println("Amount you want to transfer");
                    double cash = scan.nextDouble();

                    transfer(arrayList.get(select), arrayList.get(select2), cash);
                case 5:
                    printAccounts(arrayList);
                    break;
                default:
                    System.out.println("You have chosen an invalid option");
                    break;
            }

        } while(input != 0);
    }
    public static void createAccount(double amount, String accountNumber, ArrayList arrayList) {
        Account account;
        Scanner input = new Scanner(System.in);
        int choice;

        System.out.println("Choose which account you would like to create:");
        System.out.println("1) Checkout Account");
        System.out.println("2) Savings Account");

        choice = input.nextInt();

        if (choice == 1) {
            account = new CheckingAccount(amount, accountNumber);
            arrayList.add(account);

        } else if (choice == 2) {
            account = new SavingsAccount(amount, accountNumber);
            arrayList.add(account);
        }
        else {
            System.out.println("You chose an option that is not on the list.");
        }
    }

    public static void printAccounts(ArrayList<Account> arrayList){
        System.out.println("Your current accounts:");

        for(int i = 0; i < arrayList.size(); i++){
            System.out.println("Account " + i + " is " + arrayList.get(i)
                               + ", balance is " + arrayList.get(i).getBalance());
        }
    }
}