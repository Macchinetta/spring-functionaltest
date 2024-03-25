/*
 * Copyright(c) 2024 NTT Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package jp.co.ntt.fw.spring.functionaltest.config.app;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.transaction.TransactionManager;
import org.terasoluna.gfw.common.time.DefaultClockFactory;

import jakarta.inject.Inject;

/**
 * Define settings for the environment.
 */
@Configuration
public class SpringFunctionaltestEnvConfig {

    /**
     * Bean of ResourceLoader
     */
    @Inject
    private ResourceLoader resourceLoader;

    /**
     * DataSource.driverClassName property.
     */
    @Value("${database.driverClassName}")
    private String driverClassName;

    /**
     * DataSource.url property.
     */
    @Value("${database.url}")
    private String url;

    /**
     * DataSource.url.open property.
     */
    @Value("${database.url.open}")
    private String openUrl;

    /**
     * DataSource.url.open property.
     */
    @Value("${database.url.close}")
    private String closeUrl;

    /**
     * DataSource.username property.
     */
    @Value("${database.username}")
    private String username;

    /**
     * DataSource.password property.
     */
    @Value("${database.password}")
    private String password;

    /**
     * DataSource.maxTotal property.
     */
    @Value("${cp.maxActive}")
    private Integer maxActive;

    /**
     * DataSource.maxIdle property.
     */
    @Value("${cp.maxIdle}")
    private Integer maxIdle;

    /**
     * DataSource.minIdle property.
     */
    @Value("${cp.minIdle}")
    private Integer minIdle;

    /**
     * DataSource.maxWaitMillis property.
     */
    @Value("${cp.maxWait}")
    private Integer maxWait;

    /**
     * Property databaseName.
     */
    @Value("${database}")
    private String database;

    /**
     * Configure {@link DefaultClockFactory}.
     * @return Bean of configured {@link DefaultClockFactory}
     */
    @Bean("dateFactory")
    public DefaultClockFactory dateFactory() {
        return new DefaultClockFactory();
    }

    /**
     * Configure {@link DataSource} bean.
     * @return Bean of configured {@link BasicDataSource}
     */
    @Bean(name = "dataSource", destroyMethod = "close")
    public DataSource dataSource() {
        BasicDataSource bean = new BasicDataSource();
        bean.setDriverClassName(driverClassName);
        bean.setUrl(url);
        bean.setUsername(username);
        bean.setPassword(password);
        bean.setDefaultAutoCommit(false);
        bean.setMaxTotal(maxActive);
        bean.setMaxIdle(maxIdle);
        bean.setMinIdle(minIdle);
        bean.setMaxWait(Duration.ofMillis(maxWait));
        return bean;
    }

    /**
     * Configure {@link DataSource} bean.
     * @return Bean of configured {@link BasicDataSource}
     */
    @Bean(name = "dataSourceOpen", destroyMethod = "close")
    public DataSource dataSourceOpen() {
        BasicDataSource bean = new BasicDataSource();
        bean.setDriverClassName(driverClassName);
        bean.setUrl(openUrl);
        bean.setUsername(username);
        bean.setPassword(password);
        bean.setDefaultAutoCommit(false);
        bean.setMaxTotal(maxActive);
        bean.setMaxIdle(maxIdle);
        bean.setMinIdle(minIdle);
        bean.setMaxWait(Duration.ofMillis(maxWait));
        return bean;
    }

    /**
     * Configure {@link DataSource} bean.
     * @return Bean of configured {@link BasicDataSource}
     */
    @Bean(name = "dataSourceClose", destroyMethod = "close")
    public DataSource dataSourceClose() {
        BasicDataSource bean = new BasicDataSource();
        bean.setDriverClassName(driverClassName);
        bean.setUrl(closeUrl);
        bean.setUsername(username);
        bean.setPassword(password);
        bean.setDefaultAutoCommit(false);
        bean.setMaxTotal(maxActive);
        bean.setMaxIdle(maxIdle);
        bean.setMinIdle(minIdle);
        bean.setMaxWait(Duration.ofMillis(maxWait));
        return bean;
    }

