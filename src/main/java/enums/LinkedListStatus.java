package enums;

public enum LinkedListStatus {
    ADD("A"),
    DELETE("D"),
    PRINT("P"),
    QUIT("Q");

    private final String shortName;

    LinkedListStatus(String shortName) {
        this.shortName = shortName;
    }

    public String getShortName() {
        return this.shortName;
    }
}
