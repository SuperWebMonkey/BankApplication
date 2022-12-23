package enums;

public enum Taxes {
    SALES(0.10),
    INCOME(0.15),
    PROPERTY(0.0125);

    private final double percent;

    Taxes(double percent) {
        this.percent = percent;
    }

    public double getPercent() {
        return this.percent;
    }
}
