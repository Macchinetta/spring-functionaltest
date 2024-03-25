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

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.io.Resource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.terasoluna.gfw.common.exception.ExceptionCodeResolver;
import org.terasoluna.gfw.common.exception.ExceptionLogger;
import org.terasoluna.gfw.common.exception.SimpleMappingExceptionCodeResolver;
import org.terasoluna.gfw.web.exception.ExceptionLoggingFilter;

/**
 * Application context.
 */
@Configuration
@EnableAspectJAutoProxy
@Import({ SpringFunctionaltestWebRestDomainConfig.class })
public class ApplicationContextConfig {

    /**
     * Configure {@link PasswordEncoder} bean.
     * @return Bean of configured {@link DelegatingPasswordEncoder}
     */
    @Bean("passwordEncoder")
    public PasswordEncoder passwordEncoder() {
        Map<String, PasswordEncoder> idToPasswordEncoder = new HashMap<>();
        idToPasswordEncoder.put("pbkdf2", pbkdf2PasswordEncoder());
        idToPasswordEncoder.put("bcrypt", bCryptPasswordEncoder());
        // When using commented out PasswordEncoders, you need to add bcprov-jdk18on.jar to the dependency.
        // idToPasswordEncoder.put("argon2", argon2PasswordEncoder());
        // idToPasswordEncoder.put("scrypt", sCryptPasswordEncoder());
        return new DelegatingPasswordEncoder("pbkdf2", idToPasswordEncoder);
    }

    /**
     * Configure {@link Pbkdf2PasswordEncoder} bean.
     * @return Bean of configured {@link Pbkdf2PasswordEncoder}
     */
    @Bean
    public Pbkdf2PasswordEncoder pbkdf2PasswordEncoder() {
        return Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8();
    }

    /**
     * Configure {@link BCryptPasswordEncoder} bean.
     * @return Bean of configured {@link BCryptPasswordEncoder}
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // When using commented out PasswordEncoders, you need to add bcprov-jdk18on.jar to the dependency.

//     @Bean
//     public Argon2PasswordEncoder argon2PasswordEncoder() {
//         return Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8();
//     }

//     @Bean
//     public SCryptPasswordEncoder sCryptPasswordEncoder() {
//         return SCryptPasswordEncoder.defaultsForSpringSecurity_v5_8();
//     }

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
     * Configure {@link MessageSource} bean.
     * @return Bean of configured {@link ResourceBundleMessageSource}
     */
    @Bean("messageSource")
    public MessageSource messageSource() {
        ResourceBundleMessageSource bean = new ResourceBundleMessageSource();
        bean.setBasenames("i18n/application-messages",
                "i18n/application-messages-rest");
        return bean;
    }

    /**
     * Configure {@link ExceptionCodeResolver} bean.
     * @return Bean of configured {@link SimpleMappingExceptionCodeResolver}
     */
    @Bean("exceptionCodeResolver")
    public ExceptionCodeResolver exceptionCodeResolver() {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("ResourceNotFoundException", "e.sf.cmmn.5001");
        map.put("MultipartException", "w.sf.cmmn.5002");
        map.put("BusinessException", "e.sf.cmmn.8001");
        map.put("InvalidRequestException", "e.sf.cmmn.8002");
        map.put("HttpSessionRequiredException", "e.sf.cmmn.8002");
        map.put("IntentionalException", "e.sf.cmmn.8003");
        map.put("HttpRequestMethodNotSupportedException", "e.sf.cmmn.6001");
        map.put("MediaTypeNotAcceptableException", "e.sf.cmmn.6002");
        map.put("HttpMediaTypeNotSupportedException", "e.sf.cmmn.6003");
        map.put("MethodArgumentNotValidException", "e.sf.cmmn.7002");
        map.put("JsonParseException", "e.sf.cmmn.7003");
        map.put("UnrecognizedPropertyException", "e.sf.cmmn.7004");
        map.put("JsonMappingException", "e.sf.cmmn.7005");
        map.put("TypeMismatchException", "e.sf.cmmn.7006");
        map.put("OptimisticLockingFailureException", "e.sf.cmmn.8006");
        map.put("PessimisticLockingFailureException", "e.sf.cmmn.8006");
        map.put("DataAccessException", "e.sf.cmmn.9009");
        map.put("MappingException", "e.sf.bnmp.0001");
        SimpleMappingExceptionCodeResolver bean = new SimpleMappingExceptionCodeResolver();
        bean.setExceptionMappings(map);
        bean.setDefaultExceptionCode("e.sf.cmmn.9001");
        return bean;
    }

    /**
     * Configure {@link ExceptionLogger} bean.
     * @return Bean of configured {@link ExceptionLogger}
     */
    @Bean("exceptionLogger")
    public ExceptionLogger exceptionLogger() {
        ExceptionLogger bean = new ExceptionLogger();
        bean.setExceptionCodeResolver(exceptionCodeResolver());
        return bean;
    }

    /**
     * Configure {@link ExceptionLoggingFilter} bean.
     * @return Bean of configured {@link ExceptionLoggingFilter}
     */
    @Bean("exceptionLoggingFilter")
    public ExceptionLoggingFilter exceptionLoggingFilter() {
        ExceptionLoggingFilter bean = new ExceptionLoggingFilter();
        bean.setExceptionLogger(exceptionLogger());
        return bean;
    }
}
