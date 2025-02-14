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
package jp.co.ntt.fw.spring.functionaltest.config.app;

import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.terasoluna.gfw.common.codelist.SimpleMapCodeList;

/**
 * Bean definition regarding CodeLists.
 */
@Configuration
public class SpringFunctionaltestWebSsmnCodelistConfig {

    /**
     * Configure {@link SimpleMapCodeList} bean.
     * @return Bean of configured {@link SimpleMapCodeList}
     */
    @Bean("CL_GENDER")
    public SimpleMapCodeList clGender() {
        SimpleMapCodeList bean = new SimpleMapCodeList();
        Map<String, String> map = new LinkedHashMap<String, String>();
        map.put("1", "男");
        map.put("2", "女");
        bean.setMap(map);
        return bean;
    }

}
