package creditcard;

public class CreditCard extends Card{
    private static double credit;
    private static final double CREDIT_LIMIT;

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
        System.out.println("Credit card debt is " + this.credit);
    }

    public double getDebt(){
        credit += debt;
        return credit;
    }
    public void increaseCredit(double amount){
        if ((credit + amount) > CREDIT_LIMIT){
            System.out.println("You have reached your credit limit");
        } else {
            credit += amount;
        }
    }

    public boolean isCreditLimit(){
        if (credit < CREDIT_LIMIT){
            return true;
        }
        System.out.println("You've reached your credit limit");
        return false;
    }
}
