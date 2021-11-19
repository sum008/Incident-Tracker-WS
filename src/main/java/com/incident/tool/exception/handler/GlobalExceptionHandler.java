package com.incident.tool.exception.handler;

import java.util.Date;

import com.incident.tool.error.model.ErrorData;
import com.incident.tool.exception.DataValidationFailedException;
import com.incident.tool.exception.InvalidJsonDataException;
import com.incident.tool.exception.NoRecordFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorData<String>> dataNotFound(NoRecordFoundException exception) {
        return new ResponseEntity<>(exception.getErrorData(), HttpStatus.ACCEPTED);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorData<String>> invalidJsonData(InvalidJsonDataException invalidJson) {
        ErrorData<String> data = new ErrorData<>(invalidJson.getMessage(), null, HttpStatus.BAD_REQUEST.value(),
                new Date());
        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorData<String>> failedValidation(DataValidationFailedException validation) {
        return new ResponseEntity<>(validation.getErrorData(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorData<String>> genericExceptionHandler(Exception exc) {
        ErrorData<String> data = new ErrorData<>(exc.getLocalizedMessage(), null, HttpStatus.BAD_REQUEST.value(),
                new Date());
        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorData<String>> runtimeExceptionHandler(RuntimeException exc) {
        ErrorData<String> data = new ErrorData<>(exc.getLocalizedMessage(), null, HttpStatus.BAD_REQUEST.value(),
                new Date());
        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }

}
