package am.basic.springTest.model.exceptions;

public class AccessDeniedException extends Exception {

    public AccessDeniedException(String message) {
        super(message);
    }


    public static void check(boolean expresion, String message) throws AccessDeniedException {
        if (expresion) {
            throw new AccessDeniedException(message);
        }
    }
}
