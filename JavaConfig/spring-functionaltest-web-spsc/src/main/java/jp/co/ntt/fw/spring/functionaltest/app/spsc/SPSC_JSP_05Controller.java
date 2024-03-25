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

@RequestMapping("jsp")
@Controller
public class SPSC_JSP_05Controller {

    @Value("${host.ip}")
    private String connectIp;

    @Value("${host.http.port}")
    private String connectHttpPort;

    @Value("${host.https.port}")
    private String connectHttpsPort;

    @GetMapping("0501/001")
    public String handle0501001() {
        return "jsp/spsc/result";
    }

    @GetMapping("0501/002")
    public String handle0501002() {
        return "jsp/spsc/result";
    }

    @GetMapping("0501/003")
    public String handle0501003() {
        return "jsp/spsc/result";
    }

    @GetMapping("0501/004")
    public String handle0501004() {
        return "jsp/spsc/result";
    }

    @GetMapping("0501/005")
    public String handle0501005(Model model) {
        model.addAttribute("connectIp", connectIp);
        model.addAttribute("connectHttpPort", connectHttpPort);
        model.addAttribute("connectHttpsPort", connectHttpsPort);
        return "jsp/spsc/resultImage";
    }
}
