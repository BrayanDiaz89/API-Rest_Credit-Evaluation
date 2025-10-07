package com.brayan.CrediScore_API.infraestructure.errors;

import com.brayan.CrediScore_API.infraestructure.errors.dto.DataErrorValidationDTO;
import com.brayan.CrediScore_API.model.enums.TypeOfLoan;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleError400(MethodArgumentNotValidException e){
        var errors = e.getFieldErrors().stream()
                .map(DataErrorValidationDTO::new).toList();
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity handleErrorOfValidation(ValidationException e){
        return ResponseEntity
                .badRequest()
                .body(e.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<DataErrorValidationDTO> handleErrorOfValidationEnum(HttpMessageNotReadableException e) {
        var cause = e.getCause();
        String fieldError;
        if (cause instanceof InvalidFormatException ex && ex.getTargetType().isEnum()) {
            fieldError = ex.getPath().isEmpty() ? "unknowField" : ex.getPath().getFirst().getFieldName();
            String validValues = Arrays.stream(TypeOfLoan.values())
                        .map(Enum::name)
                        .collect(Collectors.joining(", "));
            var errorResponse = new DataErrorValidationDTO(
                        fieldError,
                        String.format("Invalid loan type. You must enter one of the following: %s", validValues));
            return ResponseEntity.badRequest().body(errorResponse);
        }
        var errorResponse = new DataErrorValidationDTO("requestBody", e.getMessage());
        return ResponseEntity.badRequest().body(errorResponse);
    }
}
