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
package jp.co.ntt.fw.spring.functionaltest.domain.service.rscl.resttemplate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import jakarta.inject.Inject;
import jp.co.ntt.fw.spring.functionaltest.app.cmmn.model.User;

@Service
public class TimeoutRestTemplateServiceImpl implements TimeoutRestTemplateService {

    @Inject
    private RestTemplate timeoutRestTemplate;

    @Value("${rscl.applicationContextUrl}/api/v1")
    private String uri;

    @Value("${rscl.notExistServer.uri:http://111.111.111.111:8080}")
    private String notExistServerUri;

    @Override
    public User connectTimeout() {
        return this.timeoutRestTemplate.getForObject(notExistServerUri + "/timeout", User.class);
    }

    @Override
    public User readTimeout() {
        return this.timeoutRestTemplate.getForObject(uri + "/timeout/readTimeout", User.class);
    }
}