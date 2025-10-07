package com.brayan.CrediScore_API.model.dto;

import com.brayan.CrediScore_API.model.enums.TypeOfLoan;
import jakarta.validation.constraints.*;

public record CreditRequestDTO(
        @NotBlank(message = "Name is required")
        String name,
        @NotBlank(message = "Email is required")
        @Email(message = "Email is not valid")
        String email,
        @NotBlank(message = "Phone is required")
        @Pattern(regexp = "^\\d{10}$", message = "Phone number must be 10 digits")
        String phone,
        @NotNull(message = "Age is required")
        @Min(value = 18, message = "Age must be at least 18")
        @Max(value = 75, message = "Age must be at most 65")
        Integer age,
        @NotNull(message = "Income is required")
        @Min(value = 100, message = "Income must be at least 100")
        Integer income,
        @NotNull(message = "Type of loan is required")
        //@Pattern(regexp = "FREE_INVESTMENT_LOAN|MORTGAGE_LOAN|EDUCATIONAL_LOAN|COMERCIAL_LOAN", message = "Loan type must be FREE_INVESTMENT_LOAN, MORTGAGE_LOAN, EDUCATIONAL_LOAN or COMERCIAL_LOAN")
        TypeOfLoan typeOfLoan,
        @NotNull(message = "Loan amount is required")
        Integer loanAmount,
        @NotNull(message = "Loan term months is required")
        @Min(value = 1, message = "Loan term months must be at least 1")
        Integer loanTermMonths,
        @NotNull(message = "Credit history score is required")
        @Min(value = 0, message = "Credit history score must be at least 1")
        Integer creditHistoryScore
) {
}
