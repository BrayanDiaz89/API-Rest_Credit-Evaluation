package com.brayan.CrediScore_API.service.rules;

import com.brayan.CrediScore_API.model.dto.CreditRequestDTO;
import com.brayan.CrediScore_API.model.dto.CreditResponseDTO;
import com.brayan.CrediScore_API.model.enums.CreditRisk;
import com.brayan.CrediScore_API.model.enums.TypeOfLoan;
import com.brayan.CrediScore_API.util.CreditMaxEligibleAmount;
import com.brayan.CrediScore_API.util.CreditRecomendationUtil;
import org.springframework.stereotype.Component;

@Component
public class LoanAmountGreaterIncomeRule implements ICreditRule {

    public boolean appliesTo(CreditRequestDTO request){
        return (request.typeOfLoan() == TypeOfLoan.FREE_INVESTMENT_LOAN ||
                request.typeOfLoan() == TypeOfLoan.MORTGAGE_LOAN)
                && (request.loanAmount() > (request.income() * 10));
    }

    public CreditResponseDTO evaluate(CreditRequestDTO request){
        return new CreditResponseDTO(
                request.name(),
                request.email(),
                request.phone(),
                false,
                CreditRisk.HIGH_RISK,
                CreditMaxEligibleAmount.getMaxEligibleAmount(request),
                CreditRecomendationUtil.APPROVED_FALSE_BY_LOAN_AMOUNT
        );
    }

}
