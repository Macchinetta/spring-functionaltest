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
package jp.co.ntt.fw.spring.functionaltest.api.rscl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
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
import org.springframework.data.mapping.MappingException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.terasoluna.gfw.common.exception.ResourceNotFoundException;
import org.terasoluna.gfw.common.exception.SystemException;
import org.terasoluna.gfw.common.message.ResultMessage;
import org.terasoluna.gfw.common.message.ResultMessages;

import jakarta.servlet.http.HttpServletResponse;
import jp.co.ntt.fw.spring.functionaltest.app.cmmn.exception.IntentionalException;

@RestController
public class RSCLController {
    private static final Logger LOGGER = LoggerFactory.getLogger(
            RSCLController.class);

    @Value("${rscl.timeoutRestTemplate.readTimeout}")
    long readTimeout;

    private final CyclicBarrier barrierForAsyncThreadLimitation;

    private final Lock lock = new ReentrantLock();

    private final Condition startSignal = lock.newCondition();

    private volatile boolean await;

    public RSCLController(
            @Value("${rscl.asyncRestTemplate.maxPoolSize}") int maxPoolSize) {
        this.barrierForAsyncThreadLimitation = new CyclicBarrier(maxPoolSize
                + 1);
    }

    /**
     * <ul>
     * <li>UserResourceを返却する。</li> <br>
     * GETメソッドの確認用。
     * </ul>
     * @return
     */
    @GetMapping
    public UserResource getUser() {
        LOGGER.debug("getUser");
        UserResource user = new UserResource();
        user.setName("test");
        user.setAge(20);
        return user;
    }

    /**
     * <ul>
     * <li>UserResourceを返却する。</li> <br>
     * nameにパス終端の文字列を付与する。 <br>
     * GETメソッドの確認用。
     * </ul>
     * @param id
     * @return
     */
    @GetMapping(value = "{id}")
    public UserResource getUser(@PathVariable("id") String id) {
        LOGGER.debug("getUser{id:{}}", id);
        UserResource user = new UserResource();
        user.setName("test_" + id);
        user.setAge(20);

        return user;
    }

    /**
     * <ul>
     * <li>UserResourceを受け取り、nameに文字を追加してUserResourceを返却する。</li> <br>
     * POSTメソッド確認用。
     * </ul>
     * @return
     */
    @PostMapping
    public UserResource create(
            @RequestHeader(name = "Content-Length", required = false) String length,
            @RequestBody UserResource user) {
        LOGGER.debug("create");
        LOGGER.info("Content-Length : {}", length);

        user.setName(user.getName() + "_created");

        return user;
    }

    /**
     * <ul>
     * <li>UserResourceのリストを返却する。</li> <br>
     * Collection系の取得確認用。
     * </ul>
     * @return
     */
    @GetMapping(value = "list")
    public List<UserResource> list() {
        LOGGER.debug("list");
        List<UserResource> retList = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            UserResource user = new UserResource();
            user.setName("test" + i);
            user.setAge(20 + i);
            retList.add(user);
        }

