package com.brayan.CrediScore_API.util;

import com.brayan.CrediScore_API.model.enums.TypeOfLoan;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CreditMaxEligibleAmount {

    public static Integer getMaxEligibleAmount(TypeOfLoan typeOfLoan, Integer creditHistoryScore){
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
