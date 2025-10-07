package com.brayan.CrediScore_API.service.rules;

import com.brayan.CrediScore_API.model.dto.CreditRequestDTO;
import com.brayan.CrediScore_API.model.dto.CreditResponseDTO;

public interface ICreditRule {
    boolean appliesTo(CreditRequestDTO request);
    CreditResponseDTO evaluate(CreditRequestDTO request);
}
