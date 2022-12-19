package creditcard;
import interfaces.ICard;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class Card implements ICard {
    private String cardNumber;
    private String expirationDate;
    private String name;
    private static int countCards = 0;
    protected double debt;
    private final static Logger LOGGER = LogManager.getLogger(Card.class);

    private String type;

    static {
        countCards++;
    }

    public Card(String cardNumber, String expirationDate, String name, String type, double debt) {
        setCardNumber(cardNumber);
        setExpirationDate(expirationDate);
        setName(name);
        setType(type);
        setDebt(debt);
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

    public void setType(String type){
        this.type = type;
    }

    public void setDebt(double debt){
        this.debt = debt;
    }

    public String getCardNumber() {
        return this.cardNumber;
    }

    public String getExpirationDate() {
        return this.expirationDate;
    }

    public String getName() {
        return this.name;
    }

    public static final int getCount(){
        return countCards;
    }

    public void use(double amount){
        this.debt += amount;
        LOGGER.info("Card debt is " + this.debt);
    }

    public double getDebt(){
        return debt;
    }

    public String getType(){
        return type;
    }

    public boolean equals(Object o){
        if (o == this)
            return true;
        if (!(o instanceof Card))
            return false;
        Card other = (Card)o;
        boolean cardNumberEquals = (this.cardNumber == null && other.cardNumber == null)
                || (this.cardNumber != null && this.cardNumber.equals(other.cardNumber));
        boolean expirationDateEquals = (this.expirationDate == null && other.expirationDate == null)
                || (this.expirationDate != null && this.expirationDate.equals(other.expirationDate));
        boolean nameEquals = (this.name == null && other.name == null)
                || (this.name != null && this.name.equals(other.name));
        return cardNumberEquals && expirationDateEquals;
    }

    public int hashCode() {
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
        return "Your card number is " + cardNumber;
    }
}
