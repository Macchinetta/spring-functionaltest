/*
 * Copyright 2014-2018 NTT Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jp.co.ntt.fw.spring.functionaltest.app.aply;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

@ControllerAdvice
@Order(0)
public class APLY04CommonControllerAdvice01 {

    private static final Logger logger = LoggerFactory.getLogger(
            APLY04CommonControllerAdvice01.class);

    // パラメータ指定無しだと全てのAdviceクラスのパラメータに対してinitBinderを実施する為、今回の試験ではパラメータを指定
    @InitBinder("commonParam1")
    public void initBinder() {
        logger.info(
                "[APLY040300301]APLY04CommonControllerAdvice01 initBinder Method Called");
    }

    @ModelAttribute
    public CommonParameters setUpCommonParameters01(
            @RequestParam(value = "commonParam1", defaultValue = "defCommonParam1") String commonParam1) {
        logger.info(
                "[APLY040300302]APLY04CommonControllerAdvice01 setUpCommonParameters01 Method Called");
        CommonParameters params = new CommonParameters();
        params.setCommonParam1(commonParam1);
        return params;
    }
}
