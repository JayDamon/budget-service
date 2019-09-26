package com.protean.moneymaker.rin.service;

import com.protean.moneymaker.rin.dto.BudgetCategoryDto;
import com.protean.moneymaker.rin.dto.BudgetSummary;
import com.protean.moneymaker.rin.model.Budget;
import com.protean.moneymaker.rin.model.BudgetCategory;
import com.protean.moneymaker.rin.model.BudgetSubCategory;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

    Set<BudgetSummary> getBudgetSummary(int[] years, int[] months);

    @Transactional
    Set<BudgetCategory> getAllBudgetCategories();

    @Transactional
    Set<BudgetCategoryDto> getAllBudgetCategoryDtos();

    // TODO reset generic budget?

}
