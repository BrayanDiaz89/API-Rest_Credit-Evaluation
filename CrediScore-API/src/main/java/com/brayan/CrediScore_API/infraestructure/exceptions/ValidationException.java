package com.brayan.CrediScore_API.infraestructure.exceptions;

public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
}
