package com.brayan.CrediScore_API.service.validations;

import com.brayan.CrediScore_API.model.dto.CreditRequestDTO;
import com.brayan.CrediScore_API.model.enums.TypeOfLoan;

public interface CreditValidator {
    TypeOfLoan validate(CreditRequestDTO creditRequestDTO);
}
