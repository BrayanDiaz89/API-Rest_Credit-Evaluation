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
        if(request.age() < 21 && request.typeOfLoan() == TypeOfLoan.EDUCATIONAL_LOAN) {
            return new CreditResponseDTO(request.name(), request.email(), request.phone(), false, CreditRisk.HIGH_RISK,
                    getMaxEligibleAmount(request.typeOfLoan(), request.creditHistoryScore()), CreditRecomendationUtil.REQUIRED_CODEUDOR);
        } else if(request.creditHistoryScore() < 500) {
            return new CreditResponseDTO(request.name(), request.email(), request.phone(), false, CreditRisk.HIGH_RISK,
                    getMaxEligibleAmount(request.typeOfLoan(), request.creditHistoryScore()), CreditRecomendationUtil.APPROVED_FALSE_BY_SCORE_HISTORY);
        } else if(request.creditHistoryScore() < 700) {
            return new CreditResponseDTO(request.name(), request.email(), request.phone(), true, CreditRisk.MEDIUM_RISK,
                                        getMaxEligibleAmount(request.typeOfLoan(), request.creditHistoryScore()), CreditRecomendationUtil.APPROVED_TRUE_MEDIUM_RISK);
        } else if(request.creditHistoryScore() > 700){
            return new CreditResponseDTO(request.name(), request.email(), request.phone(), true, CreditRisk.LOW_RISK,
                                        getMaxEligibleAmount(request.typeOfLoan(), request.creditHistoryScore()), CreditRecomendationUtil.APPROVED_TRUE_LOW_RISK);
        }else {
            return new CreditResponseDTO(request.name(), request.email(), request.phone(), false, CreditRisk.HIGH_RISK,
                    getMaxEligibleAmount(request.typeOfLoan(), request.creditHistoryScore()), CreditRecomendationUtil.APPROVED_FALSE_BY_AGE);
        }
    }

    public Integer getMaxEligibleAmount(TypeOfLoan typeOfLoan, Integer creditHistoryScore){
        return switch (typeOfLoan) {
            case TypeOfLoan.FREE_INVESTMENT_LOAN  -> {
                if(creditHistoryScore >= 700){
                    yield 70_000_000;
                }
                yield 35_000_000;
            }
            case TypeOfLoan.COMERCIAL_LOAN -> {
                if(creditHistoryScore >= 700){
                    yield 120_000_000;
                }
                yield 60_000_000;
            }
            case TypeOfLoan.EDUCATIONAL_LOAN -> {
                if(creditHistoryScore >= 700){
                    yield 50_000_000;
                }
                yield 25_000_000;
            }
            case TypeOfLoan.MORTGAGE_LOAN -> {
                if(creditHistoryScore >= 700){
                    yield 200_000_000;
                }
                yield 100_000_000;
            }
        };
    }

}
