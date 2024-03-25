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

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.mapping.VendorDatabaseIdProvider;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.TransactionManager;

import jakarta.persistence.EntityManagerFactory;
import jp.co.ntt.fw.spring.functionaltest.config.app.mybatis.MybatisConfig;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.djpa.MyProjectRepositoryImpl;

/**
 * Bean definitions for infrastructure layer.
 */
@Configuration
@MapperScan(basePackages = "jp.co.ntt.fw.spring.functionaltest.domain.repository", sqlSessionFactoryRef = "sqlSessionFactory")
@EnableJpaRepositories(basePackages = "jp.co.ntt.fw.spring.functionaltest.domain.repository.djpa",
            transactionManagerRef = "jpaTransactionManager",
            repositoryBaseClass = MyProjectRepositoryImpl.class)
@EnableJpaAuditing(auditorAwareRef = "springSecurityAuditorAware",
            dateTimeProviderRef = "auditDateTimeProvider")
@Import({ SpringFunctionaltestEnvConfig.class })
public class SpringFunctionaltestWebExcnInfraConfig {

    /**
     * Database property.
     */
    @Value("${database}")
    private Database database;

    /**
     * dialect property.
     */
    @Value("${dialect}")
    private String dialect;

    /**
     * Configure {@link VendorDatabaseIdProvider} bean.
     * @return Bean of configured {@link VendorDatabaseIdProvider}
     */
    @Bean("databaseIdProvider")
    public VendorDatabaseIdProvider databaseIdProvider() {
        VendorDatabaseIdProvider bean = new VendorDatabaseIdProvider();
        Properties properties = new Properties();
        properties.setProperty("Oracle", "oracle");
        properties.setProperty("PostgreSQL", "postgres");
        properties.setProperty("H2", "h2");
        bean.setProperties(properties);
        return bean;
    }

    /**
     * Configure {@link SqlSessionFactory} bean.
     * @param dataSource DataSource defined by SpringFunctionaltestEnvConfig#dataSource()
     * @return Bean of configured {@link SqlSessionFactoryBean}
     */
    @Bean("sqlSessionFactory")
    public SqlSessionFactoryBean sqlSessionFactory(
            @Qualifier("dataSource") DataSource dataSource) {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setDatabaseIdProvider(databaseIdProvider());
        bean.setConfiguration(MybatisConfig.configuration());
        return bean;
    }

    /**
     * Configure {@link SqlSessionTemplate} bean.
     * @param sqlSessionFactory Bean defined by #sqlSessionFactory()
     * @return Bean of configured {@link SqlSessionTemplate}
     */
    @Bean("reUseSqlSessionTemplate")
    public SqlSessionTemplate reUseSqlSessionTemplate(
            @Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory, ExecutorType.REUSE);
    }

    /**
     * Configure {@link TransactionManager} bean for use with JPA.
     * @param entityManagerFactory EntityManager used within a transaction
     * @return Bean of configured {@link JpaTransactionManager}
     */
    @Bean("jpaTransactionManager")
    public TransactionManager jpaTransactionManager(
            @Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager bean = new JpaTransactionManager();
        bean.setEntityManagerFactory(entityManagerFactory);
        return bean;
    }

    /**
     * Configure {@link HibernateJpaVendorAdapter} bean.
     * @return Bean of configured {@link HibernateJpaVendorAdapter}
     */
    @Bean("jpaVendorAdapter")
    public HibernateJpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter bean = new HibernateJpaVendorAdapter();
        bean.setDatabasePlatform(dialect);
        bean.setShowSql(false);
        bean.setDatabase(database);
        return bean;
    }

    /**
     * Configure {@link LocalContainerEntityManagerFactoryBean} bean.
     * @param dataSource DataSource defined by SpringFunctionaltestEnvConfig#dataSource()
     * @return Bean of configured {@link LocalContainerEntityManagerFactoryBean}
     */
    @Bean("entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            @Qualifier("dataSource") DataSource dataSource) {

        LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
        bean.setPackagesToScan(
                "jp.co.ntt.fw.spring.functionaltest.domain.model");
        bean.setDataSource(dataSource);
        bean.setJpaVendorAdapter(jpaVendorAdapter());

        Map<String, Object> param = new LinkedHashMap<String, Object>();
        param.put("hibernate.hbm2ddl.auto", "none");
        param.put("hibernate.ejb.naming_strategy",
                "org.hibernate.cfg.ImprovedNamingStrategy");
        param.put("hibernate.connection.charSet", "UTF-8");
        param.put("hibernate.show_sql", false);
        param.put("hibernate.format_sql", false);
        param.put("hibernate.use_sql_comments", true);
        param.put("hibernate.jdbc.batch_size", 30);
        param.put("hibernate.jdbc.fetch_size", 100);
        bean.getJpaPropertyMap().putAll(param);

        return bean;
    }
}
