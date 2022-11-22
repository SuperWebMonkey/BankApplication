package creditcard;

public class Visa extends CreditCard {
    public Visa(String cardNumber, String expirationDate, String name, double debt) {
        super(cardNumber, expirationDate, name, debt);
    }

    public void use(double debt) {
        System.out.println("You are using your Visa."
        + "Your debt is " + debt + "\n");
    }
}
