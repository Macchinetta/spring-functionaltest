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

import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.terasoluna.gfw.common.exception.ExceptionLogger;
import org.terasoluna.gfw.common.exception.ResultMessagesLoggingInterceptor;

/**
 * Bean definitions for domain layer.
 */
@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = {"jp.co.ntt.fw.spring.functionaltest.domain"})
@Import({SpringFunctionaltestWebJmssInfraConfig.class})
public class SpringFunctionaltestWebJmssDomainConfig {

    /**
     * Configure {@link ResultMessagesLoggingInterceptor} bean.
     * @param exceptionLogger Bean defined by ApplicationContextConfig#exceptionLogger
     * @see ApplicationContextConfig#exceptionLogger()
     * @return Bean of configured {@link ResultMessagesLoggingInterceptor}
     */
    @Bean("resultMessagesLoggingInterceptor")
    public ResultMessagesLoggingInterceptor resultMessagesLoggingInterceptor(
            ExceptionLogger exceptionLogger) {
        ResultMessagesLoggingInterceptor bean = new ResultMessagesLoggingInterceptor();
        bean.setExceptionLogger(exceptionLogger);
        return bean;
    }

    /**
     * Configure messages logging AOP advisor.
     * @param resultMessagesLoggingInterceptor Bean defined by #resultMessagesLoggingInterceptor
     * @see #resultMessagesLoggingInterceptor(ExceptionLogger)
     * @return Advisor configured for PointCut
     */
    @Bean
    public Advisor resultMessagesLoggingInterceptorAdvisor(
            ResultMessagesLoggingInterceptor resultMessagesLoggingInterceptor) {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("@within(org.springframework.stereotype.Service)");
        return new DefaultPointcutAdvisor(pointcut, resultMessagesLoggingInterceptor);
    }

    /**
     * Configure {@link LocalValidatorFactoryBean} bean.
     * @return Bean of configured {@link LocalValidatorFactoryBean}
     */
    @Bean("validator")
    public LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean();
    }

    /**
     * Configure {@link MethodValidationPostProcessor} bean.
     * @param validator Bean defined by SpringFunctionaltestEnvConfig#validator
     * @return Bean of configured {@link MethodValidationPostProcessor}
     */
    @Bean
    public MethodValidationPostProcessor validationMethodValidationPostProcessor() {
        MethodValidationPostProcessor bean = new MethodValidationPostProcessor();
        bean.setValidator(validator());
        return bean;
    }

}
