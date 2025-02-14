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
import jakarta.inject.Inject;
import jp.co.ntt.fw.spring.functionaltest.app.thym.THYM0803007Controller;

/**
 * Configure SpringMVC.
 */
@Configuration
@EnableWebMvc
@Import({SpringMvcCommonConfig.class})
public class SpringMvcThym0803007Config implements WebMvcConfigurer {

    /**
     * Bean of SpringTemplateEngine.
     */
    @Inject
    private SpringTemplateEngine templateEngine;

    /**
     * {@inheritDoc}
     */
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.beanName();
        registry.viewResolver(thymeleafViewResolver());
        registry.viewResolver(jsThymeleafViewResolver());
    }

    /**
     * Configure Thymeleaf bean.
     * @return Bean of configured ThymeleafViewResolver
     */
    @Bean
    public ThymeleafViewResolver thymeleafViewResolver() {
        ThymeleafViewResolver bean = new ThymeleafViewResolver();
        bean.setTemplateEngine(templateEngine);
        bean.setCharacterEncoding("UTF-8");
        bean.setForceContentType(true);
        bean.setContentType("text/html;charset=UTF-8");
        bean.setExcludedViewNames(new String[] {"*.js"});
        return bean;
    }

    /**
     * Configure Thymeleaf bean.
     * @return Bean of configured ThymeleafViewResolver
     */
    @Bean
    public ThymeleafViewResolver jsThymeleafViewResolver() {
        ThymeleafViewResolver bean = new ThymeleafViewResolver();
        bean.setTemplateEngine(jsTemplateEngine());
        bean.setCharacterEncoding("UTF-8");
        bean.setForceContentType(true);
        bean.setContentType("text/javascript;charset=UTF-8");
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
        return bean;
    }

    /**
     * Configure SpringTemplateEngine Bean.
     * @return Bean of configured SpringTemplateEngine
     */
    @Bean("jsTemplateEngine")
    public SpringTemplateEngine jsTemplateEngine() {
        SpringTemplateEngine bean = new SpringTemplateEngine();
        bean.setEnableSpringELCompiler(true);
        bean.setTemplateResolver(jsTemplateResolver());
        Set<IDialect> set = new HashSet<>();
        set.add(new SpringSecurityDialect());
        bean.setAdditionalDialects(set);
        return bean;
    }

    /**
     * Configure {@link THYM0803007Controller} bean.
     * @return Bean of configured {@link THYM0803007Controller}
     */
    @Bean
    public THYM0803007Controller tHYM0803007Controller() {
        return new THYM0803007Controller();
    }
}
