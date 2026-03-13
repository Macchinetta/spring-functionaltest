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

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.inject.Inject;
import jp.co.ntt.fw.spring.functionaltest.app.cmmn.model.User;

@Service
public class RestfulRestTemplateServiceImpl implements RestfulRestTemplateService {

    @Inject
    private RestTemplate restTemplate;

    @Value("${rscl.applicationContextUrl}/api/v1")
    private String uri;

    @Override
    public User getForObject() {
        return restTemplate.getForObject(uri + "/users/{userId}", User.class, "0001");
    }

    @Override
    public User exchange(User user) {
        URI targetUri = UriComponentsBuilder.fromUriString(uri + "/users/restful/{action}").buildAndExpand("create")
                .toUri();

        RequestEntity<User> requestEntity = RequestEntity
                .post(targetUri)
                .body(user);

        ResponseEntity<User> responseEntity = restTemplate.exchange(requestEntity, User.class);
        return responseEntity.getBody();
    }
}