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

import java.util.regex.Pattern;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.terasoluna.gfw.web.codelist.CodeListInterceptor;
import jp.co.ntt.fw.spring.functionaltest.app.cdls.CDLS_JSP_01Controller;
import jp.co.ntt.fw.spring.functionaltest.app.cdls.CDLS_JSP_02Controller;
import jp.co.ntt.fw.spring.functionaltest.app.cdls.CDLS_JSP_03Controller;
import jp.co.ntt.fw.spring.functionaltest.app.cdls.CDLS_JSP_04Controller;
import jp.co.ntt.fw.spring.functionaltest.app.cdls.CDLS_Thymeleaf_01Controller;
import jp.co.ntt.fw.spring.functionaltest.app.cdls.CDLS_Thymeleaf_02Controller;
import jp.co.ntt.fw.spring.functionaltest.app.cdls.CDLS_Thymeleaf_03Controller;
import jp.co.ntt.fw.spring.functionaltest.app.cdls.CDLS_Thymeleaf_04Controller;
import jp.co.ntt.fw.spring.functionaltest.config.app.SpringFunctionaltestWebCdlsCodelistConfig;

/**
 * Configure SpringMVC.
 */
@Configuration
@EnableWebMvc
@Import({SpringMvcCommonConfig.class, SpringFunctionaltestWebCdlsCodelistConfig.class})
// Definitions that are not imported from anywhere and are not used
// Save the file itself, but deprecate it.
@Deprecated
public class SpringMvcCdlsConfig implements WebMvcConfigurer {

    /**
     * {@inheritDoc}
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        addInterceptor(registry, localeChangeInterceptor());
        addInterceptor(registry, codeListInterceptor());
    }

    /**
     * Common processes used in #addInterceptors.
     * @param registry {@link InterceptorRegistry}
     * @param interceptor {@link HandlerInterceptor}
     */
    private void addInterceptor(InterceptorRegistry registry, HandlerInterceptor interceptor) {
        registry.addInterceptor(interceptor).addPathPatterns("/**")
                .excludePathPatterns("/resources/**");
    }

    /**
     * Configure {@link LocaleChangeInterceptor} bean.
     * @return Bean of configured {@link LocaleChangeInterceptor}
     */
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        return new LocaleChangeInterceptor();
    }

    /**
     * Configure {@link CodeListInterceptor} bean.
     * @return Bean of configured {@link CodeListInterceptor}
     */
    @Bean
    public CodeListInterceptor codeListInterceptor() {
        CodeListInterceptor bean = new CodeListInterceptor();
        bean.setCodeListIdPattern(Pattern.compile("CL_.+"));
        return bean;
    }

    /**
     * Configure {@link CookieLocaleResolver} bean.
     * @return Bean of configured {@link CookieLocaleResolver}
     */
    @Bean("localeResolver")
    public CookieLocaleResolver localeResolver() {
        return new CookieLocaleResolver();
    }

    /**
     * Configure {@link CDLS_JSP_01Controller} bean.
     * @return Bean of configured {@link CDLS_JSP_01Controller}
     */
    @Bean
    public CDLS_JSP_01Controller cDLS_JSP_01Controller() {
        return new CDLS_JSP_01Controller();
    }

    /**
     * Configure {@link CDLS_JSP_02Controller} bean.
     * @return Bean of configured {@link CDLS_JSP_02Controller}
     */
    @Bean
    public CDLS_JSP_02Controller cDLS_JSP_02Controller() {
        return new CDLS_JSP_02Controller();
    }

    /**
     * Configure {@link CDLS_JSP_03Controller} bean.
     * @return Bean of configured {@link CDLS_JSP_03Controller}
     */
    @Bean
    public CDLS_JSP_03Controller cDLS_JSP_03Controller() {
        return new CDLS_JSP_03Controller();
    }

    /**
     * Configure {@link CDLS_JSP_04Controller} bean.
     * @return Bean of configured {@link CDLS_JSP_04Controller}
     */
    @Bean
    public CDLS_JSP_04Controller cDLS_JSP_04Controller() {
        return new CDLS_JSP_04Controller();
    }

    /**
     * Configure {@link CDLS_Thymeleaf_01Controller} bean.
     * @return Bean of configured {@link CDLS_Thymeleaf_01Controller}
     */
    @Bean
    public CDLS_Thymeleaf_01Controller cDLS_Thymeleaf_01Controller() {
        return new CDLS_Thymeleaf_01Controller();
    }

    /**
     * Configure {@link CDLS_Thymeleaf_02Controller} bean.
     * @return Bean of configured {@link CDLS_Thymeleaf_02Controller}
     */
    @Bean
    public CDLS_Thymeleaf_02Controller cDLS_Thymeleaf_02Controller() {
        return new CDLS_Thymeleaf_02Controller();
    }

    /**
     * Configure {@link CDLS_Thymeleaf_03Controller} bean.
     * @return Bean of configured {@link CDLS_Thymeleaf_03Controller}
     */
    @Bean
    public CDLS_Thymeleaf_03Controller cDLS_Thymeleaf_03Controller() {
        return new CDLS_Thymeleaf_03Controller();
    }

    /**
     * Configure {@link CDLS_Thymeleaf_04Controller} bean.
     * @return Bean of configured {@link CDLS_Thymeleaf_04Controller}
     */
    @Bean
    public CDLS_Thymeleaf_04Controller cDLS_Thymeleaf_04Controller() {
        return new CDLS_Thymeleaf_04Controller();
    }
}
