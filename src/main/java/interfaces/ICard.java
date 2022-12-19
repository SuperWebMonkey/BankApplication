package interfaces;

public interface ICard {
    public void setCardNumber(String cardNumber);

    public void setExpirationDate(String expirationDate);

    public void setName(String name);

    public void use(double amount);
}
