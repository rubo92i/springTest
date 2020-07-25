package am.basic.springTest.config;


import am.basic.springTest.model.exceptions.DuplicateDataException;
import am.basic.springTest.model.exceptions.NotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;

@Log4j2
@ControllerAdvice
public class ExceptionConfig {



    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity handleNotFoundException(NotFoundException exception){
        log.warn(exception.getMessage());
        return ResponseEntity.status(404).body(Collections.singletonMap("message",exception.getMessage()));
    }


    @ExceptionHandler(DuplicateDataException.class)
    public ResponseEntity handleDuplicateDataException(DuplicateDataException exception){
        log.warn(exception.getMessage());
        return ResponseEntity.status(409).body(Collections.singletonMap("message",exception.getMessage()));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity handleRuntimeException(RuntimeException exception){
        log.error(exception.getMessage());
        return ResponseEntity.status(500).body(Collections.singletonMap("message","something.went.wrong"));
    }

}
