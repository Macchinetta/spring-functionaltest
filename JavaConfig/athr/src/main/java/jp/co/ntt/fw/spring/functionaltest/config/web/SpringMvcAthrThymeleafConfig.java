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

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configure SpringMVC.
 */
@ComponentScan(basePackages = {"jp.co.ntt.fw.spring.functionaltest.app.athr"},
        excludeFilters = {@Filter(type = FilterType.REGEX,
                pattern = "jp.co.ntt.fw.spring.functionaltest.app.athr.ATHR_JSP_..*")})
@Configuration
@EnableWebMvc
@Import({SpringMvcCommonConfig.class})
public class SpringMvcAthrThymeleafConfig implements WebMvcConfigurer {

    /**
     * {@inheritDoc}
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/thymeleaf/loginForStaff")
                .setViewName("thymeleaf/athr/loginForStaff");
        registry.addViewController("/thymeleaf/loginForRoleAdmin")
                .setViewName("thymeleaf/athr/loginForRoleAdmin");
        registry.addViewController("/thymeleaf/loginForMethod")
                .setViewName("thymeleaf/athr/loginForMethod");
        registry.addViewController("/thymeleaf/loginForRoleHierarchy")
                .setViewName("thymeleaf/athr/loginForRoleHierarchy");
        registry.addViewController("/thymeleaf/loginForJspTagRoleAdmin")
                .setViewName("thymeleaf/athr/loginForJspTagRoleAdmin");
        registry.addViewController("/thymeleaf/methodAccessAllowedPage")
                .setViewName("thymeleaf/athr/methodAccessAllowedPage");
        registry.addViewController("/thymeleaf/methodAccessDeniedPage")
                .setViewName("thymeleaf/athr/methodAccessDeniedPage");
        registry.addViewController("/thymeleaf/hierarchyAccessDeniedPage")
                .setViewName("thymeleaf/athr/hierarchyAccessDeniedPage");
        registry.addViewController("/thymeleaf/accessDeniedPage")
                .setViewName("thymeleaf/athr/accessDeniedPage");
    }
}
