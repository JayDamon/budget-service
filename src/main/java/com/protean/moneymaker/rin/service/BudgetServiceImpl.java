package com.protean.moneymaker.rin.service;

import com.protean.moneymaker.rin.model.Budget;
import com.protean.moneymaker.rin.model.BudgetCategory;
import com.protean.moneymaker.rin.repository.BudgetCategoryRepository;
import com.protean.moneymaker.rin.repository.BudgetRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BudgetServiceImpl implements BudgetService {

    private BudgetRepository budgetRepository;
    private BudgetCategoryRepository budgetCategoryRepository;

    public BudgetServiceImpl(BudgetRepository budgetRepository) {
        this.budgetRepository = budgetRepository;
    }

    @Override
    public List<Budget> getAllActiveBudgets() {
        return new ArrayList<>(budgetRepository.findBudgetsByInUseTrue());
    }

    @Override
    public List<Budget> getAllBudgets() {

        List<Budget> budgets = new ArrayList<>();
        budgetRepository.findAll().forEach(budgets::add);

        return budgets;
    }

    @Override
    public List<Budget> getAllInactiveBudgets() {
        return new ArrayList<>(budgetRepository.findBudgetsByInUseFalse());
    }

    @Override
    public List<Budget> getAllGenericBudgets() {
        return new ArrayList<>(budgetRepository.findBudgetsByGenericTrue());
    }

    @Override
    public List<Budget> getAllUserDefinedBudgets() {
        return new ArrayList<>(budgetRepository.findBudgetsByGenericFalse());
    }

    @Override
    public List<BudgetCategory> getBudgetNames() {

        List<BudgetCategory> budgetCategories = new ArrayList<>();
        budgetCategoryRepository.findAll().forEach(budgetCategories::add);

        return budgetCategories;
    }

    @Override
    public Budget saveBudget(Budget budget) {
        return budgetRepository.save(budget);
    }

    @Override
    public List<Budget> saveBudgets(List<Budget> budgets) {

        List<Budget> savedBudgets = new ArrayList<>();
        budgetRepository.saveAll(budgets).forEach(savedBudgets::add);

        return savedBudgets;
    }

    @Override
    public List<Budget> deactivateBudgets(List<Budget> budgets) {

        List<Budget> deactivatedBudgets = new ArrayList<>();

        budgets.forEach(budget -> budget.setInUse(false));
        budgetRepository.saveAll(budgets).forEach(deactivatedBudgets::add);

        return deactivatedBudgets;
    }

    @Override
    public void deleteUserDefinedBudgets(List<Budget> budgets) {
        budgetRepository.deleteAll(budgets);
    }

    @Override
    public void deleteUserDefinedBudget(Budget budget) {
        budgetRepository.delete(budget);
    }
}
