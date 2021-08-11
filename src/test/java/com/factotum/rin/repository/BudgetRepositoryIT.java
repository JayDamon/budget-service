package com.factotum.rin.repository;

import com.factotum.rin.dto.BudgetCategoryInUse;
import com.factotum.rin.dto.BudgetSummary;
import com.factotum.rin.enumeration.BudgetType;
import com.factotum.rin.model.BudgetCategoryType;
import com.factotum.rin.util.SecurityTestUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;

@SpringBootTest
@ActiveProfiles({"test"})
class BudgetRepositoryIT {

    @Autowired
    private BudgetRepository budgetRepository;

    @Test
    void getAllBudgetCategoryInUse_GivenBudgetsExist_ThenReturnBudgetCategoriesInUse() {

        Set<BudgetCategoryInUse> inUseCats = budgetRepository.getAllBudgetCategoryInUse();

        assertThat(inUseCats, hasSize(10));
    }

    @Test
    void getAllBudgetCategoryTypes_GivenBudgetsExist_ThenReturnBudgetCategoryTypes() {

        Set<BudgetCategoryType> inUseCats = budgetRepository.getAllBudgetCategoryTypesInUse();

        assertThat(inUseCats, hasSize(3));
    }

    @Test
    void getBudgetSummary_GivenBudgetsExist_TenReturnBudgetSummaries() {

        ZonedDateTime startDate = ZonedDateTime.of(2017, 1, 1, 0, 0, 0, 0, ZoneId.systemDefault());
        ZonedDateTime endDate = ZonedDateTime.of(2017, 1, 31, 0, 0, 0, 0, ZoneId.systemDefault());

        List<BudgetSummary> summaries = budgetRepository.getBudgetSummaries(
                startDate,
                endDate,
                SecurityTestUtil.getTestJwt().getClaimAsString("sub"));

        assertThat(summaries, hasSize(5));

        int summariesChecked = 0;

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

}
