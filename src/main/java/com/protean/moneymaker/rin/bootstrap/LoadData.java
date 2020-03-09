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
@Profile({"mysql"})
public class LoadData implements ApplicationListener<ContextRefreshedEvent> {

    private DataSource dataSource;

    public LoadData(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        loadInitialStartData(dataSource);
    }

    public static void loadInitialStartData(DataSource dataSource) {
        loadSql(dataSource, "sql/initial_data.sql");
    }

    private static void loadSql(DataSource dataSource, String s) {
        Resource resource = new ClassPathResource(s);
        ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator(resource);
        databasePopulator.execute(dataSource);
    }

}

