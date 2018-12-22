package com.protean.moneymaker.rin.service;

import com.protean.moneymaker.rin.model.Budget;

import java.util.List;

public interface BudgetService {

    // TODO monthly budget remainine

    Budget saveBudget(Budget budget);

    List<Budget> saveBudgets(List<Budget> budgets);

    // TODO reset generic budget?

}
