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

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.data.web.SortHandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jp.co.ntt.fw.spring.functionaltest.app.pgnt.PGNT_JSP_0102002Controller;
import jp.co.ntt.fw.spring.functionaltest.app.pgnt.PGNT_Thymeleaf_0102002Controller;

/**
 * Configure SpringMVC.
 */
@Configuration
@EnableWebMvc
@Import({ SpringMvcCommonConfig.class })
public class SpringMvcPgnt0102002Config implements WebMvcConfigurer {

    /**
     * Configure {@link PageableHandlerMethodArgumentResolver} bean.
     * @return Bean of configured {@link PageableHandlerMethodArgumentResolver}
     */
    @Bean("pageableHandlerMethodArgumentResolver")
    public PageableHandlerMethodArgumentResolver pageableHandlerMethodArgumentResolver() {
        PageableHandlerMethodArgumentResolver bean = new PageableHandlerMethodArgumentResolver(sortHandlerMethodArgumentResolver());
        bean.setFallbackPageable(pageRequest());
        return bean;
    }

    /**
     * Configure {@link PageRequest} bean.
     * @return Bean of configured {@link PageRequest}
     */
    @Bean
    public PageRequest pageRequest() {
        return PageRequest.of(0, 10);
    }

    /**
     * Configure {@link SortHandlerMethodArgumentResolver} bean.
     * @return Bean of configured {@link SortHandlerMethodArgumentResolver}
     */
    @Bean
    public SortHandlerMethodArgumentResolver sortHandlerMethodArgumentResolver() {
        SortHandlerMethodArgumentResolver bean = new SortHandlerMethodArgumentResolver();
        bean.setFallbackSort(sort());
        return bean;
    }

    /**
     * Configure {@link Sort} bean.
     * @return Bean of configured {@link Sort}
     */
    @Bean
    public Sort sort() {
        List<Order> list = new ArrayList<Order>();
        list.add(order());
        return Sort.by(list);
    }

    /**
     * Configure {@link Order} bean.
     * @return Bean of configured {@link Order}
     */
    @Bean
    public Order order() {
        return new Order(Direction.DESC, "article_id");
    }

    /**
     * Configure {@link PGNT_JSP_0102002Controller} bean.
     * @return Bean of configured {@link PGNT_JSP_0102002Controller}
     */
    @Bean
    public PGNT_JSP_0102002Controller pGNT_JSP_0102002Controller() {
        return new PGNT_JSP_0102002Controller();
    }

    /**
     * Configure {@link PGNT_Thymeleaf_0102002Controller} bean.
     * @return Bean of configured {@link PGNT_Thymeleaf_0102002Controller}
     */
    @Bean
    public PGNT_Thymeleaf_0102002Controller pGNT_Thymeleaf_0102002Controller() {
        return new PGNT_Thymeleaf_0102002Controller();
    }
}
