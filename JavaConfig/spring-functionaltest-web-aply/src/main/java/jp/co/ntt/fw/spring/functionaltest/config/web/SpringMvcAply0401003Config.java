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
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jp.co.ntt.fw.spring.functionaltest.app.aply.APLYController;
import jp.co.ntt.fw.spring.functionaltest.app.aply.APLY_JSP_02Controller;
import jp.co.ntt.fw.spring.functionaltest.app.aply.APLY_JSP_03Controller;
import jp.co.ntt.fw.spring.functionaltest.app.aply.APLY_JSP_04Controller;
import jp.co.ntt.fw.spring.functionaltest.app.aply.APLY_Thymeleaf_02Controller;
import jp.co.ntt.fw.spring.functionaltest.app.aply.APLY_Thymeleaf_03Controller;
import jp.co.ntt.fw.spring.functionaltest.app.aply.APLY_Thymeleaf_04Controller;

/**
 * Configure SpringMVC.
 */
@ComponentScan(basePackages = {
        "jp.co.ntt.fw.spring.functionaltest.app.aply" }, excludeFilters = {
                @Filter(type = FilterType.REGEX, pattern = "jp.co.ntt.fw.spring.functionaltest.app.aply.APLY_JSP_01..*"),
                @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = APLY_JSP_02Controller.class),
                @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = APLY_JSP_03Controller.class),
                @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = APLY_JSP_04Controller.class),
                @Filter(type = FilterType.REGEX, pattern = "jp.co.ntt.fw.spring.functionaltest.app.aply.APLY_Thymeleaf_01..*"),
                @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = APLY_Thymeleaf_02Controller.class),
                @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = APLY_Thymeleaf_03Controller.class),
                @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = APLY_Thymeleaf_04Controller.class),
                @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = APLYController.class),
                @Filter(type = FilterType.REGEX, pattern = "jp.co.ntt.fw.spring.functionaltest.app.aply.aply040100[4-9].*") })
@Configuration
@EnableWebMvc
@Import({ SpringMvcCommonConfig.class })
public class SpringMvcAply0401003Config implements WebMvcConfigurer {
}
