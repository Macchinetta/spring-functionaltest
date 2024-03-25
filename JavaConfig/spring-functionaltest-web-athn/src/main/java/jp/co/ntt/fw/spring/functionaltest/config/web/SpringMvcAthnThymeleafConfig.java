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
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jp.co.ntt.fw.spring.functionaltest.app.athn.ATHN_Thymeleaf_01Controller;
import jp.co.ntt.fw.spring.functionaltest.app.athn.ATHN_Thymeleaf_02Controller;
import jp.co.ntt.fw.spring.functionaltest.app.athn.ATHN_Thymeleaf_03Controller;
import jp.co.ntt.fw.spring.functionaltest.app.athn.ATHN_Thymeleaf_04Controller;
import jp.co.ntt.fw.spring.functionaltest.app.athn.ATHN_Thymeleaf_05Controller;
import jp.co.ntt.fw.spring.functionaltest.app.athn.ATHN_Thymeleaf_06Controller;
import jp.co.ntt.fw.spring.functionaltest.app.athn.ATHN_Thymeleaf_07Controller;
import jp.co.ntt.fw.spring.functionaltest.app.athn.ATHN_Thymeleaf_09Controller;
import jp.co.ntt.fw.spring.functionaltest.app.athn.ATHN_Thymeleaf_12Controller;
import jp.co.ntt.fw.spring.functionaltest.app.athn.ATHN_Thymeleaf_13Controller;
import jp.co.ntt.fw.spring.functionaltest.app.athn.ATHN_Thymeleaf_15Controller;
import jp.co.ntt.fw.spring.functionaltest.app.athn.ATHN_Thymeleaf_16Controller;
import jp.co.ntt.fw.spring.functionaltest.app.athn.ATHN_Thymeleaf_17Controller;
import jp.co.ntt.fw.spring.functionaltest.app.athn.ATHN_Thymeleaf_18Controller;
import jp.co.ntt.fw.spring.functionaltest.app.athn.ATHN_Thymeleaf_19Controller;
import jp.co.ntt.fw.spring.functionaltest.app.athn.ATHN_Thymeleaf_22Controller;
import jp.co.ntt.fw.spring.functionaltest.app.athn.ATHN_Thymeleaf_23Controller;

/**
 * Configure SpringMVC.
 */
@Configuration
@EnableWebMvc
@Import({ SpringMvcCommonConfig.class })
public class SpringMvcAthnThymeleafConfig implements WebMvcConfigurer {

    /**
     * Configure {@link ATHN_Thymeleaf_01Controller} bean.
     * @return Bean of configured {@link ATHN_Thymeleaf_01Controller}
     */
    @Bean
    public ATHN_Thymeleaf_01Controller aTHN_Thymeleaf_01Controller() {
        return new ATHN_Thymeleaf_01Controller();
    }

    /**
     * Configure {@link ATHN_Thymeleaf_02Controller} bean.
     * @return Bean of configured {@link ATHN_Thymeleaf_02Controller}
     */
    @Bean
    public ATHN_Thymeleaf_02Controller aTHN_Thymeleaf_02Controller() {
        return new ATHN_Thymeleaf_02Controller();
    }

    /**
     * Configure {@link ATHN_Thymeleaf_03Controller} bean.
     * @return Bean of configured {@link ATHN_Thymeleaf_03Controller}
     */
    @Bean
    public ATHN_Thymeleaf_03Controller aTHN_Thymeleaf_03Controller() {
        return new ATHN_Thymeleaf_03Controller();
    }

    /**
     * Configure {@link ATHN_Thymeleaf_04Controller} bean.
     * @return Bean of configured {@link ATHN_Thymeleaf_04Controller}
     */
    @Bean
    public ATHN_Thymeleaf_04Controller aTHN_Thymeleaf_04Controller() {
        return new ATHN_Thymeleaf_04Controller();
    }

