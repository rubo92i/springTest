package am.basic.springTest.model.exceptions;

public class InvalidParametersException extends Exception {


    public InvalidParametersException(String message) {
        super(message);
    }



    public static void check(boolean expresion,String message) throws  InvalidParametersException {
        if (expresion){
            throw new  InvalidParametersException(message);
        }
    }
}
