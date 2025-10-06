package com.brayan.CrediScore_API.controller;

import com.brayan.CrediScore_API.model.dto.CreditRequestDTO;
import com.brayan.CrediScore_API.model.dto.CreditResponseDTO;
import com.brayan.CrediScore_API.service.CreditAnalysisService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("credits")
public class CreditController {

    @Autowired
    private CreditAnalysisService serviceAnalysis;

    @PostMapping("/evaluateCredit")
    private ResponseEntity<CreditResponseDTO> getEvaluationCredit(@RequestBody @Valid CreditRequestDTO request){
        var responseCredit = serviceAnalysis.getCreditStudyResponse(request);
        return ResponseEntity.ok(responseCredit);
    }

}
