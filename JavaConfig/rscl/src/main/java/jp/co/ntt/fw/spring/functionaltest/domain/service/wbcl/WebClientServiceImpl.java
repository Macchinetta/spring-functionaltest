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
package jp.co.ntt.fw.spring.functionaltest.domain.service.wbcl;

import java.net.URI;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import org.terasoluna.gfw.common.exception.SystemException;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jp.co.ntt.fw.spring.functionaltest.domain.model.UserResource;

@Service
public class WebClientServiceImpl implements WebClientService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebClientServiceImpl.class);

    @Value("${wbcl.applicationContextUrl}/api/v1/rscl/{opt}")
    String uri;

    @Inject
    @Named("defaultWebClient")
    WebClient defaultWebClient;

    @Inject
    @Named("connectionWebClient")
    WebClient connectionWebClient;

    @Inject
    @Named("loggingWebClient")
    WebClient loggingWebClient;

    @Inject
    @Named("filtersWebClient")
    WebClient filtersWebClient;

    @Value("${wbcl.asyncRestTemplate.waitCompleteOffsetMillis:15000}")
    long waitCompleteOffsetMillis;

    @Value("${wbcl.webClient.pendingAcquireMaxCount:5}")
    int queueCapacity;

    @Value("${wbcl.webClient.maxConnections:5}")
    int maxConnectionSize;

    @Value("${wbcl.notExistServer.uri:http://111.111.111.111:8080}/api/{opt}")
    String notExistServerUri;

    @Override
    public UserResource confirmAsync01(String path) {

        URI targetUri = this.getUri(this.uri, path);

        // このカウントダウンラッチで何か処理しているわけではなく、非同期処理が呼ばれたことを確認しているだけ
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        ResponseEntity<UserResource> res = null;
        UserResource resUser = new UserResource();
        String statusCode = null;

        LOGGER.info("RSCL1301001 : {}", targetUri);

        // @formatter:off
        CompletableFuture<ResponseEntity<UserResource>> responseEntity = this.defaultWebClient
                .get()
                .uri(targetUri)
                .retrieve()
                .toEntity(UserResource.class)
                .doOnSuccess(r -> {
                    countDownLatch.countDown();
                })
                .doOnError(t -> {
                    countDownLatch.countDown();
                    throw new SystemException("e.sf.rc.9005", "async processing error.", t);
                }).toFuture();
        // @formatter:on

        try {
            res = responseEntity.get();
        } catch (InterruptedException | ExecutionException e) {
            LOGGER.error(e.getMessage(), e);
        }
        try {
            countDownLatch.await(waitCompleteOffsetMillis, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            // SystemExceptionをthrowしているため、SonarQube指摘に未対応としています。
            throw new SystemException("e.sf.rc.9006", "interrupted error.", e);
        }
        LOGGER.info("RSCL1301001 : CountDownLatch = {}", countDownLatch.getCount());

        if (res != null) {
            statusCode = res.getStatusCode().toString();
            resUser = res.getBody();
        }

        LOGGER.info("RSCL1301001 : {}", statusCode);

        return resUser;
    }

    @Override
    public Map<String, Integer> confirmAsync02() {

        // スレッド数 + キューサイズ が最大数
        final int threadCapacity = this.maxConnectionSize + this.queueCapacity;

        URI targetUri_1 = this.getUri(this.uri, "noawait");

        List<CompletableFuture<ResponseEntity<UserResource>>> futures = new ArrayList<>();

        for (int i = 0; i < threadCapacity + 1; i++) {
            // @formatter:off
            CompletableFuture<ResponseEntity<UserResource>> responseEntity = this.connectionWebClient
                .get()
                .uri(targetUri_1)
                .retrieve()
                .toEntity(
                    UserResource.class)
                    .doOnSuccess(r -> {
                        LOGGER.info("noawait");
                    })
                    .doOnError(t -> {
                        throw new SystemException("e.sf.rc.9005", "async processing error.", t);
                    })
                .toFuture();
            // @formatter:on
            futures.add(responseEntity);
        }

        LOGGER.info("List Size : {}", futures.size());

        final AtomicInteger successCount = new AtomicInteger(0);
        final AtomicInteger errorCount = new AtomicInteger(0);

        futures.forEach(f -> {
            try {
                ResponseEntity<UserResource> responseEntity = f.get();
                LOGGER.info("status : {}", responseEntity.getStatusCode());
                successCount.incrementAndGet();
            } catch (InterruptedException | ExecutionException e) {
                LOGGER.info("error : {}", e.getMessage());
                errorCount.incrementAndGet();
            }
        });

        Map<String, Integer> resultMap = new HashMap<String, Integer>();
        resultMap.put("success", successCount.get());
        resultMap.put("error", errorCount.get());

        return resultMap;
    }

    @Override
    public UserResource confirmAsync03(String path) {

        URI targetUri = this.getUri(this.uri, path);

        // このカウントダウンラッチで何か処理しているわけではなく、非同期処理が呼ばれたことを確認しているだけ
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        ResponseEntity<UserResource> res = null;
        UserResource resUser = new UserResource();
        String statusCode = null;

        LOGGER.info("RSCL1303001 : {}", targetUri);

        // @formatter:off
        CompletableFuture<ResponseEntity<UserResource>> responseEntity = this.loggingWebClient
                .get()
                .uri(targetUri)
                .retrieve()
                .toEntity(UserResource.class)
                .doOnSuccess(r -> {
                    // 処理完了後のinterceptorの代わりはdoOnSuccessがあるため準備されていないと思われる
                    LOGGER.info("RSCL1303001 : CountDownLatch = {}", countDownLatch
                            .getCount());
                    countDownLatch.countDown();
                })
                .doOnError(t -> {
                    countDownLatch.countDown();
                    throw new SystemException("e.sf.rc.9005", "async processing error.", t);
                }).toFuture();
        // @formatter:on

        try {
            res = responseEntity.get();
        } catch (InterruptedException | ExecutionException e) {
            LOGGER.error(e.getMessage(), e);
        }
        try {
            countDownLatch.await(waitCompleteOffsetMillis, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            // SystemExceptionをthrowしているため、SonarQube指摘に未対応としています。
            throw new SystemException("e.sf.rc.9006", "interrupted error.", e);
        }
        LOGGER.info("RSCL1303001 : CountDownLatch = {}", countDownLatch.getCount());

        if (res != null) {
            statusCode = res.getStatusCode().toString();
            resUser = res.getBody();
        }

        LOGGER.info("RSCL1303001 : {}", statusCode);

        return resUser;
    }

    @Override
    public UserResource confirmAsync04(String path) {

        // 存在しないサーバへアクセスする
        URI targetUri = UriComponentsBuilder.fromUriString(this.notExistServerUri)
                .buildAndExpand("test").toUri();

        ResponseEntity<UserResource> res = null;
        UserResource resUser = new UserResource();
        String statusCode = null;

        // タイムアウト値を明示的に設定していないため、デフォルトの挙動となる
        // ついでにfiltersの確認

        // @formatter:off
        CompletableFuture<ResponseEntity<UserResource>> responseEntity = this.filtersWebClient
                .get()
                .uri(targetUri)
                .retrieve()
                .toEntity(UserResource.class)
                .doOnSuccess(r -> {
                    LOGGER.error("想定外の挙動");
                    throw new SystemException("e.sf.rc.9005", "async processing error.");
                }).doOnError(t -> {
                    LOGGER.info("接続先が存在しない", t);
                    // org.springframework.web.reactive.function.client.WebClientRequestException:
                    // Connection timed out: no further information: /111.111.111.111:8080
                }).toFuture();
        // @formatter:on

        try {
            res = responseEntity.get();
        } catch (InterruptedException | ExecutionException e) {
            LOGGER.info(e.getMessage(), e);
        }

        if (res != null) {
            statusCode = res.getStatusCode().toString();
            resUser = res.getBody();
        }

        // resがないため、Httpステータスはnullとなる
        LOGGER.info("RSCL1303003 : {}", statusCode);

        return resUser;
    }

    @Override
    public UserResource confirmAsync05(String path) {

        // 存在しないサーバへアクセスする
        URI targetUri = UriComponentsBuilder.fromUriString(this.notExistServerUri)
                .buildAndExpand("test").toUri();

        ResponseEntity<UserResource> res = null;
        UserResource resUser = new UserResource();
        String statusCode = null;

        // @formatter:off
        CompletableFuture<ResponseEntity<UserResource>> responseEntity = this.defaultWebClient
                .get()
                .uri(targetUri)
                .retrieve()
                .toEntity(UserResource.class)
                .timeout(Duration.ofSeconds(1)) // タイムアウトの設定（リクエスト毎に設定可能）
                .doOnSuccess(r -> {
                    LOGGER.error("想定外の挙動");
                    throw new SystemException("e.sf.rc.9005", "async processing error.");
                }).doOnError(t -> {
                    LOGGER.info("タイムアウトが発生する", t);
                    // java.util.concurrent.TimeoutException:
                    // Did not observe any item or terminal signal within 1000ms in 'flatMap' (and no fallback has been configured)
                }).toFuture();
        // @formatter:on

        // HttpClientにタイムアウト値を設定することで、全体的なタイムアウト値を設定することも可能

        try {
            res = responseEntity.get();
        } catch (InterruptedException | ExecutionException e) {
            LOGGER.info(e.getMessage(), e);
        }

        if (res != null) {
            statusCode = res.getStatusCode().toString();
            resUser = res.getBody();
        }

        // resがないため、Httpステータスはnullとなる
        LOGGER.info("RSCL1303004 : {}", statusCode);

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
        return UriComponentsBuilder.fromUriString(uri).buildAndExpand(args).toUri();
    }

}
