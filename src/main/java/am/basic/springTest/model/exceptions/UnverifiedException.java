package am.basic.springTest.model.exceptions;

public class UnverifiedException extends Exception{

    public UnverifiedException(String message) {
        super(message);
    }


    public static void check(boolean expresion, String message) throws UnverifiedException {
        if (expresion) {
            throw new UnverifiedException(message);
        }
    }
}
