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

import jp.co.ntt.fw.spring.functionaltest.app.athn.ATHN_JSP_01Controller;
import jp.co.ntt.fw.spring.functionaltest.app.athn.ATHN_JSP_02Controller;
import jp.co.ntt.fw.spring.functionaltest.app.athn.ATHN_JSP_03Controller;
import jp.co.ntt.fw.spring.functionaltest.app.athn.ATHN_JSP_04Controller;
import jp.co.ntt.fw.spring.functionaltest.app.athn.ATHN_JSP_05Controller;
import jp.co.ntt.fw.spring.functionaltest.app.athn.ATHN_JSP_06Controller;
import jp.co.ntt.fw.spring.functionaltest.app.athn.ATHN_JSP_07Controller;
import jp.co.ntt.fw.spring.functionaltest.app.athn.ATHN_JSP_09Controller;
import jp.co.ntt.fw.spring.functionaltest.app.athn.ATHN_JSP_12Controller;
import jp.co.ntt.fw.spring.functionaltest.app.athn.ATHN_JSP_13Controller;
import jp.co.ntt.fw.spring.functionaltest.app.athn.ATHN_JSP_15Controller;
import jp.co.ntt.fw.spring.functionaltest.app.athn.ATHN_JSP_16Controller;
import jp.co.ntt.fw.spring.functionaltest.app.athn.ATHN_JSP_17Controller;
import jp.co.ntt.fw.spring.functionaltest.app.athn.ATHN_JSP_18Controller;
import jp.co.ntt.fw.spring.functionaltest.app.athn.ATHN_JSP_19Controller;
import jp.co.ntt.fw.spring.functionaltest.app.athn.ATHN_JSP_22Controller;
import jp.co.ntt.fw.spring.functionaltest.app.athn.ATHN_JSP_23Controller;

/**
 * Configure SpringMVC.
 */
@Configuration
@EnableWebMvc
@Import({SpringMvcCommonConfig.class})
public class SpringMvcAthnJspConfig implements WebMvcConfigurer {

    /**
     * Configure {@link ATHN_JSP_01Controller} bean.
     * @return Bean of configured {@link ATHN_JSP_01Controller}
     */
    @Bean
    public ATHN_JSP_01Controller aTHN_JSP_01Controller() {
        return new ATHN_JSP_01Controller();
    }

    /**
     * Configure {@link ATHN_JSP_02Controller} bean.
     * @return Bean of configured {@link ATHN_JSP_02Controller}
     */
    @Bean
    public ATHN_JSP_02Controller aTHN_JSP_02Controller() {
        return new ATHN_JSP_02Controller();
    }

    /**
     * Configure {@link ATHN_JSP_03Controller} bean.
     * @return Bean of configured {@link ATHN_JSP_03Controller}
     */
    @Bean
    public ATHN_JSP_03Controller aTHN_JSP_03Controller() {
        return new ATHN_JSP_03Controller();
    }

    /**
     * Configure {@link ATHN_JSP_04Controller} bean.
     * @return Bean of configured {@link ATHN_JSP_04Controller}
     */
    @Bean
    public ATHN_JSP_04Controller aTHN_JSP_04Controller() {
        return new ATHN_JSP_04Controller();
    }

    /**
     * Configure {@link ATHN_JSP_05Controller} bean.
     * @return Bean of configured {@link ATHN_JSP_05Controller}
     */
    @Bean
    public ATHN_JSP_05Controller aTHN_JSP_05Controller() {
        return new ATHN_JSP_05Controller();
    }

    /**
     * Configure {@link ATHN_JSP_06Controller} bean.
     * @return Bean of configured {@link ATHN_JSP_06Controller}
     */
    @Bean
    public ATHN_JSP_06Controller aTHN_JSP_06Controller() {
        return new ATHN_JSP_06Controller();
    }

    /**
     * Configure {@link ATHN_JSP_07Controller} bean.
     * @return Bean of configured {@link ATHN_JSP_07Controller}
     */
    @Bean
    public ATHN_JSP_07Controller aTHN_JSP_07Controller() {
        return new ATHN_JSP_07Controller();
    }

    /**
     * Configure {@link ATHN_JSP_09Controller} bean.
     * @return Bean of configured {@link ATHN_JSP_09Controller}
     */
    @Bean
    public ATHN_JSP_09Controller aTHN_JSP_09Controller() {
        return new ATHN_JSP_09Controller();
    }

    /**
     * Configure {@link ATHN_JSP_12Controller} bean.
     * @return Bean of configured {@link ATHN_JSP_12Controller}
     */
    @Bean
    public ATHN_JSP_12Controller aTHN_JSP_12Controller() {
        return new ATHN_JSP_12Controller();
    }

    /**
     * Configure {@link ATHN_JSP_13Controller} bean.
     * @return Bean of configured {@link ATHN_JSP_13Controller}
     */
    @Bean
    public ATHN_JSP_13Controller aTHN_JSP_13Controller() {
        return new ATHN_JSP_13Controller();
    }

    /**
     * Configure {@link ATHN_JSP_15Controller} bean.
     * @return Bean of configured {@link ATHN_JSP_15Controller}
     */
    @Bean
    public ATHN_JSP_15Controller aTHN_JSP_15Controller() {
        return new ATHN_JSP_15Controller();
    }

