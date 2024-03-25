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
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.dialect.IDialect;
import org.thymeleaf.extras.springsecurity6.dialect.SpringSecurityDialect;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

import jp.co.ntt.fw.spring.functionaltest.app.thym.THYM0803004Controller;

/**
 * Configure SpringMVC.
 */
@Configuration
@EnableWebMvc
@Import({ SpringMvcCommonConfig.class })
public class SpringMvcThym0803004Config implements WebMvcConfigurer {

    /**
     * {@inheritDoc}
     */
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.beanName();
        registry.viewResolver(thymeleafViewResolver());
    }

    /**
     * Configure Thymeleaf bean.
     * @return Bean of configured ThymeleafViewResolver
     */
    @Bean
    public ThymeleafViewResolver thymeleafViewResolver() {
        ThymeleafViewResolver bean = new ThymeleafViewResolver();
        bean.setTemplateEngine(templateEngine());
        bean.setCharacterEncoding("UTF-8");
        return bean;
    }

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
        bean.setOrder(1);
        bean.setCheckExistence(true);
        return bean;
    }

    /**
     * Configure ITemplateResolver Bean.
     * @return Bean of configured SpringResourceTemplateResolver
     */
    @Bean("jsTemplateResolver")
    public ITemplateResolver jsTemplateResolver() {
        SpringResourceTemplateResolver bean = new SpringResourceTemplateResolver();
        bean.setPrefix("/WEB-INF/js/");
        bean.setSuffix(".js");
        bean.setTemplateMode("JAVASCRIPT");
        bean.setCharacterEncoding("UTF-8");
        bean.setOrder(2);
        return bean;
    }

    /**
     * Configure SpringTemplateEngine Bean.
     * @return Bean of configured SpringTemplateEngine
     */
    @Bean("templateEngine")
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine bean = new SpringTemplateEngine();
        bean.setEnableSpringELCompiler(true);
        Set<ITemplateResolver> resolvers = new HashSet<>();
        resolvers.add(templateResolver());
        resolvers.add(jsTemplateResolver());
        bean.setTemplateResolvers(resolvers);
        Set<IDialect> set = new HashSet<>();
        set.add(new SpringSecurityDialect());
        bean.setAdditionalDialects(set);
        return bean;
    }

    /**
     * Configure {@link THYM0803004Controller} bean.
     * @return Bean of configured {@link THYM0803004Controller}
     */
    @Bean
    public THYM0803004Controller tHYM0803004Controller() {
        return new THYM0803004Controller();
    }
}
