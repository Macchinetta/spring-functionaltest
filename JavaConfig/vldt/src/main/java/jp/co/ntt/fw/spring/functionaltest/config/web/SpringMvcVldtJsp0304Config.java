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

import java.util.List;
import java.util.Properties;
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
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.method.annotation.AuthenticationPrincipalArgumentResolver;
import org.springframework.security.web.servlet.support.csrf.CsrfRequestDataValueProcessor;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
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
import jakarta.inject.Inject;
import jp.co.ntt.fw.spring.functionaltest.app.vldt.CommonParametersMethodArgumentResolver;
import jp.co.ntt.fw.spring.functionaltest.app.vldt.DeliveryOrderFormMethodArgumentResolver;
import jp.co.ntt.fw.spring.functionaltest.app.vldt.DeliveryOrderListFormMethodArgumentResolver;
import jp.co.ntt.fw.spring.functionaltest.app.vldt.SuccessLoggingInterceptor;
import jp.co.ntt.fw.spring.functionaltest.app.vldt.VLDT_JSP_0304001Controller;

/**
 * Configure SpringMVC.
 */
@EnableAspectJAutoProxy
@EnableWebMvc
@Configuration
public class SpringMvcVldtJsp0304Config implements WebMvcConfigurer {

    /**
     * Bean of LocalValidatorFactoryBean.
     */
    @Inject
    private LocalValidatorFactoryBean notAsciiValidator;

    /**
     * upload temporaryDirectory property.
     */
    @Value("${app.upload.temporaryDirectory}")
    private String temporaryDirectory;

    /**
     * upload directory property.
     */
    @Value("${app.upload.directory}")
    private String directory;

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
    public Validator getValidator() {
        return notAsciiValidator;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(pageableHandlerMethodArgumentResolver());
        argumentResolvers.add(commonParametersMethodArgumentResolver());
        argumentResolvers.add(deliveryOrderFormMethodArgumentResolver());
        argumentResolvers.add(deliveryOrderListFormMethodArgumentResolver());
        argumentResolvers.add(authenticationPrincipalArgumentResolver());
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
     * Configure {@link DeliveryOrderFormMethodArgumentResolver} bean.
     * @return Bean of configured {@link DeliveryOrderFormMethodArgumentResolver}
     */
    @Bean
    public DeliveryOrderFormMethodArgumentResolver deliveryOrderFormMethodArgumentResolver() {
        return new DeliveryOrderFormMethodArgumentResolver();
    }

    /**
     * Configure {@link DeliveryOrderListFormMethodArgumentResolver} bean.
     * @return Bean of configured {@link DeliveryOrderListFormMethodArgumentResolver}
     */
    @Bean
    public DeliveryOrderListFormMethodArgumentResolver deliveryOrderListFormMethodArgumentResolver() {
        return new DeliveryOrderListFormMethodArgumentResolver();
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
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(marshallingHttpMessageConverter());
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
        registry.addResourceHandler("/uploadedTemporaryFiles/**")
                .addResourceLocations("file://" + temporaryDirectory).setCachePeriod(60 * 60);
        registry.addResourceHandler("/uploadedFiles/**").addResourceLocations("file://" + directory)
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
        addInterceptor(registry, successLoggingInterceptor(), "/aply/0402/**", null);
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
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(interceptor);
        interceptorRegistration.addPathPatterns(path);
        if (excludePath != null) {
            interceptorRegistration.excludePathPatterns(excludePath);
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
     * Configure {@link CodeListInterceptor} bean.
     * @return Bean of configured {@link CodeListInterceptor}
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
        registry.beanName();
        registry.jsp("/WEB-INF/views/", ".jsp");
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

        bean.setExcludedExceptions(AccessDeniedException.class);

        Properties exceptionMappings = new Properties();
        exceptionMappings.setProperty("ResourceNotFoundException",
                "common/error/resourceNotFoundError");
        exceptionMappings.setProperty("BusinessException", "common/error/businessError");
        exceptionMappings.setProperty("InvalidTransactionTokenException",
                "common/error/transactionTokenError");
        exceptionMappings.setProperty(".DataAccessException", "common/error/dataAccessError");
        exceptionMappings.setProperty("InvalidRequestException", "common/error/operationError");
        exceptionMappings.setProperty("MultipartException", "common/error/fileUploadError");
        exceptionMappings.setProperty("HttpSessionRequiredException",
                "common/error/operationError");
        exceptionMappings.setProperty("IntentionalException", "common/error/intentionalError");
        bean.setExceptionMappings(exceptionMappings);

        Properties statusCodes = new Properties();
        statusCodes.setProperty("common/error/resourceNotFoundError",
                String.valueOf(HttpStatus.NOT_FOUND.value()));
        statusCodes.setProperty("common/error/businessError",
                String.valueOf(HttpStatus.CONFLICT.value()));
        statusCodes.setProperty("common/error/transactionTokenError",
                String.valueOf(HttpStatus.CONFLICT.value()));
        statusCodes.setProperty("common/error/dataAccessError",
                String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
        statusCodes.setProperty("common/error/operationError",
                String.valueOf(HttpStatus.BAD_REQUEST.value()));
        statusCodes.setProperty("common/error/fileUploadError",
                String.valueOf(HttpStatus.BAD_REQUEST.value()));
        statusCodes.setProperty("common/error/intentionalError",
                String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
        bean.setStatusCodes(statusCodes);

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
     * Configure {@link StandardServletMultipartResolver} bean.
     * @return Bean of configured {@link StandardServletMultipartResolver}
     */
    @Bean("multipartResolver")
    public StandardServletMultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }

    /**
     * Configure {@link MethodValidationPostProcessor} bean.
     * @return Bean of configured {@link MethodValidationPostProcessor}
     */
    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        MethodValidationPostProcessor bean = new MethodValidationPostProcessor();
        bean.setValidator(notAsciiValidator);
        return bean;
    }

    /**
     * Configure {@link PageableHandlerMethodArgumentResolver} bean.
     * @return Bean of configured {@link PageableHandlerMethodArgumentResolver}
     */
    @Bean("pageableHandlerMethodArgumentResolver")
    public PageableHandlerMethodArgumentResolver pageableHandlerMethodArgumentResolver() {
        return new PageableHandlerMethodArgumentResolver();
    }

    /**
     * Configure {@link MarshallingHttpMessageConverter} bean.
     * @return Bean of configured {@link MarshallingHttpMessageConverter}
     */
    @Bean("marshallingHttpMessageConverter")
    public MarshallingHttpMessageConverter marshallingHttpMessageConverter() {
        return new MarshallingHttpMessageConverter();
    }

    /**
     * Configure {@link VLDT_JSP_0304001Controller} bean.
     * @return Bean of configured {@link VLDT_JSP_0304001Controller}
     */
    @Bean
    public VLDT_JSP_0304001Controller vLDT_JSP_0304001Controller() {
        return new VLDT_JSP_0304001Controller();
    }
}
