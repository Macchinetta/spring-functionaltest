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
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import jp.co.ntt.fw.spring.functionaltest.domain.model.UserResource;

@Service
public class CollectionRestClientServiceImpl implements
                                             CollectionRestClientService {

    private static final Logger logger = LoggerFactory.getLogger(
            RestClientServiceImpl.class);

    @Inject
    RestTemplate collectionRestTemplate;

    @Value("${rscl.applicationContextUrl}/api/v1/rscl/{opt}")
    String uri;

    @Override
    public List<UserResource> exchangeCollection(String path) {
        URI targetUri = this.getUri(this.uri, path);
        // XMLで受け取り、Jaxb2CollectionHttpMessageConverterでList<UserResource>へ変換させる。
        RequestEntity<Void> req = RequestEntity.get(targetUri).accept(
                MediaType.APPLICATION_XML).build();

        ResponseEntity<List<UserResource>> res = this.collectionRestTemplate
                .exchange(req,
                        new ParameterizedTypeReference<List<UserResource>>() {
                        });
        logger.info("RSCL0105001 : {}", res.getStatusCode());

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
