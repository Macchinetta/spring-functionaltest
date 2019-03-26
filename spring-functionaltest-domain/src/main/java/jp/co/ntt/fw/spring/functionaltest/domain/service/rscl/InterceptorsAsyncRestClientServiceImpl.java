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
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.terasoluna.gfw.common.exception.SystemException;

import jp.co.ntt.fw.spring.functionaltest.domain.model.UserResource;

@Service
@SuppressWarnings("deprecation")
public class InterceptorsAsyncRestClientServiceImpl implements
                                                    InterceptorsAsyncRestClientService {

    private static final Logger logger = LoggerFactory.getLogger(
            InterceptorsAsyncRestClientServiceImpl.class);

    @Inject
    AsyncRestTemplate interceptorsAsyncRestTemplate;

    @Inject
    AsyncRestTemplate timeoutInterceptorsAsyncRestTemplate;

    @Value("${rscl.applicationContextUrl}/api/v1/rscl/{opt}")
    String uri;

    @Value("${rscl.notExistServer.uri:http://111.111.111.111:8080}/api/{opt}")
    String notExistServerUri;

    @Value("${rscl.asyncRestTemplate.waitCompleteOffsetMillis}")
    long waitCompleteOffsetMillis;

    @Override
    public UserResource getUserForStatusCode200() {

        URI targetUri = this.getUri(this.uri, "");

        return getUser(targetUri, interceptorsAsyncRestTemplate);
    }

    @Override
    public UserResource getUserForStatusCode401() {

        URI targetUri = this.getUri(this.uri, "basic");

        return getUser(targetUri, interceptorsAsyncRestTemplate);
    }

    @Override
    public UserResource getUserForConnectError() {
        URI targetUri = this.getUri(this.notExistServerUri, "");

        return getUser(targetUri, timeoutInterceptorsAsyncRestTemplate);
    }

    @Override
    public UserResource getUserForReadTimeout() {

        URI targetUri = this.getUri(this.uri, "readTimeout");

        return getUser(targetUri, timeoutInterceptorsAsyncRestTemplate);
    }

    private UserResource getUser(URI targetUri,
            AsyncRestTemplate asyncRestTemplate) {
        ResponseEntity<UserResource> res = null;
        UserResource resUser = null;
        String statusCode = null;

        ListenableFuture<ResponseEntity<UserResource>> responseEntity = asyncRestTemplate
                .getForEntity(targetUri, UserResource.class);

        responseEntity.addCallback(
                new ListenableFutureCallback<ResponseEntity<UserResource>>() {
                    @Override
                    public void onSuccess(ResponseEntity<UserResource> res) {
                        logger.info("onSuccess Called!");
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        logger.info("onFailure Called!");
                        throw new SystemException("e.sf.rscl.9005", "async processing error.", t);
                    }
                });
        logger.info("before ListenableFuture#get(long, TimeUnit)");
        try {
            res = responseEntity.get(waitCompleteOffsetMillis * 2,
                    TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            logger.error(e.getMessage(), e);
        }

        if (res != null) {
            statusCode = res.getStatusCode().toString();
            resUser = res.getBody();
        }

        logger.info("RSCL1303001 : {}", statusCode);

        return resUser;
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
