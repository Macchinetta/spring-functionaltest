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
package jp.co.ntt.fw.spring.functionaltest.config.web;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.Resource;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.security.web.method.annotation.AuthenticationPrincipalArgumentResolver;
import org.springframework.security.web.servlet.support.csrf.CsrfRequestDataValueProcessor;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.support.RequestDataValueProcessor;
import org.terasoluna.gfw.common.exception.ExceptionLogger;
import org.terasoluna.gfw.web.codelist.CodeListInterceptor;
import org.terasoluna.gfw.web.exception.HandlerExceptionResolverLoggingInterceptor;
import org.terasoluna.gfw.web.logging.TraceLoggingInterceptor;
import org.terasoluna.gfw.web.mvc.support.CompositeRequestDataValueProcessor;
import org.terasoluna.gfw.web.token.transaction.TransactionTokenInterceptor;
import org.terasoluna.gfw.web.token.transaction.TransactionTokenRequestDataValueProcessor;
import org.thymeleaf.dialect.IDialect;
import org.thymeleaf.extras.springsecurity6.dialect.SpringSecurityDialect;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

import jp.co.ntt.fw.spring.functionaltest.app.ThymeleafCommonControllerAdvice;
import jp.co.ntt.fw.spring.functionaltest.app.cmmn.serverinfo.GetServerInfoInterceptor;

/**
 * Configure SpringMVC.
 */
@Configuration
@EnableAspectJAutoProxy
public class SpringMvcCommonConfig implements WebMvcConfigurer {

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
     * {@inheritDoc}
     */
    @Override
    public void addArgumentResolvers(
            List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(pageableHandlerMethodArgumentResolver());
        argumentResolvers.add(authenticationPrincipalArgumentResolver());
    }

    /**
     * Configure {@link PageableHandlerMethodArgumentResolver} bean.
     * @return Bean of configured {@link PageableHandlerMethodArgumentResolver}
     */
    @Bean
    public PageableHandlerMethodArgumentResolver pageableHandlerMethodArgumentResolver() {
        return new PageableHandlerMethodArgumentResolver();
    }

    /**
     * Configure {@link AuthenticationPrincipalArgumentResolver} bean.
     * @return Bean of configured {@link AuthenticationPrincipalArgumentResolver}
     */
    @Bean
    public AuthenticationPrincipalArgumentResolver authenticationPrincipalArgumentResolver() {
        return new AuthenticationPrincipalArgumentResolver();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations(
                "/resources/", "classpath:META-INF/resources/").setCachePeriod(
                        60 * 60);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        addInterceptor(registry, getServerInfoInterceptor());
        addInterceptor(registry, traceLoggingInterceptor());
        addInterceptor(registry, transactionTokenInterceptor());
        addInterceptor(registry, codeListInterceptor());
    }

    /**
     * Common processes used in #addInterceptors.
     * @param registry {@link InterceptorRegistry}
     * @param interceptor {@link HandlerInterceptor}
     */
    private void addInterceptor(InterceptorRegistry registry,
            HandlerInterceptor interceptor) {
        registry.addInterceptor(interceptor).addPathPatterns("/**")
                .excludePathPatterns("/resources/**");
    }

    /**
     * Configure {@link GetServerInfoInterceptor} bean.
     * @return Bean of configured {@link GetServerInfoInterceptor}
     */
    @Bean
    public GetServerInfoInterceptor getServerInfoInterceptor() {
        return new GetServerInfoInterceptor();
    }

    /**
     * Configure {@link TraceLoggingInterceptor} bean.
     * @return Bean of configured {@link TraceLoggingInterceptor}
     */
    @Bean
    public TraceLoggingInterceptor traceLoggingInterceptor() {
        return new TraceLoggingInterceptor();
    }

    /**
     * Configure {@link TransactionTokenInterceptor} bean.
     * @return Bean of configured {@link TransactionTokenInterceptor}
     */
    @Bean
    public TransactionTokenInterceptor transactionTokenInterceptor() {
        return new TransactionTokenInterceptor();
    }

    /**
     * Configure {@link CodeListInterceptor} bean.
     * @return Bean of configured {@link CodeListInterceptor}
     */
    @Bean
    public CodeListInterceptor codeListInterceptor() {
        CodeListInterceptor codeListInterceptor = new CodeListInterceptor();
        codeListInterceptor.setCodeListIdPattern(Pattern.compile("CL_.+"));
        return codeListInterceptor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.viewResolver(thymeleafViewResolver());
    }

