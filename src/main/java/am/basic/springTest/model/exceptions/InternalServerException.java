package am.basic.springTest.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR,reason = "something.went.wrong")
public class InternalServerException extends Exception {


    public InternalServerException(String message) {
        super(message);
    }

    public InternalServerException() {
    }
}
