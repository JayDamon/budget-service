package com.protean.moneymaker.rin.util;

import com.protean.moneymaker.rin.dto.BudgetDto;
import com.protean.moneymaker.rin.model.Budget;
import org.modelmapper.ModelMapper;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

public class BudgetUtil {

    private static ModelMapper modelMapper = new ModelMapper();

    private BudgetUtil() {
    }

    public static Set<BudgetDto> convertBudgetsToDto(Collection<Budget> budgets) {

        if (budgets == null) {
            throw new IllegalArgumentException("Budgets must not be null");
        }

        Set<BudgetDto> dtos = new LinkedHashSet<>();

        for (Budget b : budgets) {
            dtos.add(modelMapper.map(b, BudgetDto.class));
        }

        return dtos;

    }

    public static BudgetDto convertBudgetToDto(Budget budget) {

        if (budget == null) {
            throw new IllegalArgumentException("Budget must not be null.");
        }

        return modelMapper.map(budget, BudgetDto.class);
    }

    public static Set<Budget> convertBudgetDtosToBudget(Collection<BudgetDto> budgetDtos) {

        if (budgetDtos == null) {
            throw new IllegalArgumentException("Budget Dto collection must not be null");
        }

        Set<Budget> budgets = new LinkedHashSet<>();

        for (BudgetDto dto : budgetDtos) {
            budgets.add(modelMapper.map(dto, Budget.class));
        }

        return budgets;
    }

    public static Set<Budget> convertBudgetDtosToBudgetIncludeOnlyIdForChildEntity(Collection<BudgetDto> budgetDtos) {

        if (budgetDtos == null) {
            throw new IllegalArgumentException("Budget Dto collection must not be null");
        }

        Set<Budget> budgets = new LinkedHashSet<>();

        for (BudgetDto dto : budgetDtos) {
            Budget budget = modelMapper.map(dto, Budget.class);
            if (budget.getFrequencyType() != null) {
                budget.getFrequencyType().setFrequencyTypeName(null);
            }
            if (budget.getBudgetCategory() != null) {
                budget.getBudgetCategory().setName(null);
                budget.getBudgetCategory().setType(null);
            }
            if (dto.getInUse() == null) {
                budget.setInUse(true);
            }
            budgets.add(budget);
        }

        return budgets;
    }

}
