package com.factotum.budgetservice.controller;

import com.factotum.budgetservice.dto.BudgetTypeDto;
import com.factotum.budgetservice.service.BudgetService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
