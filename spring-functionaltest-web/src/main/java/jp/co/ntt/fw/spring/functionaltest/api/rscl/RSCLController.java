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
package jp.co.ntt.fw.spring.functionaltest.api.rscl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.terasoluna.gfw.common.exception.ResourceNotFoundException;
import org.terasoluna.gfw.common.exception.SystemException;
import org.terasoluna.gfw.common.message.ResultMessage;
import org.terasoluna.gfw.common.message.ResultMessages;

import jp.co.ntt.fw.spring.functionaltest.app.cmmn.exception.IntentionalException;

@RestController
public class RSCLController {
    private static final Logger logger = LoggerFactory.getLogger(
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
    @RequestMapping(method = RequestMethod.GET)
    public UserResource getUser() {
        logger.debug("getUser");
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
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public UserResource getUser(@PathVariable("id") String id) {
        logger.debug("getUser{id:{}}", id);
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
    @RequestMapping(method = RequestMethod.POST)
    public UserResource create(@RequestBody UserResource user) {
        logger.debug("create");

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
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public List<UserResource> list() {
        logger.debug("list");
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
    @RequestMapping(value = "str", method = RequestMethod.POST)
    public String str(@RequestBody String str) {
        logger.debug("str");
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
    @RequestMapping(value = "map", method = RequestMethod.POST, produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public MultiValueMap<String, String> map(
            @RequestBody MultiValueMap<String, String> rcvMap) {
        logger.debug("map");

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
    @RequestMapping(method = RequestMethod.PUT)
    public UserResource put(@RequestBody UserResource user) {
        logger.debug("put");

        user.setName(user.getName() + "_put");

        return user;
    }

    /**
     * <ul>
     * <li>DELETEメソッドを受けるのみで何もしない。</li> <br>
     * DELETEメソッド確認用。
     * </ul>
     */
    @RequestMapping(value = "{deleteId}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("deleteId") String deleteId) {
        logger.debug("delete:{}", deleteId);
    }

    /**
     * <ul>
     * <li>UserResourceを返却する。</li> <br>
     * spring-mvc-rscl.xmlにて、パス：/rscl/basic には、BASIC認証を設定している。 <br>
     * ユーザ：hoge、 パスワード：password1234 <br>
     * BASIC認証確認用
     * </ul>
     */
    @RequestMapping(value = "basic", method = RequestMethod.GET)
    public UserResource basic() {
        logger.debug("basic");

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
    @RequestMapping(value = "exception/{id}", method = RequestMethod.GET)
    public UserResource exception(@PathVariable("id") String id) {
        logger.debug("exception:{}", id);

        UserResource user = null;

        if (id.equals("1")) {
            throw new ResourceNotFoundException(ResultMessages.error().add(
                    "e.rc.fw.8000"));
        } else if (id.equals("2")) {
            throw new IntentionalException(ResultMessages.error().add(
                    "e.rc.fw.9000"));
        } else {
            user = new UserResource();
        }

        return user;
    }

    /**
     * <ul>
     * <li>503を返却するが、2回目のリトライでUserResourceを返却。</li> <br>
     * リトライ処理確認用。
     * </ul>
     * @param retryCount
     * @return
     */
    @RequestMapping(value = "retry", method = RequestMethod.GET)
    public UserResource retry(@RequestHeader("x-Retry") String retryCount) {
        logger.debug("retry:{}", retryCount);

        UserResource user = null;

        // 2回目のリトライで正常終了させる。
        if (!"2".equals(retryCount)) {
            throw new MappingException(ResultMessage.fromCode("e.rc.fw.9002")
                    .getText());
        } else {
            user = new UserResource();
            user.setName("test");
            user.setAge(20);
        }

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
    @RequestMapping(value = "returnHttpStatus901", method = RequestMethod.GET)
    public UserResource returnHttpStatus901(HttpServletResponse res) {
        logger.debug("exp901");

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
    @RequestMapping(value = "await", method = RequestMethod.GET)
    public UserResource await() throws InterruptedException, BrokenBarrierException, TimeoutException {
        barrierForAsyncThreadLimitation.await(30, TimeUnit.SECONDS);
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
    @RequestMapping(value = "awaitForMaxPool", method = RequestMethod.GET)
    public UserResource awaitForMaxPool() throws InterruptedException {
        UserResource user = new UserResource();
        user.setName("test");
        user.setAge(20);

        long nanos = TimeUnit.SECONDS.toNanos(10L);
        lock.lock();
        try {
            await = true;
            while (await) {
                if (nanos <= 0L) {
                    return new UserResource();
                }
                nanos = startSignal.awaitNanos(nanos);
            }
            return user;
        } finally {
            lock.unlock();
        }
    }

    /**
     * <ul>
     * <li>{@link #awaitForMaxPool()}の待機を解除する。</li> <br>
     * スレッド数制限確認用
     * </ul>
     */
    @RequestMapping(value = "releaseForMaxPool", method = RequestMethod.GET)
    public boolean releaseForMaxPool() {
        lock.lock();
        try {
            await = false;
            startSignal.signalAll();
            return true;
        } finally {
            lock.unlock();
        }
    }

    /**
     * <ul>
     * <li>UserResourceを返却する。</li> <br>
     * スレッド数制限確認用
     * </ul>
     */
    @RequestMapping(value = "noawait", method = RequestMethod.GET)
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
    @RequestMapping(value = "readTimeout", method = RequestMethod.GET)
    public UserResource readTimeout() throws InterruptedException {
        logger.debug("readTimeout");

        UserResource user = new UserResource();
        user.setName("test");
        user.setAge(20);

        Thread.sleep(readTimeout + 1000);

        return user;
    }

    /**
     * <ul>
     * <li>ファイルを受信し、受信したファイルからUserResourceを生成して返却する。</li> <br>
     * ファイルアップロード確認用。
     * </ul>
     * @return
     */
    @RequestMapping(value = "upload", method = RequestMethod.POST)
    public UserResource upload(@RequestParam("file") MultipartFile mf) {
        logger.debug("upload");

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
     * XML　⇒　JavaBeanのリスト確認用。
     * </ul>
     * @return
     */
    @RequestMapping(value = "collection", method = RequestMethod.GET)
    public UserResourceList collection() {
        logger.debug("collection");

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
