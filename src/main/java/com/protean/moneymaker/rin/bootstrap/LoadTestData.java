package com.protean.moneymaker.rin.bootstrap;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

@Component
@Profile("test")
public class LoadTestData implements ApplicationListener<ContextRefreshedEvent> {

    private DataSource dataSource;

    public LoadTestData(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        loadInitialStartData();
    }

//    public void loadInitialBills() {
//        Resource resource = new ClassPathResource("initialbilldata.sql");
//        ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator(resource);
//        databasePopulator.execute(dataSource);
//    }
//
//    public void loadInitialSessions() {
//        Resource resource = new ClassPathResource("initialsessiondata.sql");
//        ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator(resource);
//        databasePopulator.execute(dataSource);
//    }

    public void loadInitialStartData() {
        Resource resource = new ClassPathResource("sql/initial_data.sql");
        ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator(resource);
        databasePopulator.execute(dataSource);
    }

}
