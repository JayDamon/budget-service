package com.factotum.budgetservice.service;

import com.factotum.budgetservice.dto.BudgetCategoryDto;
import com.factotum.budgetservice.dto.BudgetItemDto;
import com.factotum.budgetservice.dto.BudgetSummary;
import com.factotum.budgetservice.enumeration.BudgetType;
import com.factotum.budgetservice.model.Budget;
import com.factotum.budgetservice.model.BudgetCategory;
import com.factotum.budgetservice.model.BudgetItem;
import com.factotum.budgetservice.util.SecurityTestUtil;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@Transactional
@SpringBootTest
@AutoConfigureEmbeddedDatabase
@ActiveProfiles("test")
class BudgetServiceImplIntegrationTest {

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

        UUID budgetCategoryIdOne = UUID.fromString("0121056f-a732-4d9d-a554-06223969f0cc");
        UUID budgetCategoryIdTwo = UUID.fromString("b3c16c07-8c73-427b-94d3-b6aa2ec56c65");
        UUID budgetCategoryIdThree = UUID.fromString("06aa4213-b898-4dae-8c5b-da5b07a47363");

        System.out.println(summaries);
        for (BudgetSummary summary : summaries) {
            UUID categoryId = summary.getCategoryId();
            BudgetType budgetType = summary.getBudgetType();
            if (categoryId.equals(budgetCategoryIdThree) && BudgetType.EXPENSE.equals(budgetType)) {
                assertThat(summary.getPlanned().doubleValue(), is(closeTo(1674.9999996700, 6)));
                summariesChecked++;
            } else if (categoryId.equals(budgetCategoryIdOne) && BudgetType.INCOME.equals(budgetType)) {
                assertThat(summary.getPlanned().doubleValue(), is(closeTo(3000.0, 6)));
                summariesChecked++;
            } else if (categoryId.equals(budgetCategoryIdOne) && BudgetType.EXPENSE.equals(budgetType)) {
                assertThat(summary.getPlanned().doubleValue(), is(closeTo(2127.5699999999997, 6)));
                summariesChecked++;
            } else if (categoryId.equals(budgetCategoryIdTwo) && BudgetType.INCOME.equals(budgetType)) {
                assertThat(summary.getPlanned().doubleValue(), is(closeTo(0.0, 6)));
                summariesChecked++;
            } else if (categoryId.equals(budgetCategoryIdTwo) && BudgetType.EXPENSE.equals(budgetType)) {
                assertThat(summary.getPlanned().doubleValue(), is(closeTo(350.0, 6)));
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
            assertThat(budgetCategory.getId(), is(notNullValue()));
            assertThat(budgetCategory.getType().getId(), is(notNullValue()));
            assertThat(budgetCategory.getType().getName(), is(not(nullValue())));
            assertThat(budgetCategory.getName().getId(), is(notNullValue()));
            assertThat(budgetCategory.getName().getName(), is(not(nullValue())));
            assertThat(budgetCategory.getBudgetItems(), is(not(emptyIterable())));
            for (BudgetItem item : budgetCategory.getBudgetItems()) {
                assertThat(item.getId(), is(notNullValue()));
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
            assertThat(budgetCategory.getId(), is(notNullValue()));
            assertThat(budgetCategory.getTypeName(), is(not(nullValue())));
            assertThat(budgetCategory.getName(), is(not(nullValue())));
            assertThat(budgetCategory.getBudgetItems(), is(not(emptyIterable())));
            for (BudgetItemDto budgetItem : budgetCategory.getBudgetItems()) {
                assertThat(budgetItem.getId(), is(notNullValue()));
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
