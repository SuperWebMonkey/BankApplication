import creditcard.*;
import org.apache.logging.log4j.*;
import person.*;
import account.*;
import exceptions.*;

import java.text.NumberFormat;
import java.util.*;

import Interfaces.*;

public class Main {
    private static Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws NegativeCardException, NegativeValueException,
    InvalidMenuException
    {

        // Testing accounts
        ArrayList<Account> accountList = new ArrayList<Account>();
        ArrayList<Card> cardList = new ArrayList<Card>();

        Account account = new Account(5000, "123456");
        accountList.add(account);
        Account account2 = new Account(2500, "123457");
        accountList.add(account2);

        while (accountList.size() < 2) {
            logger.info("You must have at least two account. Please create two accounts.");
            createAccount(accountList);
        }

        // Creating a card class;
        Card card = new CreditCard("800165", "7/28", "Sam", 0, 0, "Credit Card");
        cardList.add(card);

        menu(accountList, cardList);

    }

    public static void showMenu() {
        logger.info("This is the menu: ");
        logger.info("0) to quit the program");
        logger.info("1) to withdraw");
        logger.info("2) to deposit");
        logger.info("3) Create an account");
        logger.info("4) Transfer between accounts");
        logger.info("5) Show accounts and their balance");
        logger.info("6) Go to card menu");
        logger.info("7) Get a loan");
    }

    public static void menu(ArrayList<Account> arrayList, ArrayList<Card> cardList) throws NegativeCardException,
            NegativeValueException, InvalidMenuException
    {
        Transaction transaction = new Transaction(arrayList);
        int input;

        try (Scanner scan = new Scanner(System.in)) {
            do {
                showMenu();
                input = scan.nextInt();

                switch (input) {
                    case 0:
                        logger.info("You are quitting the menu");
                        break;
                    case 1:
                        printAccounts(arrayList);
                        logger.info("Choose one of your accounts:");
                        int choice = scan.nextInt();

                        logger.info("Select how much you want to withdraw:");
                        double withdraw = scan.nextDouble();

                        arrayList.get(choice).withdraw(withdraw);

                        logger.info(arrayList.get(choice).getBalance());

                        break;
                    case 2:
                        printAccounts(arrayList);
                        logger.info("Choose one of your accounts:");
                        int choice2 = scan.nextInt();

                        logger.info("Select how much you want to deposit:");
                        double deposit = scan.nextDouble();

                        arrayList.get(choice2).deposit(deposit);

                        logger.info(arrayList.get(choice2).getBalance());

                        break;
                    case 3:
                        createAccount(arrayList);
                        break;
                    case 4:
                        printAccounts(arrayList);
                        logger.info("Choose 2 accounts");
                        logger.info("First account:");
                        int select = scan.nextInt();
                        logger.info("Second account:");
                        int select2 = scan.nextInt();
                        logger.info("Amount you want to transfer");
                        double cash = scan.nextDouble();

                        transaction.transact(arrayList.get(select), arrayList.get(select2), cash);
                        break;
                    case 5:
                        printAccounts(arrayList);
                        break;
                    case 6:
                        while (cardList.size() < 1) {
                            logger.info("You need at least 1 card to use the menu:");
                            createCard(cardList);
                        }
                        cardMenu(cardList);
                        break;
                    case 7:
                        getLoan();
                        break;
                    default:
                        String message = "You have chosen an invalid option";
                        logger.info(message);
                        throw new InvalidMenuException(message);
                }

            } while (input != 0);
        }
    }

    public static void createAccount(ArrayList arrayList) {
        Scanner scan = new Scanner(System.in);
        try {
            Account account;
            Scanner input = new Scanner(System.in);
            int choice;

            logger.info("Enter the amount of your balance:");
            double amount = scan.nextDouble();

            scan.nextLine();

            logger.info("Enter your account number:");
            String accountNumber = scan.nextLine();


            account = new Account(amount, accountNumber);
            arrayList.add(account);
        } catch (Exception e) {
            logger.error(e);
        }
    }

