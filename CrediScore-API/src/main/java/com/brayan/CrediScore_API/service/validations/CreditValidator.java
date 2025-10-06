package com.brayan.CrediScore_API.service.validations;

import com.brayan.CrediScore_API.model.dto.CreditRequestDTO;

public interface CreditValidator {
    void validate(CreditRequestDTO creditRequestDTO);
}
