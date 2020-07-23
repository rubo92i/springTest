package am.basic.springTest.model.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


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
