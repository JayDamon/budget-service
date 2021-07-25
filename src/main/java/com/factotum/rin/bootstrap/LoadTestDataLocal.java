package com.factotum.rin.bootstrap;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

@Component
@Profile({"localmysql"})
public class LoadTestDataLocal implements ApplicationListener<ContextRefreshedEvent> {

    private final DataSource dataSource;

    public LoadTestDataLocal(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    @Transactional
    public void onApplicationEvent(@NonNull ContextRefreshedEvent event) {
//        loadInitialStartData(dataSource);
//        loadCompleteBudgets(dataSource);
//        loadInitialBudgets(dataSource);
//        loadTestRecurringTransactionData(dataSource);
    }

    public static void loadInitialStartData(DataSource dataSource) {
        loadSql(dataSource, "test_data/initial_data.sql");
    }

    public static void loadInitialBudgets(DataSource dataSource) {
        loadSql(dataSource, "test_data/initial_budgets.sql");
    }

    public static void loadTestRecurringTransactionData(DataSource dataSource) {
        loadSql(dataSource, "test_data/V1_1__add_test_recurrin_transactions.sql");
    }

    public static void loadCompleteBudgets(DataSource dataSource) {
        loadSql(dataSource, "test_data/V1_2__add_test_budgets.sql");
    }

    private static void loadSql(DataSource dataSource, String s) {
        Resource resource = new ClassPathResource(s);
        ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator(resource);
        databasePopulator.execute(dataSource);
    }

}
