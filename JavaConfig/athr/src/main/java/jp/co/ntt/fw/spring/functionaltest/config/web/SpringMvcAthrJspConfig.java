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
                pattern = "jp.co.ntt.fw.spring.functionaltest.app.athr.ATHR_Thymeleaf_..*")})
@Configuration
@EnableWebMvc
@Import({SpringMvcCommonConfig.class})
public class SpringMvcAthrJspConfig implements WebMvcConfigurer {

    /**
     * {@inheritDoc}
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/jsp/loginForStaff").setViewName("jsp/athr/loginForStaff");
        registry.addViewController("/jsp/loginForRoleAdmin")
                .setViewName("jsp/athr/loginForRoleAdmin");
        registry.addViewController("/jsp/loginForMethod").setViewName("jsp/athr/loginForMethod");
        registry.addViewController("/jsp/loginForRoleHierarchy")
                .setViewName("jsp/athr/loginForRoleHierarchy");
        registry.addViewController("/jsp/loginForJspTagRoleAdmin")
                .setViewName("jsp/athr/loginForJspTagRoleAdmin");
        registry.addViewController("/jsp/methodAccessAllowedPage")
                .setViewName("jsp/athr/methodAccessAllowedPage");
        registry.addViewController("/jsp/methodAccessDeniedPage")
                .setViewName("jsp/athr/methodAccessDeniedPage");
        registry.addViewController("/jsp/hierarchyAccessDeniedPage")
                .setViewName("jsp/athr/hierarchyAccessDeniedPage");
        registry.addViewController("/jsp/accessDeniedPage")
                .setViewName("jsp/athr/accessDeniedPage");
    }
}
