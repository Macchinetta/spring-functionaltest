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
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jp.co.ntt.fw.spring.functionaltest.app.ssmn.Cart;
import jp.co.ntt.fw.spring.functionaltest.app.ssmn.EnableSynchronizeOnSessionPostProcessor;

/**
 * Configure SpringMVC.
 */
@ComponentScan(basePackages = {
        "jp.co.ntt.fw.spring.functionaltest.app.ssmn" }, excludeFilters = {
                @Filter(type = FilterType.REGEX, pattern = "jp.co.ntt.fw.spring.functionaltest.app.ssmn.SSMN_JSP_03..*"),
                @Filter(type = FilterType.REGEX, pattern = "jp.co.ntt.fw.spring.functionaltest.app.ssmn.SSMN_JSP_04..*"),
                @Filter(type = FilterType.REGEX, pattern = "jp.co.ntt.fw.spring.functionaltest.app.ssmn.SSMN_JSP_06..*"),
                @Filter(type = FilterType.REGEX, pattern = "jp.co.ntt.fw.spring.functionaltest.app.ssmn.SSMN_Thymeleaf_03..*"),
                @Filter(type = FilterType.REGEX, pattern = "jp.co.ntt.fw.spring.functionaltest.app.ssmn.SSMN_Thymeleaf_04..*"),
                @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = Cart.class) })
@Configuration
@EnableWebMvc
@Import({ SpringMvcCommonConfig.class })
public class SpringMvcSsmnThymeleaf0601001Config implements WebMvcConfigurer {

    /**
     * Configure {@link EnableSynchronizeOnSessionPostProcessor} bean.
     * @return Bean of configured {@link EnableSynchronizeOnSessionPostProcessor}
     */
    @Bean
    public EnableSynchronizeOnSessionPostProcessor enableSynchronizeOnSessionPostProcessor() {
        return new EnableSynchronizeOnSessionPostProcessor();
    }
}