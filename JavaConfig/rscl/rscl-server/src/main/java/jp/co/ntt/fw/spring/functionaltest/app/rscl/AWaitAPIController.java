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
package jp.co.ntt.fw.spring.functionaltest.app.rscl;

import java.time.Duration;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.lang3.ThreadUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jp.co.ntt.fw.spring.functionaltest.app.cmmn.model.User;

@RestController
@RequestMapping(value = "/api/v1/await")
public class AWaitAPIController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AWaitAPIController.class);

    @Value("${rscl.timeoutRestTemplate.readTimeout}")
    long readTimeout;

    private final CyclicBarrier barrierForAsyncThreadLimitation;

    private final Lock lock = new ReentrantLock();

    private final Condition startSignal = lock.newCondition();

    private volatile boolean await;

    public AWaitAPIController(@Value("${rscl.asyncRestTemplate.maxPoolSize}") int maxPoolSize) {
        this.barrierForAsyncThreadLimitation = new CyclicBarrier(maxPoolSize + 1);
    }

    /**
     * <ul>
     * <li>例外が発生するまで待機し、maxPoolSize+1個目のリクエストがきたらUserを返却する。</li> <br>
     * スレッド数制限確認用
     * </ul>
     * 
     * @throws InterruptedException
     * @throws TimeoutException
     * @throws BrokenBarrierException
     */
    @GetMapping
    public User await()
            throws InterruptedException, BrokenBarrierException, TimeoutException {
        this.barrierForAsyncThreadLimitation.await(30, TimeUnit.SECONDS);
        User user = new User();
        user.setName("test");
        user.setAge(20);
        return user;
    }

    /**
     * <ul>
     * <li>{@link #releaseForMaxPool()}が呼び出されるまで待機し、Userを返却する。</li> <br>
     * スレッド数制限確認用
     * </ul>
     * 
     * @throws InterruptedException
     */
    @GetMapping(value = "awaitForMaxPool")
    public User awaitForMaxPool() throws InterruptedException {
        User user = new User();
        user.setName("test");
        user.setAge(20);

        long nanos = TimeUnit.SECONDS.toNanos(10L);
        this.lock.lock();
        try {
            this.await = true;
            while (this.await) {
                if (nanos <= 0L) {
                    return new User();
                }
                nanos = this.startSignal.awaitNanos(nanos);
            }
            return user;
        } finally {
            this.lock.unlock();
        }
    }

    /**
     * <ul>
     * <li>{@link #awaitForMaxPool()}の待機を解除する。</li> <br>
     * スレッド数制限確認用
     * </ul>
     */
    @GetMapping(value = "releaseForMaxPool")
    public boolean releaseForMaxPool() {
        this.lock.lock();
        try {
            this.await = false;
            this.startSignal.signalAll();
            return true;
        } finally {
            this.lock.unlock();
        }
    }

    /**
     * <ul>
     * <li>Userを返却する。</li> <br>
     * スレッド数制限確認用
     * </ul>
     */
    @GetMapping(value = "noawait")
    public User noAwait() {
        User user = new User();
        user.setName("test");
        user.setAge(20);
        return user;
    }

    /**
     * <ul>
     * <li>readTimeout + 1 秒待って、Userを返却する。</li> <br>
     * </ul>
     * 
     * @throws InterruptedException
     */
    @GetMapping(value = "readTimeout")
    public User readTimeout() throws InterruptedException {
        LOGGER.info("Call API:readTimeout");

        User user = new User();
        user.setName("test");
        user.setAge(20);

        ThreadUtils.sleep(Duration.ofMillis(this.readTimeout + 1000));

        return user;
    }

}
