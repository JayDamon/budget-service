package com.factotum.budgetservice.controller;

import com.factotum.budgetservice.dto.BudgetDto;
import com.factotum.budgetservice.dto.TransactionBudgetSummary;
import com.factotum.budgetservice.model.Budget;
import com.factotum.budgetservice.repository.BudgetRepository;
import com.factotum.budgetservice.service.BudgetService;
import com.factotum.budgetservice.util.BudgetUtil;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Slf4j
@Validated
@RestController
@RequestMapping("/v1/budgets")
public class BudgetController {

    private final BudgetService budgetService;
    private final BudgetRepository budgetRepository;

    public BudgetController(BudgetService budgetService, BudgetRepository budgetRepository) {
        this.budgetService = budgetService;
        this.budgetRepository = budgetRepository;
    }

    @GetMapping("")
    public List<BudgetDto> getActiveBudgets(JwtAuthenticationToken jwt) {
        return BudgetUtil.convertBudgetsToDto(
                budgetService.getAllActiveBudgets(jwt.getToken())
        );

    }

    @GetMapping("/{id}")
    public BudgetDto getBudgetById(JwtAuthenticationToken jwt, @PathVariable(name = "id") UUID id) {

        Budget budget = budgetRepository
                .findByIdAndTenantId(id, jwt.getToken().getClaimAsString("sub"))
                .orElseThrow(() -> new EntityNotFoundException(String.format("Unable to find budget with id %s", id)));

        return BudgetUtil.convertBudgetToDto(budget);
    }

    @PostMapping("")
    public List<BudgetDto> createNewBudgets(@Valid @RequestBody Set<BudgetDto> newBudgets) {
        return budgetService.createNewBudgets(newBudgets);
    }

    @PatchMapping("/{id}")
    public BudgetDto updateBudget(
            JwtAuthenticationToken jwtAuthenticationToken,
            @PathVariable(name = "id") UUID id,
            @RequestBody BudgetDto budgetDto) {

        if (budgetDto.getId() == null) {
            throw new IllegalArgumentException("Budget id must be provided in the body.");
        }
        if (!id.equals(budgetDto.getId())) {
            throw new IllegalArgumentException("Path id and body id were not equal.");
        }

        return BudgetUtil.convertBudgetToDto(
                budgetService.updateBudget(jwtAuthenticationToken.getToken(), budgetDto)
        );

    }

    @GetMapping("/summary")
    public List<TransactionBudgetSummary> getBudgetSummary(
            JwtAuthenticationToken jwt,
            @RequestParam(name = "year") int year,
            @RequestParam(name = "month") int month) {

        return budgetService.getBudgetSummary(jwt.getToken(), year, month);
    }

}
