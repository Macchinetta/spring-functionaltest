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
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.terasoluna.gfw.common.exception.ExceptionCodeResolver;
import org.terasoluna.gfw.common.exception.ExceptionLogger;
import jp.co.ntt.fw.spring.functionaltest.app.lggn.LGGN_JSP_0402001Controller;

/**
 * Configure SpringMVC.
 */
@Configuration
@EnableWebMvc
@Import({SpringMvcCommonConfig.class})
public class SpringMvcLggn0402001Config implements WebMvcConfigurer {

    /**
     * Configure {@link LGGN_JSP_0402001Controller} bean.
     * @return Bean of configured {@link LGGN_JSP_0402001Controller}
     */
    @Bean
    public LGGN_JSP_0402001Controller lGGN_JSP_0402001Controller() {
        return new LGGN_JSP_0402001Controller();
    }

    /**
     * Configure {@link ExceptionLogger} bean.
     * @param exceptionCodeResolver Bean defined by ApplicationContextConfig#exceptionCodeResolver
     * @return Bean of configured {@link ExceptionLogger}
     */
    @Bean("exceptionLogger")
    public ExceptionLogger exceptionLogger(ExceptionCodeResolver exceptionCodeResolver) {
        ExceptionLogger bean = new ExceptionLogger();
        bean.setExceptionCodeResolver(exceptionCodeResolver);
        bean.setLogMessageFormat("[{0}], {1}");
        return bean;
    }
}
