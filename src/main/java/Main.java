import creditcard.*;
import enums.Tax;
import org.apache.logging.log4j.*;

import person.*;
import account.*;
import exceptions.*;

import java.text.NumberFormat;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private final static Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws NegativeCardException, NegativeValueException,
            InvalidMenuException {

        List<Account> accountList = new ArrayList<Account>();
        List<Card> cardList = new LinkedList<Card>();

        Account account = new Account(5000, "123456");
        accountList.add(account);
        Account account2 = new Account(2500, "123457");
        accountList.add(account2);

        while (accountList.size() < 2) {
            LOGGER.info("You must have at least two account. Please create two accounts.");
            createAccount(accountList);
        }

        // Creating a card class;
        Card card = new CreditCard("800165", "7/28", "Sam", 0, 0, "Credit Card");
        cardList.add(card);

        taxes(Tax.PROPERTY, 100000);
        menu(accountList, cardList);
    }

    public static void showMenu() {
        LOGGER.info("This is the menu: ");
        LOGGER.info("0) to quit the program");
        LOGGER.info("1) to withdraw");
        LOGGER.info("2) to deposit");
        LOGGER.info("3) Create an account");
        LOGGER.info("4) Transfer between accounts");
        LOGGER.info("5) Show accounts and their balance");
        LOGGER.info("6) Go to card menu");
        LOGGER.info("7) Get a loan");
        LOGGER.info("8) Store info");
        LOGGER.info("9) Find the max balance");
        LOGGER.info("10) Find the min balance");
        LOGGER.info("11) Show Unique Accounts");
        LOGGER.info("12) Check if account exist in system");
    }

    public static void menu(List<Account> accountList, List<Card> cardList) throws NegativeCardException,
            NegativeValueException, InvalidMenuException {
        Transaction transaction = new Transaction(accountList);
        int input;
        Map<String, List<Account>> hashMap = new HashMap<>();

        try (Scanner scan = new Scanner(System.in)) {
            do {
                showMenu();
                input = scan.nextInt();

                switch (input) {
                    case 0:
                        LOGGER.info("You are quitting the menu");
                        break;
                    case 1:
                        printAccounts(accountList);
                        LOGGER.info("Choose one of your accounts:");
                        int choice = scan.nextInt();

                        LOGGER.info("Select how much you want to withdraw:");
                        double withdraw = scan.nextDouble();

                        accountList.get(choice).withdraw(withdraw);

                        LOGGER.info(accountList.get(choice).getBalance());

                        break;
                    case 2:
                        printAccounts(accountList);
                        LOGGER.info("Choose one of your accounts:");
                        int choice2 = scan.nextInt();

                        LOGGER.info("Select how much you want to deposit:");
                        double deposit = scan.nextDouble();

                        accountList.get(choice2).deposit(deposit);

                        LOGGER.info(accountList.get(choice2).getBalance());

                        break;
                    case 3:
                        createAccount(accountList);
                        break;
                    case 4:
                        printAccounts(accountList);
                        LOGGER.info("Choose 2 accounts");
                        LOGGER.info("First account:");
                        int select = scan.nextInt();
                        LOGGER.info("Second account:");
                        int select2 = scan.nextInt();
                        LOGGER.info("Amount you want to transfer");
                        double cash = scan.nextDouble();

                        transaction.transact(accountList.get(select), accountList.get(select2), cash);
                        break;
                    case 5:
                        printAccounts(accountList);
                        break;
                    case 6:
                        while (cardList.size() < 1) {
                            LOGGER.info("You need at least 1 card to use the menu:");
                            createCard(cardList);
                        }
                        cardMenu(cardList);
                        break;
                    case 7:
                        getLoan();
                        break;
                    case 8:
                        scan.nextLine();
                        LOGGER.info("You are storing your informaton");
                        LOGGER.info("Please enter your name");
                        String name = scan.nextLine();
                        hashMap.put(name, accountList);
                        break;
                    case 9:
                        findMaxBalance(accountList);
                        break;
                    case 10:
                        findMinBalance(accountList);
                        break;
                    case 11:
                        showUniqueAccounts(accountList);
                        break;
                    case 12:
                        scan.nextLine();
                        LOGGER.info("Please enter your account number to see if it is in the system:");
                        String accountNumber = scan.nextLine();
                        accountExist(accountNumber, accountList);
                        break;
                    default:
                        String message = "You have chosen an invalid option";
                        LOGGER.info(message);
                        throw new InvalidMenuException(message);
                }

            } while (input != 0);
        }
    }

    public static void createAccount(List arrayList) {
        Scanner scan = new Scanner(System.in);
        try {
            Account account;
            Scanner input = new Scanner(System.in);
            int choice;

            LOGGER.info("Enter the amount of your balance:");
            double amount = scan.nextDouble();

            scan.nextLine();

            LOGGER.info("Enter your account number:");
            String accountNumber = scan.nextLine();

            account = new Account(amount, accountNumber);
            arrayList.add(account);
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    public static void printAccounts(List<Account> arrayList) {
        if (arrayList.size() != 0) {
            LOGGER.info("Your current accounts:");

            for (int i = 0; i < arrayList.size(); i++) {
                LOGGER.info("Account " + i + " has a "
                        + "" +
                        "balance of " + arrayList.get(i).getBalance());
            }
        } else {
            LOGGER.info("You have 0 accounts.");
        }
    }

    public static void showCardMenu() {
        LOGGER.info("Select one of the following options:");
        LOGGER.info("0) Quit the programs");
        LOGGER.info("1) Create a card");
        LOGGER.info("2) Use card");
        LOGGER.info("3) Show total debt");
    }

    public static void cardMenu(List<Card> cardList) throws NegativeCardException {
        Scanner input = new Scanner(System.in);
        int choice;

        do {
            showCardMenu();
            choice = input.nextInt();

            switch (choice) {
                case 0:
                    LOGGER.info("You are quitting the card menu.");
                    break;
                case 1:
                    createCard(cardList);
                    break;
                case 2:
                    if (hasCard(cardList)) {
                        try {
                            printCard(cardList);
                            LOGGER.info("Select one of your cards to use:");
                            int select = input.nextInt();

                            LOGGER.info("Select how much you want to use:");
                            double amount = input.nextDouble();

                            cardList.get(select).use(amount);
                        } catch (Exception e) {
                            input.nextLine();
                            LOGGER.error(e);
                        }
                    } else {
                        LOGGER.info("You must have at least one card.");
                    }
                    break;
                case 3:
                    if (hasCard(cardList)) {
                        showCardDebt(cardList);
                    } else {
                        LOGGER.info("You have no cards.");
                    }

                    break;
                default:
                    LOGGER.info("You have selected an invalid option. Please choose again.");
            }

        } while (choice != 0);
    }

    public static void createCard(List<Card> cardList) throws NegativeCardException {
        Scanner input = new Scanner(System.in);

        LOGGER.info("Which type of card do you want to create:\n" +
                "1) Credit Card\n" +
                "2) Debit card\n");

        int choice = input.nextInt();

        if (choice < 0) {
            String message = "The value cannot be negative";
            LOGGER.error(message);
            throw new NegativeCardException(message);
        }

        input.nextLine();

        if (choice == 1) {
            LOGGER.info("Please enter the following");
            LOGGER.info("Card Number:");
            String cardNumber = input.nextLine();
            LOGGER.info("Name:");
            String name = input.nextLine();

            Card creditCard = new CreditCard(cardNumber, "11/27", name, 0, 0, "Credit Card");
            cardList.add(creditCard);
        } else if (choice == 2) {
            LOGGER.info("Please enter the following");
            LOGGER.info("Card Number:");
            String cardNumber = input.nextLine();
            LOGGER.info("Name:");
            String name = input.nextLine();
            LOGGER.info("Balance:");
            double balance = input.nextDouble();

            Card debitCard = new DebitCard(cardNumber, "12/27", name, 0, balance, "Debit Card");
            cardList.add(debitCard);
        } else {
            LOGGER.info("You chose an invalid option.");
        }
    }

    public static boolean hasCard(List<Card> card) {
        if (card.size() == 0) {
            return false;
        }
        return true;
    }

    public static void printCard(List<Card> cardList) {
        for (int i = 0; i < cardList.size(); i++) {
            LOGGER.info("Card " + i + " is a " + cardList.get(i).getType());
        }
    }

    public static void showCardDebt(List<Card> cardList) {
        for (int i = 0; i < cardList.size(); i++) {
            LOGGER.info("Card " + i + " has a debt of " + cardList.get(i).getDebt());
        }
    }

    public static void getLoan() throws NegativeValueException {
        Scanner input = new Scanner(System.in);

        LOGGER.info("Enter the principle:");
        double principle = input.nextDouble();

        LOGGER.info("Enter term in years:");
        int years = input.nextInt();

        LOGGER.info("Enter the annual interest rate");
        double annualInterestRate = input.nextInt();

        if (principle < 0 || years < 0 || annualInterestRate < 0) {
            String message = "The value cannot be negative";
            LOGGER.error(message);
            throw new NegativeValueException(message);
        }

        Loan loan = new Loan(principle);

        double monthlyLoan = loan.calculateMonthlyLoan(years, annualInterestRate);

        LOGGER.info("Your payment for each month is " + NumberFormat.getCurrencyInstance().format(monthlyLoan) + "\n");
    }

    public static List<Customer> removeDuplicates(List<Customer> customerList) {
        HashSet<Customer> customerSet = new HashSet<>(customerList);
        List<Customer> customerList2 = new Vector<Customer>(customerSet);
        return customerList2;
    }

    public static void taxes(Tax taxes, double amount) {
        double total;
        total = amount * taxes.getPercent();
        LOGGER.info("Amount is " + total);
    }

    public static List<Account> filter(List<Account> accountList) {
        return accountList.stream().filter(account -> account.getBalance() > 5000)
                .collect(Collectors.toList());
    }

    public static void accountExist(String accountNumber, List<Account> accountList) {
        boolean match = accountList.stream().anyMatch(account -> account.getAccountNumber().equals(accountNumber));

        if (match) {
            LOGGER.info("You're account exist");
        } else {
            LOGGER.info("You're account doesn't exist");
        }
    }

    public static void findMaxBalance(List<Account> accountList) {
        LOGGER.info("Max balance is " + accountList.stream().max(Comparator.comparing(Account::getBalance)));
    }

    public static void findMinBalance(List<Account> accountList) {
        LOGGER.info("Min balance is " + accountList.stream().min(Comparator.comparing(Account::getBalance)));
    }

    public static void showEmailAddresses(List<Person> personList) {
        List<String> emailsList = personList.stream().map(persons -> persons.getEmailAddress())
                .collect(Collectors.toList());
        LOGGER.info(emailsList);
    }

    public static void showUniqueAccounts(List<Account> accountLists) {
        List<Account> uniqueList = accountLists.stream().distinct().collect(Collectors.toList());
        LOGGER.info(uniqueList);
    }
}