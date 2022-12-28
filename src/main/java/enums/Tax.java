package enums;

public enum Tax {
    SALES(0.10),
    INCOME(0.15),
    PROPERTY(0.0125);

    private final double percent;

    Tax(double percent) {
        this.percent = percent;
    }

    public double getPercent() {
        return this.percent;
    }
}
