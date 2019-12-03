package com.protean.moneymaker.rin.service;

import com.protean.moneymaker.rin.dto.BudgetCategoryDto;
import com.protean.moneymaker.rin.dto.BudgetDto;
import com.protean.moneymaker.rin.dto.BudgetTypeDto;
import com.protean.moneymaker.rin.dto.TransactionBudgetSummary;
import com.protean.moneymaker.rin.model.Budget;
import com.protean.moneymaker.rin.model.BudgetCategory;
import com.protean.moneymaker.rin.model.BudgetSubCategory;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

public interface BudgetService {

    // TODO monthly budget remaining - this is for cashflow

    Set<Budget> getAllActiveBudgets();

    Set<Budget> getAllBudgets();

    Set<Budget> getAllInactiveBudgets();

    Set<BudgetSubCategory> getBudgetNames();

    Budget saveBudget(Budget budget);

    Set<Budget> saveBudgets(Set<Budget> budgets);

    Set<Budget> deactivateBudgets(Set<Budget> budgets);

    void deleteUserDefinedBudgets(Set<Budget> budgets);

    void deleteUserDefinedBudget(Budget budgets);

    Set<TransactionBudgetSummary> getBudgetSummary(int years, int months);

    @Transactional
    Set<BudgetCategory> getAllBudgetCategories();

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
