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

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.cache.StandardCacheManager;
import org.thymeleaf.dialect.IDialect;
import org.thymeleaf.extras.springsecurity6.dialect.SpringSecurityDialect;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ITemplateResolver;

import jakarta.inject.Inject;
import jp.co.ntt.fw.spring.functionaltest.app.thym.THYM0605004Controller;

/**
 * Configure SpringMVC.
 */
@Configuration
@EnableWebMvc
@Import({ SpringMvcCommonConfig.class })
public class SpringMvcThym0605004Config implements WebMvcConfigurer {

    /**
     * Bean of ITemplateResolver.
     */
    @Inject
    private ITemplateResolver templateResolver;

    /**
     * Configure SpringTemplateEngine Bean.
     * @return Bean of configured SpringTemplateEngine
     */
    @Bean("templateEngine")
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine bean = new SpringTemplateEngine();
        bean.setEnableSpringELCompiler(true);
        bean.setTemplateResolver(templateResolver);
        Set<IDialect> set = new HashSet<>();
        set.add(new SpringSecurityDialect());
        bean.setAdditionalDialects(set);
        bean.setCacheManager(cacheManager());
        return bean;
    }

    /**
     * Configure {@link StandardCacheManager} bean.
     * @return Bean of configured {@link StandardCacheManager}
     */
    @Bean("cacheManager")
    public StandardCacheManager cacheManager() {
        StandardCacheManager bean = new StandardCacheManager();
        bean.setTemplateCacheMaxSize(0);
        return bean;
    }

    /**
     * Configure {@link THYM0605004Controller} bean.
     * @return Bean of configured {@link THYM0605004Controller}
     */
    @Bean
    public THYM0605004Controller tHYM0605004Controller() {
        return new THYM0605004Controller();
    }
}
