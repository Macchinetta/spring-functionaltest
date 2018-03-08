/*
 * Copyright 2014-2018 NTT Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.rscl;

import java.net.URI;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.task.TaskRejectedException;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.terasoluna.gfw.common.exception.SystemException;

import jp.co.ntt.fw.spring.functionaltest.domain.model.UserResource;

@Service
public class AsyncRestClientServiceImpl implements AsyncRestClientService {

    private static final Logger logger = LoggerFactory.getLogger(
            AsyncRestClientServiceImpl.class);

    @Inject
    AsyncRestTemplate asyncRestTemplate;

    @Inject
    ThreadPoolTaskExecutor asyncTaskExecutor;

    @Value("${rscl.applicationContextUrl}/api/v1/rscl/{opt}")
    String uri;

    @Value("${rscl.asyncRestTemplate.waitCompleteOffsetMillis}")
    long waitCompleteOffsetMillis;

    @Value("${rscl.asyncRestTemplate.sleepMillis}")
    long sleepMillis;

    @Value("${rscl.asyncRestTemplate.queueCapacity}")
    int queueCapacity;

    @Value("${rscl.asyncRestTemplate.maxPoolSize}")
    int maxPoolSize;

    @Value("${rscl.asyncRestTemplate.waitStartQueuedTaskMillis}")
    long waitStartQueuedTaskMillis;

    @Override
    public UserResource confirmAsync01(String path) {
        URI targetUri = this.getUri(this.uri, path);

        final CountDownLatch countDownLatch = new CountDownLatch(1);
        ResponseEntity<UserResource> res = null;
        UserResource resUser = new UserResource();
        String statusCode = null;

        ListenableFuture<ResponseEntity<UserResource>> responseEntity = this.asyncRestTemplate
                .getForEntity(targetUri, UserResource.class);

        responseEntity.addCallback(
                new ListenableFutureCallback<ResponseEntity<UserResource>>() {
                    @Override
                    public void onSuccess(ResponseEntity<UserResource> res) {
                        countDownLatch.countDown();
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        countDownLatch.countDown();
                        throw new SystemException("e.sf.rscl.9005", "async processing error.", t);
                    }
                });
        try {
            res = responseEntity.get();
        } catch (InterruptedException | ExecutionException e) {
            logger.error(e.getMessage(), e);
        }
        try {
            countDownLatch.await(waitCompleteOffsetMillis,
                    TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            // SystemExceptionをthrowしているため、SonarQube指摘に未対応としています。
            throw new SystemException("e.sf.rscl.9006", "interrupted error.", e);
        }
        logger.info("RSCL1301001 : CountDownLatch = {}", countDownLatch
                .getCount());

        if (res != null) {
            statusCode = res.getStatusCode().toString();
            resUser = res.getBody();
        }

        logger.info("RSCL1301001 : {}", statusCode);

        return resUser;
    }

    @Override
    public void confirmAsync02() {

        final int threadCapacity = queueCapacity + maxPoolSize;

        final CountDownLatch countDownLatch = new CountDownLatch(threadCapacity);
        final AtomicInteger successCount = new AtomicInteger();
        ListenableFutureCallback<? super ResponseEntity<UserResource>> callback = new ListenableFutureCallback<ResponseEntity<UserResource>>() {
            @Override
            public void onSuccess(ResponseEntity<UserResource> entity) {
                successCount.incrementAndGet();
                countDownLatch.countDown();
            }

            @Override
            public void onFailure(Throwable t) {
                logger.error(t.getMessage(), t);
                countDownLatch.countDown();
            }
        };

        int callCount = 0;
        int finishedCount = 0;
        ThreadPoolExecutor executor = asyncTaskExecutor.getThreadPoolExecutor();
        try {
            // キューを溢れさせるため、queueCapacity + maxPoolSize + 1 回連続でRESTAPI呼び出しを行う。
            for (int i = 0; i < threadCapacity + 1; i++) {
                long totalMillis = 0L;
                // activeスレッドがMAXプールサイズ以下で、キューがMAXキュー数まで詰まっている場合は一時待機
                while ((executor.getActiveCount() < maxPoolSize) && (executor
                        .getQueue().size() == queueCapacity)) {
                    try {
                        Thread.sleep(waitStartQueuedTaskMillis);
                    } catch (InterruptedException e) {
                        // SystemExceptionをthrowしているため、SonarQube指摘に未対応としています。
                        throw new SystemException("e.sf.rscl.9006", "interrupted error.", e);
                    }
                    totalMillis += waitStartQueuedTaskMillis;
                    // 直前のREST API 呼出しの応答が変えるであろう「sleepMillis」m秒 までは繰り返し待つ
                    if (Long.compare(sleepMillis, totalMillis) <= 0) {
                        throw new SystemException("e.sf.rscl.9007", "queued task is not start.");
                    }
                }
                URI targetUri;
                if (i < maxPoolSize) {
                    targetUri = getUri(uri, "await");
                } else {
                    targetUri = getUri(uri, "noawait");
                }
                ListenableFuture<ResponseEntity<UserResource>> responseEntity = this.asyncRestTemplate
                        .getForEntity(targetUri, UserResource.class);
                responseEntity.addCallback(callback);

                if (successCount.get() > finishedCount) {
                    finishedCount++;
                }
                callCount++;
            }

        } catch (TaskRejectedException e) {
            // 例外が発生したらRestTemplateでバリアを解除し、プール内のスレッドの処理が進むようにする
            asyncRestTemplate.getRestOperations().getForEntity(getUri(uri,
                    "await"), UserResource.class);
            await(countDownLatch, (sleepMillis * 2) + waitCompleteOffsetMillis);

            int finishedCallCount = callCount - finishedCount;
            int finishedSuccessCount = successCount.get() - finishedCount;

            logger.info(
                    "RSCL1302001 : CallCount = {}, SuccessCount = {}, FinishedCount = {}",
                    callCount, successCount.get(), finishedCount);
            logger.info(
                    "RSCL1302001 : CallCount - FinishedCount = {}, SuccessCount - FinishedCount = {}",
                    finishedCallCount, finishedSuccessCount);

            throw e;
        }
        // 9回とも正常に実行できてしまった場合はシステム例外
        throw new SystemException("e.sf.rscl.9008", "all tasks are executed.");
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

    /**
     * <ul>
     * <li>待機させる</li>
     * </ul>
     * @param latch 対象のラッチ
     * @param sleepMillis スリープ時間(ms)
     */
    private void await(CountDownLatch latch, long sleepMillis) {
        try {
            latch.await(sleepMillis, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new SystemException("e.sf.rscl.9006", "interrupted error.", e);
        }
    }
}
