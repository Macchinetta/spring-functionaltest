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
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import jakarta.inject.Inject;
import jp.co.ntt.fw.spring.functionaltest.app.cmmn.model.User;

@Service
public class HeaderRestTemplateServiceImpl implements HeaderRestTemplateService {

    @Value("${rscl.applicationContextUrl}/api/v1")
    private String uri;

    @Inject
    private RestTemplate restTemplate;

    @Override
    public User contentTypeHeader(User user) {
        RequestEntity<User> requestEntity = RequestEntity
                .post(uri + "/headers/contenttype")
                .contentType(MediaType.APPLICATION_JSON)
                .body(user);

        ResponseEntity<User> res = this.restTemplate.exchange(requestEntity, User.class);
        return res.getBody();
    }

    @Override
    public User acceptHeader() {
        RequestEntity<Void> requestEntity = RequestEntity
                .get(uri + "/headers/accept")
                .accept(MediaType.APPLICATION_JSON).build();

        ResponseEntity<User> res = this.restTemplate.exchange(requestEntity, User.class);
        return res.getBody();
    }

    @Override
    public User customHeader() {
        RequestEntity<Void> requestEntity = RequestEntity
                .get(uri + "/headers/custom")
                .header("Custom-Header", "CustomValue")
                .build();

        ResponseEntity<User> res = this.restTemplate.exchange(requestEntity, User.class);
        return res.getBody();
    }
}