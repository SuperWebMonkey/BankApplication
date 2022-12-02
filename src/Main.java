import creditcard.*;
import person.*;
import account.*;

import java.text.NumberFormat;
import java.util.*;
import Interfaces.*;

public class Main {

    public static void main(String[] args) {

        // Testing accounts
        ArrayList<Account> accountList = new ArrayList<Account>();
        ArrayList<Card> cardList = new ArrayList<Card>();

        Account account = new Account(5000, "123456");
        System.out.println(account);
        accountList.add(account);
        Account account2 = new Account(2500, "123457");
        accountList.add(account2);
        System.out.println(account2);

        System.out.println(account.equals(account2));

        // Creating a customer class
        Person sam = new Customer("Sam", "sam@yahoo.com", accountList);
        System.out.println(sam);

        while(accountList.size() < 2){
            System.out.println("You must have at least two account. Please create two accounts.");
            createAccount(accountList);
        }

        // Creating a card class;
        Card card = new CreditCard("800165", "7/28","Sam", 0, 0,"Credit Card");
        cardList.add(card);

        menu(accountList, cardList);

    }
    public static void showMenu() {
        System.out.println("This is the menu: ");
        System.out.println("0) to quit the program");
        System.out.println("1) to withdraw");
        System.out.println("2) to deposit");
        System.out.println("3) Create an account");
        System.out.println("4) Transfer between accounts");
        System.out.println("5) Show accounts and their balance");
        System.out.println("6) Go to card menu");
        System.out.println("7) Get a loan");
    }

    public static void menu(ArrayList<Account> arrayList, ArrayList<Card> cardList) {
        Scanner scan = new Scanner(System.in);
        Transaction transaction = new Transaction(arrayList);
        int input;

        do {
            showMenu();
            input = scan.nextInt();

            switch (input) {
                case 0:
                    System.out.println("You are quitting the menu");
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
                    createAccount(arrayList);
                    break;
                case 4:
                    printAccounts(arrayList);
                    System.out.println("Choose 2 accounts");
                    System.out.println("First account:");
                    int select = scan.nextInt();
                    System.out.println("Second account:");
                    int select2 = scan.nextInt();
                    System.out.println("Amount you want to transfer");
                    double cash = scan.nextDouble();

                    transaction.transact(arrayList.get(select), arrayList.get(select2), cash);
                    break;
                case 5:
                    printAccounts(arrayList);
                    break;
                case 6:
                    while(cardList.size() < 1){
                        System.out.println("You need at least 1 card to use the menu:");
                        createCard(cardList);
                    }
                    cardMenu(cardList);
                    break;
                case 7:
                    getLoan();
                    break;
                default:
                    System.out.println("You have chosen an invalid option");
                    break;
            }

        } while(input != 0);
        scan.close();
    }
    public static void createAccount(ArrayList arrayList) {
        Scanner scan = new Scanner(System.in);

        Account account;
        Scanner input = new Scanner(System.in);
        int choice;

        System.out.println("Enter the amount of your balance:");
        double amount = scan.nextDouble();

        scan.nextLine();

        System.out.println("Enter your account number:");
        String accountNumber= scan.nextLine();

        account = new Account(amount, accountNumber);
        arrayList.add(account);
    }

    public static void printAccounts(ArrayList<Account> arrayList){
        if( arrayList.size() != 0){
            System.out.println("Your current accounts:");

            for(int i = 0; i < arrayList.size(); i++){
                System.out.println("Account " + i + " has a "
                        + "" +
                        "balance of " + arrayList.get(i).getBalance());
            }
            System.out.println();
        } else {
            System.out.println("You have 0 accounts.");
        }
    }

    public static void showCardMenu(){
        System.out.println("Select one of the following options:");
        System.out.println("0) Quit the programs");
        System.out.println("1) Create a card");
        System.out.println("2) Use card");
        System.out.println("3) Show total debt");
    }
    public static void cardMenu(ArrayList<Card> cardList){
        Scanner input = new Scanner(System.in);
        int choice;

        do {
            showCardMenu();
            choice = input.nextInt();

            switch(choice) {
                case 0:
                    System.out.println("You are quitting the card menu.");
                    break;
                case 1:
                    createCard(cardList);
                    break;
                case 2:
                    if (hasCard(cardList)){
                        printCard(cardList);
                        System.out.println("Select one of your cards to use:");
                        int select = input.nextInt();

                        System.out.println("Select how much you want to use:");
                        double amount = input.nextDouble();

                        cardList.get(select).use(amount);
                    } else {
                        System.out.println("You must have at least one card.");
                    }
                    break;
                case 3:
                    if (hasCard(cardList)){
                        showCardDebt(cardList);
                    } else {
                        System.out.println("You have no cards.");
                    }

                    break;
                default:
                    System.out.println("You have selected an invalid option. Please choose again.");
            }

        } while(choice != 0);
    }

    public static void createCard(ArrayList<Card> cardList){
        Scanner input = new Scanner(System.in);

        System.out.println("Which type of card do you want to create:\n" +
                           "1) Credit Card\n" +
                           "2) Debit card\n");

        int choice = input.nextInt();

        input.nextLine();

        if (choice == 1){
            System.out.println("Please enter the following");
            System.out.println("Card Number:");
            String cardNumber = input.nextLine();
            System.out.println("Name:");
            String name = input.nextLine();

            Card creditCard = new CreditCard(cardNumber, "11/27", name, 0, 0, "Credit Card");
            cardList.add(creditCard);
        } else if (choice == 2) {
            System.out.println("Please enter the following");
            System.out.println("Card Number:");
            String cardNumber = input.nextLine();
            System.out.println("Name:");
            String name = input.nextLine();
            System.out.println("Balance:");
            double balance = input.nextDouble();

            Card debitCard = new DebitCard(cardNumber, "12/27", name, 0, balance,"Debit Card");
            cardList.add(debitCard);
        } else {
            System.out.println("You chose an invalid option.");
        }
    }

    public static boolean hasCard(ArrayList<Card> card){
        if (card.size() == 0){
            return false;
        }
        return true;
    }

    public static void printCard(ArrayList<Card> cardList){
        for (int i = 0; i < cardList.size(); i++){
            System.out.println("Card " + i + " is a " + cardList.get(i).getType());
        }
    }

    public static void showCardDebt(ArrayList<Card> cardList){
        for (int i = 0; i < cardList.size(); i++){
            System.out.println("Card " + i + " has a debt of " + cardList.get(i).getDebt());
        }
    }

    public static void getLoan(){
        Scanner input = new Scanner(System.in);

        System.out.println("Enter the principle:");
        double principle = input.nextDouble();

        System.out.println("Enter term in years:");
        int years = input.nextInt();

        System.out.println("Enter the annual interest rate");
        double annualInterestRate = input.nextInt();

        Loan loan = new Loan(principle);

        double monthlyLoan = loan.calculateMonthlyLoan(years, annualInterestRate);

        System.out.println("Your payment for each month is " + NumberFormat.getCurrencyInstance().format(monthlyLoan) + "\n");
    }
}