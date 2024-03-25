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

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

import jakarta.inject.Inject;
import jp.co.ntt.fw.spring.functionaltest.config.app.mybatis.MybatisConfig;
import jp.co.ntt.fw.spring.functionaltest.domain.DBLogCleaner;

/**
 * Bean definitions for infrastructure layer.
 */
@Configuration
@EnableScheduling
@MapperScan("jp.co.ntt.fw.spring.functionaltest.domain.repository")
@Import({ SpringFunctionaltestEnvConfig.class })
public class SpringFunctionaltestInfraConfig implements SchedulingConfigurer {

    /**
     * Bean of DataSource
     */
    @Inject
    private DataSource dataSourceForLogging;

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
        bean.setConfiguration(MybatisConfig.configuration());
        return bean;
    }

    /**
     * Configure {@link DBLogCleaner} bean.
     * @return Bean of configured {@link DBLogCleaner}
     */
    @Bean("dbLogCleaner")
    public DBLogCleaner dbLogCleaner() {
        DBLogCleaner bean = new DBLogCleaner();
        bean.setDataSource(dataSourceForLogging);
        return bean;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(dbLogCleanupTaskScheduler());
        taskRegistrar.addTriggerTask(() -> dbLogCleaner().cleanup(),
                new CronTrigger("0 0 6 * * ?"));
    }

    /**
     * Configure {@link Executor} bean.
     * @return Bean of configured {@link Executor}
     */
    @Bean("dbLogCleanupTaskScheduler")
    public Executor dbLogCleanupTaskScheduler() {
        return Executors.newSingleThreadScheduledExecutor();
    }

}
