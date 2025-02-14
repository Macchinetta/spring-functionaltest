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
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import jp.co.ntt.fw.spring.functionaltest.app.thym.THYM0402Controller;
import jp.co.ntt.fw.spring.functionaltest.app.thym.THYM0701Controller;

/**
 * Configure SpringMVC.
 */
@ComponentScan(basePackages = {"jp.co.ntt.fw.spring.functionaltest.app"},
        excludeFilters = {
                @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = THYM0402Controller.class),
                @Filter(type = FilterType.REGEX,
                        pattern = "jp.co.ntt.fw.spring.functionaltest.app.thym.THYM050[1-2].*"),
                @Filter(type = FilterType.REGEX,
                        pattern = "jp.co.ntt.fw.spring.functionaltest.app.thym.THYM06.*"),
                @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = THYM0701Controller.class),
                @Filter(type = FilterType.REGEX,
                        pattern = "jp.co.ntt.fw.spring.functionaltest.app.thym.THYM0803.*")})
@Configuration
@EnableWebMvc
@Import({SpringMvcCommonConfig.class})
public class SpringMvcConfig implements WebMvcConfigurer {

    /**
     * {@inheritDoc}
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
}
