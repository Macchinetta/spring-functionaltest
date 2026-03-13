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

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;
import jp.co.ntt.fw.spring.functionaltest.app.cmmn.model.Message;
import jp.co.ntt.fw.spring.functionaltest.app.cmmn.model.Result;
import jp.co.ntt.fw.spring.functionaltest.app.cmmn.model.User;

@RestController
@RequestMapping(value = "/api/v1/async")
public class AsyncAPIController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncAPIController.class);

    /**
     * <ul>
     * <li>Userを受け取り、nameに文字を追加してUserを返却する。</li> <br>
     * POSTメソッド確認用。
     * </ul>
     * 
     * @return 変更後のユーザ情報
     */
    @PostMapping()
    public User insert(@RequestBody User user) {
        LOGGER.info("Call API:insert");
        user.setName(user.getName() + "_created");
        return user;
    }

    /**
     * <ul>
     * <li>Userのリストを受け取り件数を返却する</li> <br>
     * データ一括登録確認用。
     * </ul>
     * 
     * @return 件数
     */
    @PostMapping(value = "bulk")
    public Result bulkInsert(@RequestBody List<User> users) {
        LOGGER.info("Call API:bulk");
        users.forEach(user -> {
            LOGGER.info("user name, age: {}, {}", user.getName(), user.getAge());
        });
        return new Result(users.size());
    }

    /**
     * <ul>
     * <li>メッセージを返却する。</li> <br>
     * 大量データ取得確認用。
     * </ul>
     * 
     * @return
     */
    @GetMapping(value = "largeData")
    public Message largeData() {
        LOGGER.info("Call API:largeData");
        var message = new Message();
        message.setTitle("タイトル");
        var sb = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            sb.append("1234567890");
        }
        message.setText(sb.toString());
        return message;
    }

    /**
     * <ul>
     * <li>返却するステータスコード変更する。</li>
     * 特定のステータスコード返却確認用。
     * </ul>
     * 
     * @param id
     * @return
     */
    @GetMapping(value = "exception/{status}")
    public User exception(@PathVariable("status") int status, HttpServletResponse res) {
        LOGGER.info("Call API:exception:{}", status);
        res.setStatus(status);

        if (status >= 400 && status < 600) {
            return null;
        }
        return new User("exceptionTest", 20);
    }

    /**
     * <ul>
     * <li>Userを返却する。</li> <br>
     * リクエストヘッダの確認用。
     * </ul>
     * 
     * @return ユーザ情報
     */
    @GetMapping(value = "header")
    public User customHeader(@RequestHeader("Custom-Header") String customHeader) {
        LOGGER.info("Call API:customHeader");
        LOGGER.info("Custom-Header: {}", customHeader);
        User user = new User();
        user.setName("test");
        user.setAge(20);
        return user;
    }
}
