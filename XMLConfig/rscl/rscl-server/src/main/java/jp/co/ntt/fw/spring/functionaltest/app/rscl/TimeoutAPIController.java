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

import org.apache.commons.lang3.ThreadUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jp.co.ntt.fw.spring.functionaltest.app.cmmn.model.User;

@RestController
@RequestMapping(value = "/api/v1/timeout")
public class TimeoutAPIController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TimeoutAPIController.class);

    @Value("${rscl.timeoutRestTemplate.readTimeout}")
    long readTimeout;

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
