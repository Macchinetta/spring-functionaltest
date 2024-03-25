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

import java.time.Duration;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * Define settings for the environment.
 */
@Configuration
@Import({ SpringFunctionaltestJsr310Config.class })
public class SpringFunctionaltestEnvConfig {

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
    @Bean(name = "dataSourceDefault", destroyMethod = "close")
    public DataSource dataSourceDefault() {
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

    //JPA transaction Manager

//    @Bean("jpaTransactionManager")
//    public TransactionManager jpaTransactionManager(
//            @Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory) {
//        JpaTransactionManager bean = new JpaTransactionManager();
//        bean.setEntityManagerFactory(entityManagerFactory);
//        return bean;
//    }

    /**
     * Configure {@link LocalValidatorFactoryBean} bean.
     * @return Bean of configured {@link LocalValidatorFactoryBean}
     */
    @Bean("validator")
    public LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean();
    }

    /**
     * Configure {@link ResourceBundleMessageSource} bean.
     * @return Bean of configured {@link ResourceBundleMessageSource}
     */
    @Bean
    public ResourceBundleMessageSource resourceBundleMessageSource() {
        ResourceBundleMessageSource bean = new ResourceBundleMessageSource();
        bean.setBasenames("ValidationMessagesNotAscii");
        bean.setDefaultEncoding("UTF-8");
        return bean;
    }

    /**
     * Configure {@link LocalValidatorFactoryBean} bean.
     * @return Bean of configured {@link LocalValidatorFactoryBean}
     */
    @Bean("notAsciiValidator")
    public LocalValidatorFactoryBean notAsciiValidator() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(resourceBundleMessageSource());
        return bean;
    }
}
