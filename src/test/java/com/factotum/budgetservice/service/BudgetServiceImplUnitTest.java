package com.factotum.budgetservice.service;

import com.factotum.budgetservice.dto.*;
import com.factotum.budgetservice.enumeration.BudgetType;
import com.factotum.budgetservice.http.TransactionService;
import com.factotum.budgetservice.model.*;
import com.factotum.budgetservice.repository.BudgetCategoryRepository;
import com.factotum.budgetservice.repository.BudgetRepository;
import com.factotum.budgetservice.repository.FrequencyTypeRepository;
import com.factotum.budgetservice.util.SecurityTestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.NoResultException;
import java.math.BigDecimal;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BudgetServiceImplUnitTest {

    @Mock
    private BudgetCategoryRepository budgetCategoryRepository;

    @Mock
    private BudgetRepository budgetRepository;

    @Mock
    private FrequencyTypeRepository frequencyTypeRepository;

    @Mock
    private TransactionService transactionService;

    private BudgetService budgetService;

    @BeforeEach
    void setUp() {
        FrequencyService frequencyService = new FrequencyServiceImpl(frequencyTypeRepository);
        budgetService = new BudgetServiceImpl(
                budgetRepository, budgetCategoryRepository, frequencyService, transactionService);
    }

    //    getAllBudgetCategoryDtos
    @Test
    void getAllBudgetCategories_GivenValidReturn_ThenCategoriesComplete() {

        // Arrange
        when(budgetCategoryRepository.findAllByOrderByTypeOrderAndNameOrder()).thenReturn(createBudgetCategories());

        // Act
        List<BudgetCategory> budgetCategories = budgetService.getAllBudgetCategories();

        // Assert
        assertThat(budgetCategories, hasSize(1));
        int budgetCategoriesChecked = 0;
        for (BudgetCategory budgetCategory : budgetCategories) {
            assertThat(budgetCategory.getId(), is(equalTo(3)));
            assertThat(budgetCategory.getType().getId(), is(equalTo(1)));
            assertThat(budgetCategory.getType().getName(), is(equalTo("TestBudgetCategoryType")));
            assertThat(budgetCategory.getName().getId(), is(equalTo(2)));
            assertThat(budgetCategory.getName().getName(), is(equalTo("TestBudgetCategoryName")));
            assertThat(budgetCategory.getBudgetItems(), hasSize(1));
            int budgetItemsChecked = 0;
            for (BudgetItem item : budgetCategory.getBudgetItems()) {
                assertThat(item.getId(), is(equalTo(4L)));
                assertThat(item.getName(), is("TestBudgetItemName"));
                assertThat(item.getBudgetCategory(), is(equalTo(budgetCategory)));
                budgetItemsChecked++;
            }
            assertThat(budgetItemsChecked, is(equalTo(1)));
            budgetCategoriesChecked++;
        }
        assertThat(budgetCategoriesChecked, is(equalTo(1)));
    }

    @Test
    void getAllBudgetCategoryDtos_GivenValidReturn_ThenCategoriesComplete() {

        // Arrange
        when(budgetCategoryRepository.findAllByOrderByTypeOrderAndNameOrder()).thenReturn(createBudgetCategories());

        // Act
        Set<BudgetCategoryDto> budgetCategories = budgetService.getAllBudgetCategoryDtos();

        // Assert
        validateBudgetCategoriesIsCorrect(budgetCategories);

    }

    private void validateBudgetCategoriesIsCorrect(Set<BudgetCategoryDto> budgetCategories) {
        assertThat(budgetCategories, hasSize(1));
        int budgetCategoriesChecked = 0;
        for (BudgetCategoryDto budgetCategory : budgetCategories) {
            assertThat(budgetCategory.getTypeName(), is(equalTo("TestBudgetCategoryType")));
            budgetCategoriesChecked = validateIndividualBudgetCategory(budgetCategoriesChecked, budgetCategory);
        }
        assertThat(budgetCategoriesChecked, is(equalTo(1)));
    }

    private int validateIndividualBudgetCategory(int budgetCategoriesChecked, BudgetCategoryDto budgetCategory) {
        assertThat(budgetCategory.getId(), is(equalTo(3)));
        assertThat(budgetCategory.getName(), is(equalTo("TestBudgetCategoryName")));
        assertThat(budgetCategory.getBudgetItems(), hasSize(1));
        int budgetItemsChecked = 0;
        for (BudgetItemDto item : budgetCategory.getBudgetItems()) {
            assertThat(item.getId(), is(equalTo(4)));
            assertThat(item.getName(), is("TestBudgetItemName"));
            assertThat(item.getCategoryName(), is(equalTo("TestBudgetCategoryName")));
            budgetItemsChecked++;
        }
        assertThat(budgetItemsChecked, is(equalTo(1)));
        budgetCategoriesChecked++;
        return budgetCategoriesChecked;
    }

    //    getAllBudgetCategoriesByType
    @Test
    void getAllBudgetCategoriesByType_GivenDataReturned_ThenProduceValidtBdugetTypeDto() {

        // Arrange
        List<BudgetCategory> budgetCategories = new ArrayList<>();
        budgetCategories.add(getBudgetCategory("Type1", 1));
        budgetCategories.add(getBudgetCategory("Type2", 2));
        budgetCategories.add(getBudgetCategory("Type3", 3));
        when(budgetCategoryRepository.findAllByOrderByTypeOrderAndNameOrder()).thenReturn(budgetCategories);

        // Act
        List<BudgetTypeDto> budgetTypes = budgetService.getAllBudgetCategoriesByType();

        // Assert
        assertThat(budgetTypes, is(not(nullValue())));
        assertThat(budgetTypes, hasSize(3));
        int budgetTypesChecked = 0;
        for (BudgetTypeDto type : budgetTypes) {

            assertThat(type.getId(), is(oneOf(1, 2, 3)));
            assertThat(type.getType(), is(oneOf("Type1", "Type2", "Type3")));

            for (BudgetCategoryDto cat : type.getBudgetCategories()) {
                assertThat(cat.getTypeName(), is(nullValue()));
                validateIndividualBudgetCategory(1, cat);
            }

            budgetTypesChecked++;
        }
        assertThat(budgetTypesChecked, is(equalTo(3)));

    }

    @Test
    void getAllBudgetCategoriesByType_GivenNoDataReturned_ThenReturnEmptySet() {
        // Arrange
        when(budgetCategoryRepository.findAllByOrderByTypeOrderAndNameOrder()).thenReturn(Collections.emptyList());

        // Act
        List<BudgetTypeDto> budgetTypes = budgetService.getAllBudgetCategoriesByType();

        // Assert
        assertThat(budgetTypes, is(not(nullValue())));
        assertThat(budgetTypes, hasSize(0));
    }

    //    createNewBudgets
    @Test
    @SuppressWarnings("unchecked")
    void createNewBudgets_GivenBudgetsProvided_ThenSaveAndReturnWithIds() {

        // Arrange
        BudgetDto budgetDto = getBudgetDto();

        when(budgetRepository.saveAll(any())).thenAnswer(i -> {
            List<Budget> budgets = (ArrayList<Budget>) i.getArguments()[0];
            long id = 1;
            for (Budget b : budgets) {
                b.setId(id);
                id++;
            }
            return budgets;
        });

        // Act
        List<BudgetDto> budgetDtos = budgetService.createNewBudgets(new HashSet<>(Collections.singletonList(budgetDto)));

        int i = 0;
        for (BudgetDto dto : budgetDtos) {
            assertThat(dto.getId(), is(not(nullValue())));
        }
    }

    @Test
    void createNewBudgets_GivenBudgetsIsNull_ThenThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> budgetService.createNewBudgets(null));
    }

    //    updateBudget
    @Test
    void updateBudget_GivenBudgetExistsAndAllValuesChanged_ThenUpdateAllValues() {

        // Arrange
        Budget currentBudget = createBudget(
                "A Budget Name", ZonedDateTime.now(), ZonedDateTime.now().plusDays(5),
                35.02, 1L);

        when(budgetRepository.findByIdAndTenantId(eq(1L), any())).thenReturn(Optional.of(currentBudget));
        when(budgetRepository.save(any())).thenAnswer(i -> i.getArguments()[0]);

        BudgetCategory budgetCategory = createBudgetCategory("TestType", 2, "TestCatName", 3, 5);
        FrequencyType frequencyType = createFrequencyType("TestFrequency", 10);

        when(budgetCategoryRepository.findById(eq(5))).thenReturn(Optional.of(budgetCategory));
        when(frequencyTypeRepository.findById(eq(10))).thenReturn(Optional.of(frequencyType));

        BudgetDto budgetDto = getBudgetDto();

        // Act
        Budget budget = budgetService.updateBudget(SecurityTestUtil.getTestJwt(), budgetDto);

        // Assert
        assertThat(budget.getId(), is(equalTo(1L)));
        assertThat(budget.getName(), is(equalTo("TestName")));
        assertThat(budget.getStartDate().getDayOfMonth(), is(equalTo(LocalDate.now().getDayOfMonth())));
        assertThat(budget.getEndDate().getDayOfMonth(), is(equalTo(ZonedDateTime.now().plusDays(6).getDayOfMonth())));
        assertThat(budget.getFrequencyType().getId(), is(equalTo(10)));
        assertThat(budget.getFrequencyType().getFrequencyTypeName(), is(equalTo("TestFrequency")));
        assertThat(budget.getAmount(), is(equalTo(BigDecimal.valueOf(54.21))));
        assertThat(budget.getInUse(), is(false));

        BudgetCategory cat = budget.getBudgetCategory();
        assertThat(cat.getId(), is(equalTo(5)));
        assertThat(cat.getType().getName(), is(equalTo("TestType")));
        assertThat(cat.getName().getName(), is(equalTo("TestCatName")));

    }

    @Test
    void updateBudget_GivenDtoWithOnlyPartialValuesProvided_ThenUpdateOnlyValuesProvided() {

        // Arrange
        Budget currentBudget = createBudget(
                "A Budget Name", ZonedDateTime.now(), ZonedDateTime.now().plusDays(5),
                35.02, 1L);

        when(budgetRepository.findByIdAndTenantId(eq(1L), any())).thenReturn(Optional.of(currentBudget));
        when(budgetRepository.save(any())).thenAnswer(i -> i.getArguments()[0]);

        BudgetCategory budgetCategory = createBudgetCategory("TestType", 2, "TestCatName", 3, 5);

        when(budgetCategoryRepository.findById(eq(5))).thenReturn(Optional.of(budgetCategory));

        BudgetDto budgetDto = getBudgetDto();
        budgetDto.setFrequencyTypeId(null);
        budgetDto.setFrequencyTypeName(null);
        budgetDto.setAmount(null);

        // Act
        Budget budget = budgetService.updateBudget(SecurityTestUtil.getTestJwt(), budgetDto);

        // Assert
        verify(frequencyTypeRepository, times(0)).findById(any());

        assertThat(budget.getId(), is(equalTo(1L)));
        assertThat(budget.getName(), is(equalTo("TestName")));
        assertThat(budget.getStartDate().getDayOfMonth(), is(equalTo(LocalDate.now().getDayOfMonth())));
        assertThat(budget.getEndDate().getDayOfMonth(), is(equalTo(ZonedDateTime.now().plusDays(6).getDayOfMonth())));
        assertThat(budget.getFrequencyType().getId(), is(equalTo(3)));
        assertThat(budget.getFrequencyType().getFrequencyTypeName(), is(equalTo("TestFrequencyTypeName")));
        assertThat(budget.getAmount(), is(equalTo(BigDecimal.valueOf(35.02))));
        assertThat(budget.getInUse(), is(false));

        BudgetCategory cat = budget.getBudgetCategory();
        assertThat(cat.getId(), is(equalTo(5)));
        assertThat(cat.getType().getName(), is(equalTo("TestType")));
        assertThat(cat.getName().getName(), is(equalTo("TestCatName")));

    }

    @Test
    void updateBudget_GivenDtoIsNull_ThenThrowIllegalArgumentException() {

        assertThrows(IllegalArgumentException.class, () -> budgetService.updateBudget(null, null));

    }

    @Test
    void updateBudget_GivenDtoIdIsNull_ThenThrowIllegalArgumentException() {

        BudgetDto budgetDto = getBudgetDto();
        budgetDto.setId(null);

        assertThrows(IllegalArgumentException.class, () -> budgetService.updateBudget(null, budgetDto));
    }

    @Test
    void updateBudget_GivenBudgetWithIdDoesNotExist_ThenThrowIllegalArgumentException() {

        when(budgetRepository.findByIdAndTenantId(anyLong(), any())).thenReturn(Optional.empty());

        assertThrows(NoResultException.class, () -> budgetService.updateBudget(SecurityTestUtil.getTestJwt(), getBudgetDto()));
    }

    @Test
    void getBudgetSummary_GivenTransactionsForBudgetsExist_THenReturnBudgetSummary() {

        // Arrange
        TransactionBudgetSummary summary = new TransactionBudgetSummary(
                BudgetType.INCOME, "TestCategory", 1, 2017, 50.02, BigDecimal.valueOf(40.30), true);

        when(budgetRepository.getBudgetSummaries(any(), any(), anyString()))
                .thenReturn(
                        Collections.singletonList(
                                new BudgetSummary(
                                        "TestCategory",
                                        1,
                                        BudgetType.INCOME,
                                        50.02)));

        when(budgetRepository.queryAllBudgetIdsForSummary(any(), anyInt(), any(), any(), anyString()))
                .thenReturn(new HashSet<>(Collections.singletonList(1L)));

        //noinspection unchecked
        when(transactionService.getTransactionTotal(eq(2017), eq(1), eq(BudgetType.INCOME), any(Set.class))).thenReturn(new TransactionTotal(BudgetType.INCOME, BigDecimal.valueOf(40.30)));

        // Act
        List<TransactionBudgetSummary> summaries = budgetService.getBudgetSummary(SecurityTestUtil.getTestJwt(), 2017, 1);

        // Assert
        assertThat(summaries, hasSize(1));

        summaries.forEach(s -> assertThat(s, is(equalTo(summary))));
    }

    @Test
    void getBudgetSummary_GivenMonthGreaterThanTwelve_ThenThrowIllegalArgumentException() {
        assertThrows(DateTimeException.class,
                () -> budgetService.getBudgetSummary(SecurityTestUtil.getTestJwt(), 1, 13));
    }

    private Budget getBudgetWithCustomCategory(String categoryName, int categoryId, String budgetname, int amount, int budgetId) {
        BudgetCategory incomeCat = createBudgetCategory("TestBudgetCategoryType", 1, categoryName, 2, categoryId);
        Budget budget = createBudget(budgetname, ZonedDateTime.now(), null, amount, budgetId);
        budget.setBudgetCategory(incomeCat);
        return budget;
    }

    private FrequencyType createFrequencyType(String testFrequency, int id) {
        FrequencyType frequencyType = new FrequencyType();
        frequencyType.setFrequencyTypeName(testFrequency);
        frequencyType.setId(id);
        return frequencyType;
    }

    private BudgetCategory createBudgetCategory(String testType, int typeId, String testCatName, int nameId, int categoryId) {
        BudgetCategoryType budgetCategoryType = new BudgetCategoryType(typeId, testType, 1);

        BudgetCategoryName budgetCategoryName = new BudgetCategoryName(nameId, testCatName, 1);

        BudgetCategory budgetCategory = new BudgetCategory();
        budgetCategory.setType(budgetCategoryType);
        budgetCategory.setName(budgetCategoryName);
        budgetCategory.setId(categoryId);
        return budgetCategory;
    }


    private BudgetDto getBudgetDto() {
        BudgetCategoryDto budgetCategoryDto = new BudgetCategoryDto(5, "TestType", "TestCatName");
        return new BudgetDto(1L, "TestName", budgetCategoryDto, ZonedDateTime.now(),
                ZonedDateTime.now().plusDays(6), 10, "TestFrequency", BigDecimal.valueOf(54.21), false);
    }

    private List<BudgetCategory> createBudgetCategories() {

        BudgetCategory budgetCategory = getBudgetCategory("TestBudgetCategoryType", 1);

        return Collections.singletonList(budgetCategory);

    }

    private BudgetCategory getBudgetCategory(String type, int typeId) {
        BudgetCategory budgetCategory = createBudgetCategory(type, typeId, "TestBudgetCategoryName", 2, 3);

        Set<BudgetItem> budgetItems = new HashSet<>();
        budgetItems.add(new BudgetItem(4L, budgetCategory, "TestBudgetItemName"));

        budgetCategory.setBudgetItems(budgetItems);
        return budgetCategory;
    }

    private Budget createBudget(String name, ZonedDateTime startDate, ZonedDateTime endDate, double amount, long id) {
        BudgetCategory budgetCategory = createBudgetCategory("TestBudgetCategoryType", 1, "TestBudgetCategoryName", 2, 1);

        BudgetItem itemOne = new BudgetItem(6L, budgetCategory, "Item Name");

        BudgetItem itemTwo = new BudgetItem(7L, budgetCategory, "Item Name Two");

        itemOne.setBudgetCategory(budgetCategory);

        budgetCategory.getBudgetItems().add(itemOne);
        budgetCategory.getBudgetItems().add(itemTwo);

        FrequencyType frequencyType = createFrequencyType("TestFrequencyTypeName", 3);

        return new Budget(
                id, budgetCategory, name, startDate,
                endDate, frequencyType, BigDecimal.valueOf(amount), true, 1, null);
    }

}