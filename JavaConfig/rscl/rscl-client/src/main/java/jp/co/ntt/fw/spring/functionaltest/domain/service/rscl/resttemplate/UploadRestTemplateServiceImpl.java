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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import jakarta.inject.Inject;
import jp.co.ntt.fw.spring.functionaltest.app.cmmn.model.User;

@Service
public class UploadRestTemplateServiceImpl implements UploadRestTemplateService {

    private static final Logger logger = LoggerFactory.getLogger(MethodRestTemplateServiceImpl.class);

    @Inject
    private RestTemplate restTemplate;

    @Value("${rscl.applicationContextUrl}/api/v1")
    private String uri;

    @Value("${rscl.upload.filePath}")
    private String sendFilePath;

    @Override
    public User upload() {
        MultiValueMap<String, Object> multiPartBody = new LinkedMultiValueMap<>();
        multiPartBody.add("file", new ClassPathResource(sendFilePath));

        RequestEntity<MultiValueMap<String, Object>> requestEntity = RequestEntity
                .post(uri + "/users/upload")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(multiPartBody);

        ResponseEntity<User> res = this.restTemplate.exchange(requestEntity, User.class);

        logger.info("RSCL0901001 : {}", res.getStatusCode());

        return res.getBody();
    }
}