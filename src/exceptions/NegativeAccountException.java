package exceptions;

public class NegativeAccountException extends Exception{
    public NegativeAccountException(String message){
        super(message);
    }
}
