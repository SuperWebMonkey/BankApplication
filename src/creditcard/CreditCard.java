package creditcard;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CreditCard extends Card{
    private static double credit;
    private static final double CREDIT_LIMIT;
    private final static Logger LOGGER = LogManager.getLogger(CreditCard.class);

    static {
        CREDIT_LIMIT = 12945;
    }

    public CreditCard(String cardNumber, String expirationDate, String name, double debt, double credit, String type){
        super(cardNumber, expirationDate, name, type, debt);
        setCredit(credit);
    }

    public void setCredit(double credit){
        this.credit = credit;
    }

    public double getCredit(){
        return credit;
    }

    public double getCreditLimit(){
        return CREDIT_LIMIT;
    }

    public void use(double debt){
        this.credit += debt;
        LOGGER.info("Credit card debt is " + this.credit);
    }

    public double getDebt(){
        credit += debt;
        return credit;
    }
    public void increaseCredit(double amount){
        if ((credit + amount) > CREDIT_LIMIT){
            LOGGER.info("You have reached your credit limit");
        } else {
            credit += amount;
        }
    }

    public boolean isCreditLimit(){
        if (credit < CREDIT_LIMIT){
            return true;
        }
        LOGGER.info("You've reached your credit limit");
        return false;
    }
}