        return retList;
    }

    /**
     * <ul>
     * <li>Stringの送受信を行う。</li> <br>
     * ByteArrayHttpMessageConverter確認用 <br>
     * StringHttpMessageConverter確認用 <br>
     * ResourceHttpMessageConverter確認用
     * </ul>
     * @return
     */
    @PostMapping(value = "str")
    public String str(@RequestBody String str) {
        LOGGER.debug("str");
        return str + "_received";
    }

    /**
     * <ul>
     * <li>MultiValueMap<String, String>の送受信を行う。</li> <br>
     * AllEncompassingFormHttpMessageConverter確認用。
     * </ul>
     * @param rcvMap
     * @return
     */
    @PostMapping(value = "map", produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public MultiValueMap<String, String> map(
            @RequestBody MultiValueMap<String, String> rcvMap) {
        LOGGER.debug("map");

        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();

        map.add("name", rcvMap.getFirst("name") + "_received");
        map.add("age", "20");

        return map;
    }

    /**
     * <ul>
     * <li>UserResourceを受信して変更を加えて送信する。</li> <br>
     * PUTメソッド確認用。
     * </ul>
     */
    @PutMapping
    public UserResource put(@RequestBody UserResource user) {
        LOGGER.debug("put");

        user.setName(user.getName() + "_put");

        return user;
    }

    /**
     * <ul>
     * <li>DELETEメソッドを受けるのみで何もしない。</li> <br>
     * DELETEメソッド確認用。
     * </ul>
     */
    @DeleteMapping(value = "{deleteId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("deleteId") String deleteId) {
        LOGGER.debug("delete:{}", deleteId);
    }

    /**
     * <ul>
     * <li>UserResourceを返却する。</li> <br>
     * spring-mvc-rscl.xmlにて、パス：/rscl/basic には、BASIC認証を設定している。 <br>
     * ユーザ：hoge、 パスワード：password1234 <br>
     * BASIC認証確認用
     * </ul>
     */
    @GetMapping(value = "basic")
    public UserResource basic() {
        LOGGER.debug("basic");

        UserResource user = new UserResource();
        user.setName("test");
        user.setAge(20);
        return user;
    }

    /**
     * <ul>
     * <li>パスによって、返却するステータスコード変更する。</li>
     * <li>パス：exp/1 : 404を返却。</li>
     * <li>パス：exp/2 : 500を返却。</li> <br>
     * 特定のステータスコード返却確認用。
     * </ul>
     * @param id
     * @return
     */
    @GetMapping(value = "exception/{id}")
    public UserResource exception(@PathVariable("id") String id) {
        LOGGER.debug("exception:{}", id);

        return switch (id) {
        case "1" -> throw new ResourceNotFoundException(ResultMessages.error()
                .add("e.rc.fw.8000"));
        case "2" -> throw new IntentionalException(ResultMessages.error().add(
                "e.rc.fw.9000"));
        default -> new UserResource();
        };
    }

    /**
     * <ul>
     * <li>503を返却するが、2回目のリトライでUserResourceを返却。</li> <br>
     * リトライ処理確認用。
     * </ul>
     * @param retryCount
     * @return
     */
    @GetMapping(value = "retry")
    public UserResource retry(@RequestHeader("x-Retry") String retryCount) {
        LOGGER.debug("retry:{}", retryCount);

        UserResource user = null;

        // 2回目のリトライ以外の場合は例外をスローする
        if (!"2".equals(retryCount)) {
            throw new MappingException(ResultMessage.fromCode("e.rc.fw.9002")
                    .getText());
        }

        user = new UserResource();
        user.setName("test");
        user.setAge(20);

        return user;
    }

    /**
     * リトライ処理確認でスローするMappingException用のハンドラ
     * @param ex
     * @return
     */
    @ExceptionHandler({ MappingException.class })
    public ResponseEntity<ApiError> mappingExceptionHandler(Exception ex) {
        ApiError err = new ApiError(ResultMessage.fromCode("e.rc.fw.9002")
                .getText());
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).headers(
                new HttpHeaders()).body(err);
    }

    /**
     * <ul>
     * <li>独自ステータスコード 901 を返却する。</li> <br>
     * 独自ステータスコード(901)返却確認用
     * </ul>
     * @param res
     * @return
     */
    @GetMapping(value = "returnHttpStatus901")
    public UserResource returnHttpStatus901(HttpServletResponse res) {
        LOGGER.debug("exp901");

        res.setStatus(901);

        return null;
    }

    /**
     * <ul>
     * <li>例外が発生するまで待機し、maxPoolSize+1個目のリクエストがきたらUserResourceを返却する。</li> <br>
     * スレッド数制限確認用
     * </ul>
     * @throws InterruptedException
     * @throws TimeoutException
     * @throws BrokenBarrierException
     */
    @GetMapping(value = "await")
    public UserResource await() throws InterruptedException, BrokenBarrierException, TimeoutException {
        this.barrierForAsyncThreadLimitation.await(30, TimeUnit.SECONDS);
        UserResource user = new UserResource();
        user.setName("test");
        user.setAge(20);
        return user;
    }

    /**
     * <ul>
     * <li>{@link #releaseForMaxPool()}が呼び出されるまで待機し、UserResourceを返却する。</li> <br>
     * スレッド数制限確認用
     * </ul>
     * @throws InterruptedException
     */
    @GetMapping(value = "awaitForMaxPool")
    public UserResource awaitForMaxPool() throws InterruptedException {
        UserResource user = new UserResource();
        user.setName("test");
        user.setAge(20);

        long nanos = TimeUnit.SECONDS.toNanos(10L);
        this.lock.lock();
        try {
            this.await = true;
            while (this.await) {
                if (nanos <= 0L) {
                    return new UserResource();
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
     * <li>UserResourceを返却する。</li> <br>
     * スレッド数制限確認用
     * </ul>
     */
    @GetMapping(value = "noawait")
    public UserResource noAwait() {
        UserResource user = new UserResource();
        user.setName("test");
        user.setAge(20);
        return user;
    }

    /**
     * <ul>
     * <li>readTimeout + 1 秒待って、UserResourceを返却する。</li> <br>
     * </ul>
     * @throws InterruptedException
     */
    @GetMapping(value = "readTimeout")
    public UserResource readTimeout() throws InterruptedException {
        LOGGER.debug("readTimeout");

        UserResource user = new UserResource();
        user.setName("test");
        user.setAge(20);

        ThreadUtils.sleep(Duration.ofMillis(this.readTimeout + 1000));

        return user;
    }

    /**
     * <ul>
     * <li>ファイルを受信し、受信したファイルからUserResourceを生成して返却する。</li> <br>
     * ファイルアップロード確認用。
     * </ul>
     * @return
     */
    @PostMapping(value = "upload")
    public UserResource upload(@RequestParam("file") MultipartFile mf) {
        LOGGER.debug("upload");

        UserResource user = new UserResource();
        File rcvFile = null;
        try (InputStream in = mf.getInputStream()) {
            rcvFile = File.createTempFile("rcv", ".txt");
            Files.copy(in, rcvFile.toPath(),
                    StandardCopyOption.REPLACE_EXISTING);

            List<String> lines = Files.readAllLines(rcvFile.toPath(),
                    StandardCharsets.UTF_8);

            user.setName(lines.get(0));
            user.setAge(Integer.parseInt(lines.get(1)));
        } catch (IOException e) {
            throw new SystemException("e.rc.fw.9001", e);
        } finally {
            if (rcvFile != null) {
                // deleteメソッドによる削除の成功失敗によってその後のアクションをとることは無いため、SonarQube指摘は未対応としています。
                rcvFile.delete();
            }
        }

        return user;
    }

    /**
     * <ul>
     * <li>JavaBeanのリストをXML文字列で返却。</li> <br>
     * XML ⇒ JavaBeanのリスト確認用。
     * </ul>
     * @return
     */
    @GetMapping(value = "collection")
    public UserResourceList collection() {
        LOGGER.debug("collection");

        UserResourceList userList = new UserResourceList();
        List<UserResource> list = new ArrayList<UserResource>();
        for (int i = 1; i <= 5; i++) {
            UserResource user = new UserResource();
            user.setName("test" + i);
            user.setAge(20 + i);
            list.add(user);
        }
        userList.setList(list);

        return userList;
    }

}
