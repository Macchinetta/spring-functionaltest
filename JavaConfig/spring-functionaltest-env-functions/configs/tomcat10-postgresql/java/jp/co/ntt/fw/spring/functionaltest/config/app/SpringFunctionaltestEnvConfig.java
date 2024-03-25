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

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.transaction.TransactionManager;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * Define settings for the environment.
 */
@Configuration
@Import({ SpringFunctionaltestJsr310Config.class })
public class SpringFunctionaltestEnvConfig {

    /**
     * Configure {@link DataSource} bean.
     * @return Bean of configured {@link JndiObjectFactoryBean}
     * @throws NamingException JNDI Lookup failed.
     */
    @Bean(name = "dataSource")
    public DataSource dataSource() throws NamingException {
        JndiObjectFactoryBean bean = new JndiObjectFactoryBean();
        bean.setJndiName("jdbc/springFunctionaltestDataSource");
        bean.setExpectedType(javax.sql.DataSource.class);
        bean.setResourceRef(true);
        bean.afterPropertiesSet();
        return (DataSource) bean.getObject();
    }

    /**
     * Configure {@link DataSource} bean.
     * @return Bean of configured {@link JndiObjectFactoryBean}
     * @throws NamingException JNDI Lookup failed.
     */
    @Bean(name = "dataSourceDefault")
    public DataSource dataSourceDefault() throws NamingException {
        JndiObjectFactoryBean bean = new JndiObjectFactoryBean();
        bean.setJndiName("jdbc/springFunctionaltestDataSource");
        bean.setExpectedType(javax.sql.DataSource.class);
        bean.setResourceRef(true);
        bean.afterPropertiesSet();
        return (DataSource) bean.getObject();
    }

    /**
     * Configure {@link DataSource} bean.
     * @return Bean of configured {@link JndiObjectFactoryBean}
     * @throws NamingException JNDI Lookup failed.
     */
    @Bean(name = "dataSourceOpen")
    public DataSource dataSourceOpen() throws NamingException {
        JndiObjectFactoryBean bean = new JndiObjectFactoryBean();
        bean.setJndiName("jdbc/springFunctionaltestDataSourceOpen");
        bean.setExpectedType(javax.sql.DataSource.class);
        bean.setResourceRef(true);
        bean.afterPropertiesSet();
        return (DataSource) bean.getObject();
    }

    /**
     * Configure {@link DataSource} bean.
     * @return Bean of configured {@link JndiObjectFactoryBean}
     * @throws NamingException JNDI Lookup failed.
     */
    @Bean(name = "dataSourceClose")
    public DataSource dataSourceClose() throws NamingException {
        JndiObjectFactoryBean bean = new JndiObjectFactoryBean();
        bean.setJndiName("jdbc/springFunctionaltestDataSourceClose");
        bean.setExpectedType(javax.sql.DataSource.class);
        bean.setResourceRef(true);
        bean.afterPropertiesSet();
        return (DataSource) bean.getObject();
    }

    /**
     * Configure {@link TransactionManager} bean.
     * @return Bean of configured {@link DataSourceTransactionManager}
     * @throws NamingException JNDI Lookup failed.
     */
    @Bean("transactionManager")
    public TransactionManager transactionManager() throws NamingException {
        DataSourceTransactionManager bean = new DataSourceTransactionManager();
        bean.setDataSource(dataSource());
        bean.setRollbackOnCommitFailure(true);
        return bean;
    }

    // JPA transaction Manager

    // @Bean("jpaTransactionManager")
    // public TransactionManager jpaTransactionManager(
    // @Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory) {
    // JpaTransactionManager bean = new JpaTransactionManager();
    // bean.setEntityManagerFactory(entityManagerFactory);
    // return bean;
    // }

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
