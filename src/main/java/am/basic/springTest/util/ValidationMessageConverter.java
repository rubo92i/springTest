package am.basic.springTest.util;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

public class ValidationMessageConverter {


    public static String getMessage(ConstraintViolationException ex) {
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        StringBuilder message = new StringBuilder();
        for (ConstraintViolation constraintViolation : constraintViolations) {
            message.append(constraintViolation.getMessage()).append(" <br>");
        }

        return message.toString();
    }
}
