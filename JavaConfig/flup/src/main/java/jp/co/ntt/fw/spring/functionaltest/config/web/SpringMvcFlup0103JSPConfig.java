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
import jp.co.ntt.fw.spring.functionaltest.app.flup.FLUP_JSP_0103Controller;
import jp.co.ntt.fw.spring.functionaltest.app.flup.FileUploadHelper;

/**
 * Configure SpringMvcFlup0103Jsp.
 */
@Import({SpringMvcCommonConfig.class})
@Configuration
@EnableWebMvc
public class SpringMvcFlup0103JSPConfig implements WebMvcConfigurer {

    /**
     * Configure {@link FLUP_JSP_0103Controller} bean.
     * @return Bean of configured {@link FLUP_JSP_0103Controller}
     */
    @Bean
    public FLUP_JSP_0103Controller fLUP_JSP_0103Controller() {
        return new FLUP_JSP_0103Controller();
    }

    /**
     * Configure {@link FileUploadHelper} bean.
     * @return Bean of configured {@link FileUploadHelper}
     */
    @Bean
    public FileUploadHelper fileUploadHelper() {
        return new FileUploadHelper();
    }
}
