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
public class CreditHistoryRuleLessThan700Test {

    private CreditHistoryRuleLessThan700 testComponent = new CreditHistoryRuleLessThan700();

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
                699
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
                699
        );
        CreditResponseDTO responseTest = testComponent.evaluate(requestTest);
        assertEquals(responseTest.creditApproved(), true);
        assertEquals(responseTest.recomendation(), CreditRecomendationUtil.APPROVED_TRUE_MEDIUM_RISK);
        assertEquals(responseTest.riskLevel(), CreditRisk.MEDIUM_RISK);
    }
}
