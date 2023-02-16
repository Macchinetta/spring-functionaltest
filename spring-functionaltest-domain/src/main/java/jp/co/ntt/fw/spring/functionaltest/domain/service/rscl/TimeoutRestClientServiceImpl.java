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

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.inject.Inject;
import jp.co.ntt.fw.spring.functionaltest.domain.model.UserResource;

@Service
public class TimeoutRestClientServiceImpl implements TimeoutRestClientService {

    @Inject
    RestTemplate timeoutRestTemplate;

    @Value("${rscl.applicationContextUrl}/api/v1/rscl/{opt}")
    String uri;

    @Value("${rscl.notExistServer.uri:http://111.111.111.111:8080}/api/{opt}")
    String notExistServerUri;

    @Override
    public void confirmTimeout01() {

        try {
            // 存在しないサーバへアクセスを試みさせて、タイムアウトを狙う。
            URI targetUri = UriComponentsBuilder.fromUriString(
                    this.notExistServerUri).buildAndExpand("test").toUri();
            this.timeoutRestTemplate.getForObject(targetUri,
                    UserResource.class);

        } catch (ResourceAccessException e) {
            throw e;
        }
    }

    @Override
    public void confirmTimeout02() {

        try {
            // 処理返却までにreadTimeout + 1秒sleepするRESTAPIを呼んで、リードタイムアウトを狙う。
            URI targetUri = this.getUri(this.uri, "readTimeout");
            this.timeoutRestTemplate.getForObject(targetUri,
                    UserResource.class);

        } catch (ResourceAccessException e) {
            throw e;
        }
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
