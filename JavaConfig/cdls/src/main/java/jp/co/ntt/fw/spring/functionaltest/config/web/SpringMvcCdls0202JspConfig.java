/*
 * Copyright(c) 2025 NTT Corporation.
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
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.terasoluna.gfw.web.codelist.CodeListInterceptor;
import jp.co.ntt.fw.spring.functionaltest.app.cdls.CDLS_JSP_0105Controller;
import jp.co.ntt.fw.spring.functionaltest.app.cdls.CDLS_JSP_01Controller;
import jp.co.ntt.fw.spring.functionaltest.app.cdls.CDLS_JSP_02Controller;
import jp.co.ntt.fw.spring.functionaltest.app.cdls.CDLS_JSP_03Controller;
import jp.co.ntt.fw.spring.functionaltest.app.cdls.CDLS_JSP_0401Controller;
import jp.co.ntt.fw.spring.functionaltest.app.cdls.CDLS_JSP_0402Controller;
import jp.co.ntt.fw.spring.functionaltest.app.cdls.CDLS_JSP_04Controller;
import jp.co.ntt.fw.spring.functionaltest.app.cdls.CDLS_JSP_05Controller;
import jp.co.ntt.fw.spring.functionaltest.app.cdls.CDLS_Thymeleaf_0105Controller;
import jp.co.ntt.fw.spring.functionaltest.app.cdls.CDLS_Thymeleaf_01Controller;
import jp.co.ntt.fw.spring.functionaltest.app.cdls.CDLS_Thymeleaf_0202Controller;
import jp.co.ntt.fw.spring.functionaltest.app.cdls.CDLS_Thymeleaf_02Controller;
import jp.co.ntt.fw.spring.functionaltest.app.cdls.CDLS_Thymeleaf_03Controller;
import jp.co.ntt.fw.spring.functionaltest.app.cdls.CDLS_Thymeleaf_0401Controller;
import jp.co.ntt.fw.spring.functionaltest.app.cdls.CDLS_Thymeleaf_0402Controller;
import jp.co.ntt.fw.spring.functionaltest.app.cdls.CDLS_Thymeleaf_04Controller;
import jp.co.ntt.fw.spring.functionaltest.app.cdls.CDLS_Thymeleaf_05Controller;

/**
 * Configure SpringMVC.
 */
@ComponentScan(basePackages = {"jp.co.ntt.fw.spring.functionaltest.app.cdls"}, excludeFilters = {
        @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = CDLS_JSP_0105Controller.class),
        @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = CDLS_JSP_01Controller.class),
        @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = CDLS_JSP_02Controller.class),
        @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = CDLS_JSP_03Controller.class),
        @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = CDLS_JSP_0401Controller.class),
        @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = CDLS_JSP_0402Controller.class),
        @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = CDLS_JSP_04Controller.class),
        @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = CDLS_JSP_05Controller.class),
        @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = CDLS_Thymeleaf_0105Controller.class),
        @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = CDLS_Thymeleaf_01Controller.class),
        @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = CDLS_Thymeleaf_0202Controller.class),
        @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = CDLS_Thymeleaf_02Controller.class),
        @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = CDLS_Thymeleaf_03Controller.class),
        @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = CDLS_Thymeleaf_0401Controller.class),
        @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = CDLS_Thymeleaf_0402Controller.class),
        @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = CDLS_Thymeleaf_04Controller.class),
        @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = CDLS_Thymeleaf_05Controller.class)})
@Configuration
@EnableWebMvc
@Import({SpringMvcCommonConfig.class})
public class SpringMvcCdls0202JspConfig implements WebMvcConfigurer {

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
}
