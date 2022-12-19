package enums;

public enum Taxes {
    Sales(10),
    Income(15),
    Property(1.25);

    private final double percent;

    Taxes(double percent) {
        this.percent = percent;
    }

    double percent() {
        return this.percent;
    }
}
