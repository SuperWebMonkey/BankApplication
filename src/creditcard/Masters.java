package creditcard;

public class Masters extends CreditCard {
    public Masters(String cardNumber, String expirationDate, String name, double debt) {
        super(cardNumber, expirationDate, name, debt);
    }

    public void use(double debt) {
        System.out.println("You are using your Masters" +
                "\nYour debt is " + debt + "\n");
    }
}
