package com.protean.moneymaker.rin.bootstrap;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

@Component
@Profile({"test"})
public class LoadTestDataTest implements ApplicationListener<ContextRefreshedEvent> {

    private DataSource dataSource;

    public LoadTestDataTest(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        loadInitialStartData(dataSource);
        loadCompleteBudgets(dataSource);
//        loadInitialBudgets(dataSource);
        loadTestAccountData(dataSource);
        loadTestRecurringTransactionData(dataSource);
        loadTestTransactionData(dataSource);
    }

    public static void loadInitialStartData(DataSource dataSource) {
        loadSql(dataSource, "sql/initial_data.sql");
    }

    public static void loadInitialBudgets(DataSource dataSource) {
        loadSql(dataSource, "sql/initial_budgets.sql");
    }

    public static void loadTestAccountData(DataSource dataSource) {
        loadSql(dataSource, "sql/test_accounts.sql");
    }

    public static void loadTestRecurringTransactionData(DataSource dataSource) {
        loadSql(dataSource, "sql/test_recurring_transactions.sql");
    }

    public static void loadTestTransactionData(DataSource dataSource) {
        loadSql(dataSource, "sql/test_transactions.sql");
    }

    public static void loadCompleteBudgets(DataSource dataSource) {
        loadSql(dataSource, "sql/complete_budgets.sql");
    }

    private static void loadSql(DataSource dataSource, String s) {
        Resource resource = new ClassPathResource(s);
        ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator(resource);
        databasePopulator.execute(dataSource);
    }

}
