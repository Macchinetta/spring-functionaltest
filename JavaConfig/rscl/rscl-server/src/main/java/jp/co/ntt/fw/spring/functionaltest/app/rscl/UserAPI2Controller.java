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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jp.co.ntt.fw.spring.functionaltest.app.cmmn.model.User;

@RestController
@RequestMapping(value = "/api2/v1/users")
public class UserAPI2Controller {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserAPI2Controller.class);

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
        user.setName("test2");
        user.setAge(25);
        return user;
    }

}
