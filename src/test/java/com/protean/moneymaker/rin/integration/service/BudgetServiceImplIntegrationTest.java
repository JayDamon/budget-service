package com.protean.moneymaker.rin.integration.service;

import com.protean.moneymaker.rin.dto.BudgetCategoryDto;
import com.protean.moneymaker.rin.dto.BudgetItemDto;
import com.protean.moneymaker.rin.model.Budget;
import com.protean.moneymaker.rin.model.BudgetCategory;
import com.protean.moneymaker.rin.model.BudgetItem;
import com.protean.moneymaker.rin.service.BudgetService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyIterable;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.is;

@Transactional
@SpringBootTest
@ActiveProfiles("test")
class BudgetServiceImplIntegrationTest {

    @Autowired private BudgetService budgetService;
    @Autowired private DataSource dataSource;

    @Test
    void getAllActiveBudgets() {
        Set<Budget> activeBudgets = budgetService.getAllActiveBudgets();
        assertThat(activeBudgets, is(not(nullValue())));
        assertThat(activeBudgets, hasSize(30));
        int found = 0;
        for (Budget budget : activeBudgets) {
            if (budget.isInUse()) {
                found++;
            }
        }
        assertThat(found, is(equalTo(30)));
    }

    @Test
    void getAllInactiveBudgets() {
        Set<Budget> inactiveBudgets = budgetService.getAllInactiveBudgets();
        assertThat(inactiveBudgets, is(not(nullValue())));
        assertThat(inactiveBudgets, hasSize(2));
        int found = 0;
        for (Budget budget : inactiveBudgets) {
            if (!budget.isInUse()) {
                found++;
            }
        }
        assertThat(found, is((equalTo(2))));
    }

    @Test
    void getAllBudgetCategories() {

        Set<BudgetCategory> budgetCategories = budgetService.getAllBudgetCategories();

        assertThat(budgetCategories, hasSize(10));
        int budgetCategoriesChecked = 0;
        int budgetItemsChecked = 0;
        for (BudgetCategory budgetCategory : budgetCategories) {
            assertThat(budgetCategory.getId(), is(greaterThan(0)));
            assertThat(budgetCategory.getType().getId(), is(greaterThan(0)));
            assertThat(budgetCategory.getType().getName(), is(not(nullValue())));
            assertThat(budgetCategory.getName().getId(), is(greaterThan(0)));
            assertThat(budgetCategory.getName().getName(), is(not(nullValue())));
            assertThat(budgetCategory.getBudgetItems(), is(not(emptyIterable())));
            for (BudgetItem item : budgetCategory.getBudgetItems()) {
                assertThat(item.getId(), is(greaterThan(0L)));
                assertThat(item.getName(), is(not(nullValue())));
                assertThat(item.getBudgetCategory(), is(not(nullValue())));
                budgetItemsChecked++;
            }
            budgetCategoriesChecked++;
        }
        assertThat(budgetItemsChecked, is(equalTo(37)));
        assertThat(budgetCategoriesChecked, is(equalTo(10)));
    }

    @Test
    void getAllBudgetCategoryDtos() {

        Set<BudgetCategoryDto> budgetCategories = budgetService.getAllBudgetCategoryDtos();

        assertThat(budgetCategories, hasSize(10));
        int budgetCategoriesChecked = 0;
        int budgetItemsChecked = 0;
        for (BudgetCategoryDto budgetCategory : budgetCategories) {
            assertThat(budgetCategory.getId(), is(greaterThan(0)));
            assertThat(budgetCategory.getTypeName(), is(not(nullValue())));
            assertThat(budgetCategory.getName(), is(not(nullValue())));
            assertThat(budgetCategory.getBudgetItems(), is(not(emptyIterable())));
            for (BudgetItemDto budgetItem : budgetCategory.getBudgetItems()) {
                assertThat(budgetItem.getId(), is(greaterThan(0)));
                assertThat(budgetItem.getCategoryName(), is(not(nullValue())));
                assertThat(budgetItem.getName(), is(not(nullValue())));
                budgetItemsChecked++;
            }
            budgetCategoriesChecked++;
        }
        assertThat(budgetItemsChecked, is(equalTo(37)));
        assertThat(budgetCategoriesChecked, is(equalTo(10)));
    }


}