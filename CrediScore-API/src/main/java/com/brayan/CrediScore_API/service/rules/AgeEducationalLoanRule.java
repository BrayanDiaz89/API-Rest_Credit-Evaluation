package com.brayan.CrediScore_API.service.rules;

import com.brayan.CrediScore_API.model.dto.CreditRequestDTO;
import com.brayan.CrediScore_API.model.dto.CreditResponseDTO;
import com.brayan.CrediScore_API.model.enums.CreditRisk;
import com.brayan.CrediScore_API.model.enums.TypeOfLoan;
import com.brayan.CrediScore_API.util.CreditMaxEligibleAmount;
import com.brayan.CrediScore_API.util.CreditRecomendationUtil;
import org.springframework.stereotype.Component;

@Component
public class AgeEducationalLoanRule implements ICreditRule{

    public boolean appliesTo(CreditRequestDTO request) {
        return request.age() < 21 && request.typeOfLoan() == TypeOfLoan.EDUCATIONAL_LOAN;
    }

    public CreditResponseDTO evaluate(CreditRequestDTO request) {
        return new CreditResponseDTO(
                request.name(),
                request.email(),
                request.phone(),
                false,
                CreditRisk.HIGH_RISK,
                CreditMaxEligibleAmount.getMaxEligibleAmount(request.typeOfLoan(), request.creditHistoryScore()),
                CreditRecomendationUtil.REQUIRED_CODEUDOR
        );
    }
}
