package com.brayan.CrediScore_API.service.rules;

import com.brayan.CrediScore_API.model.dto.CreditRequestDTO;
import com.brayan.CrediScore_API.model.dto.CreditResponseDTO;
import com.brayan.CrediScore_API.model.enums.CreditRisk;
import com.brayan.CrediScore_API.model.enums.TypeOfLoan;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.TestComponent;

import static org.junit.jupiter.api.Assertions.*;

@TestComponent
public class AgeEducationalLoanRuleTest {

    private AgeEducationalLoanRule testComponent = new AgeEducationalLoanRule();

    @Test
    void testAppliesTo(){
        CreditRequestDTO requestTest = new CreditRequestDTO(
                "brayan",
                "brayan@gmail.com",
                "3013360266",
                24,
                2500,
                TypeOfLoan.EDUCATIONAL_LOAN,
                1900,
                24,
                600
        );
        assertFalse(testComponent.appliesTo(requestTest));
    }

    @Test
    void testAppliesToTrue(){
        CreditRequestDTO requestTest = new CreditRequestDTO(
                "brayan",
                "brayan@gmail.com",
                "3013360266",
                20,
                2500,
                TypeOfLoan.EDUCATIONAL_LOAN,
                1900,
                24,
                600
        );
        assertTrue(testComponent.appliesTo(requestTest));
    }

    @Test
    void testEvaluate(){
        CreditRequestDTO requestTest = new CreditRequestDTO(
                "brayan",
                "brayan@gmail.com",
                "3013360266",
                20,
                2500,
                TypeOfLoan.EDUCATIONAL_LOAN,
                1900,
                24,
                600
        );
        CreditResponseDTO responseTest = testComponent.evaluate(requestTest);
        assertEquals(responseTest.creditApproved(), false);
        assertEquals(responseTest.riskLevel(), CreditRisk.HIGH_RISK);
    }

}
