package com.brayan.CrediScore_API.service;

import com.brayan.CrediScore_API.model.dto.CreditRequestDTO;
import com.brayan.CrediScore_API.model.dto.CreditResponseDTO;
import com.brayan.CrediScore_API.model.enums.CreditRisk;
import com.brayan.CrediScore_API.model.enums.TypeOfLoan;
import com.brayan.CrediScore_API.util.CreditRecomendationUtil;
import org.springframework.stereotype.Service;

@Service
public class CreditAnalysisService {
    public CreditResponseDTO getCreditStudyResponse(CreditRequestDTO request) {
        if(request.age() < 21){
            return new CreditResponseDTO(request.name(), request.email(), request.phone(), false, CreditRisk.HIGH_RISK,
                                        getMaxEligibleAmount(request.typeOfLoan(), request.creditHistoryScore()), CreditRecomendationUtil.APPROVED_FALSE_BY_AGE);
        } else if(request.creditHistoryScore() < 500) {
            return new CreditResponseDTO(request.name(), request.email(), request.phone(), false, CreditRisk.HIGH_RISK,
                    getMaxEligibleAmount(request.typeOfLoan(), request.creditHistoryScore()), CreditRecomendationUtil.APPROVED_FALSE_BY_SCORE_HISTORY);
        } else if(request.creditHistoryScore() < 700) {
            return new CreditResponseDTO(request.name(), request.email(), request.phone(), true, CreditRisk.MEDIUM_RISK,
                                        getMaxEligibleAmount(request.typeOfLoan(), request.creditHistoryScore()), CreditRecomendationUtil.APPROVED_TRUE_MEDIUM_RISK);
        }
        return new CreditResponseDTO(request.name(), request.email(), request.phone(), true, CreditRisk.LOW_RISK,
                                        getMaxEligibleAmount(request.typeOfLoan(), request.creditHistoryScore()), CreditRecomendationUtil.APPROVED_TRUE_LOW_RISK);
    }

}
