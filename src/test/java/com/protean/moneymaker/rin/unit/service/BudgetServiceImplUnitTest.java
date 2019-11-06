package com.protean.moneymaker.rin.unit.service;

import com.protean.moneymaker.rin.dto.BudgetCategoryDto;
import com.protean.moneymaker.rin.dto.BudgetDto;
import com.protean.moneymaker.rin.dto.BudgetItemDto;
import com.protean.moneymaker.rin.dto.BudgetTypeDto;
import com.protean.moneymaker.rin.model.Budget;
import com.protean.moneymaker.rin.model.BudgetCategory;
import com.protean.moneymaker.rin.model.BudgetCategoryName;
import com.protean.moneymaker.rin.model.BudgetCategoryType;
import com.protean.moneymaker.rin.model.BudgetItem;
import com.protean.moneymaker.rin.repository.BudgetCategoryRepository;
import com.protean.moneymaker.rin.repository.BudgetRepository;
import com.protean.moneymaker.rin.repository.BudgetSubCategoryRepository;
import com.protean.moneymaker.rin.service.BudgetService;
import com.protean.moneymaker.rin.service.BudgetServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.oneOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BudgetServiceImplUnitTest {

    @Mock private BudgetCategoryRepository budgetCategoryRepository;
    @Mock private BudgetRepository budgetRepository;

    private BudgetService budgetService;

    @BeforeEach
    void setUp() {
        budgetService = new BudgetServiceImpl(budgetRepository, mock(BudgetSubCategoryRepository.class), budgetCategoryRepository);
    }

//    getAllBudgetCategoryDtos
    @Test
    void getAllBudgetCategories_GivenValidReturn_ThenCategoriesComplete() {

        // Arrange
        when(budgetCategoryRepository.findAll()).thenReturn(createBudgetCategories());

        // Act
        Set<BudgetCategory> budgetCategories = budgetService.getAllBudgetCategories();

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
        when(budgetCategoryRepository.findAll()).thenReturn(createBudgetCategories());

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
        when(budgetCategoryRepository.findAll()).thenReturn(budgetCategories);

        // Act
        Set<BudgetTypeDto> budgetTypes = budgetService.getAllBudgetCategoriesByType();

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
        when(budgetCategoryRepository.findAll()).thenReturn(Collections.emptyList());

        // Act
        Set<BudgetTypeDto> budgetTypes = budgetService.getAllBudgetCategoriesByType();

        // Assert
        assertThat(budgetTypes, is(not(nullValue())));
        assertThat(budgetTypes, hasSize(0));
    }

//    createNewBudgets
    @Test
    @SuppressWarnings("unchecked")
    void createNewBudgets_GivenBudgetsProvided_ThenSaveAndReturnWithIds() {

        // Arrange
        BudgetCategoryDto budgetCategoryDto = new BudgetCategoryDto(5, "TestType", "TestCatName");
        BudgetDto budgetDto = new BudgetDto(1L, "TestName", budgetCategoryDto, ZonedDateTime.now(),
                ZonedDateTime.now().plusDays(6), 10, "TestFrequency", BigDecimal.valueOf(54.21), false);

        when(budgetRepository.saveAll(any())).thenAnswer(i -> {
            List<Budget> budgets = (ArrayList<Budget>)i.getArguments()[0];
            long id = 1;
            for (Budget b : budgets) {
                b.setId(id);
                id++;
            }
            return budgets;
        });

        // Act
        Set<BudgetDto> budgetDtos = budgetService.createNewBudgets(new HashSet<>(Collections.singletonList(budgetDto)));

        int i = 0;
        for (BudgetDto dto : budgetDtos) {
            assertThat(dto.getId(), is(not(nullValue())));
        }
    }

    @Test
    void createNewBudgets_GivenBudgetsIsNull_ThenThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> budgetService.createNewBudgets(null));
    }

    private List<BudgetCategory> createBudgetCategories() {

        BudgetCategory budgetCategory = getBudgetCategory("TestBudgetCategoryType", 1);

        return Collections.singletonList(budgetCategory);

    }

    private BudgetCategory getBudgetCategory(String type, int typeId) {
        BudgetCategoryType budgetCategoryType = new BudgetCategoryType(type);
        budgetCategoryType.setId(typeId);

        BudgetCategoryName budgetCategoryName = new BudgetCategoryName("TestBudgetCategoryName");
        budgetCategoryName.setId(2);
        BudgetCategory budgetCategory = new BudgetCategory(budgetCategoryType, budgetCategoryName);
        budgetCategory.setId(3);

        Set<BudgetItem> budgetItems = new HashSet<>();
        BudgetItem budgetItem = new BudgetItem(budgetCategory, "TestBudgetItemName");
        budgetItem.setId(4L);
        budgetItems.add(budgetItem);

        budgetCategory.setBudgetItems(budgetItems);
        return budgetCategory;
    }

}
