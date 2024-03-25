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

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.terasoluna.gfw.common.date.jodatime.JdbcFixedJodaTimeDateFactory;

/**
 * JodaTimeDateFactory configuration definition.
 */
@SuppressWarnings("deprecation")
@Configuration
public class SpringFunctionaltestJodaConfig {

    /**
     * Configure {@link JdbcFixedJodaTimeDateFactory} bean.
     * @param dataSource DataSource defined by SpringFunctionaltestEnvConfig#dataSource()
     * @return Bean of configured {@link JdbcFixedJodaTimeDateFactory}
     */
    @Bean("dateFactory")
    public JdbcFixedJodaTimeDateFactory dateFactory(
            @Qualifier("dataSource") DataSource dataSource) {
        JdbcFixedJodaTimeDateFactory factory = new JdbcFixedJodaTimeDateFactory();
        factory.setDataSource(dataSource);
        factory.setCurrentTimestampQuery("SELECT now FROM system_date");
        return factory;
    }
}