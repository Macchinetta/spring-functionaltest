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

import java.nio.file.AccessDeniedException;
import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.terasoluna.gfw.common.exception.ExceptionCodeResolver;
import org.terasoluna.gfw.web.exception.SystemExceptionResolver;

import jp.co.ntt.fw.spring.functionaltest.app.exhn.EXHN_JSP_0201001Controller;
import jp.co.ntt.fw.spring.functionaltest.app.exhn.EXHN_JSP_08Controller;
import jp.co.ntt.fw.spring.functionaltest.app.exhn.EXHN_Thymeleaf_0201001Controller;
import jp.co.ntt.fw.spring.functionaltest.app.exhn.EXHN_Thymeleaf_08Controller;

/**
 * Configure SpringMVC.
 */
@ComponentScan(basePackages = {
        "jp.co.ntt.fw.spring.functionaltest.app" }, excludeFilters = {
                @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = EXHN_JSP_0201001Controller.class),
                @Filter(type = FilterType.REGEX, pattern = "jp.co.ntt.fw.spring.functionaltest.app.exhn.EXHN_JSP_060100..*"),
                @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = EXHN_JSP_08Controller.class),
                @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = EXHN_Thymeleaf_0201001Controller.class),
                @Filter(type = FilterType.REGEX, pattern = "jp.co.ntt.fw.spring.functionaltest.app.exhn.EXHN_Thymeleaf_060100..*"),
                @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = EXHN_Thymeleaf_08Controller.class) })
@Configuration
@EnableWebMvc
@Import({ SpringMvcCommonConfig.class })
public class SpringMvcConfig implements WebMvcConfigurer {

    /**
     * {@inheritDoc}
     */
    @Override
    public void configureDefaultServletHandling(
            DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
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
        exceptionMappings.setProperty("BusinessException",
                "jsp/common/error/businessError");
        exceptionMappings.setProperty("InvalidTransactionTokenException",
                "jsp/common/error/transactionTokenError");
        exceptionMappings.setProperty(".DataAccessException",
                "jsp/common/error/dataAccessError");
        exceptionMappings.setProperty("InvalidRequestException",
                "jsp/common/error/operationError");
        exceptionMappings.setProperty("MultipartException",
                "jsp/common/error/fileUploadError");
        exceptionMappings.setProperty("HttpSessionRequiredException",
                "jsp/common/error/operationError");
        exceptionMappings.setProperty("IntentionalException",
                "jsp/common/error/intentionalError");
        bean.setExceptionMappings(exceptionMappings);

        Properties statusCodes = new Properties();
        statusCodes.setProperty("jsp/common/error/resourceNotFoundError", String
                .valueOf(HttpStatus.NOT_FOUND.value()));
        statusCodes.setProperty("jsp/common/error/businessError", String
                .valueOf(HttpStatus.CONFLICT.value()));
        statusCodes.setProperty("jsp/common/error/transactionTokenError", String
                .valueOf(HttpStatus.CONFLICT.value()));
        statusCodes.setProperty("jsp/common/error/dataAccessError", String
                .valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
        statusCodes.setProperty("jsp/common/error/operationError", String
                .valueOf(HttpStatus.BAD_REQUEST.value()));
        statusCodes.setProperty("jsp/common/error/fileUploadError", String
                .valueOf(HttpStatus.BAD_REQUEST.value()));
        statusCodes.setProperty("jsp/common/error/intentionalError", String
                .valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
        bean.setStatusCodes(statusCodes);

        bean.setExcludedExceptions(AccessDeniedException.class);

        bean.setDefaultErrorView("jsp/common/error/systemError");
        bean.setDefaultStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return bean;
    }
}