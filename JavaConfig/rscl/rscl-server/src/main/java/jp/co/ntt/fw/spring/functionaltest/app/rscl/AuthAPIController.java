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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jp.co.ntt.fw.spring.functionaltest.app.cmmn.model.User;

@RestController
@RequestMapping(value = "/api/v1/auth")
public class AuthAPIController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthAPIController.class);

    /**
     * <ul>
     * <li>Userを返却する。</li> <br>
     * spring-mvc-rscl.xmlにて、パス：/rscl/basic には、BASIC認証を設定している。 <br>
     * ユーザ：hoge、 パスワード：password1234 <br>
     * BASIC認証確認用
     * </ul>
     */
    @GetMapping(value = "basic")
    public User basic(@RequestHeader("Authorization") String authHeader) {
        LOGGER.info("Call API:basic");
        LOGGER.info("Authorization: {}", authHeader);

        User user = new User();
        user.setName("test");
        user.setAge(20);
        return user;
    }

}
