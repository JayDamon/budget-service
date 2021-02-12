package com.protean.moneymaker.rin.service;

import com.protean.moneymaker.rin.dto.BudgetCategoryDto;
import com.protean.moneymaker.rin.dto.BudgetDto;
import com.protean.moneymaker.rin.dto.BudgetSummary;
import com.protean.moneymaker.rin.dto.BudgetTypeDto;
import com.protean.moneymaker.rin.dto.TransactionBudgetSummary;
import com.protean.moneymaker.rin.model.Budget;
import com.protean.moneymaker.rin.model.BudgetCategory;
import com.protean.moneymaker.rin.model.BudgetCategoryType;
import com.protean.moneymaker.rin.model.BudgetSubCategory;
import com.protean.moneymaker.rin.model.FrequencyType;
import com.protean.moneymaker.rin.repository.BudgetCategoryRepository;
import com.protean.moneymaker.rin.repository.BudgetRepository;
import com.protean.moneymaker.rin.repository.BudgetSubCategoryRepository;
import com.protean.moneymaker.rin.util.BudgetUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class BudgetServiceImpl implements BudgetService {

    private final ModelMapper modelMapper = new ModelMapper();

    private final BudgetRepository budgetRepository;
    private final BudgetSubCategoryRepository budgetSubCategoryRepository;
    private final BudgetCategoryRepository budgetCategoryRepository;
    private final FrequencyService frequencyService;
    private final TransactionService transactionService;

    public BudgetServiceImpl(
            BudgetRepository budgetRepository,
            BudgetSubCategoryRepository budgetSubCategoryRepository,
            BudgetCategoryRepository budgetCategoryRepository,
            FrequencyService frequencyService,
            TransactionService transactionService) {

        this.budgetRepository = budgetRepository;
        this.budgetSubCategoryRepository = budgetSubCategoryRepository;
        this.budgetCategoryRepository = budgetCategoryRepository;
        this.frequencyService = frequencyService;
        this.transactionService = transactionService;
    }

    @Override
    public Set<Budget> getAllActiveBudgets() {
        return new LinkedHashSet<>(budgetRepository.findBudgetsByInUseTrue());
    }

    @Override
    public Set<Budget> getAllBudgets() {

        return new HashSet<>(budgetRepository.findAll());
    }

    @Override
    public Set<Budget> getAllInactiveBudgets() {
        return new LinkedHashSet<>(budgetRepository.findBudgetsByInUseFalse());
    }

    @Override
    public Set<BudgetSubCategory> getBudgetNames() {

        return new HashSet<>(budgetSubCategoryRepository.findAll());
    }

    @Override
    public Budget saveBudget(Budget budget) {
        return budgetRepository.save(budget);
    }

    @Override
    public Set<Budget> saveBudgets(Set<Budget> budgets) {

        return new HashSet<>(budgetRepository.saveAll(budgets));
    }

    @Override
    public Set<Budget> deactivateBudgets(Set<Budget> budgets) {


        budgets.forEach(budget -> budget.setInUse(false));

        return new HashSet<>(budgetRepository.saveAll(budgets));
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
    public Set<TransactionBudgetSummary> getBudgetSummary(int year, int month) {

        ZonedDateTime startDate = ZonedDateTime.of(year, month, 1, 0, 0, 0, 0, ZoneId.systemDefault());
        ZonedDateTime endDate = startDate.withDayOfMonth(startDate.plusMonths(1).minusDays(1).getDayOfMonth());

        List<BudgetSummary> summaries = budgetRepository.getBudgetSummaries(startDate, endDate);

        return transactionService.getTransactionBudgetSummaryForAllTransactionTypes(year, month, summaries);
    }

    @Override
    public Set<BudgetCategory> getAllBudgetCategories() {
        return new HashSet<>(budgetCategoryRepository.findAll());
    }

    @Override
    public Set<BudgetCategoryDto> getAllBudgetCategoryDtos() {

        Set<BudgetCategoryDto> budgetCategoryDtos = new HashSet<>();
        for (BudgetCategory budgetCategory : getAllBudgetCategories()) {
            budgetCategoryDtos.add(modelMapper.map(budgetCategory, BudgetCategoryDto.class));
        }
        return budgetCategoryDtos;
    }

    @Override
    public Set<BudgetTypeDto> getAllBudgetCategoriesByType() {

        Map<Integer, BudgetTypeDto> budgetTypeMap = new HashMap<>();
        for (BudgetCategory budgetCategory : getAllBudgetCategories()) {

            BudgetTypeDto budgetTypeDto;

            BudgetCategoryType type = budgetCategory.getType();
            if (budgetTypeMap.containsKey(type.getId())) {

                budgetTypeDto = budgetTypeMap.get(type.getId());

            } else {

                budgetTypeDto = new BudgetTypeDto();
                budgetTypeDto.setId(type.getId());
                budgetTypeDto.setType(type.getName());

            }

            BudgetCategoryDto cat = modelMapper.map(budgetCategory, BudgetCategoryDto.class);
            cat.setTypeName(null);
            budgetTypeDto.getBudgetCategories().add(cat);

            budgetTypeMap.put(type.getId(), budgetTypeDto);
        }

        return new HashSet<>(budgetTypeMap.values());
    }

    @Override
    public Set<BudgetDto> createNewBudgets(Set<BudgetDto> newBudgets) {

        if (newBudgets == null) {
            throw new IllegalArgumentException("Budget Dtos must not be null");
        }

        List<Budget> budgetList = new ArrayList<>(BudgetUtil.convertBudgetDtosToBudgetIncludeOnlyIdForChildEntity(newBudgets));

        budgetList = budgetRepository.saveAll(budgetList);

        return BudgetUtil.convertBudgetsToDto(budgetList);
    }

    @Override
    public Budget updateBudget(BudgetDto budgetDto) {

        if (budgetDto == null) {
            throw new IllegalArgumentException("Budget must not be null.");
        }
        if (budgetDto.getId() == null) {
            throw new IllegalArgumentException("Budget must have a valid id.");
        }

        Budget budget = budgetRepository.findById(budgetDto.getId()).orElseThrow(
                () -> new NoResultException("No budget with id <" + budgetDto.getId() + "> was found."));

        if (budgetDto.getFrequencyTypeId() != null) {
            FrequencyType frequencyType = frequencyService.getFrequencyTypeById(budgetDto.getFrequencyTypeId());
            budget.setFrequencyType(frequencyType);
        }
        if (budgetDto.getBudgetCategory() != null && budgetDto.getBudgetCategory().getId() != null) {
            BudgetCategory budgetCategory = budgetCategoryRepository.findById(
                    budgetDto.getBudgetCategory().getId()).orElseThrow(
                            () -> new NoResultException("Budget category with id <" + budgetDto.getBudgetCategory().getId() + "> was not found."));
            budget.setBudgetCategory(budgetCategory);
        }
        if (budgetDto.getName() != null) {
            budget.setName(budgetDto.getName());
        }
        if (budgetDto.getStartDate() != null) {
            budget.setStartDate(budgetDto.getStartDate());
        }
        if (budgetDto.getEndDate() != null) {
            budget.setEndDate(budgetDto.getEndDate());
        }
        if (budgetDto.getAmount() != null) {
            budget.setAmount(budgetDto.getAmount());
        }
        if (budgetDto.getInUse() != null) {
            budget.setInUse(budgetDto.getInUse());
        }

        return budgetRepository.save(budget);
    }

}
