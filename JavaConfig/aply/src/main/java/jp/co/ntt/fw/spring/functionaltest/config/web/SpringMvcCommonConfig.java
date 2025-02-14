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
import java.util.Properties;
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
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.method.annotation.AuthenticationPrincipalArgumentResolver;
import org.springframework.security.web.servlet.support.csrf.CsrfRequestDataValueProcessor;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.support.RequestDataValueProcessor;
import org.terasoluna.gfw.common.exception.ExceptionCodeResolver;
import org.terasoluna.gfw.common.exception.ExceptionLogger;
import org.terasoluna.gfw.web.codelist.CodeListInterceptor;
import org.terasoluna.gfw.web.exception.HandlerExceptionResolverLoggingInterceptor;
import org.terasoluna.gfw.web.exception.SystemExceptionResolver;
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
import jp.co.ntt.fw.spring.functionaltest.app.ControllerAspect;
import jp.co.ntt.fw.spring.functionaltest.app.ThymeleafCommonControllerAdvice;
import jp.co.ntt.fw.spring.functionaltest.app.aply.CommonParametersMethodArgumentResolver;
import jp.co.ntt.fw.spring.functionaltest.app.aply.SuccessLoggingInterceptor;

/**
 * Configure SpringMVC Common.
 */
@EnableAspectJAutoProxy
@Configuration
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
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(pageableHandlerMethodArgumentResolver());
        argumentResolvers.add(commonParametersMethodArgumentResolver());
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
     * Configure {@link CommonParametersMethodArgumentResolver} bean.
     * @return Bean of configured {@link CommonParametersMethodArgumentResolver}
     */
    @Bean
    public CommonParametersMethodArgumentResolver commonParametersMethodArgumentResolver() {
        return new CommonParametersMethodArgumentResolver();
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
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**")
                .addResourceLocations("/resources/", "classpath:META-INF/resources/")
                .setCachePeriod(60 * 60);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        addInterceptor(registry, traceLoggingInterceptor());
        addInterceptor(registry, transactionTokenInterceptor());
        addInterceptor(registry, codeListInterceptor());
        addInterceptor(registry, successLoggingInterceptor(), "/jsp/0402/**", null);
        addInterceptor(registry, successLoggingInterceptor(), "/thymeleaf/0402/**", null);
    }

    /**
     * Common processes used in #addInterceptors.
     * @param registry {@link InterceptorRegistry}
     * @param interceptor {@link HandlerInterceptor}
     */
    private void addInterceptor(InterceptorRegistry registry, HandlerInterceptor interceptor) {
        addInterceptor(registry, interceptor, "/**", "/resources/**");
    }

    /**
     * Common processes used in #addInterceptors.
     * @param registry {@link InterceptorRegistry}
     * @param interceptor {@link HandlerInterceptor}
     * @param path
     * @param excludePath
     */
    private void addInterceptor(InterceptorRegistry registry, HandlerInterceptor interceptor,
            String path, String excludePath) {
        InterceptorRegistration registration = registry.addInterceptor(interceptor);
        registration.addPathPatterns(path);
        if (excludePath != null) {
            registration.excludePathPatterns(excludePath);
        }
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
     * Configure {@link SuccessLoggingInterceptor} bean.
     * @return Bean of configured {@link SuccessLoggingInterceptor}
     */
    @Bean
    public SuccessLoggingInterceptor successLoggingInterceptor() {
        return new SuccessLoggingInterceptor();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.viewResolver(thymeleafViewResolver());
        registry.jsp("/WEB-INF/views/", ".jsp");
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
        bean.setViewNames(new String[] {"thymeleaf/*"});
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
        return new CompositeRequestDataValueProcessor(csrfRequestDataValueProcessor(),
                transactionTokenRequestDataValueProcessor());
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
     * Configure {@link SystemExceptionResolver} bean.
     * @param exceptionCodeResolver Bean defined by ApplicationContext#exceptionCodeResolver
     * @return Bean of configured {@link SystemExceptionResolver}
     */
    @Bean("systemExceptionResolver")
    public SystemExceptionResolver systemExceptionResolver(
            ExceptionCodeResolver exceptionCodeResolver) {
        SystemExceptionResolver bean = new SystemExceptionResolver();
        bean.setExceptionCodeResolver(exceptionCodeResolver);
        bean.setOrder(3);

        Properties exceptionMappings = new Properties();
        exceptionMappings.setProperty("ResourceNotFoundException",
                "jsp/common/error/resourceNotFoundError");
        exceptionMappings.setProperty("BusinessException", "jsp/common/error/businessError");
        exceptionMappings.setProperty("InvalidTransactionTokenException",
                "jsp/common/error/transactionTokenError");
        exceptionMappings.setProperty(".DataAccessException", "jsp/common/error/dataAccessError");
        exceptionMappings.setProperty("InvalidRequestException", "jsp/common/error/operationError");
        exceptionMappings.setProperty("MultipartException", "jsp/common/error/fileUploadError");
        exceptionMappings.setProperty("HttpSessionRequiredException",
                "jsp/common/error/operationError");
        exceptionMappings.setProperty("IntentionalException", "jsp/common/error/intentionalError");
        bean.setExceptionMappings(exceptionMappings);

        Properties statusCodes = new Properties();
        statusCodes.setProperty("jsp/common/error/resourceNotFoundError",
                String.valueOf(HttpStatus.NOT_FOUND.value()));
        statusCodes.setProperty("jsp/common/error/businessError",
                String.valueOf(HttpStatus.CONFLICT.value()));
        statusCodes.setProperty("jsp/common/error/transactionTokenError",
                String.valueOf(HttpStatus.CONFLICT.value()));
        statusCodes.setProperty("jsp/common/error/dataAccessError",
                String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
        statusCodes.setProperty("jsp/common/error/operationError",
                String.valueOf(HttpStatus.BAD_REQUEST.value()));
        statusCodes.setProperty("jsp/common/error/fileUploadError",
                String.valueOf(HttpStatus.BAD_REQUEST.value()));
        statusCodes.setProperty("jsp/common/error/intentionalError",
                String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
        bean.setStatusCodes(statusCodes);

        bean.setExcludedExceptions(AccessDeniedException.class);
        bean.setDefaultErrorView("jsp/common/error/systemError");
        bean.setDefaultStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return bean;
    }

    /**
     * Configure messages logging AOP.
     * @param exceptionLogger Bean defined by ApplicationContext#exceptionLogger
     * @return Bean of configured {@link HandlerExceptionResolverLoggingInterceptor}
     */
    @Bean("handlerExceptionResolverLoggingInterceptor")
    public HandlerExceptionResolverLoggingInterceptor handlerExceptionResolverLoggingInterceptor(
            ExceptionLogger exceptionLogger) {
        HandlerExceptionResolverLoggingInterceptor bean =
                new HandlerExceptionResolverLoggingInterceptor();
        bean.setExceptionLogger(exceptionLogger);
        return bean;
    }

    /**
     * Configure messages logging AOP advisor.
     * @param handlerExceptionResolverLoggingInterceptor Bean defined by
     *        #handlerExceptionResolverLoggingInterceptor
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
     * Configure messages logging AOP advisor.
     * @return Advisor configured for PointCut
     */
    @Bean
    public Advisor controllerAspectAdvisor() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(
                "@annotation(org.springframework.web.bind.annotation.ExceptionHandler)");
        return new DefaultPointcutAdvisor(pointcut, controllerAspect());
    }

    /**
     * Configure {@link ControllerAspect} bean.
     * @return Bean of configured {@link ControllerAspect}
     */
    @Bean("controllerAspect")
    public ControllerAspect controllerAspect() {
        return new ControllerAspect();
    }

    /**
     * Configure {@link ThymeleafCommonControllerAdvice} bean.
     * @return Bean of configured {@link ThymeleafCommonControllerAdvice}
     */
    @Bean
    public ThymeleafCommonControllerAdvice thymeleafCommonControllerAdvice() {
        return new ThymeleafCommonControllerAdvice();
    }
}
