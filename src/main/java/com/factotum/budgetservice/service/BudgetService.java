package com.factotum.budgetservice.service;

import com.factotum.budgetservice.dto.*;
import com.factotum.budgetservice.model.Budget;
import com.factotum.budgetservice.model.BudgetCategory;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;

public interface BudgetService {

    List<Budget> getAllActiveBudgets(Jwt jwt);

    List<TransactionBudgetSummary> getBudgetSummary(Jwt jwt, int years, int months);

    List<BudgetSummary> getBudgetSummaries(Jwt jwt, ZonedDateTime startDate, ZonedDateTime endDate);

    @Transactional
    List<BudgetCategory> getAllBudgetCategories();

    @Transactional
    Set<BudgetCategoryDto> getAllBudgetCategoryDtos();

    /**
     * Retrieve all budget categories and group them by budget type.
     *
     * @return Set of Budget Type Dto
     */
    List<BudgetTypeDto> getAllBudgetCategoriesByType();

    /**
     * Create new budgets from dtos and add newly created ids.
     *
     * @param newBudgets List of budgets to be created.
     * @return Set of BudgetDtos with ids added.
     */
    List<BudgetDto> createNewBudgets(Set<BudgetDto> newBudgets);

    /**
     * Updates budget in the database with only the values provided. Null Values are ignored.
     *
     *
     * @param jwt User token
     * @param budgetDto Dto containing the values being changed.
     * @return The updated budget.
     */
    Budget updateBudget(Jwt jwt, BudgetDto budgetDto);

    // TODO reset generic budget?

}
