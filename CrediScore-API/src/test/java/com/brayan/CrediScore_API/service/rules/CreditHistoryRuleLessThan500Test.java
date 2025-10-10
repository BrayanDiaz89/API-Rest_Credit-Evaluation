package com.brayan.CrediScore_API.service.rules;

import com.brayan.CrediScore_API.model.dto.CreditRequestDTO;
import com.brayan.CrediScore_API.model.dto.CreditResponseDTO;
import com.brayan.CrediScore_API.model.enums.CreditRisk;
import com.brayan.CrediScore_API.model.enums.TypeOfLoan;
import com.brayan.CrediScore_API.util.CreditRecomendationUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.TestComponent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestComponent
public class CreditHistoryRuleLessThan500Test {

    private CreditHistoryRuleLessThan500 testComponent = new CreditHistoryRuleLessThan500();

    @Test
    void testAppliesTo(){
        CreditRequestDTO requestTest = new CreditRequestDTO(
                "brayan",
                "brayan@gmail.com",
                "3013360266",
                23,
                2500,
                TypeOfLoan.MORTGAGE_LOAN,
                1900,
                24,
                430
        );
        assertTrue(testComponent.appliesTo(requestTest));
    }

    @Test
    void testEvaluate(){
        CreditRequestDTO requestTest = new CreditRequestDTO(
                "brayan",
                "brayan@gmail.com",
                "3013360266",
                23,
                2500,
                TypeOfLoan.MORTGAGE_LOAN,
                1900,
                24,
                430
        );
        CreditResponseDTO responseTest = testComponent.evaluate(requestTest);
        assertEquals(responseTest.creditApproved(), false);
        assertEquals(responseTest.riskLevel(), CreditRisk.HIGH_RISK);
        assertEquals(responseTest.recomendation(), CreditRecomendationUtil.APPROVED_FALSE_BY_SCORE_HISTORY);
    }

}
