package com.brayan.CrediScore_API.infraestructure.errors;

import com.brayan.CrediScore_API.infraestructure.errors.dto.DataErrorValidationDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<DataErrorValidationDTO> handleErrorOfValidationEnum(HttpMessageNotReadableException e){
        var exception = new ValidationException("typeOfLoan. You must enter a valid loan type.", e.getCause());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new DataErrorValidationDTO(exception.getMessage(), exception.getCause().toString()));
    }
}
