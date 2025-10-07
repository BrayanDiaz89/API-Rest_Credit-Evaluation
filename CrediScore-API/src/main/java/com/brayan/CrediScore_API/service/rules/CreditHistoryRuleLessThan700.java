package com.brayan.CrediScore_API.service.rules;

import com.brayan.CrediScore_API.model.dto.CreditRequestDTO;
import com.brayan.CrediScore_API.model.dto.CreditResponseDTO;
import com.brayan.CrediScore_API.model.enums.CreditRisk;
import com.brayan.CrediScore_API.util.CreditMaxEligibleAmount;
import com.brayan.CrediScore_API.util.CreditRecomendationUtil;
import org.springframework.stereotype.Component;

@Component
public class CreditHistoryRuleLessThan700 implements ICreditRule{

    public boolean appliesTo(CreditRequestDTO request){
        return request.creditHistoryScore() < 700;
    }

    public CreditResponseDTO evaluate(CreditRequestDTO request){
        return new CreditResponseDTO(
                request.name(),
                request.email(),
                request.phone(),
                true,
                CreditRisk.MEDIUM_RISK,
                CreditMaxEligibleAmount.getMaxEligibleAmount(request.typeOfLoan(), request.creditHistoryScore()),
                CreditRecomendationUtil.APPROVED_TRUE_MEDIUM_RISK
        );
    }

}
