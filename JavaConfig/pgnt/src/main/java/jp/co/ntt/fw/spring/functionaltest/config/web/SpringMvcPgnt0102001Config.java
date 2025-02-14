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
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import jp.co.ntt.fw.spring.functionaltest.app.pgnt.PGNT_JSP_0102001Controller;
import jp.co.ntt.fw.spring.functionaltest.app.pgnt.PGNT_Thymeleaf_0102001Controller;

/**
 * Configure SpringMVC.
 */
@Configuration
@EnableWebMvc
@Import({SpringMvcCommonConfig.class})
public class SpringMvcPgnt0102001Config implements WebMvcConfigurer {

    /**
     * Configure {@link PageableHandlerMethodArgumentResolver} bean.
     * @return Bean of configured {@link PageableHandlerMethodArgumentResolver}
     */
    @Bean("pageableHandlerMethodArgumentResolver")
    public PageableHandlerMethodArgumentResolver pageableHandlerMethodArgumentResolver() {
        PageableHandlerMethodArgumentResolver bean = new PageableHandlerMethodArgumentResolver();
        bean.setMaxPageSize(5);
        return bean;
    }

    /**
     * Configure {@link PGNT_JSP_0102001Controller} bean.
     * @return Bean of configured {@link PGNT_JSP_0102001Controller}
     */
    @Bean
    public PGNT_JSP_0102001Controller pGNT_JSP_0102001Controller() {
        return new PGNT_JSP_0102001Controller();
    }

    /**
     * Configure {@link PGNT_Thymeleaf_0102001Controller} bean.
     * @return Bean of configured {@link PGNT_Thymeleaf_0102001Controller}
     */
    @Bean
    public PGNT_Thymeleaf_0102001Controller pGNT_Thymeleaf_0102001Controller() {
        return new PGNT_Thymeleaf_0102001Controller();
    }
}
