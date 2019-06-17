package com.protean.moneymaker.rin.service;

import com.protean.moneymaker.rin.dto.BudgetSummary;
import com.protean.moneymaker.rin.model.Budget;
import com.protean.moneymaker.rin.model.BudgetSubCategory;

import java.util.List;

public interface BudgetService {

    // TODO monthly budget remaining - this is for cashflow

    List<Budget> getAllActiveBudgets();

    List<Budget> getAllBudgets();

    List<Budget> getAllInactiveBudgets();

    List<BudgetSubCategory> getBudgetNames();

    Budget saveBudget(Budget budget);

    List<Budget> saveBudgets(List<Budget> budgets);

    List<Budget> deactivateBudgets(List<Budget> budgets);

    void deleteUserDefinedBudgets(List<Budget> budgets);

    void deleteUserDefinedBudget(Budget budgets);

    List<BudgetSummary> getBudgetSummary(int[] years, int[] months);

    // TODO reset generic budget?

}
