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
import java.util.Properties;
import javax.sql.DataSource;
import org.apache.ibatis.mapping.VendorDatabaseIdProvider;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import jp.co.ntt.fw.spring.functionaltest.config.app.mybatis.MybatisConfig;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.dam3.TodoBatchRepository;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.dam3.TodoReUseRepository;

/**
 * Bean definitions for infrastructure layer.
 */
@Configuration
@MapperScan(basePackages = "jp.co.ntt.fw.spring.functionaltest.domain.repository",
        sqlSessionFactoryRef = "sqlSessionFactory")
@Import({SpringFunctionaltestEnvConfig.class})
public class SpringFunctionaltestWebDam3InfraConfig {

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
     * @throws IOException Exception when loading property fails
     */
    @Bean("sqlSessionFactory")
    public SqlSessionFactoryBean sqlSessionFactory(@Qualifier("dataSource") DataSource dataSource)
            throws IOException {
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
     * Configure {@link MapperFactoryBean} bean.
     * @param reUseSqlSessionTemplate Bean defined by #reUseSqlSessionTemplate()
     * @return Bean of configured {@link MapperFactoryBean}
     */
    @Bean("todoReUseRepository")
    public MapperFactoryBean<TodoReUseRepository> todoReUseRepository(
            @Qualifier("reUseSqlSessionTemplate") SqlSessionTemplate reUseSqlSessionTemplate) {
        MapperFactoryBean<TodoReUseRepository> bean = new MapperFactoryBean<TodoReUseRepository>();
        bean.setMapperInterface(TodoReUseRepository.class);
        bean.setSqlSessionTemplate(reUseSqlSessionTemplate);
        return bean;
    }

    /**
     * Configure {@link SqlSessionTemplate} bean.
     * @param sqlSessionFactory Bean defined by #sqlSessionFactory()
     * @return Bean of configured {@link SqlSessionTemplate}
     */
    @Bean("batchSqlSessionTemplate")
    public SqlSessionTemplate batchSqlSessionTemplate(
            @Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory, ExecutorType.BATCH);
    }

    // (4)
    /**
     * Configure {@link SqlSessionTemplate} bean.
     * @param batchSqlSessionTemplate Bean defined by #batchSqlSessionTemplate()
     * @return Bean of configured {@link SqlSessionTemplate}
     */
    @Bean("todoBatchRepository")
    public MapperFactoryBean<TodoBatchRepository> todoBatchRepository(
            @Qualifier("batchSqlSessionTemplate") SqlSessionTemplate batchSqlSessionTemplate) {
        MapperFactoryBean<TodoBatchRepository> bean = new MapperFactoryBean<TodoBatchRepository>();
        // (5)
        bean.setMapperInterface(TodoBatchRepository.class);
        // (6)
        bean.setSqlSessionTemplate(batchSqlSessionTemplate);
        return bean;
    }

    // @Bean("userListRoutingRepository")
    // public UserListRoutingRepository userListRoutingRepository(
    // @Quazlifier("routingSqlSessionFactory") SqlSessionFactoryBean routingSqlSessionFactory) {
    // UserListRoutingRepositoryImpl bean = new UserListRoutingRepositoryImpl();
    // bean.setSqlSessionFactory(routingSqlSessionFactory);
    // return bean;
    // }

    // @Bean("routingSqlSessionFactory")
    // public SqlSessionFactoryBean routingSqlSessionFactory(
    // @Quazlifier("routingDataSource") DataSource routingDataSource) {
    // SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
    // bean.setDataSource(routingDataSource);
    // bean.setTypeAliasesPackage(
    // "jp.co.ntt.fw.spring.functionaltest.domain.model");
    // bean.setDatabaseIdProvider(databaseIdProvider();
    // return bean;
    // }

}
