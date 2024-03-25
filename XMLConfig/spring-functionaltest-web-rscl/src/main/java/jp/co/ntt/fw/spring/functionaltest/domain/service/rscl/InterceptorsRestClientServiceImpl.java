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
package jp.co.ntt.fw.spring.functionaltest.domain.service.rscl;

import java.io.File;
import java.io.IOException;
import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.terasoluna.gfw.common.exception.BusinessException;
import org.terasoluna.gfw.common.exception.SystemException;

import jakarta.inject.Inject;
import jp.co.ntt.fw.spring.functionaltest.domain.model.UserResource;

@Service
public class InterceptorsRestClientServiceImpl implements
                                               InterceptorsRestClientService {

    @Inject
    RestTemplate interceptorsRestTemplate;

    @Inject
    RestTemplate invalidCredentialInterceptorsRestTemplate;

    @Value("${rscl.applicationContextUrl}/api/v1/rscl/{opt}")
    String uri;

    @Override
    public UserResource confirmInterceptor0101() {

        URI targetUri = this.getUri(this.uri, "basic");

        UserResource user = this.interceptorsRestTemplate.getForObject(
                targetUri, UserResource.class);

        return user;
    }

    @Override
    public UserResource confirmInterceptor0102() {

        URI targetUri = this.getUri(this.uri, "basic");

        UserResource user = this.invalidCredentialInterceptorsRestTemplate
                .getForObject(targetUri, UserResource.class);

        return user;
    }

    @Override
    public void confirmInterceptor02() {
        URI targetUri = this.getUri(this.uri, "");

        // 閉塞ファイルを作成する。
        File tmpFile = null;
        try {
            tmpFile = File.createTempFile("RSCL1104", ".block");
        } catch (IOException e) {
            throw new SystemException("e.sf.rscl.9001", "input/output error.", e);
        }

        try {
            this.interceptorsRestTemplate.getForObject(targetUri,
                    UserResource.class);
            // deleteメソッドによる削除の成功失敗によってその後のアクションをとることは無いため、SonarQube指摘は未対応としています。
            tmpFile.delete();
        } catch (BusinessException e) {
            throw e;
        } finally {
            // deleteメソッドによる削除の成功失敗によってその後のアクションをとることは無いため、SonarQube指摘は未対応としています。
            tmpFile.delete();
        }
    }

    @Override
    public UserResource confirmInterceptor03() {

        URI targetUri = this.getUri(this.uri, "retry");

        RequestEntity<Void> req = RequestEntity.get(targetUri).build();
        ResponseEntity<UserResource> res = this.interceptorsRestTemplate
                .exchange(req, UserResource.class);
        UserResource user = res.getBody();

        return user;
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
