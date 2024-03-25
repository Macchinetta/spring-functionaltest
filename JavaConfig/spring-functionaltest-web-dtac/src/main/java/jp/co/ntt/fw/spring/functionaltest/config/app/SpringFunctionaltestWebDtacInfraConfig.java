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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.terasoluna.gfw.common.time.ClockFactory;

import jp.co.ntt.fw.spring.functionaltest.config.app.mybatis.MybatisConfig;
import jp.co.ntt.fw.spring.functionaltest.infra.datasource.dtac.ChangeTimeClockFactory;
import jp.co.ntt.fw.spring.functionaltest.infra.datasource.dtac.RoutingDataSource;

/**
 * Bean definitions for infrastructure layer.
 */
@Configuration
@MapperScan(basePackages = "jp.co.ntt.fw.spring.functionaltest.domain.repository", sqlSessionFactoryRef = "sqlSessionFactory")
@Import({ SpringFunctionaltestEnvConfig.class })
public class SpringFunctionaltestWebDtacInfraConfig {

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
     * @param sqlSessionFactory Bean defined by #sqlSessionFactory
     * @return Bean of configured {@link SqlSessionTemplate}
     */
    @Bean("reUseSqlSessionTemplate")
    public SqlSessionTemplate reUseSqlSessionTemplate(
            @Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory, ExecutorType.REUSE);
    }

    /**
     * Configure {@link RoutingDataSource} bean.
     * @param dataSourceOpen DataSource defined by SpringFunctionaltestEnvConfig#dataSourceOpen()
     * @param dataSourceClose DataSource defined by SpringFunctionaltestEnvConfig#dataSourceClose()
     * @param dataSourceDefault DataSource defined by SpringFunctionaltestEnvConfig#dataSourceDefault()
     * @return Bean of configured {@link RoutingDataSource}
     */
    @Bean("routingDataSource")
    public RoutingDataSource routingDataSource(
            @Qualifier("dataSourceOpen") DataSource dataSourceOpen,
            @Qualifier("dataSourceClose") DataSource dataSourceClose,
            @Qualifier("dataSourceDefault") DataSource dataSourceDefault) {

        RoutingDataSource bean = new RoutingDataSource();
        Map<Object, Object> target = new LinkedHashMap<Object, Object>();
        target.put("OPEN", dataSourceOpen);
        target.put("CLOSE", dataSourceClose);
        bean.setTargetDataSources(target);
        bean.setDefaultTargetDataSource(dataSourceDefault);
        bean.setClockFactory(clockFactoryDtac());
        return bean;
    }

    /**
     * Configure {@link ClockFactory} bean.
     * @return Bean of configured {@link ClockFactory}
     */
    @Bean("clockFactoryDtac")
    public ClockFactory clockFactoryDtac() {
        return new ChangeTimeClockFactory();
    }
}