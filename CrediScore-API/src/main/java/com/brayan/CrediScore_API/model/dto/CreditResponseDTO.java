package com.brayan.CrediScore_API.model.dto;

import com.brayan.CrediScore_API.model.enums.CreditRisk;

public record CreditResponseDTO(
        String name,
        String email,
        String phone,
        Boolean creditApproved,
        CreditRisk riskLevel,
        Integer maxEligibleAmount,
        String recomendation
) {
}