    /**
     * Configure {@link ATHN_Thymeleaf_05Controller} bean.
     * @return Bean of configured {@link ATHN_Thymeleaf_05Controller}
     */
    @Bean
    public ATHN_Thymeleaf_05Controller aTHN_Thymeleaf_05Controller() {
        return new ATHN_Thymeleaf_05Controller();
    }

    /**
     * Configure {@link ATHN_Thymeleaf_06Controller} bean.
     * @return Bean of configured {@link ATHN_Thymeleaf_06Controller}
     */
    @Bean
    public ATHN_Thymeleaf_06Controller aTHN_Thymeleaf_06Controller() {
        return new ATHN_Thymeleaf_06Controller();
    }

    /**
     * Configure {@link ATHN_Thymeleaf_07Controller} bean.
     * @return Bean of configured {@link ATHN_Thymeleaf_07Controller}
     */
    @Bean
    public ATHN_Thymeleaf_07Controller aTHN_Thymeleaf_07Controller() {
        return new ATHN_Thymeleaf_07Controller();
    }

    /**
     * Configure {@link ATHN_Thymeleaf_09Controller} bean.
     * @return Bean of configured {@link ATHN_Thymeleaf_09Controller}
     */
    @Bean
    public ATHN_Thymeleaf_09Controller aTHN_Thymeleaf_09Controller() {
        return new ATHN_Thymeleaf_09Controller();
    }

    /**
     * Configure {@link ATHN_Thymeleaf_12Controller} bean.
     * @return Bean of configured {@link ATHN_Thymeleaf_12Controller}
     */
    @Bean
    public ATHN_Thymeleaf_12Controller aTHN_Thymeleaf_12Controller() {
        return new ATHN_Thymeleaf_12Controller();
    }

    /**
     * Configure {@link ATHN_Thymeleaf_13Controller} bean.
     * @return Bean of configured {@link ATHN_Thymeleaf_13Controller}
     */
    @Bean
    public ATHN_Thymeleaf_13Controller aTHN_Thymeleaf_13Controller() {
        return new ATHN_Thymeleaf_13Controller();
    }

    /**
     * Configure {@link ATHN_Thymeleaf_15Controller} bean.
     * @return Bean of configured {@link ATHN_Thymeleaf_15Controller}
     */
    @Bean
    public ATHN_Thymeleaf_15Controller aTHN_Thymeleaf_15Controller() {
        return new ATHN_Thymeleaf_15Controller();
    }

    /**
     * Configure {@link ATHN_Thymeleaf_16Controller} bean.
     * @return Bean of configured {@link ATHN_Thymeleaf_16Controller}
     */
    @Bean
    public ATHN_Thymeleaf_16Controller aTHN_Thymeleaf_16Controller() {
        return new ATHN_Thymeleaf_16Controller();
    }

    /**
     * Configure {@link ATHN_Thymeleaf_17Controller} bean.
     * @return Bean of configured {@link ATHN_Thymeleaf_17Controller}
     */
    @Bean
    public ATHN_Thymeleaf_17Controller aTHN_Thymeleaf_17Controller() {
        return new ATHN_Thymeleaf_17Controller();
    }

    /**
     * Configure {@link ATHN_Thymeleaf_18Controller} bean.
     * @return Bean of configured {@link ATHN_Thymeleaf_18Controller}
     */
    @Bean
    public ATHN_Thymeleaf_18Controller aTHN_Thymeleaf_18Controller() {
        return new ATHN_Thymeleaf_18Controller();
    }

    /**
     * Configure {@link ATHN_Thymeleaf_19Controller} bean.
     * @return Bean of configured {@link ATHN_Thymeleaf_19Controller}
     */
    @Bean
    public ATHN_Thymeleaf_19Controller aTHN_Thymeleaf_19Controller() {
        return new ATHN_Thymeleaf_19Controller();
    }

    /**
     * Configure {@link ATHN_Thymeleaf_22Controller} bean.
     * @return Bean of configured {@link ATHN_Thymeleaf_22Controller}
     */
    @Bean
    public ATHN_Thymeleaf_22Controller aTHN_Thymeleaf_22Controller() {
        return new ATHN_Thymeleaf_22Controller();
    }