    /**
     * Configure {@link ATHN_JSP_16Controller} bean.
     * @return Bean of configured {@link ATHN_JSP_16Controller}
     */
    @Bean
    public ATHN_JSP_16Controller aTHN_JSP_16Controller() {
        return new ATHN_JSP_16Controller();
    }

    /**
     * Configure {@link ATHN_JSP_17Controller} bean.
     * @return Bean of configured {@link ATHN_JSP_17Controller}
     */
    @Bean
    public ATHN_JSP_17Controller aTHN_JSP_17Controller() {
        return new ATHN_JSP_17Controller();
    }

    /**
     * Configure {@link ATHN_JSP_18Controller} bean.
     * @return Bean of configured {@link ATHN_JSP_18Controller}
     */
    @Bean
    public ATHN_JSP_18Controller aTHN_JSP_18Controller() {
        return new ATHN_JSP_18Controller();
    }

    /**
     * Configure {@link ATHN_JSP_19Controller} bean.
     * @return Bean of configured {@link ATHN_JSP_19Controller}
     */
    @Bean
    public ATHN_JSP_19Controller aTHN_JSP_19Controller() {
        return new ATHN_JSP_19Controller();
    }

    /**
     * Configure {@link ATHN_JSP_22Controller} bean.
     * @return Bean of configured {@link ATHN_JSP_22Controller}
     */
    @Bean
    public ATHN_JSP_22Controller aTHN_JSP_22Controller() {
        return new ATHN_JSP_22Controller();
    }

    /**
     * Configure {@link ATHN_JSP_23Controller} bean.
     * @return Bean of configured {@link ATHN_JSP_23Controller}
     */
    @Bean
    public ATHN_JSP_23Controller aTHN_JSP_23Controller() {
        return new ATHN_JSP_23Controller();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/0102/001/login")
                .setViewName("jsp/athn/loginForDefaultFormAuthentication");
        registry.addViewController("/0201/001/login")
                .setViewName("jsp/athn/loginForDefaultAuthenticationSeccess");
        registry.addViewController("/0301/001/login")
                .setViewName("jsp/athn/loginForDefaultAuthenticationFailure");
        registry.addViewController("/0401/001/login")
                .setViewName("jsp/athn/loginForDbFormAuthentication");
        registry.addViewController("/0402/001/login")
                .setViewName("jsp/athn/loginForDbFormAuthenticationSpecifyEncoder");
        registry.addViewController("/0402/004/login")
                .setViewName("jsp/athn/loginForDbFormAuthenticationSpecifyHash");
        registry.addViewController("/0501/002/login")
                .setViewName("jsp/athn/loginForUsingBCryptPassword");
        registry.addViewController("/0501/004/login")
                .setViewName("jsp/athn/loginForUsingPbkdf2Password");
        registry.addViewController("/0501/006/login")
                .setViewName("jsp/athn/loginForUsingSCryptPassword");
        registry.addViewController("/0501/008/login")
                .setViewName("jsp/athn/loginForUsingArgon2Password");
        registry.addViewController("/0502/002/login")
                .setViewName("jsp/athn/loginForUsingDelegatingPassword");
        registry.addViewController("/0601/001/login")
                .setViewName("jsp/athn/loginForAuthEventHandle");
        registry.addViewController("/0602/002/login")
                .setViewName("jsp/athn/loginForAuthenticationServiceException");
        registry.addViewController("/0701/001/login").setViewName("jsp/athn/loginForLogout");
        registry.addViewController("/0702/001/login")
                .setViewName("jsp/athn/loginForLogoutDeleteCookie");
        registry.addViewController("/0901/001/login")
                .setViewName("jsp/athn/loginForDispAutentication");
        registry.addViewController("/1201/001/login")
                .setViewName("jsp/athn/loginForCustomizedAuthSucessUrl");
        registry.addViewController("/1202/001/login")
                .setViewName("jsp/athn/loginForCustomizedAuthSucessHandler");
        registry.addViewController("/1302/001/login")
                .setViewName("jsp/athn/loginForCustomizedAuthFailure");
        registry.addViewController("/1501/001/login")
                .setViewName("jsp/athn/loginForCustomizedLogout");
        registry.addViewController("/1601/001/login")
                .setViewName("jsp/athn/loginForAutenticationSystemError");
        registry.addViewController("/1602/001/login")
                .setViewName("jsp/athn/loginForAutenticationSystemErrorfailureUrl");
        registry.addViewController("/1801/002/login")
                .setViewName("jsp/athn/loginForUsingCustomPbkdf2Password");
        registry.addViewController("/loginForRememberMe")
                .setViewName("jsp/athn/loginForRememberMe");
        registry.addViewController("/2301/001/login")
                .setViewName("jsp/athn/loginForLogoutSuccessEventHandle");

        registry.addViewController("/jsp/badCredentials").setViewName("jsp/athn/badCredentials");
        registry.addViewController("/jsp/usernameNotFound")
                .setViewName("jsp/athn/usernameNotFound");
        registry.addViewController("/jsp/disabled").setViewName("jsp/athn/disabled");
        registry.addViewController("/jsp/systemError").setViewName("jsp/athn/systemError");
    }
}
