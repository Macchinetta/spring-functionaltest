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
package jp.co.ntt.fw.spring.functionaltest.app.spsm;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

/**
 * セッション削除機能<br>
 * <p>
 * セッションを画面遷移の途中で削除したい場合、restTemplateを使用してこのControllerにアクセスする。
 * </p>
 */
@Controller
public class SPSM03Controller {

    @RequestMapping(value = "0301/001", params = "afterLogin", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Void> handleDeleteSession(
            SessionStatus sessionStatus) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
