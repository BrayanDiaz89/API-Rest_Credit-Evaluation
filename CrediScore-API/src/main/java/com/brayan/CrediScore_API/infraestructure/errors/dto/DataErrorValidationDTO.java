package com.brayan.CrediScore_API.infraestructure.errors.dto;

import org.springframework.validation.FieldError;

public record DataErrorValidationDTO(
        String field,
        String error
) {
    public DataErrorValidationDTO(FieldError fieldError){
        this(fieldError.getField(), fieldError.getDefaultMessage());
    }
}
