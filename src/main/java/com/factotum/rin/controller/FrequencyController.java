package com.factotum.rin.controller;

import com.factotum.rin.model.FrequencyType;
import com.factotum.rin.service.FrequencyService;
import com.factotum.rin.util.FrequencyUtil;
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
