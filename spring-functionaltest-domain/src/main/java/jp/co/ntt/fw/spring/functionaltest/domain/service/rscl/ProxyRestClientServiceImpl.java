/*
 * Copyright(c) 2014 NTT Corporation.
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
package jp.co.ntt.fw.spring.functionaltest.domain.service.rscl;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.inject.Inject;
import jp.co.ntt.fw.spring.functionaltest.domain.model.UserResource;

@Service
public class ProxyRestClientServiceImpl implements ProxyRestClientService {

    private static final Logger logger = LoggerFactory.getLogger(
            ProxyRestClientServiceImpl.class);

    @Inject
    RestTemplate proxyAuthRestTemplate;

    @Inject
    RestTemplate proxyRestTemplate;

    @Inject
    RestTemplate simpleClientRestTemplate;

    @Value("${rscl.applicationContextUrl}/api/v1/rscl/{opt}")
    String uri;

    @Override
    public UserResource confirmProxy01(String path) {

        URI targetUri = this.getUri(this.uri, path);
        ResponseEntity<UserResource> res = this.proxyAuthRestTemplate
                .getForEntity(targetUri, UserResource.class);

        logger.info(
                "RSCL1401001 : Headers containsKey 'Pass-Internal-Proxy' is {}",
                res.getHeaders().containsKey("Pass-Internal-Proxy"));

        return res.getBody();
    }

    @Override
    public UserResource confirmProxy02(String path) {

        URI targetUri = this.getUri(this.uri, path);
        ResponseEntity<UserResource> res = this.proxyRestTemplate.getForEntity(
                targetUri, UserResource.class);

        logger.info(
                "RSCL1401002 : Headers containsKey 'Pass-Internal-Proxy' is {}",
                res.getHeaders().containsKey("Pass-Internal-Proxy"));

        return res.getBody();
    }

    @Override
    public UserResource confirmSimpleHttpClientProxy(String path) {

        URI targetUri = this.getUri(this.uri, path);
        ResponseEntity<UserResource> res = this.simpleClientRestTemplate
                .getForEntity(targetUri, UserResource.class);

        logger.info(
                "RSCL1402001 : Headers containsKey 'Pass-Internal-Proxy' is {}",
                res.getHeaders().containsKey("Pass-Internal-Proxy"));

        return res.getBody();
    }

    /**
     * <ul>
     * <li>URIを取得する</li>
     * </ul>
     * @param args URIのパラメータ
     * @return URI
     */
    private URI getUri(String uri, Object... args) {
        return UriComponentsBuilder.fromUriString(uri).buildAndExpand(args)
                .toUri();
    }
}
