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

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import jp.co.ntt.fw.spring.functionaltest.app.intr.INTR_JSP_0102Controller;

/**
 * Configure SpringMVC.
 */
@Configuration
@EnableWebMvc
@Import({ SpringMvcCommonConfig.class })
public class SpringMvcIntrJsp0102Config implements WebMvcConfigurer {

    /**
     * Configure {@link LocaleChangeInterceptor} bean.
     * @return Bean of configured {@link LocaleChangeInterceptor}
     */
    @Bean("localeChangeInterceptor")
    public LocaleChangeInterceptor localeChangeInterceptor() {
        return new LocaleChangeInterceptor();
    }

    /**
     * Configure {@link AcceptHeaderLocaleResolver} bean.
     * @return Bean of configured {@link AcceptHeaderLocaleResolver}
     */
    @Bean("localeResolver")
    public AcceptHeaderLocaleResolver localeResolver() {
        return new AcceptHeaderLocaleResolver();
    }

    /**
     * Configure {@link INTR_JSP_0102Controller} bean.
     * @return Bean of configured {@link INTR_JSP_0102Controller}
     */
    @Bean
    public INTR_JSP_0102Controller iNTR_JSP_0102Controller() {
        return new INTR_JSP_0102Controller();
    }

}
