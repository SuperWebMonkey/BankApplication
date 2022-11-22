package creditcard;

public class AmericanExpress extends CreditCard {

    public AmericanExpress(String cardNumber, String expirationDate, String name, double debt) {
        super(cardNumber, expirationDate, name, debt);
    }

    public void use(double debt) {
        System.out.println("You are using your American Express." +
                           "\nYour debt is " + debt + "\n");
    }
}
