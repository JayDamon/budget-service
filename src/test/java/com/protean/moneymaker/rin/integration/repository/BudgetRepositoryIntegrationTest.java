package com.protean.moneymaker.rin.integration.repository;

import com.protean.moneymaker.rin.dto.BudgetCategoryInUse;
import com.protean.moneymaker.rin.dto.BudgetSummary;
import com.protean.moneymaker.rin.dto.TransactionBudgetSummary;
import com.protean.moneymaker.rin.model.BudgetCategoryType;
import com.protean.moneymaker.rin.repository.BudgetRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

@DataJpaTest
@ActiveProfiles({"test"})
@TestPropertySource(properties = {
        "spring.jpa.hibernate.ddl-auto=create-drop"
})
@Sql({"classpath:sql/initial_data.sql", "classpath:sql/complete_budgets.sql"})
class BudgetRepositoryIntegrationTest {

    @Autowired private BudgetRepository budgetRepository;

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
    void getBudgetSummary_GivenBudgetsExist_ThenReturnBudgetSummary() {

        Set<TransactionBudgetSummary> summaries = new HashSet<>();

        assertThat(summaries, hasSize(4));
//        assertThat(); // TODO lets instead get this for the month and year for each budget category i.e fixed income, flexible income, fixed expense, flexible expense, discretionary expense
        // TODO in order to do this, i need to associate a transaction type (expense or income) with budget categories then the above getAllBudgetCategoryInUse can be modified to get each series and then query on each

    }

    @Test
    void getBudgetSummary_GivenBudgetsExist_TenReturnBudgetSummaries() {

        ZonedDateTime startDate = ZonedDateTime.of(2017, 1, 1, 0, 0, 0, 0, ZoneId.systemDefault());
        ZonedDateTime endDate = ZonedDateTime.of(2017, 1, 31, 0, 0, 0, 0, ZoneId.systemDefault());

        Set<BudgetSummary> summaries = budgetRepository.getBudgetSummaries(startDate, endDate);

        assertThat(summaries, hasSize(5));

        System.out.println(summaries);
    }

}
