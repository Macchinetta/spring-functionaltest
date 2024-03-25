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
package jp.co.ntt.fw.spring.functionaltest.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.terasoluna.gfw.common.exception.ExceptionLogger;

import jp.co.ntt.fw.spring.functionaltest.config.app.SpringFunctionaltestDomainConfig;

/**
 * Bean definition to TestContext configure .
 */
@Configuration
@Import({ SpringFunctionaltestDomainConfig.class })
public class TestContextConfig {

    /**
     * Configure {@link PropertySourcesPlaceholderConfigurer} bean.
     * @param properties Property files to be read
     * @return Bean of configured {@link PropertySourcesPlaceholderConfigurer}
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer(
            @Value("classpath*:/META-INF/spring/*.properties") Resource... properties) {
        PropertySourcesPlaceholderConfigurer bean = new PropertySourcesPlaceholderConfigurer();
        bean.setLocations(properties);
        return bean;
    }

    /**
     * Configure {@link ExceptionLogger} bean.
     * @return Bean of configured {@link ExceptionLogger}
     */
    @Bean("exceptionLogger")
    public ExceptionLogger exceptionLogger() {
        return new ExceptionLogger();
    }

    /**
     * Configure {@link JdbcTemplate} bean.
     * @param dataSource Bean defined by SpringFunctionaltestEnvConfig#dataSource()
     * @return Bean of configured {@link JdbcTemplate}
     */
    @Bean("jdbcTemplate")
    public JdbcTemplate testJdbcTemplate(
            @Qualifier("dataSource") DataSource dataSource) {
        JdbcTemplate bean = new JdbcTemplate();
        bean.setDataSource(dataSource);
        return bean;
    }
}
