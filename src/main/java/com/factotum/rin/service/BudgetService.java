package com.factotum.rin.service;

import com.factotum.rin.dto.BudgetCategoryDto;
import com.factotum.rin.dto.BudgetDto;
import com.factotum.rin.dto.BudgetSummary;
import com.factotum.rin.dto.BudgetTypeDto;
import com.factotum.rin.dto.TransactionBudgetSummary;
import com.factotum.rin.model.Budget;
import com.factotum.rin.model.BudgetCategory;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;

public interface BudgetService {

    // TODO monthly budget remaining - this is for cashflow

    Set<Budget> getAllActiveBudgets();

    Set<Budget> getAllBudgets();

    Set<Budget> getAllInactiveBudgets();

    Budget saveBudget(Budget budget);

    Set<Budget> saveBudgets(Set<Budget> budgets);

    Set<Budget> deactivateBudgets(Set<Budget> budgets);

    void deleteUserDefinedBudgets(Set<Budget> budgets);

    void deleteUserDefinedBudget(Budget budgets);

    List<TransactionBudgetSummary> getBudgetSummary(int years, int months);

    List<BudgetSummary> getBudgetSummaries(ZonedDateTime startDate, ZonedDateTime endDate);

    @Transactional
    List<BudgetCategory> getAllBudgetCategories();

    @Transactional
    Set<BudgetCategoryDto> getAllBudgetCategoryDtos();

    /**
     * Retrieve all budget categories and group them by budget type.
     *
     * @return Set of Budget Type Dto
     */
    Set<BudgetTypeDto> getAllBudgetCategoriesByType();

    /**
     * Create new budgets from dtos and add newly created ids.
     *
     * @param newBudgets
     *          List of budgets to be created.
     * @return Set of BudgetDtos with ids added.
     */
    Set<BudgetDto> createNewBudgets(Set<BudgetDto> newBudgets);

    /**
     * Updates budget in the database with only the values provided. Null Values are ignored.
     *
     * @param budgetDto
     *          Dto containing the values being changed.
     * @return The updated budget.
     */
    Budget updateBudget(BudgetDto budgetDto);

    // TODO reset generic budget?

}
