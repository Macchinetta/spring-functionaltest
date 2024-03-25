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
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import jp.co.ntt.fw.spring.functionaltest.app.intr.INTR_Thymeleaf_0201008Controller;

/**
 * Configure SpringMVC.
 */
@Configuration
@EnableWebMvc
@Import({ SpringMvcCommonConfig.class })
public class SpringMvcIntrThymeleaf0201008Config implements WebMvcConfigurer {

    /**
     * Configure {@link LocaleChangeInterceptor} bean.
     * @return Bean of configured {@link LocaleChangeInterceptor}
     */
    @Bean("localeChangeInterceptor")
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor bean = new LocaleChangeInterceptor();
        bean.setIgnoreInvalidLocale(true);
        return bean;
    }

    /**
     * Configure {@link SessionLocaleResolver} bean.
     * @return Bean of configured {@link SessionLocaleResolver}
     */
    @Bean("localeResolver")
    public SessionLocaleResolver localeResolver() {
        return new SessionLocaleResolver();
    }

    /**
     * Configure {@link INTR_Thymeleaf_0201008Controller} bean.
     * @return Bean of configured {@link INTR_Thymeleaf_0201008Controller}
     */
    @Bean
    public INTR_Thymeleaf_0201008Controller iNTR_Thymeleaf_0201008Controller() {
        return new INTR_Thymeleaf_0201008Controller();
    }

}
