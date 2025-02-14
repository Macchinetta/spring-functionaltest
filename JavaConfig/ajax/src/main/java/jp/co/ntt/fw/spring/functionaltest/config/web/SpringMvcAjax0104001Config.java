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
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import jp.co.ntt.fw.spring.functionaltest.app.ajax.AJAX_JSP_0104001Controller;
import jp.co.ntt.fw.spring.functionaltest.app.ajax.AJAX_Thymeleaf_0104001Controller;
import jp.co.ntt.fw.spring.functionaltest.app.ajax.MessageBoardHelper;

/**
 * Configure SpringMVCAjax0104001.
 */
@Configuration
@EnableWebMvc
@Import({SpringMvcCommonConfig.class})
public class SpringMvcAjax0104001Config implements WebMvcConfigurer {

    /**
     * Configure {@link Jaxb2Marshaller} bean.
     * @return Bean of configured {@link Jaxb2Marshaller}
     */
    @Bean("xmlMarshaller")
    public Jaxb2Marshaller xmlMarshaller() {
        Jaxb2Marshaller bean = new Jaxb2Marshaller();
        bean.setPackagesToScan("jp.co.ntt.fw.spring.functionaltest.app.ajax");
        return bean;
    }

    /**
     * Configure {@link PageableHandlerMethodArgumentResolver} bean.
     * @return Bean of configured {@link PageableHandlerMethodArgumentResolver}
     */
    @Bean("pageableHandlerMethodArgumentResolver")
    public PageableHandlerMethodArgumentResolver pageableHandlerMethodArgumentResolver() {
        PageableHandlerMethodArgumentResolver bean = new PageableHandlerMethodArgumentResolver();
        bean.setMaxPageSize(5);
        return bean;
    }

    /**
     * Configure {@link MarshallingHttpMessageConverter} bean.
     * @return Bean of configured {@link MarshallingHttpMessageConverter}
     */
    @Bean("marshallingHttpMessageConverter")
    public MarshallingHttpMessageConverter marshallingHttpMessageConverter() {
        MarshallingHttpMessageConverter bean = new MarshallingHttpMessageConverter();
        bean.setMarshaller(xmlMarshaller());
        bean.setUnmarshaller(xmlMarshaller());
        return bean;
    }

    /**
     * Configure {@link AJAX_JSP_0104001Controller} bean.
     * @return Bean of configured {@link AJAX_JSP_0104001Controller}
     */
    @Bean
    public AJAX_JSP_0104001Controller aJAX_JSP_0104001Controller() {
        return new AJAX_JSP_0104001Controller();
    }

    /**
     * Configure {@link AJAX_Thymeleaf_0104001Controller} bean.
     * @return Bean of configured {@link AJAX_Thymeleaf_0104001Controller}
     */
    @Bean
    public AJAX_Thymeleaf_0104001Controller aJAX_Thymeleaf_0104001Controller() {
        return new AJAX_Thymeleaf_0104001Controller();
    }

    /**
     * Configure {@link MessageBoardHelper} bean.
     * @return Bean of configured {@link MessageBoardHelper}
     */
    @Bean
    public MessageBoardHelper messageBoardHelper() {
        return new MessageBoardHelper();
    }

}
