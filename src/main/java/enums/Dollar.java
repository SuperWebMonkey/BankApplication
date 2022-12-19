package enums;

public enum Dollar {
    ONE(1),
    TWO(2),
    FIVE(5),
    TEN(10),
    TWENTY(20),
    FIFTY(50),
    HUNDRED(100);

    private final int number;

    Dollar (int number){
        this.number = number;
    }
}
