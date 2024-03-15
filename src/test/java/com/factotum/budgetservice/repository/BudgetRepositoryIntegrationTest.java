package com.factotum.budgetservice.repository;

import com.factotum.budgetservice.dto.BudgetCategoryInUse;
import com.factotum.budgetservice.dto.BudgetSummary;
import com.factotum.budgetservice.enumeration.BudgetType;
import com.factotum.budgetservice.model.BudgetCategoryType;
import com.factotum.budgetservice.util.SecurityTestUtil;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

@SpringBootTest
@AutoConfigureEmbeddedDatabase
@ActiveProfiles({"test"})
class BudgetRepositoryIntegrationTest {

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

        String tenantId = SecurityTestUtil.getTestJwt().getClaimAsString("sub");

        List<BudgetSummary> summaries = budgetRepository.getBudgetSummaries(
                startDate,
                endDate,
                tenantId);

        assertThat(summaries, hasSize(5));

        int summariesChecked = 0;

        UUID budgetCategoryIdOne = UUID.fromString("0121056f-a732-4d9d-a554-06223969f0cc");
        UUID budgetCategoryIdTwo = UUID.fromString("b3c16c07-8c73-427b-94d3-b6aa2ec56c65");
        UUID budgetCategoryIdThree = UUID.fromString("06aa4213-b898-4dae-8c5b-da5b07a47363");

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

}
