package com.factotum.budgetservice.controller;

import com.factotum.budgetservice.model.FrequencyType;
import com.factotum.budgetservice.service.FrequencyService;
import com.factotum.budgetservice.util.FrequencyUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/v1/budgets/frequency-types")
public class FrequencyController {

    private final FrequencyService frequencyService;

    public FrequencyController(FrequencyService frequencyService) {
        this.frequencyService = frequencyService;
    }

    @GetMapping("")
    public ResponseEntity<?> getAllFrequencyTypes() {

        List<FrequencyType> types = frequencyService.getAllFrequencyTypes();

        return ok(FrequencyUtil.convertFrequencyTypesToDto(types));
    }
}
