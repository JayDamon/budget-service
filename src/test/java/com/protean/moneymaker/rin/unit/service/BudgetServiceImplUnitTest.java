package com.protean.moneymaker.rin.unit.service;

import com.protean.moneymaker.rin.dto.BudgetCategoryDto;
import com.protean.moneymaker.rin.dto.BudgetItemDto;
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

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BudgetServiceImplUnitTest {

    @Mock private BudgetCategoryRepository budgetCategoryRepository;

    private BudgetService budgetService;

    @BeforeEach
    void setUp() {
        budgetService = new BudgetServiceImpl(mock(BudgetRepository.class), mock(BudgetSubCategoryRepository.class), budgetCategoryRepository);
    }

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
        assertThat(budgetCategories, hasSize(1));
        int budgetCategoriesChecked = 0;
        for (BudgetCategoryDto budgetCategory : budgetCategories) {
            assertThat(budgetCategory.getId(), is(equalTo(3)));
            assertThat(budgetCategory.getTypeName(), is(equalTo("TestBudgetCategoryType")));
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
        }
        assertThat(budgetCategoriesChecked, is(equalTo(1)));

    }

    private List<BudgetCategory> createBudgetCategories() {

        BudgetCategoryType budgetCategoryType = new BudgetCategoryType("TestBudgetCategoryType");
        budgetCategoryType.setId(1);

        BudgetCategoryName budgetCategoryName = new BudgetCategoryName("TestBudgetCategoryName");
        budgetCategoryName.setId(2);
        BudgetCategory budgetCategory = new BudgetCategory(budgetCategoryType, budgetCategoryName);
        budgetCategory.setId(3);

        Set<BudgetItem> budgetItems = new HashSet<>();
        BudgetItem budgetItem = new BudgetItem(budgetCategory, "TestBudgetItemName");
        budgetItem.setId(4L);
        budgetItems.add(budgetItem);

        budgetCategory.setBudgetItems(budgetItems);

        return Collections.singletonList(budgetCategory);


    }

}