    /**
     * Configure Thymeleaf bean.
     * @return Bean of configured ThymeleafViewResolver
     */
    @Bean
    public ThymeleafViewResolver thymeleafViewResolver() {
        ThymeleafViewResolver bean = new ThymeleafViewResolver();
        bean.setTemplateEngine(templateEngine());
        bean.setCharacterEncoding("UTF-8");
        bean.setForceContentType(true);
        bean.setContentType("text/html;charset=UTF-8");
        return bean;
    }

    /**
     * Configure ITemplateResolver Bean.
     * @return Bean of configured SpringResourceTemplateResolver
     */
    @Bean("templateResolver")
    public ITemplateResolver templateResolver() {
        SpringResourceTemplateResolver bean = new SpringResourceTemplateResolver();
        bean.setPrefix("/WEB-INF/views/");
        bean.setSuffix(".html");
        bean.setTemplateMode("HTML");
        bean.setCharacterEncoding("UTF-8");
        return bean;
    }

    /**
     * Configure SpringTemplateEngine Bean.
     * @return Bean of configured SpringTemplateEngine
     */
    @Bean("templateEngine")
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine bean = new SpringTemplateEngine();
        bean.setTemplateResolver(templateResolver());
        bean.setEnableSpringELCompiler(true);
        Set<IDialect> set = new HashSet<>();
        set.add(new SpringSecurityDialect());
        bean.setAdditionalDialects(set);
        return bean;
    }

    /**
     * Configure {@link RequestDataValueProcessor} bean.
     * @return Bean of configured {@link CompositeRequestDataValueProcessor}
     */
    @Bean("requestDataValueProcessor")
    public RequestDataValueProcessor requestDataValueProcessor() {
        return new CompositeRequestDataValueProcessor(csrfRequestDataValueProcessor(), transactionTokenRequestDataValueProcessor());
    }

    /**
     * Configure {@link CsrfRequestDataValueProcessor} bean.
     * @return Bean of configured {@link CsrfRequestDataValueProcessor}
     */
    @Bean
    public CsrfRequestDataValueProcessor csrfRequestDataValueProcessor() {
        return new CsrfRequestDataValueProcessor();
    }

    /**
     * Configure {@link TransactionTokenRequestDataValueProcessor} bean.
     * @return Bean of configured {@link TransactionTokenRequestDataValueProcessor}
     */
    @Bean
    public TransactionTokenRequestDataValueProcessor transactionTokenRequestDataValueProcessor() {
        return new TransactionTokenRequestDataValueProcessor();
    }

    /**
     * Configure messages logging AOP.
     * @param exceptionLogger Bean defined by ApplicationContext#exceptionLogger
     * @return Bean of configured {@link HandlerExceptionResolverLoggingInterceptor}
     */
    @Bean("handlerExceptionResolverLoggingInterceptor")
    public HandlerExceptionResolverLoggingInterceptor handlerExceptionResolverLoggingInterceptor(
            ExceptionLogger exceptionLogger) {
        HandlerExceptionResolverLoggingInterceptor bean = new HandlerExceptionResolverLoggingInterceptor();
        bean.setExceptionLogger(exceptionLogger);
        return bean;
    }

    /**
     * Configure messages logging AOP advisor.
     * @param handlerExceptionResolverLoggingInterceptor Bean defined by #handlerExceptionResolverLoggingInterceptor
     * @return Advisor configured for PointCut
     */
    @Bean
    public Advisor handlerExceptionResolverLoggingInterceptorAdvisor(
            HandlerExceptionResolverLoggingInterceptor handlerExceptionResolverLoggingInterceptor) {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(
                "execution(* org.springframework.web.servlet.HandlerExceptionResolver.resolveException(..))");
        return new DefaultPointcutAdvisor(pointcut, handlerExceptionResolverLoggingInterceptor);
    }

    /**
     * Configure {@link ThymeleafCommonControllerAdvice} bean.
     * @return Bean of configured {@link ThymeleafCommonControllerAdvice}
     */
    @Bean
    public ThymeleafCommonControllerAdvice thymeleafCommonControllerAdvice() {
        return new ThymeleafCommonControllerAdvice();
    }

    /**
     * Configure {@link StandardServletMultipartResolver} bean.
     * @return Bean of configured {@link StandardServletMultipartResolver}
     */
    @Bean("multipartResolver")
    public StandardServletMultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }
}
