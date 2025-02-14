/*
 * Copyright(c) 2025 NTT Corporation.
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
package jp.co.ntt.fw.spring.functionaltest.config.h2;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import jakarta.inject.Inject;
import jakarta.servlet.ServletContext;
import jp.co.ntt.fw.spring.functionaltest.database.PortChecker;

/**
 * H2DatabaseConfig class is a Spring Configuration class for setting up the H2 database. This class
 * configures the properties required for database connection and initializes the database.
 */
@Configuration
public class H2DatabaseConfig {

    @Inject
    private ServletContext context;

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
     * Property tcp.port.
     */
    @Value("${tcp.port}")
    private int tcpPort;

    /**
     * Property database.url.tcp.
     */
    @Value("${database.url.tcp}")
    private String tcpUrl;

    /**
     * Property database.url.open.tcp.
     */
    @Value("${database.url.open.tcp}")
    private String tcpOpenUrl;

    /**
     * Property database.url.close.tcp.
     */
    @Value("${database.url.close.tcp}")
    private String tcpCloseUrl;

    /**
     * Configure {@link DataSource} bean.
     * @return Bean of configured {@link BasicDataSource}
     */
    @Bean(name = "dataSource", destroyMethod = "close")
    public DataSource dataSource() {
        BasicDataSource bean = new BasicDataSource();
        bean.setDriverClassName(this.driverClassName);
        bean.setUrl(getUrl());
        bean.setUsername(this.username);
        bean.setPassword(this.password);
        bean.setDefaultAutoCommit(false);
        bean.setMaxTotal(this.maxActive);
        bean.setMaxIdle(this.maxIdle);
        bean.setMinIdle(this.minIdle);
        bean.setMaxWait(Duration.ofMillis(this.maxWait));
        return bean;
    }

    /**
     * Configure {@link DataSource} bean.
     * @return Bean of configured {@link BasicDataSource}
     */
    @Bean(name = "dataSourceDefault", destroyMethod = "close")
    public DataSource dataSourceDefault() {
        BasicDataSource bean = new BasicDataSource();
        bean.setDriverClassName(driverClassName);
        bean.setUrl(getUrl());
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
        bean.setUrl(getOpenUrl());
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
        bean.setUrl(getCloseUrl());
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

        if (isH2Initialized()) {
            ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
            PathMatchingResourcePatternResolver resolver =
                    new PathMatchingResourcePatternResolver();
            Arrays.asList(
                    resolver.getResources("classpath*:/database/" + database + "-schema*.sql"))
                    .forEach(f -> databasePopulator.addScript(f));
            Arrays.asList(
                    resolver.getResources("classpath*:/database/" + database + "-procedure*.sql"))
                    .forEach(f -> databasePopulator.addScript(f));
            Arrays.asList(
                    resolver.getResources("classpath*:/database/" + database + "-dataload*.sql"))
                    .forEach(f -> databasePopulator.addScript(f));
            databasePopulator.setSqlScriptEncoding("UTF-8");
            databasePopulator.setIgnoreFailedDrops(true);
            bean.setDatabasePopulator(databasePopulator);
        }

        return bean;
    }

    /**
     * Configuration to set up database during initialization.
     * @return Bean of configured {@link DataSourceInitializer}
     * @throws IOException
     */
    @Bean("dataSourceOpenInitializer")
    @DependsOn({"dataSourceInitializer"})
    public DataSourceInitializer dataSourceOpenInitializer() throws IOException {
        DataSourceInitializer bean = new DataSourceInitializer();
        bean.setDataSource(dataSourceOpen());

        if (isH2Initialized()) {
            ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
            PathMatchingResourcePatternResolver resolver =
                    new PathMatchingResourcePatternResolver();
            Arrays.asList(
                    resolver.getResources("classpath*:/database/open/" + database + "-schema*.sql"))
                    .forEach(f -> databasePopulator.addScript(f));
            Arrays.asList(resolver
                    .getResources("classpath*:/database/open/" + database + "-procedure*.sql"))
                    .forEach(f -> databasePopulator.addScript(f));
            Arrays.asList(resolver
                    .getResources("classpath*:/database/open/" + database + "-dataload*.sql"))
                    .forEach(f -> databasePopulator.addScript(f));
            databasePopulator.setSqlScriptEncoding("UTF-8");
            databasePopulator.setIgnoreFailedDrops(true);
            bean.setDatabasePopulator(databasePopulator);
        }

        return bean;
    }

    /**
     * Configuration to set up database during initialization.
     * @return Bean of configured {@link DataSourceInitializer}
     * @throws IOException
     */
    @Bean("dataSourceCloseInitializer")
    @DependsOn({"dataSourceOpenInitializer"})
    public DataSourceInitializer dataSourceCloseInitializer() throws IOException {
        DataSourceInitializer bean = new DataSourceInitializer();
        bean.setDataSource(dataSourceClose());

        if (isH2Initialized()) {
            ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
            PathMatchingResourcePatternResolver resolver =
                    new PathMatchingResourcePatternResolver();
            Arrays.asList(resolver
                    .getResources("classpath*:/database/close/" + database + "-schema*.sql"))
                    .forEach(f -> databasePopulator.addScript(f));
            Arrays.asList(resolver
                    .getResources("classpath*:/database/close/" + database + "-procedure*.sql"))
                    .forEach(f -> databasePopulator.addScript(f));
            Arrays.asList(resolver
                    .getResources("classpath*:/database/close/" + database + "-dataload*.sql"))
                    .forEach(f -> databasePopulator.addScript(f));
            databasePopulator.setSqlScriptEncoding("UTF-8");
            databasePopulator.setIgnoreFailedDrops(true);
            bean.setDatabasePopulator(databasePopulator);
        }

        return bean;
    }

    /**
     * Determine if H2 has been initialized in this project.
     */
    private boolean isH2Initialized() {
        return !PortChecker.isCustomPortInUse(tcpPort)
                || Boolean.parseBoolean((String) context.getAttribute("h2InitializeStatus"));
    }

    private String getUrl() {
        return isH2Initialized() ? this.url : this.tcpUrl;
    }

    private String getOpenUrl() {
        return isH2Initialized() ? this.openUrl : this.tcpOpenUrl;
    }

    private String getCloseUrl() {
        return isH2Initialized() ? this.closeUrl : this.tcpCloseUrl;
    }

}
