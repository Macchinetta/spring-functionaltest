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

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.transaction.TransactionManager;
import org.terasoluna.gfw.common.time.DefaultClockFactory;

/**
 * Define settings for the environment.
 */
@Configuration
public class SpringFunctionaltestEnvConfig {

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
     * Configuration to set up database during initialization.
     * @return Bean of configured {@link DataSourceInitializer}
     * @throws NamingException JNDI Lookup failed.
     */
    @Bean("dataSourceInitializer")
    public DataSourceInitializer dataSourceInitializer() throws NamingException {
        DataSourceInitializer bean = new DataSourceInitializer();
        bean.setDataSource(dataSource());
        return bean;
    }

    /**
     * Configuration to set up database during initialization.
     * @return Bean of configured {@link DataSourceInitializer}
     * @throws NamingException JNDI Lookup failed.
     */
    @Bean("dataSourceOpenInitializer")
    @DependsOn({ "dataSourceInitializer" })
    public DataSourceInitializer dataSourceOpenInitializer() throws NamingException {
        DataSourceInitializer bean = new DataSourceInitializer();
        bean.setDataSource(dataSourceOpen());
        return bean;
    }

    /**
     * Configuration to set up database during initialization.
     * @return Bean of configured {@link DataSourceInitializer}
     * @throws NamingException JNDI Lookup failed.
     */
    @Bean("dataSourceCloseInitializer")
    @DependsOn({ "dataSourceOpenInitializer" })
    public DataSourceInitializer dataSourceCloseInitializer() throws NamingException {
        DataSourceInitializer bean = new DataSourceInitializer();
        bean.setDataSource(dataSourceClose());
        return bean;
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

