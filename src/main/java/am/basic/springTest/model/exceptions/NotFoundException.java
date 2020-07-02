package am.basic.springTest.model.exceptions;

public class NotFoundException extends Exception {


    public NotFoundException(String message) {
        super(message);
    }



    public static void check(boolean expresion,String message) throws NotFoundException {
        if (expresion){
            throw new NotFoundException(message);
        }
    }
}
