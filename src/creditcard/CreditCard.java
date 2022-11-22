package creditcard;

import account.Account;

public abstract class CreditCard {
    private String cardNumber;
    private String expirationDate;
    private String name;
    private double debt;

    public CreditCard(String cardNumber, String expirationDate, String name, double debt) {
        setDebt(debt);
        setCardNumber(cardNumber);
        setExpirationDate(expirationDate);
        setName(name);
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDebt(double debt) {
        this.debt = debt;
    }

    public String getCardNumber() {
        return this.cardNumber;
    }

    public String getExpirationDate() {
        return this.expirationDate;
    }

    public String getCustomer() {
        return this.name;
    }

    public double getDebt() {
        return this.debt;
    }

    public void use(double use) {
        debt += use;
        System.out.println("You are using your credit card. " +
                           "Your current deb is " + getDebt());
    }

    public boolean equals(Object o){
        if (o == this)
            return true;
        if (!(o instanceof CreditCard))
            return false;
        CreditCard other = (CreditCard)o;
        boolean cardNumberEquals = (this.cardNumber == null && other.cardNumber == null)
                || (this.cardNumber != null && this.cardNumber.equals(other.cardNumber));
        boolean expirationDateEquals = (this.expirationDate == null && other.expirationDate == null)
                || (this.expirationDate != null && this.expirationDate.equals(other.expirationDate));
        boolean nameEquals = (this.name == null && other.name == null)
                || (this.name != null && this.name.equals(other.name));
        return this.debt == other.debt && cardNumberEquals && expirationDateEquals;
    }

    public final int hashCode() {
        int result = 17;
        if (cardNumber != null){
            result = 31 * result + cardNumber.hashCode();
        }
        if (expirationDate != null){
            result = 31 * result + cardNumber.hashCode();
        }
        if (name != null) {
            result = 31 * result + name.hashCode();
        }

        return result;
    }

    public String toString() {
        return "Your credit card number is " + cardNumber;
    }
}
