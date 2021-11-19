package com.incident.tool.exception;

import com.incident.tool.error.model.ErrorData;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NoRecordFoundException extends Exception {
    private ErrorData<String> errorData;

    public NoRecordFoundException(ErrorData<String> errorData) {
        this.errorData = errorData;
    }
}
