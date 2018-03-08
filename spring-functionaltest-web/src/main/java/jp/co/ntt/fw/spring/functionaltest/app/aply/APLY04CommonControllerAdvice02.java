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
@Order(1)
public class APLY04CommonControllerAdvice02 {

    private static final Logger logger = LoggerFactory.getLogger(
            APLY04CommonControllerAdvice02.class);

    // パラメータ指定無しだと全てのAdviceクラスのパラメータに対してinitBinderを実施する為、今回の試験ではパラメータを指定
    @InitBinder("commonParam2")
    public void initBinder() {
        logger.info(
                "[APLY040300303]APLY04CommonControllerAdvice02 initBinder Method Called");
    }

    @ModelAttribute
    public CommonParameters setUpCommonParameters02(
            @RequestParam(value = "commonParam2", defaultValue = "defCommonParam2") String commonParam2) {
        logger.info(
                "[APLY040300304]APLY04CommonControllerAdvice02 setUpCommonParameters02 Method Called");
        CommonParameters params = new CommonParameters();
        params.setCommonParam2(commonParam2);
        return params;
    }
}
