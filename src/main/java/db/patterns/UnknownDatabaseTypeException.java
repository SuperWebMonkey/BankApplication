package db.patterns;

public class UnknownDatabaseTypeException extends RuntimeException {
    public UnknownDatabaseTypeException(String msg) {
        super(msg);
    }
}
