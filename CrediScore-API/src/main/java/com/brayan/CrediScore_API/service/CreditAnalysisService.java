package com.brayan.CrediScore_API.service;

import com.brayan.CrediScore_API.model.dto.CreditRequestDTO;
import com.brayan.CrediScore_API.model.dto.CreditResponseDTO;
import com.brayan.CrediScore_API.model.enums.CreditRisk;
import org.springframework.stereotype.Service;

@Service
public class CreditAnalysisService {

    private String recomendation;
    private CreditRisk creditRisk;

    public CreditResponseDTO getCreditStudyResponse(CreditRequestDTO request){
        
    }

}
