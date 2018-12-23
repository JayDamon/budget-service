package com.protean.moneymaker.rin.service;

import com.protean.moneymaker.rin.bootstrap.LoadTestData;
import com.protean.moneymaker.rin.model.Budget;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BudgetServiceImplTest {

    @Autowired BudgetService budgetService;
    @Autowired DataSource dataSource;

    @Before
    public void setUp() {
        LoadTestData.loadInitialStartData(dataSource);
        LoadTestData.loadTestBudgetData(dataSource);
    }

    @Test
    @DirtiesContext
    public void getAllActiveBudgets() {
        List<Budget> activeBudgets = budgetService.getAllActiveBudgets();
        assertNotNull(activeBudgets);
        assertEquals(4, activeBudgets.size());
        int found = 0;
        for (Budget budget : activeBudgets) {
            if (budget.isInUse()) {
                found++;
            }
        }
        assertEquals(4, found);
    }

    @Test
    @DirtiesContext
    public void getAllInactiveBudgets() {
        List<Budget> inactiveBudgets = budgetService.getAllInactiveBudgets();
        assertNotNull(inactiveBudgets);
        assertEquals(2, inactiveBudgets.size());
        int found = 0;
        for (Budget budget : inactiveBudgets) {
            if (!budget.isInUse()) {
                found++;
            }
        }
        assertEquals(2, found);
    }

    @Test
    @DirtiesContext
    public void getAllGenericBudgets() {
        List<Budget> activeBudgets = budgetService.getAllGenericBudgets();
        assertNotNull(activeBudgets);
        assertEquals(5, activeBudgets.size());
        int found = 0;
        for (Budget budget : activeBudgets) {
            if (budget.isGeneric()) {
                found++;
            }
        }
        assertEquals(5, found);
    }

    @Test
    @DirtiesContext
    public void getAllUserDefinedBudgets() {
        List<Budget> activeBudgets = budgetService.getAllUserDefinedBudgets();
        assertNotNull(activeBudgets);
        assertEquals(1, activeBudgets.size());
        int found = 0;
        for (Budget budget : activeBudgets) {
            if (!budget.isGeneric()) {
                found++;
            }
        }
        assertEquals(1, found);
    }

}