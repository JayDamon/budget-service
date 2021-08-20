package com.factotum.rin.service;

import com.factotum.rin.dto.BudgetCategoryDto;
import com.factotum.rin.dto.BudgetItemDto;
import com.factotum.rin.dto.BudgetSummary;
import com.factotum.rin.enumeration.BudgetType;
import com.factotum.rin.model.Budget;
import com.factotum.rin.model.BudgetCategory;
import com.factotum.rin.model.BudgetItem;
import com.factotum.rin.util.SecurityTestUtil;
import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyIterable;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

@Transactional
@SpringBootTest
@ActiveProfiles("test")
class BudgetServiceImplIT {

    @Autowired
    private BudgetService budgetService;

    @Test
    void getAllActiveBudgets() {
        List<Budget> activeBudgets = budgetService.getAllActiveBudgets(SecurityTestUtil.getTestJwt());
        assertThat(activeBudgets, is(not(nullValue())));
        assertThat(activeBudgets, hasSize(30));
        int found = 0;
        for (Budget budget : activeBudgets) {
            if (budget.getInUse()) {
                found++;
            }
        }
        assertThat(found, is(equalTo(30)));
    }

    @Test
    void getAllBudgetSummaries_ReturnBudgetSummaries() {

        ZonedDateTime startDate = ZonedDateTime.of(2017, 1, 1, 0, 0, 0, 0, ZoneId.systemDefault());
        ZonedDateTime endDate = startDate.withDayOfMonth(startDate.plusMonths(1).minusDays(1).getDayOfMonth());

        List<BudgetSummary> summaries = budgetService.getBudgetSummaries(SecurityTestUtil.getTestJwt(), startDate, endDate);

        assertThat(summaries, IsCollectionWithSize.hasSize(5));

        int summariesChecked = 0;

        System.out.println(summaries);
        for (BudgetSummary summary : summaries) {
            int categoryId = summary.getCategoryId();
            BudgetType budgetType = summary.getBudgetType();
            if (categoryId == 3 && BudgetType.EXPENSE.equals(budgetType)) {
                assertThat(summary.getPlanned(), is(equalTo(BigDecimal.valueOf(1674.99999967))));
                summariesChecked++;
            } else if (categoryId == 1 && BudgetType.INCOME.equals(budgetType)) {
                assertThat(summary.getPlanned(), is(equalTo(BigDecimal.valueOf(3000.0))));
                summariesChecked++;
            } else if (categoryId == 1 && BudgetType.EXPENSE.equals(budgetType)) {
                assertThat(summary.getPlanned(), is(equalTo(BigDecimal.valueOf(2127.5699999999997))));
                summariesChecked++;
            } else if (categoryId == 2 && BudgetType.INCOME.equals(budgetType)) {
                assertThat(summary.getPlanned(), is(equalTo(BigDecimal.valueOf(0.0))));
                summariesChecked++;
            } else if (categoryId == 2 && BudgetType.EXPENSE.equals(budgetType)) {
                assertThat(summary.getPlanned(), is(equalTo(BigDecimal.valueOf(350.0))));
                summariesChecked++;
            }
        }

        assertThat(summariesChecked, is(equalTo(5)));
    }

    @Test
    void getAllBudgetCategories() {

        List<BudgetCategory> budgetCategories = budgetService.getAllBudgetCategories();

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
