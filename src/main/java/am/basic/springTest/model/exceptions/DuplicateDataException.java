package am.basic.springTest.model.exceptions;

public class DuplicateDataException extends Exception {

    public DuplicateDataException(String message) {
        super(message);
    }


    public static void check(boolean expresion,String message) throws DuplicateDataException {
        if (expresion){
            throw new DuplicateDataException(message);
        }
    }
}
