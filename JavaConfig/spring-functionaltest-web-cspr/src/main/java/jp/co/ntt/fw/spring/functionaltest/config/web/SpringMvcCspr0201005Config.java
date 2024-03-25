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
import org.terasoluna.gfw.web.mvc.support.CompositeRequestDataValueProcessor;
import org.terasoluna.gfw.web.token.transaction.TransactionTokenRequestDataValueProcessor;

import jp.co.ntt.fw.spring.functionaltest.app.cspr.CSPR_JSP_0201005Controller;
import jp.co.ntt.fw.spring.functionaltest.app.cspr.CSPR_Thymeleaf_0201005Controller;

/**
 * Configure SpringMVC.
 */
@Configuration
@EnableWebMvc
@Import({ SpringMvcCommonConfig.class })
public class SpringMvcCspr0201005Config implements WebMvcConfigurer {

    /**
     * Configure {@link CompositeRequestDataValueProcessor} bean.
     * @return Bean of configured {@link CompositeRequestDataValueProcessor}
     */
    @Bean("requestDataValueProcessor")
    public CompositeRequestDataValueProcessor requestDataValueProcessor() {
        return new CompositeRequestDataValueProcessor(transactionTokenRequestDataValueProcessor());
    }

    /**
     * Configure {@link TransactionTokenRequestDataValueProcessor} bean.
     * @return Bean of configured {@link TransactionTokenRequestDataValueProcessor}
     */
    @Bean
    public TransactionTokenRequestDataValueProcessor transactionTokenRequestDataValueProcessor() {
        return new TransactionTokenRequestDataValueProcessor();
    }

    /**
     * Configure {@link CSPR_JSP_0201005Controller} bean.
     * @return Bean of configured {@link CSPR_JSP_0201005Controller}
     */
    @Bean
    public CSPR_JSP_0201005Controller cSPR_JSP_0201005Controller() {
        return new CSPR_JSP_0201005Controller();
    }

    /**
     * Configure {@link CSPR_Thymeleaf_0201005Controller} bean.
     * @return Bean of configured {@link CSPR_Thymeleaf_0201005Controller}
     */
    @Bean
    public CSPR_Thymeleaf_0201005Controller cSPR_Thymeleaf_0201005Controller() {
        return new CSPR_Thymeleaf_0201005Controller();
    }
}
