package com.brayan.CrediScore_API.service.rules;

import com.brayan.CrediScore_API.model.dto.CreditRequestDTO;
import com.brayan.CrediScore_API.model.dto.CreditResponseDTO;
import com.brayan.CrediScore_API.model.enums.CreditRisk;
import com.brayan.CrediScore_API.model.enums.TypeOfLoan;
import com.brayan.CrediScore_API.util.CreditMaxEligibleAmount;
import com.brayan.CrediScore_API.util.CreditRecomendationUtil;
import org.springframework.stereotype.Component;

@Component
public class MortgageLoanWithUnrealisticTermRule implements ICreditRule{

    public boolean appliesTo(CreditRequestDTO request){
        return (request.typeOfLoan() == TypeOfLoan.MORTGAGE_LOAN) && (request.loanTermMonths() < 60);
    }

    public CreditResponseDTO evaluate(CreditRequestDTO request){
        return new CreditResponseDTO(
                request.name(),
                request.email(),
                request.phone(),
                false,
                CreditRisk.HIGH_RISK,
                CreditMaxEligibleAmount.getMaxEligibleAmount(request),
                CreditRecomendationUtil.APPROVED_FALSE_BY_LOAN_TERM_MORTGAGE_LOAN
        );
    }

}
