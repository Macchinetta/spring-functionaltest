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

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import jp.co.ntt.fw.spring.functionaltest.app.flup.TemporaryFilesCleaner;
import jp.co.ntt.fw.spring.functionaltest.app.flup.TemporaryFilesHelper;

/**
 * Application context.
 */
@Configuration
@EnableScheduling
public class ApplicationContextFlupConfig implements SchedulingConfigurer {

    /**
     * Configure {@link TemporaryFilesHelper} bean.
     * @return Bean of configured {@link TemporaryFilesHelper}
     */
    @Bean
    public TemporaryFilesHelper temporaryFilesHelper() {
        return new TemporaryFilesHelper();
    }

    /**
     * Configure {@link TemporaryFilesCleaner} bean.
     * @return Bean of configured {@link TemporaryFilesCleaner}
     */
    @Bean("uploadTemporaryFileCleaner")
    public TemporaryFilesCleaner uploadTemporaryFileCleaner() {
        return new TemporaryFilesCleaner();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(fileCleanupTaskScheduler());
        taskRegistrar.addTriggerTask(() -> uploadTemporaryFileCleaner().cleanup(),
                new CronTrigger("*/10 * * * * *"));
    }

    /**
     * Configure {@link ScheduledExecutorService} bean.
     * @return Bean of configured {@link ScheduledExecutorService}
     */
    @Bean("fileCleanupTaskScheduler")
    public ScheduledExecutorService fileCleanupTaskScheduler() {
        return Executors.newSingleThreadScheduledExecutor();
    }
}
