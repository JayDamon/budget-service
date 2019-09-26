package com.protean.moneymaker.rin.service;

import com.protean.moneymaker.rin.dto.BudgetCategoryDto;
import com.protean.moneymaker.rin.dto.BudgetSummary;
import com.protean.moneymaker.rin.model.Budget;
import com.protean.moneymaker.rin.model.BudgetCategory;
import com.protean.moneymaker.rin.model.BudgetSubCategory;
import com.protean.moneymaker.rin.repository.BudgetCategoryRepository;
import com.protean.moneymaker.rin.repository.BudgetSubCategoryRepository;
import com.protean.moneymaker.rin.repository.BudgetRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class BudgetServiceImpl implements BudgetService {

    private BudgetRepository budgetRepository;
    private BudgetSubCategoryRepository budgetSubCategoryRepository;
    private BudgetCategoryRepository budgetCategoryRepository;

    public BudgetServiceImpl(
            BudgetRepository budgetRepository,
            BudgetSubCategoryRepository budgetSubCategoryRepository,
            BudgetCategoryRepository budgetCategoryRepository) {

        this.budgetRepository = budgetRepository;
        this.budgetSubCategoryRepository = budgetSubCategoryRepository;
        this.budgetCategoryRepository = budgetCategoryRepository;
    }

    @Override
    public Set<Budget> getAllActiveBudgets() {
        return new LinkedHashSet<>(budgetRepository.findBudgetsByInUseTrue());
    }

    @Override
    public Set<Budget> getAllBudgets() {

        Set<Budget> budgets = new HashSet<>();
        budgetRepository.findAll().forEach(budgets::add);

        return budgets;
    }

    @Override
    public Set<Budget> getAllInactiveBudgets() {
        return new LinkedHashSet<>(budgetRepository.findBudgetsByInUseFalse());
    }

    @Override
    public Set<BudgetSubCategory> getBudgetNames() {

        Set<BudgetSubCategory> budgetCategories = new HashSet<>();
        budgetSubCategoryRepository.findAll().forEach(budgetCategories::add);

        return budgetCategories;
    }

    @Override
    public Budget saveBudget(Budget budget) {
        return budgetRepository.save(budget);
    }

    @Override
    public Set<Budget> saveBudgets(Set<Budget> budgets) {

        Set<Budget> savedBudgets = new HashSet<>();
        budgetRepository.saveAll(budgets).forEach(savedBudgets::add);

        return savedBudgets;
    }

    @Override
    public Set<Budget> deactivateBudgets(Set<Budget> budgets) {

        Set<Budget> deactivatedBudgets = new HashSet<>();

        budgets.forEach(budget -> budget.isInUse(false));
        budgetRepository.saveAll(budgets).forEach(deactivatedBudgets::add);

        return deactivatedBudgets;
    }

    @Override
    public void deleteUserDefinedBudgets(Set<Budget> budgets) {
        budgetRepository.deleteAll(budgets);
    }

    @Override
    public void deleteUserDefinedBudget(Budget budget) {
        budgetRepository.delete(budget);
    }

    @Override
    public Set<BudgetSummary> getBudgetSummary(int[] years, int[] months) {
        return null;
    }

    @Override
    public Set<BudgetCategory> getAllBudgetCategories() {
        return new HashSet<>(budgetCategoryRepository.findAll());
    }

    @Override
    public Set<BudgetCategoryDto> getAllBudgetCategoryDtos() {
        ModelMapper modelMapper = new ModelMapper();

        Set<BudgetCategoryDto> budgetCategoryDtos = new HashSet<>();
        for (BudgetCategory budgetCategory : getAllBudgetCategories()) {
            budgetCategoryDtos.add(modelMapper.map(budgetCategory, BudgetCategoryDto.class));
        }
        return budgetCategoryDtos;
    }
}
