package com.brayan.CrediScore_API.service;

import com.brayan.CrediScore_API.model.dto.CreditRequestDTO;
import com.brayan.CrediScore_API.model.dto.CreditResponseDTO;
import com.brayan.CrediScore_API.model.enums.CreditRisk;
import com.brayan.CrediScore_API.service.rules.ICreditRule;
import com.brayan.CrediScore_API.util.CreditMaxEligibleAmount;
import com.brayan.CrediScore_API.util.CreditRecomendationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditAnalysisService {

    @Autowired
    private final List<ICreditRule> rules;

    public CreditAnalysisService(List<ICreditRule> rules){
        this.rules = rules;
    }

    public CreditResponseDTO getCreditStudyResponse(CreditRequestDTO request) {
        return rules.stream()
                .filter(rule -> rule.appliesTo(request))
                .findFirst()
                .map(rule -> rule.evaluate(request))
                .orElseGet(() -> defaultApproval(request));
    }

    public CreditResponseDTO defaultApproval(CreditRequestDTO request){
        return new CreditResponseDTO(
                request.name(),
                request.email(),
                request.phone(),
                true,
                CreditRisk.LOW_RISK,
                CreditMaxEligibleAmount.getMaxEligibleAmount(request),
                CreditRecomendationUtil.APPROVED_TRUE_LOW_RISK
        );
    }

}
