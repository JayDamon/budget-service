package com.protean.moneymaker.rin.integration.repository;

import com.protean.moneymaker.rin.dto.TransactionBudgetSummary;
import com.protean.moneymaker.rin.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

@DataJpaTest
@ActiveProfiles({"test"})
@TestPropertySource(properties = {
        "spring.jpa.hibernate.ddl-auto=create-drop"
})
@Sql({
        "classpath:sql/initial_data.sql", "classpath:sql/complete_budgets.sql",
        "classpath:sql/test_accounts.sql", "classpath:sql/test_recurring_transactions.sql",
        "classpath:sql/test_transactions.sql"
})
class TransactionRepositoryIntegrationTest {

    @Autowired private TransactionRepository transactionRepository;

    @Test
    void getBudgetSummaries() {
        TransactionBudgetSummary summary = transactionRepository.getBudgetSummaries(2017, 1, 1, 1).get();

        assertThat(summary.getActual(), is(greaterThan(BigDecimal.ZERO)));

        System.out.println(summary);
    }

}
