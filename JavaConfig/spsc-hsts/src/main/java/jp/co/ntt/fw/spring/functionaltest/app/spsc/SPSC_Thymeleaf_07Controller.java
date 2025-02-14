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
package jp.co.ntt.fw.spring.functionaltest.app.spsc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("thymeleaf")
@Controller
public class SPSC_Thymeleaf_07Controller {

    @Value("${host.ip}")
    private String connectIp;

    /**
     * <ul>
     * <li>HttpResponseのヘッダに以下が含まれるかの確認用</li>
     * </ul>
     * Strict-Transport-Security: max-age=20; includeSubDomains; preload
     * @param model Model
     * @return HSTSテスト用の画面
     */
    @GetMapping("0701/001")
    public String handle0701001(Model model) {
        model.addAttribute("testNo", "0701/001");
        model.addAttribute("connectIp", connectIp);
        return "thymeleaf/spsc/resultHsts";
    }

    /**
     * <ul>
     * <li>HttpResponseのヘッダに以下が含まれるかの確認用（デフォルト）</li>
     * </ul>
     * Strict-Transport-Security: max-age=31536000; includeSubDomains;
     * @param model Model
     * @return HSTSテスト用の画面
     */
    @GetMapping("0701/002")
    public String handle0701002(Model model) {
        model.addAttribute("testNo", "0701/002");
        model.addAttribute("connectIp", connectIp);
        return "thymeleaf/spsc/resultHsts";
    }

    /**
     * <ul>
     * <li>HSTSの動作確認用</li>
     * </ul>
     * @param model Model
     * @return HSTSテスト用の画面
     */
    @GetMapping("0702/001")
    public String handle0702001(Model model) {
        model.addAttribute("testNo", "0702/001");
        model.addAttribute("connectIp", connectIp);
        return "thymeleaf/spsc/resultHsts";
    }
}