    /**
     * Configure {@link ATHN_Thymeleaf_23Controller} bean.
     * @return Bean of configured {@link ATHN_Thymeleaf_23Controller}
     */
    @Bean
    public ATHN_Thymeleaf_23Controller aTHN_Thymeleaf_23Controller() {
        return new ATHN_Thymeleaf_23Controller();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/0102/001/login").setViewName("thymeleaf/athn/loginForDefaultFormAuthentication");
        registry.addViewController("/0201/001/login").setViewName("thymeleaf/athn/loginForDefaultAuthenticationSeccess");
        registry.addViewController("/0301/001/login").setViewName("thymeleaf/athn/loginForDefaultAuthenticationFailure");
        registry.addViewController("/0401/001/login").setViewName("thymeleaf/athn/loginForDbFormAuthentication");
        registry.addViewController("/0402/001/login").setViewName("thymeleaf/athn/loginForDbFormAuthenticationSpecifyEncoder");
        registry.addViewController("/0402/004/login").setViewName("thymeleaf/athn/loginForDbFormAuthenticationSpecifyHash");
        registry.addViewController("/0501/002/login").setViewName("thymeleaf/athn/loginForUsingBCryptPassword");
        registry.addViewController("/0501/004/login").setViewName("thymeleaf/athn/loginForUsingPbkdf2Password");
        registry.addViewController("/0501/006/login").setViewName("thymeleaf/athn/loginForUsingSCryptPassword");
        registry.addViewController("/0501/008/login").setViewName("thymeleaf/athn/loginForUsingArgon2Password");
        registry.addViewController("/0502/002/login").setViewName("thymeleaf/athn/loginForUsingDelegatingPassword");
        registry.addViewController("/0601/001/login").setViewName("thymeleaf/athn/loginForAuthEventHandle");
        registry.addViewController("/0602/002/login").setViewName("thymeleaf/athn/loginForAuthenticationServiceException");
        registry.addViewController("/0701/001/login").setViewName("thymeleaf/athn/loginForLogout");
        registry.addViewController("/0702/001/login").setViewName("thymeleaf/athn/loginForLogoutDeleteCookie");
        registry.addViewController("/0901/001/login").setViewName("thymeleaf/athn/loginForDispAutentication");
        registry.addViewController("/1201/001/login").setViewName("thymeleaf/athn/loginForCustomizedAuthSucessUrl");
        registry.addViewController("/1202/001/login").setViewName("thymeleaf/athn/loginForCustomizedAuthSucessHandler");
        registry.addViewController("/1302/001/login").setViewName("thymeleaf/athn/loginForCustomizedAuthFailure");
        registry.addViewController("/1501/001/login").setViewName("thymeleaf/athn/loginForCustomizedLogout");
        registry.addViewController("/1601/001/login").setViewName("thymeleaf/athn/loginForAutenticationSystemError");
        registry.addViewController("/1602/001/login").setViewName("thymeleaf/athn/loginForAutenticationSystemErrorfailureUrl");
        registry.addViewController("/1801/002/login").setViewName("thymeleaf/athn/loginForUsingCustomPbkdf2Password");
        registry.addViewController("/loginForRememberMe").setViewName("thymeleaf/athn/loginForRememberMe");
        registry.addViewController("/2301/001/login").setViewName("thymeleaf/athn/loginForLogoutSuccessEventHandle");

        registry.addViewController("/thymeleaf/badCredentials").setViewName("thymeleaf/athn/badCredentials");
        registry.addViewController("/thymeleaf/usernameNotFound").setViewName("thymeleaf/athn/usernameNotFound");
        registry.addViewController("/thymeleaf/disabled").setViewName("thymeleaf/athn/disabled");
        registry.addViewController("/thymeleaf/systemError").setViewName("thymeleaf/athn/systemError");
    }

}
