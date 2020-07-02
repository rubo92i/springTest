package am.basic.springTest.model.exceptions;

public class UnauthorizedException extends Exception {

    public UnauthorizedException(String message) {
        super(message);
    }


    public static void check(boolean expresion, String message) throws UnauthorizedException {
        if (expresion) {
            throw new UnauthorizedException(message);
        }
    }
}
