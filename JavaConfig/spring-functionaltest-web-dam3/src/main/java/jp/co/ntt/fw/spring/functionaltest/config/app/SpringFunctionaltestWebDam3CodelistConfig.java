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

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import jakarta.inject.Inject;

/**
 * Bean definition regarding CodeLists.
 */
@Configuration
public class SpringFunctionaltestWebDam3CodelistConfig {

    /**
     * JDBC fetchSize property.
     */
    @Value("${codelist.jdbc.fetchSize:1000}")
    private Integer fetchSize;

    /**
     * Bean of DataSource.
     */
    @Inject
    private DataSource dataSource;

    /**
     * Configure {@link JdbcTemplate} bean.
     * @return Bean of configured {@link JdbcTemplate}
     */
    @Bean("jdbcTemplateForCodeList")
    public JdbcTemplate jdbcTemplateForCodeList() {
        JdbcTemplate bean = new JdbcTemplate();
        bean.setDataSource(dataSource);
        bean.setFetchSize(fetchSize);
        return bean;
    }

}
