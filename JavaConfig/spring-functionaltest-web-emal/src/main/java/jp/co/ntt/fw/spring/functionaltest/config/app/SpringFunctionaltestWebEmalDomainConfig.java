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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;
import org.terasoluna.gfw.common.exception.ExceptionLogger;
import org.terasoluna.gfw.common.exception.ResultMessagesLoggingInterceptor;

/**
 * Bean definitions for domain layer.
 */
@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = { "jp.co.ntt.fw.spring.functionaltest.domain" })
@Import({ SpringFunctionaltestWebEmalInfraConfig.class,
        SpringFunctionaltestWebEmalCodelistConfig.class })
public class SpringFunctionaltestWebEmalDomainConfig {

    /**
     * mail noauth from address property.
     */
    @Value("${mail.noauth.from.address}")
    private String from;

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
        pointcut.setExpression(
                "@within(org.springframework.stereotype.Service)");
        return new DefaultPointcutAdvisor(pointcut, resultMessagesLoggingInterceptor);
    }

    /**
     * Configure template message.
     * @return Bean of configured {@link SimpleMailMessage}
     */
    @Bean("templateMessage")
    public SimpleMailMessage templateMessage() {
        SimpleMailMessage templateMessage = new SimpleMailMessage();
        templateMessage.setFrom(from);
        templateMessage.setSubject("Registration confirmation.");
        return templateMessage;
    }

    /**
     * Configure FreeMarker configuration.
     * @return Bean of configured {@link FreeMarkerConfigurationFactoryBean}
     */
    @Bean("freemarkerConfiguration")
    public FreeMarkerConfigurationFactoryBean freemarkerConfiguration() {
        FreeMarkerConfigurationFactoryBean freemarkerConfiguration = new FreeMarkerConfigurationFactoryBean();
        freemarkerConfiguration.setPreferFileSystemAccess(false);
        freemarkerConfiguration.setTemplateLoaderPath(
                "classpath:/META-INF/freemarker/");
        freemarkerConfiguration.setDefaultEncoding("UTF-8");
        return freemarkerConfiguration;
    }

}
