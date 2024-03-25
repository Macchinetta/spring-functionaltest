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

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.mapping.VendorDatabaseIdProvider;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import jp.co.ntt.fw.spring.functionaltest.config.app.mybatis.MybatisConfig;

/**
 * Bean definitions for infrastructure layer.
 */
@Configuration
@MapperScan(basePackages = "jp.co.ntt.fw.spring.functionaltest.domain.repository", sqlSessionFactoryRef = "sqlSessionFactory")
@Import({ SpringFunctionaltestEnvConfig.class })
public class SpringFunctionaltestWebExhnInfraConfig {

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
}