    public static void printAccounts(ArrayList<Account> arrayList) {
        if (arrayList.size() != 0) {
            logger.info("Your current accounts:");

            for (int i = 0; i < arrayList.size(); i++) {
                logger.info("Account " + i + " has a "
                        + "" +
                        "balance of " + arrayList.get(i).getBalance());
            }
        } else {
            logger.info("You have 0 accounts.");
        }
    }

    public static void showCardMenu() {
        logger.info("Select one of the following options:");
        logger.info("0) Quit the programs");
        logger.info("1) Create a card");
        logger.info("2) Use card");
        logger.info("3) Show total debt");
    }

    public static void cardMenu(ArrayList<Card> cardList) throws NegativeCardException {
        Scanner input = new Scanner(System.in);
        int choice;

        do {
            showCardMenu();
            choice = input.nextInt();

            switch (choice) {
                case 0:
                    logger.info("You are quitting the card menu.");
                    break;
                case 1:
                    createCard(cardList);
                    break;
                case 2:
                    if (hasCard(cardList)) {
                        try {
                            printCard(cardList);
                            logger.info("Select one of your cards to use:");
                            int select = input.nextInt();

                            logger.info("Select how much you want to use:");
                            double amount = input.nextDouble();

                            cardList.get(select).use(amount);
                        } catch (Exception e) {
                            input.nextLine();
                            logger.error(e);
                        }
                    } else {
                       logger.info("You must have at least one card.");
                    }
                    break;
                case 3:
                    if (hasCard(cardList)) {
                        showCardDebt(cardList);
                    } else {
                        logger.info("You have no cards.");
                    }

                    break;
                default:
                    logger.info("You have selected an invalid option. Please choose again.");
            }

        } while (choice != 0);
    }

    public static void createCard(ArrayList<Card> cardList) throws NegativeCardException {
        Scanner input = new Scanner(System.in);

        logger.info("Which type of card do you want to create:\n" +
                "1) Credit Card\n" +
                "2) Debit card\n");

        int choice = input.nextInt();

        if (choice < 0) {
            String message = "The value cannot be negative";
            logger.error(message);
            throw new NegativeCardException(message);
        }

        input.nextLine();

        if (choice == 1) {
            logger.info("Please enter the following");
            logger.info("Card Number:");
            String cardNumber = input.nextLine();
            logger.info("Name:");
            String name = input.nextLine();

            Card creditCard = new CreditCard(cardNumber, "11/27", name, 0, 0, "Credit Card");
            cardList.add(creditCard);
        } else if (choice == 2) {
            logger.info("Please enter the following");
            logger.info("Card Number:");
            String cardNumber = input.nextLine();
            logger.info("Name:");
            String name = input.nextLine();
            logger.info("Balance:");
            double balance = input.nextDouble();

            Card debitCard = new DebitCard(cardNumber, "12/27", name, 0, balance, "Debit Card");
            cardList.add(debitCard);
        } else {
            logger.info("You chose an invalid option.");
        }
    }

    public static boolean hasCard(ArrayList<Card> card) {
        if (card.size() == 0) {
            return false;
        }
        return true;
    }

    public static void printCard(ArrayList<Card> cardList) {
        for (int i = 0; i < cardList.size(); i++) {
            logger.info("Card " + i + " is a " + cardList.get(i).getType());
        }
    }

    public static void showCardDebt(ArrayList<Card> cardList) {
        for (int i = 0; i < cardList.size(); i++) {
            logger.info("Card " + i + " has a debt of " + cardList.get(i).getDebt());
        }
    }

    public static void getLoan() throws NegativeValueException {
        Scanner input = new Scanner(System.in);
        logger.info("Enter the principle:");
        double principle = input.nextDouble();

        logger.info("Enter term in years:");
        int years = input.nextInt();

        logger.info("Enter the annual interest rate");
        double annualInterestRate = input.nextInt();

        if (principle < 0 || years < 0 || annualInterestRate < 0) {
            String message = "The value cannot be negative";
            logger.error(message);
            throw new NegativeValueException(message);
        }

        Loan loan = new Loan(principle);

        double monthlyLoan = loan.calculateMonthlyLoan(years, annualInterestRate);

        logger.info("Your payment for each month is " + NumberFormat.getCurrencyInstance().format(monthlyLoan) + "\n");
    }
}