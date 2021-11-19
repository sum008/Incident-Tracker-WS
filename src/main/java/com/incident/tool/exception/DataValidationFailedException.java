package com.incident.tool.exception;

import java.util.Date;
import java.util.List;

import com.incident.tool.error.model.ErrorData;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DataValidationFailedException extends Exception {

    private ErrorData<String> errorData;

    public DataValidationFailedException(String message, List<String> validationData) {
        errorData = new ErrorData<>(message, validationData, HttpStatus.BAD_REQUEST.value(), new Date());
    }
}
