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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jp.co.ntt.fw.spring.functionaltest.app.cmmn.model.User;

@RestController
@RequestMapping(value = "/api/v1/headers")
public class RequestHeaderAPIController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RequestHeaderAPIController.class);

    /**
     * <ul>
     * <li>Userを受け取り、nameに文字を追加してUserを返却する。</li> <br>
     * リクエストヘッダの確認用。
     * </ul>
     * 
     * @return ユーザ情報
     */
    @PostMapping(value = "contenttype")
    public User contentTypeHeader(
            @RequestHeader(name = "Content-Type", required = false) String contentType,
            @RequestBody User user) {
        LOGGER.info("Call API:contentTypeHeader");
        LOGGER.info("Content-Type: {}", contentType);

        user.setName(user.getName() + "_created");

        return user;
    }

    /**
     * <ul>
     * <li>Userを返却する。</li> <br>
     * リクエストヘッダの確認用。
     * </ul>
     * 
     * @return ユーザ情報
     */
    @GetMapping(value = "accept")
    public User acceptHeader(@RequestHeader("Accept") String accept) {
        LOGGER.info("Call API:acceptHeader");
        LOGGER.info("Accept: {}", accept);
        User user = new User();
        user.setName("test");
        user.setAge(20);
        return user;
    }

    /**
     * <ul>
     * <li>Userを返却する。</li> <br>
     * リクエストヘッダの確認用。
     * </ul>
     * 
     * @return ユーザ情報
     */
    @GetMapping(value = "custom")
    public User customHeader(@RequestHeader("Custom-Header") String customHeader) {
        LOGGER.info("Call API:customHeader");
        LOGGER.info("Custom-Header: {}", customHeader);
        User user = new User();
        user.setName("test");
        user.setAge(20);
        return user;
    }

    /**
     * <ul>
     * <li>Userを返却する。</li> <br>
     * グループ設定時のリクエストヘッダの確認用。
     * </ul>
     * 
     * @param defaultHeader デフォルトヘッダ
     * @param firstHeader   firstグループヘッダ
     * @param secondHeader  secondグループヘッダ
     * @return ユーザ情報
     */
    @GetMapping(value = "group")
    public User groupHeader(@RequestHeader("Default-Header") String defaultHeader,
            @RequestHeader(name = "First-Header", required = false) String firstHeader,
            @RequestHeader(name = "Second-Header", required = false) String secondHeader) {
        LOGGER.info("Call API:groupHeader");
        LOGGER.info("Default-Header: {}", defaultHeader);
        LOGGER.info("First-Header: {}", firstHeader);
        LOGGER.info("Second-Header: {}", secondHeader);
        User user = new User();
        user.setName("test");
        user.setAge(20);
        return user;
    }

}
