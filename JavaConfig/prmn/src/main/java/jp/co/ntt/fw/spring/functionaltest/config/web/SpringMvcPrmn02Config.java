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

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import jp.co.ntt.fw.spring.functionaltest.app.prmn.PRMN_JSP_02Controller;
import jp.co.ntt.fw.spring.functionaltest.app.prmn.Prmn0201BeanDefine;
import jp.co.ntt.fw.spring.functionaltest.app.prmn.Prmn0202BeanDefine;
import jp.co.ntt.fw.spring.functionaltest.app.prmn.PrmnDefines;

/**
 * Configure SpringMVC.
 */
@Configuration
@EnableWebMvc
@Import({SpringMvcCommonConfig.class})
public class SpringMvcPrmn02Config implements WebMvcConfigurer {

    /**
     * i.sf.prmn.0100 property.
     */
    @Value("${i.sf.prmn.0100}")
    private String propertyValue0101;

    /**
     * i.sf.prmn.0200:3000 property.
     */
    @Value("${i.sf.prmn.0200:3000}")
    private String propertyValue0200;

    /**
     * Configure {@link Prmn0201BeanDefine} bean.
     * @return Bean of configured {@link Prmn0201BeanDefine}
     */
    @Bean("PRMN0201")
    public Prmn0201BeanDefine prmn0201BeanDefine() {
        Prmn0201BeanDefine bean = new Prmn0201BeanDefine();
        bean.setPropertyValue0101(propertyValue0101);
        return bean;
    }

    /**
     * Configure {@link Prmn0202BeanDefine} bean.
     * @return Bean of configured {@link Prmn0202BeanDefine}
     */
    @Bean("PRMN0202")
    public Prmn0202BeanDefine prmn0202BeanDefine() {
        Prmn0202BeanDefine bean = new Prmn0202BeanDefine();
        bean.setPropertyValue0200(propertyValue0200);
        return bean;
    }

    /**
     * Configure {@link PrmnDefines} bean.
     * @return Bean of configured {@link PrmnDefines}
     */
    @Bean
    public PrmnDefines prmnDefines() {
        return new PrmnDefines();
    }

    /**
     * Configure {@link PRMN_JSP_02Controller} bean.
     * @return Bean of configured {@link PRMN_JSP_02Controller}
     */
    @Bean
    public PRMN_JSP_02Controller pRMN_JSP_02Controller() {
        return new PRMN_JSP_02Controller();
    }
}
