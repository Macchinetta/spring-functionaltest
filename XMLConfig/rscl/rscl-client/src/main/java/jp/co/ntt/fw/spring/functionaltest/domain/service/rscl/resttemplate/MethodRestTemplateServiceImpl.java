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

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import jakarta.inject.Inject;
import jp.co.ntt.fw.spring.functionaltest.app.cmmn.model.User;

@Service
public class MethodRestTemplateServiceImpl implements MethodRestTemplateService {

    private static final Logger logger = LoggerFactory.getLogger(MethodRestTemplateServiceImpl.class);

    @Value("${rscl.applicationContextUrl}/api/v1")
    private String uri;

    @Inject
    private RestTemplate restTemplate;

    @Override
    public User get() {
        return this.restTemplate.getForObject(this.uri + "/users/{id}", User.class, "00001");
    }

    @Override
    public List<User> getList() {
        RequestEntity<Void> requestEntity = RequestEntity.get(uri + "/users").build();
        ResponseEntity<List<User>> responseEntity = restTemplate.exchange(requestEntity,
                new ParameterizedTypeReference<List<User>>() {
                });
        return responseEntity.getBody();
    }

    @Override
    public User post(User user) {
        return this.restTemplate.postForObject(this.uri + "/users", user, User.class);
    }

    @Override
    public User put(User user) {
        // 標準ではputは返却値がないため、exchangeを使用する
        RequestEntity<User> requestEntity = RequestEntity.put(uri + "/users").body(user);
        return this.restTemplate.exchange(requestEntity, User.class).getBody();
    }

    @Override
    public User patch(User user) {
        return this.restTemplate.patchForObject(this.uri + "/users", user, User.class);
    }

    @Override
    public void delete() {
        this.restTemplate.delete(this.uri + "/users/{id}", "00001");
    }

    @Override
    public void head() {
        HttpHeaders headers = this.restTemplate.headForHeaders(this.uri + "/users/head/{id}", "00001");
        headers.forEach((key, value) -> {
            logger.info("{}: {}", key, value);
        });
    }
}