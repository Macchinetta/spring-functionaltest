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

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

import jp.co.ntt.fw.spring.functionaltest.app.thym.THYM0603002Controller;

/**
 * Configure SpringMVC.
 */
@Configuration
@EnableWebMvc
@Import({ SpringMvcCommonConfig.class })
public class SpringMvcThym0603002Config implements WebMvcConfigurer {

    /**
     * Configure ITemplateResolver Bean.
     * @return Bean of configured SpringResourceTemplateResolver
     */
    @Bean("templateResolver")
    public ITemplateResolver templateResolver() {
        SpringResourceTemplateResolver bean = new SpringResourceTemplateResolver();
        bean.setPrefix("/WEB-INF/views/");
        bean.setSuffix(".html");
        bean.setTemplateMode("HTML");
        bean.setCharacterEncoding("UTF-8");
        Set<String> patterns = new LinkedHashSet<String>();
        patterns.add("thym/nonCacheablePatterns02");
        bean.setNonCacheablePatterns(patterns);
        return bean;
    }

    /**
     * Configure {@link THYM0603002Controller} bean.
     * @return Bean of configured {@link THYM0603002Controller}
     */
    @Bean
    public THYM0603002Controller tHYM0603002Controller() {
        return new THYM0603002Controller();
    }
}
