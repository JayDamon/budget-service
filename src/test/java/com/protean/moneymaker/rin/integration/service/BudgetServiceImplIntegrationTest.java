package com.protean.moneymaker.rin.integration.service;

import com.protean.moneymaker.rin.bootstrap.LoadTestData;
import com.protean.moneymaker.rin.model.Budget;
import com.protean.moneymaker.rin.service.BudgetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import javax.sql.DataSource;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.is;

@SpringBootTest
@ActiveProfiles("test")
class BudgetServiceImplIntegrationTest {

    @Autowired
    BudgetService budgetService;
    @Autowired DataSource dataSource;

    @Test
    void getAllActiveBudgets() {
        List<Budget> activeBudgets = budgetService.getAllActiveBudgets();
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
        List<Budget> inactiveBudgets = budgetService.getAllInactiveBudgets();
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

}