package enums;

public enum Cents {
    PENNY(0.01),
    NICKEL(0.05),
    DIME(0.10),
    QUARTER(0.25),
    HALF_DOLLAR(0.50),
    SACAGAWEA_DOLLAR(1.00);

    private final double number;

    Cents(double number) {
        this.number = number;
    }

    public double number() {
        return this.number;
    }
}