    /**
     * Configuration to set up database during initialization.
     * @return Bean of configured {@link DataSourceInitializer}
     * @throws IOException
     */
    @Bean("dataSourceInitializer")
    public DataSourceInitializer dataSourceInitializer() throws IOException {
        DataSourceInitializer bean = new DataSourceInitializer();
        bean.setDataSource(dataSource());

        ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Arrays.asList(resolver.getResources("classpath*:/database/" + database + "-schema*.sql"))
                .forEach(f -> databasePopulator.addScript(f));
        Arrays.asList(resolver.getResources("classpath*:/database/" + database + "-procedure*.sql"))
                .forEach(f -> databasePopulator.addScript(f));
        Arrays.asList(resolver.getResources("classpath*:/database/" + database + "-dataload*.sql"))
                .forEach(f -> databasePopulator.addScript(f));
        databasePopulator.setSqlScriptEncoding("UTF-8");
        databasePopulator.setIgnoreFailedDrops(true);
        bean.setDatabasePopulator(databasePopulator);
        return bean;
    }

    /**
     * Configuration to set up database during initialization.
     * @return Bean of configured {@link DataSourceInitializer}
     * @throws IOException 
     */
    @Bean("dataSourceOpenInitializer")
    @DependsOn({ "dataSourceInitializer" })
    public DataSourceInitializer dataSourceOpenInitializer() throws IOException {
        DataSourceInitializer bean = new DataSourceInitializer();
        bean.setDataSource(dataSourceOpen());

        ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Arrays.asList(resolver.getResources("classpath*:/database/open/" + database + "-schema*.sql"))
                .forEach(f -> databasePopulator.addScript(f));
        Arrays.asList(resolver.getResources("classpath*:/database/open/" + database + "-procedure*.sql"))
                .forEach(f -> databasePopulator.addScript(f));
        Arrays.asList(resolver.getResources("classpath*:/database/open/" + database + "-dataload*.sql"))
                .forEach(f -> databasePopulator.addScript(f));
        databasePopulator.setSqlScriptEncoding("UTF-8");
        databasePopulator.setIgnoreFailedDrops(true);
        bean.setDatabasePopulator(databasePopulator);
        return bean;
    }

    /**
     * Configuration to set up database during initialization.
     * @return Bean of configured {@link DataSourceInitializer}
     * @throws IOException 
     */
    @Bean("dataSourceCloseInitializer")
    @DependsOn({ "dataSourceOpenInitializer" })
    public DataSourceInitializer dataSourceCloseInitializer() throws IOException {
        DataSourceInitializer bean = new DataSourceInitializer();
        bean.setDataSource(dataSourceClose());

        ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Arrays.asList(resolver.getResources("classpath*:/database/close/" + database + "-schema*.sql"))
                .forEach(f -> databasePopulator.addScript(f));
        Arrays.asList(resolver.getResources("classpath*:/database/close/" + database + "-procedure*.sql"))
                .forEach(f -> databasePopulator.addScript(f));
        Arrays.asList(resolver.getResources("classpath*:/database/close/" + database + "-dataload*.sql"))
                .forEach(f -> databasePopulator.addScript(f));
        databasePopulator.setSqlScriptEncoding("UTF-8");
        databasePopulator.setIgnoreFailedDrops(true);
        bean.setDatabasePopulator(databasePopulator);
        return bean;
    }

    /**
     * Configure {@link TransactionManager} bean.
     * @return Bean of configured {@link DataSourceTransactionManager}
     */
    @Bean("transactionManager")
    public TransactionManager transactionManager() {
        DataSourceTransactionManager bean = new DataSourceTransactionManager();
        bean.setDataSource(dataSource());
        bean.setRollbackOnCommitFailure(true);
        return bean;
    }

    /**
     * Configure {@link DataSource} bean.
     * @return Bean of configured {@link BasicDataSource}
     */
    @Bean(name = "dataSourceForLogging", destroyMethod = "close")
    public DataSource dataSourceForLogging() {
        BasicDataSource bean = new BasicDataSource();
        bean.setDriverClassName("org.h2.Driver");
        bean.setUrl("jdbc:h2:mem:spring-functionaltest;DB_CLOSE_DELAY=-1");
        bean.setUsername("sa");
        bean.setPassword("");
        bean.setDefaultAutoCommit(false);
        return bean;
    }
}
