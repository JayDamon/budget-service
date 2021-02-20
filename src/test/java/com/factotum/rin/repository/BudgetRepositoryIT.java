package com.factotum.rin.repository;

import com.factotum.rin.dto.BudgetCategoryInUse;
import com.factotum.rin.dto.BudgetSummary;
import com.factotum.rin.model.BudgetCategoryType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

@DataJpaTest
@ActiveProfiles({"test"})
@TestPropertySource(properties = {
        "spring.jpa.hibernate.ddl-auto=create-drop"
})
@Sql({"classpath:sql/initial_data.sql", "classpath:sql/complete_budgets.sql"})
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

        List<BudgetSummary> summaries = budgetRepository.getBudgetSummaries(startDate, endDate);

        assertThat(summaries, hasSize(5));

        System.out.println(summaries);
    }

}
