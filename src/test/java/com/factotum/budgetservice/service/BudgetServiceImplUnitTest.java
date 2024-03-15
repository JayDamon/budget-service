package com.factotum.budgetservice.service;

import com.factotum.budgetservice.dto.BudgetCategoryDto;
import com.factotum.budgetservice.dto.BudgetDto;
import com.factotum.budgetservice.dto.BudgetItemDto;
import com.factotum.budgetservice.dto.BudgetSummary;
import com.factotum.budgetservice.dto.BudgetTypeDto;
import com.factotum.budgetservice.dto.TransactionBudgetSummary;
import com.factotum.budgetservice.dto.TransactionTotal;
import com.factotum.budgetservice.enumeration.BudgetType;
import com.factotum.budgetservice.http.TransactionService;
import com.factotum.budgetservice.model.Budget;
import com.factotum.budgetservice.model.BudgetCategory;
import com.factotum.budgetservice.model.BudgetCategoryName;
import com.factotum.budgetservice.model.BudgetCategoryType;
import com.factotum.budgetservice.model.BudgetItem;
import com.factotum.budgetservice.model.FrequencyType;
import com.factotum.budgetservice.repository.BudgetCategoryRepository;
import com.factotum.budgetservice.repository.BudgetRepository;
import com.factotum.budgetservice.repository.FrequencyTypeRepository;
import com.factotum.budgetservice.util.SecurityTestUtil;
import jakarta.persistence.NoResultException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.oneOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

    private static final UUID budgetId = UUID.randomUUID();
    private static final UUID budgetCategoryId = UUID.randomUUID();
    private static final UUID budgetCategoryName = UUID.randomUUID();
    private static final UUID budgetItemId = UUID.randomUUID();
    private static final UUID budgetItemIdTwo = UUID.randomUUID();
    private static final UUID frequencyTypeId = UUID.randomUUID();

    private static final UUID budgetCategoryTypeIdOne = UUID.randomUUID();
    private static final UUID budgetCategoryTypeIdTwo = UUID.randomUUID();
    private static final UUID budgetCategoryTypeIdThree = UUID.randomUUID();
    private static final UUID frequencyTypeIdTwo = UUID.randomUUID();

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
            assertThat(budgetCategory.getId(), is(equalTo(budgetCategoryId)));
            assertThat(budgetCategory.getType().getId(), is(equalTo(budgetCategoryTypeIdOne)));
            assertThat(budgetCategory.getType().getName(), is(equalTo("TestBudgetCategoryType")));
            assertThat(budgetCategory.getName().getId(), is(equalTo(budgetCategoryName)));
            assertThat(budgetCategory.getName().getName(), is(equalTo("TestBudgetCategoryName")));
            assertThat(budgetCategory.getBudgetItems(), hasSize(1));
            int budgetItemsChecked = 0;
            for (BudgetItem item : budgetCategory.getBudgetItems()) {
                assertThat(item.getId(), is(equalTo(budgetItemId)));
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
        assertThat(budgetCategory.getId(), is(equalTo(budgetCategoryId)));
        assertThat(budgetCategory.getName(), is(equalTo("TestBudgetCategoryName")));
        assertThat(budgetCategory.getBudgetItems(), hasSize(1));
        int budgetItemsChecked = 0;
        for (BudgetItemDto item : budgetCategory.getBudgetItems()) {
            assertThat(item.getId(), is(equalTo(budgetItemId)));
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
        budgetCategories.add(getBudgetCategory("Type1", budgetCategoryTypeIdOne));
        budgetCategories.add(getBudgetCategory("Type2", budgetCategoryTypeIdTwo));
        budgetCategories.add(getBudgetCategory("Type3", budgetCategoryTypeIdThree));
        when(budgetCategoryRepository.findAllByOrderByTypeOrderAndNameOrder()).thenReturn(budgetCategories);

        // Act
        List<BudgetTypeDto> budgetTypes = budgetService.getAllBudgetCategoriesByType();

        // Assert
        assertThat(budgetTypes, is(not(nullValue())));
        assertThat(budgetTypes, hasSize(3));
        int budgetTypesChecked = 0;
        for (BudgetTypeDto type : budgetTypes) {

            assertThat(type.getId(), is(oneOf(budgetCategoryTypeIdOne, budgetCategoryTypeIdTwo, budgetCategoryTypeIdThree)));
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
            for (Budget b : budgets) {
                b.setId(UUID.randomUUID());
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
                35.02, budgetId);

        when(budgetRepository.findByIdAndTenantId(eq(budgetId), any())).thenReturn(Optional.of(currentBudget));
        when(budgetRepository.save(any())).thenAnswer(i -> i.getArguments()[0]);

        BudgetCategory budgetCategory = createBudgetCategory("TestType", UUID.fromString("b3c16c07-8c73-427b-94d3-b6aa2ec56c65"), "TestCatName", UUID.fromString("3369b402-312e-4d64-8776-30824b3d1e21"), budgetCategoryId);
        FrequencyType frequencyType = createFrequencyType("TestFrequency", frequencyTypeId);


        when(budgetCategoryRepository.findById(eq(budgetCategoryId))).thenReturn(Optional.of(budgetCategory));
        when(frequencyTypeRepository.findById(eq(frequencyTypeId))).thenReturn(Optional.of(frequencyType));

        BudgetDto budgetDto = getBudgetDto();

        // Act
        Budget budget = budgetService.updateBudget(SecurityTestUtil.getTestJwt(), budgetDto);

        // Assert
        assertThat(budget.getId(), is(equalTo(budgetId)));
        assertThat(budget.getName(), is(equalTo("TestName")));
        assertThat(budget.getStartDate().getDayOfMonth(), is(equalTo(LocalDate.now().getDayOfMonth())));
        assertThat(budget.getEndDate().getDayOfMonth(), is(equalTo(ZonedDateTime.now().plusDays(6).getDayOfMonth())));
        assertThat(budget.getFrequencyType().getId(), is(equalTo(frequencyTypeId)));
        assertThat(budget.getFrequencyType().getFrequencyTypeName(), is(equalTo("TestFrequency")));
        assertThat(budget.getAmount(), is(equalTo(BigDecimal.valueOf(54.21))));
        assertThat(budget.getInUse(), is(false));

        BudgetCategory cat = budget.getBudgetCategory();
        assertThat(cat.getId(), is(equalTo(budgetCategoryId)));
        assertThat(cat.getType().getName(), is(equalTo("TestType")));
        assertThat(cat.getName().getName(), is(equalTo("TestCatName")));

    }

    @Test
    void updateBudget_GivenDtoWithOnlyPartialValuesProvided_ThenUpdateOnlyValuesProvided() {

        // Arrange
        Budget currentBudget = createBudget(
                "A Budget Name", ZonedDateTime.now(), ZonedDateTime.now().plusDays(5),
                35.02, budgetId);

        when(budgetRepository.findByIdAndTenantId(eq(budgetId), any())).thenReturn(Optional.of(currentBudget));
        when(budgetRepository.save(any())).thenAnswer(i -> i.getArguments()[0]);

        BudgetCategory budgetCategory = createBudgetCategory("TestType", frequencyTypeId, "TestCatName", budgetCategoryName, budgetCategoryId);

        when(budgetCategoryRepository.findById(eq(budgetCategoryId))).thenReturn(Optional.of(budgetCategory));

        BudgetDto budgetDto = getBudgetDto();
        budgetDto.setFrequencyTypeId(null);
        budgetDto.setFrequencyTypeName(null);
        budgetDto.setAmount(null);

        // Act
        Budget budget = budgetService.updateBudget(SecurityTestUtil.getTestJwt(), budgetDto);

        // Assert
        verify(frequencyTypeRepository, times(0)).findById(any());

        assertThat(budget.getId(), is(equalTo(budgetId)));
        assertThat(budget.getName(), is(equalTo("TestName")));
        assertThat(budget.getStartDate().getDayOfMonth(), is(equalTo(LocalDate.now().getDayOfMonth())));
        assertThat(budget.getEndDate().getDayOfMonth(), is(equalTo(ZonedDateTime.now().plusDays(6).getDayOfMonth())));
        assertThat(budget.getFrequencyType().getId(), is(equalTo(frequencyTypeIdTwo)));
        assertThat(budget.getFrequencyType().getFrequencyTypeName(), is(equalTo("TestFrequencyTypeName")));
        assertThat(budget.getAmount(), is(equalTo(BigDecimal.valueOf(35.02))));
        assertThat(budget.getInUse(), is(false));

        BudgetCategory cat = budget.getBudgetCategory();
        assertThat(cat.getId(), is(equalTo(budgetCategoryId)));
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

        when(budgetRepository.findByIdAndTenantId(any(UUID.class), any())).thenReturn(Optional.empty());

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
                                        UUID.fromString("5c07c147-1aab-472b-9c8f-e500d3161210"),
                                        BudgetType.INCOME,
                                        BigDecimal.valueOf(50.02))));

        when(budgetRepository.queryAllBudgetIdsForSummary(any(), any(UUID.class), any(), any(), anyString()))
                .thenReturn(new HashSet<>(Collections.singletonList(budgetId)));

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

    private FrequencyType createFrequencyType(String testFrequency, UUID id) {
        FrequencyType frequencyType = new FrequencyType();
        frequencyType.setFrequencyTypeName(testFrequency);
        frequencyType.setId(id);
        return frequencyType;
    }

    private BudgetCategory createBudgetCategory(String testType, UUID typeId, String testCatName, UUID nameId, UUID categoryId) {
        BudgetCategoryType budgetCategoryType = new BudgetCategoryType(typeId, testType, 1);

        BudgetCategoryName budgetCategoryName = new BudgetCategoryName(nameId, testCatName, 1);

        BudgetCategory budgetCategory = new BudgetCategory();
        budgetCategory.setType(budgetCategoryType);
        budgetCategory.setName(budgetCategoryName);
        budgetCategory.setId(categoryId);
        return budgetCategory;
    }


    private BudgetDto getBudgetDto() {
        BudgetCategoryDto budgetCategoryDto = new BudgetCategoryDto(budgetCategoryId, "TestType", "TestCatName");
        return new BudgetDto(budgetId, "TestName", budgetCategoryDto, ZonedDateTime.now(),
                ZonedDateTime.now().plusDays(6), frequencyTypeId, "TestFrequency", BigDecimal.valueOf(54.21), false);
    }

    private List<BudgetCategory> createBudgetCategories() {

        BudgetCategory budgetCategory = getBudgetCategory("TestBudgetCategoryType", budgetCategoryTypeIdOne);

        return Collections.singletonList(budgetCategory);

    }

    private BudgetCategory getBudgetCategory(String type, UUID typeId) {
        BudgetCategory budgetCategory = createBudgetCategory(type, typeId, "TestBudgetCategoryName", budgetCategoryName, budgetCategoryId);

        Set<BudgetItem> budgetItems = new HashSet<>();
        budgetItems.add(new BudgetItem(budgetItemId, budgetCategory, "TestBudgetItemName"));

        budgetCategory.setBudgetItems(budgetItems);
        return budgetCategory;
    }

    private Budget createBudget(String name, ZonedDateTime startDate, ZonedDateTime endDate, double amount, UUID id) {
        BudgetCategory budgetCategory = createBudgetCategory("TestBudgetCategoryType", budgetCategoryTypeIdOne, "TestBudgetCategoryName", budgetCategoryName, budgetCategoryId);

        BudgetItem itemOne = new BudgetItem(budgetItemId, budgetCategory, "Item Name");

        BudgetItem itemTwo = new BudgetItem(UUID.fromString("09178354-e5c0-469a-a1f8-a7821d51bfdc"), budgetCategory, "Item Name Two");

        itemOne.setBudgetCategory(budgetCategory);

        budgetCategory.getBudgetItems().add(itemOne);
        budgetCategory.getBudgetItems().add(itemTwo);

        FrequencyType frequencyType = createFrequencyType("TestFrequencyTypeName", frequencyTypeIdTwo);

        return new Budget(
                id, budgetCategory, name, startDate,
                endDate, frequencyType, BigDecimal.valueOf(amount), true, null);
    }

}
