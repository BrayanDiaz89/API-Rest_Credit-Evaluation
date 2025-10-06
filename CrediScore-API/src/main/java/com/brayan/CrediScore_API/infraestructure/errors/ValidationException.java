package com.brayan.CrediScore_API.infraestructure.errors;

public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(String message, Throwable cause){
        super(message, cause);
    }
}
