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
package jp.co.ntt.fw.spring.functionaltest.restclient;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.SimpleClientHttpRequestFactory;

public class SimpleClientHttpRequestFactoryBean implements
                                                FactoryBean<SimpleClientHttpRequestFactory> {

    @Value("${rscl.timeoutRestTemplate.connectTimeout: 2000}")
    private int connectTimeout;

    @Value("${rscl.timeoutRestTemplate.readTimeout: 2000}")
    private int readTimeout;

    @Override
    public SimpleClientHttpRequestFactory getObject() throws Exception {
        SimpleClientHttpRequestFactory bean = new SimpleClientHttpRequestFactory();
        bean.setConnectTimeout(this.connectTimeout);
        bean.setReadTimeout(this.readTimeout);
        return bean;
    }

    @Override
    public Class<?> getObjectType() {
        return SimpleClientHttpRequestFactory.class;
    }

}
