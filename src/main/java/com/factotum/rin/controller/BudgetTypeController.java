package com.factotum.rin.controller;

import com.factotum.rin.dto.BudgetTypeDto;
import com.factotum.rin.service.BudgetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/v1/budgets/types")
public class BudgetTypeController {

    private final BudgetService budgetService;

    public BudgetTypeController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    @RequestMapping("")
    public List<BudgetTypeDto> getAllBudgetCategoriesByType() {

        return budgetService.getAllBudgetCategoriesByType();
    }
}
