/*
 * Copyright(c) 2014-2017 NTT Corporation.
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

    private static final Logger logger = LoggerFactory
            .getLogger(AsyncRestClientServiceImpl.class);

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

    @Value("${rscl.asyncRestTemplate.LoopMargin}")
    int loopMargin;

    @Override
    public UserResource confirmAsync01(String path) {
        URI targetUri = this.getUri(this.uri, path);

        final CountDownLatch countDownLatch = new CountDownLatch(1);
        ResponseEntity<UserResource> res = null;
        UserResource resUser = new UserResource();
        String statusCode = null;

        ListenableFuture<ResponseEntity<UserResource>> responseEntity = this.asyncRestTemplate
                .getForEntity(targetUri, UserResource.class);

        responseEntity
                .addCallback(new ListenableFutureCallback<ResponseEntity<UserResource>>() {
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
        URI targetUri = this.getUri(this.uri, "sleep/" + sleepMillis);

        final int threadCapacity = queueCapacity + maxPoolSize;
        final int maxLoop = threadCapacity + loopMargin + 1;

        final CountDownLatch countDownLatch = new CountDownLatch(maxLoop);
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
            for (int i = 0; i < maxLoop; i++) {
                long totalMillis = 0L;
                // activeスレッドがMAXプールサイズ以下で、キューがMAXキュー数まで詰まっている場合は一時待機
                while ((executor.getActiveCount() < maxPoolSize)
                        && (executor.getQueue().size() == queueCapacity)) {
                    try {
                        Thread.sleep(waitStartQueuedTaskMillis);
                    } catch (InterruptedException e) {
                        throw new SystemException("e.sf.rscl.9006", "interrupted error.", e);
                    }
                    totalMillis += waitStartQueuedTaskMillis;
                    // 直前のREST API 呼出しの応答が変えるであろう「sleepMillis」m秒 までは繰り返し待つ
                    if (Long.compare(sleepMillis, totalMillis) <= 0) {
                        throw new SystemException("e.sf.rscl.9007", "queued task is not start.");
                    }
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
            for (int i = 0; i < maxLoop - callCount; i++) {
                countDownLatch.countDown();
            }
            try {
                countDownLatch.await((sleepMillis * 2)
                        + waitCompleteOffsetMillis, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e1) {
                throw new SystemException("e.sf.rscl.9006", "interrupted error.", e1);
            }

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
