package com.brayan.CrediScore_API.util;

import com.brayan.CrediScore_API.model.dto.CreditRequestDTO;
import com.brayan.CrediScore_API.model.enums.TypeOfLoan;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CreditMaxEligibleAmount {

    public static Integer getMaxEligibleAmount(CreditRequestDTO request){
        return switch (request.typeOfLoan()) {
            case TypeOfLoan.FREE_INVESTMENT_LOAN, TypeOfLoan.MORTGAGE_LOAN -> {
                if(request.creditHistoryScore() >= 700){
                    yield request.income() * 10;
                }
                yield request.income() * 5;
            }
            case TypeOfLoan.COMERCIAL_LOAN -> {
                if(request.creditHistoryScore() >= 700){
                    yield request.income() * 7;
                }
                yield request.income() * 4;
            }
            case TypeOfLoan.EDUCATIONAL_LOAN -> {
                if(request.creditHistoryScore() >= 700){
                    yield request.income() * 5;
                }
                yield request.income() * 3;
            }
        };
    }
}
