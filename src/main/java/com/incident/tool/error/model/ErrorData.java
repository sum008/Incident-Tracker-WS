package com.incident.tool.error.model;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ErrorData<T> {

    private String message;
    private List<T> detailedMessage;
    private int errorCode;
    private Date timestamp;

    public List<T> getDetailedMessage() {
        return detailedMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }
}
