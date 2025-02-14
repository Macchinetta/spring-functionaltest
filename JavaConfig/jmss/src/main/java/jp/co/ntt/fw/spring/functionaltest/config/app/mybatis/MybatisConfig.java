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
package jp.co.ntt.fw.spring.functionaltest.config.app.mybatis;

import java.io.IOException;
import java.util.Properties;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeAliasRegistry;

/**
 * Mybatis config.
 */
public class MybatisConfig {

    /**
     * mybatis-config.properties
     */
    private static Properties properties;

    /**
     * Settings Mybatis config.
     * @return Configured {@link Configuration}
     * @throws IOException Exception when loading property fails
     */
    public static Configuration configuration() throws IOException {
        properties =
                Resources.getResourceAsProperties("META-INF/mybatis/mybatis-config.properties");
        Configuration configuration = new Configuration();
        setSettings(configuration);
        setTypeAliases(configuration.getTypeAliasRegistry());
        return configuration;
    }

    /**
     * Settings MyBatis behaves.
     * @param configuration Accepted at configuration
     */
    private static void setSettings(Configuration configuration) {
        configuration
                .setJdbcTypeForNull(JdbcType.valueOf(properties.getProperty("jdbcTypeForNull")));
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setDefaultFetchSize(100);
    }

    /**
     * Settings type aliases.
     * @param typeAliasRegistry Accepted at configuration
     */
    private static void setTypeAliases(TypeAliasRegistry typeAliasRegistry) {
        typeAliasRegistry.registerAliases("jp.co.ntt.fw.spring.functionaltest.domain.model");
    }

}
