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

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.terasoluna.gfw.common.exception.SystemException;

import jakarta.servlet.http.HttpServletResponse;
import jp.co.ntt.fw.spring.functionaltest.app.cmmn.model.User;
import jp.co.ntt.fw.spring.functionaltest.app.cmmn.model.UserList;

@RestController
@RequestMapping(value = "/api/v1/users")
public class UserAPIController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserAPIController.class);

    /**
     * <ul>
     * <li>Userを返却する。</li> <br>
     * nameにパス終端の文字列を付与する。 <br>
     * GETメソッドの確認用。
     * </ul>
     * 
     * @param id
     * @return
     */
    @GetMapping(value = "{id}")
    public User getUser(@PathVariable("id") String id) {
        LOGGER.info("Call API:getUser", id);
        LOGGER.info("id: {}", id);
        User user = new User();
        user.setName("test_" + id);
        user.setAge(20);

        return user;
    }

    /**
     * <ul>
     * <li>Userのリストを返却する。</li> <br>
     * Collection系の取得確認用。
     * </ul>
     * 
     * @return
     */
    @GetMapping
    public List<User> list() {
        LOGGER.info("Call API:list");
        List<User> retList = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            User user = new User();
            user.setName("test" + i);
            user.setAge(20 + i);
            retList.add(user);
        }

        return retList;
    }

    /**
     * <ul>
     * <li>Userを受け取り、nameに文字を追加してUserを返却する。</li> <br>
     * POSTメソッド確認用。
     * </ul>
     * 
     * @return
     */
    @PostMapping()
    public User create(
            @RequestHeader(name = "Content-Type", required = false) String contentType,
            @RequestHeader(name = "Content-Length", required = false) String length,
            @RequestBody User user) {
        LOGGER.info("Call API:create");
        LOGGER.info("Content-Type : {}", contentType);
        LOGGER.info("Content-Length : {}", length);

        user.setName(user.getName() + "_created");

        return user;
    }

    /**
     * <ul>
     * <li>Userを受信して変更を加えて送信する。</li> <br>
     * PUTメソッド確認用。
     * </ul>
     */
    @PutMapping()
    public User put(@RequestBody User user) {
        LOGGER.info("Call API:put");

        user.setName(user.getName() + "_put");

        return user;
    }

    /**
     * <ul>
     * <li>Userを受信して変更を加えて送信する。</li> <br>
     * PUTメソッド確認用。
     * </ul>
     */
    @PatchMapping()
    public User patch(@RequestBody User user) {
        LOGGER.info("Call API:patch");

        user.setName(user.getName() + "_patch");

        return user;
    }

    /**
     * <ul>
     * <li>DELETEメソッドを受けるのみで何もしない。</li> <br>
     * DELETEメソッド確認用。
     * </ul>
     */
    @DeleteMapping(value = "{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String deleteId) {
        LOGGER.info("Call API:delete:{}", deleteId);
    }

    /**
     * <ul>
     * <li>HEADメソッドを受けるのみで何もしない。</li> <br>
     * HEADメソッド確認用。
     * </ul>
     */
    @RequestMapping(value = "head/{id}", method = RequestMethod.HEAD)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> head(@PathVariable("id") String id) {
        LOGGER.info("Call API:head:{}", id);
        return ResponseEntity.ok().header("health", "ok").build();
    }

    /**
     * <ul>
     * <li>Userを返却する。</li> <br>
     * GETメソッドの確認用。
     * </ul>
     * 
     * @return
     */
    @GetMapping(value = "single")
    public User getSingle() {
        LOGGER.info("Call API:getSingle");

        User user = new User();
        user.setName("test");
        user.setAge(20);
        return user;
    }

    /**
     * <ul>
     * <li>Userを返却する。</li> <br>
     * Proxy認証付きGETメソッドの確認用。
     * </ul>
     * 
     * @return
     */
    @GetMapping(value = "/single/proxyAuth")
    public User getSingleProxyAuth() {
        LOGGER.info("Call API:getSingleProxyAuth");

        User user = new User();
        user.setName("test2");
        user.setAge(21);
        return user;
    }

    /**
     * <ul>
     * <li>JavaBeanのリストをXML文字列で返却。</li> <br>
     * XML ⇒ JavaBeanのリスト確認用。
     * </ul>
     * 
     * @return
     */
    @GetMapping(value = "xmlcollection")
    public UserList collection() {
        LOGGER.info("Call API:xmlcollection");

        UserList userList = new UserList();
        List<User> list = new ArrayList<User>();
        for (int i = 1; i <= 5; i++) {
            User user = new User();
            user.setName("test" + i);
            user.setAge(20 + i);
            list.add(user);
        }
        userList.setList(list);

        return userList;
    }

    /**
     * <ul>
     * <li>ファイルを受信し、受信したファイルからUserを生成して返却する。</li> <br>
     * ファイルアップロード確認用。
     * </ul>
     * 
     * @return
     */
    @PostMapping(value = "upload")
    public User upload(@RequestParam("file") MultipartFile mf) {
        LOGGER.info("Call API:upload");

        User user = new User();
        File rcvFile = null;
        try (InputStream in = mf.getInputStream()) {
            rcvFile = File.createTempFile("rcv", ".txt");
            Files.copy(in, rcvFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

            List<String> lines = Files.readAllLines(rcvFile.toPath(), StandardCharsets.UTF_8);

            user.setName(lines.get(0));
            user.setAge(Integer.parseInt(lines.get(1)));
        } catch (IOException e) {
            throw new SystemException("e.sf.rc.9001", e);
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
     * <li>ダウンロードデータ(CSV形式)を返却する。</li> <br>
     * ダウンロード確認用。
     * </ul>
     * 
     * @param statusCode HTTPステータスコード
     * @param res        HTTPレスポンス
     * @return ダウンロードデータ(CSV形式)
     */
    @GetMapping(value = "download")
    public String download() {
        LOGGER.info("Call API:download");

        StringBuilder userListStr = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            userListStr.append("test").append(i).append(",");
            userListStr.append(30).append("\n");
        }
        return userListStr.toString();
    }

    /**
     * <ul>
     * <li>ダウンロードデータ(CSV形式)を返却する。</li> <br>
     * ダウンロード確認用。
     * </ul>
     * 
     * @param statusCode HTTPステータスコード
     * @param res        HTTPレスポンス
     * @return ダウンロードデータ(CSV形式)
     */
    @GetMapping(value = "download/{statusCode}")
    public String downloadError(@PathVariable("statusCode") int statusCode, HttpServletResponse res) {
        LOGGER.info("Call API:download:{}", statusCode);
        res.setStatus(statusCode);

        if (statusCode != 200) {
            return "";
        }

        StringBuilder userListStr = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            userListStr.append("test").append(i).append(",");
            userListStr.append(30).append("\n");
        }
        return userListStr.toString();
    }

    /**
     * <ul>
     * <li>Userを受け取り、nameに文字を追加してUserを返却する。</li> <br>
     * RESTfulなURL確認用。
     * </ul>
     * 
     * @return
     */
    @PostMapping(value = "restful/{action}")
    public User createParam(@PathVariable("action") String action, @RequestBody User user) {
        LOGGER.info("Call API:createParam");
        LOGGER.info("Action: {}", action);
        user.setName(user.getName() + "_created");
        return user;
    }
}
