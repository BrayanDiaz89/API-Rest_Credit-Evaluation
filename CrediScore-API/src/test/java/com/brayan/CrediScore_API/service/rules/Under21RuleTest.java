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
public class Under21RuleTest {

    private Under21Rule testComponent = new Under21Rule();

    @Test
    void testAppliesTo(){
        CreditRequestDTO requestTest = new CreditRequestDTO(
                "brayan",
                "brayan@gmail.com",
                "3013360266",
                19,
                2500,
                TypeOfLoan.FREE_INVESTMENT_LOAN,
                356588,
                24,
                890
        );
        assertTrue(testComponent.appliesTo(requestTest));
    }

    @Test
    void testEvaluate(){
        CreditRequestDTO requestTest = new CreditRequestDTO(
                "brayan",
                "brayan@gmail.com",
                "3013360266",
                19,
                2500,
                TypeOfLoan.FREE_INVESTMENT_LOAN,
                356588,
                24,
                890
        );
        CreditResponseDTO responseTest = testComponent.evaluate(requestTest);
        assertEquals(responseTest.creditApproved(), false);
        assertEquals(responseTest.riskLevel(), CreditRisk.HIGH_RISK);
        assertEquals(responseTest.recomendation(), CreditRecomendationUtil.APPROVED_FALSE_BY_AGE);
    }

}
