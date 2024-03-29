package com.factotum.budgetservice.service;

import com.factotum.budgetservice.dto.BudgetCategoryDto;
import com.factotum.budgetservice.dto.BudgetDto;
import com.factotum.budgetservice.dto.BudgetSummary;
import com.factotum.budgetservice.dto.BudgetTypeDto;
import com.factotum.budgetservice.dto.TransactionBudgetSummary;
import com.factotum.budgetservice.dto.TransactionTotal;
import com.factotum.budgetservice.enumeration.BudgetType;
import com.factotum.budgetservice.http.TransactionService;
import com.factotum.budgetservice.model.Budget;
import com.factotum.budgetservice.model.BudgetCategory;
import com.factotum.budgetservice.model.BudgetCategoryType;
import com.factotum.budgetservice.model.FrequencyType;
import com.factotum.budgetservice.repository.BudgetCategoryRepository;
import com.factotum.budgetservice.repository.BudgetRepository;
import com.factotum.budgetservice.util.BudgetUtil;
import jakarta.persistence.NoResultException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BudgetServiceImpl implements BudgetService {

    private final ModelMapper modelMapper = new ModelMapper();

    private final BudgetRepository budgetRepository;
    private final BudgetCategoryRepository budgetCategoryRepository;
    private final FrequencyService frequencyService;
    private final TransactionService transactionService;

    public BudgetServiceImpl(
            BudgetRepository budgetRepository,
            BudgetCategoryRepository budgetCategoryRepository,
            FrequencyService frequencyService,
            TransactionService transactionService) {

        this.budgetRepository = budgetRepository;
        this.budgetCategoryRepository = budgetCategoryRepository;
        this.frequencyService = frequencyService;
        this.transactionService = transactionService;
    }

    @Override
    public List<Budget> getAllActiveBudgets(Jwt jwt) {
        return budgetRepository.findBudgetsByInUseTrueAndTenantId(jwt.getClaimAsString("sub"));
    }

    @Override
    @Transactional
    public List<TransactionBudgetSummary> getBudgetSummary(Jwt jwt, int year, int month) {

        ZonedDateTime startDate = ZonedDateTime.of(year, month, 1, 0, 0, 0, 0, ZoneId.systemDefault());
        ZonedDateTime endDate = startDate.withDayOfMonth(startDate.plusMonths(1).minusDays(1).getDayOfMonth());

        List<BudgetSummary> summaries = getBudgetSummaries(jwt, startDate, endDate);

        return summaries.stream().map(s -> {

            Set<UUID> budgetIds = budgetRepository
                    .queryAllBudgetIdsForSummary(
                            s.getBudgetType(),
                            s.getCategoryId(),
                            startDate,
                            endDate,
                            jwt.getClaimAsString("sub"));
            TransactionTotal total = transactionService.getTransactionTotal(year, month, s.getBudgetType(), budgetIds);

            return TransactionBudgetSummary.builder()
                    .budgetType(s.getBudgetType())
                    .category(s.getCategory())
                    .month(month)
                    .monthText(LocalDateTime.now().withMonth(month).format(DateTimeFormatter.ofPattern("MMMM")))
                    .year(year)
                    .planned(s.getPlanned())
                    .actual(total.getTotal())
                    .expected(totalIsExpected(total.getBudgetType(), total.getTotal(), s.getPlanned()))
                    .build();

        }).collect(Collectors.toList());

    }

    private boolean totalIsExpected(BudgetType budgetType, BigDecimal actual, BigDecimal expected) {

        if (actual == null) {
            return !(expected.doubleValue() > 0);
        }

        if (BudgetType.INCOME.equals(budgetType)) {
            return expected.compareTo(actual) > 0;
        } else if (BudgetType.EXPENSE.equals(budgetType)) {
            return expected.compareTo(actual) < 0;
        }

        return true;
    }

    @Override
    public List<BudgetSummary> getBudgetSummaries(Jwt jwt, ZonedDateTime startDate, ZonedDateTime endDate) {

        return budgetRepository.getBudgetSummaries(startDate, endDate, jwt.getClaimAsString("sub"));
    }

    @Override
    public List<BudgetCategory> getAllBudgetCategories() {
        return budgetCategoryRepository.findAllByOrderByTypeOrderAndNameOrder();
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
    public List<BudgetTypeDto> getAllBudgetCategoriesByType() {

        Map<UUID, BudgetTypeDto> budgetTypeMap = new LinkedHashMap<>();
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

        return new LinkedList<>(budgetTypeMap.values());
    }

    @Override
    public List<BudgetDto> createNewBudgets(Set<BudgetDto> newBudgets) {

        if (newBudgets == null) {
            throw new IllegalArgumentException("Budget Dtos must not be null");
        }

        for (BudgetDto b : newBudgets) {
            if (b.getBudgetCategory().getId() == null) {
                throw new IllegalArgumentException("All new budgets must hav a valid budget category");
            }
        }

        List<Budget> budgetList = new ArrayList<>(BudgetUtil.convertBudgetDtosToBudgetIncludeOnlyIdForChildEntity(newBudgets));

        budgetList = budgetRepository.saveAll(budgetList);

        return BudgetUtil.convertBudgetsToDto(budgetList);
    }

    @Override
    public Budget updateBudget(Jwt jwt, BudgetDto budgetDto) {

        if (budgetDto == null) {
            throw new IllegalArgumentException("Budget must not be null.");
        }
        if (budgetDto.getId() == null) {
            throw new IllegalArgumentException("Budget must have a valid id.");
        }

        Budget budget = budgetRepository.findByIdAndTenantId(budgetDto.getId(), jwt.getClaimAsString("sub")).orElseThrow(
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
