package com.protean.moneymaker.rin.service;

import com.protean.moneymaker.rin.model.Budget;

import java.util.List;

public interface BudgetService {

    // TODO monthly budget remaining - this is for cashflow

    List<Budget> getAllActiveBudgets();

    List<Budget> getAllBudgets();

    List<Budget> getAllInactiveBudgets();

    List<Budget> getAllGenericBudgets();

    List<Budget> getAllUserDefinedBudgets();

    List<String> getBudgetNames();

    Budget saveBudget(Budget budget);

    List<Budget> saveBudgets(List<Budget> budgets);

    List<Budget> deactivateBudgets(List<Budget> budgets);

    void deleteUserDefinedBudgets(List<Budget> budgets);

    void deleteUserDefinedBudget(Budget budgets);

    // TODO reset generic budget?

}
