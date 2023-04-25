package io.credable.connectors.controllers;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ErrorResponse implements Serializable {
    private int status;
    private String message;
    private List<FieldError> fieldErrors;

    public ErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
        this.fieldErrors = new ArrayList<>();

    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<FieldError> getFieldErrors() {
        return fieldErrors;
    }

    public void addValidationError(String field, String message) {
        FieldError error = new FieldError(field, message);
        fieldErrors.add(error);
    }

    record FieldError(String field, String message) {
    };
}
