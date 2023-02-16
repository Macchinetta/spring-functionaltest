/*
 * Copyright(c) 2014 NTT Corporation.
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

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.inject.Inject;
import jp.co.ntt.fw.spring.functionaltest.domain.model.UserResource;
import jp.co.ntt.fw.spring.functionaltest.domain.service.rscl.ProxyRestClientService;

@RequestMapping("rscl")
@Controller
public class RSCL14Controller {

    @Inject
    ProxyRestClientService proxyRestClientService;

    @GetMapping(value = "1401/001")
    public String handle1401001First(Model model) {

        model.addAttribute("testDescription", "Proxy経由によるREST API呼び出し");
        model.addAttribute("testId", "1401/001");

        return "rscl/callViaProxy";
    }

    @PostMapping(value = "1401/001")
    public String handle1401001(Model model, @RequestParam String path) {

        UserResource rcvUser = this.proxyRestClientService.confirmProxy01(path);

        model.addAttribute("resultDescription",
                "Proxyホスト/ポートをプロパティファイルに定義し、Proxy経由で、REST APIを呼び出し、JavaBeanを取得する");
        model.addAttribute("user", rcvUser);

        return "rscl/resultUserInf";
    }

    @GetMapping(value = "1401/002")
    public String handle1401002First(Model model) {

        model.addAttribute("testDescription", "Proxy経由によるREST API呼び出し");
        model.addAttribute("testId", "1401/002");

        return "rscl/callViaProxy";
    }

    @PostMapping(value = "1401/002")
    public String handle1401002(Model model, @RequestParam String path) {

        UserResource rcvUser = this.proxyRestClientService.confirmProxy02(path);

        model.addAttribute("resultDescription",
                "Proxyホスト/ポートをプロパティファイルに定義し、Proxy経由で、REST APIを呼び出し、JavaBeanを取得する");
        model.addAttribute("user", rcvUser);

        return "rscl/resultUserInf";
    }

    @GetMapping(value = "1402/001")
    public String handle1402001First(Model model) {

        model.addAttribute("testDescription", "Proxy経由によるREST API呼び出し");
        model.addAttribute("testId", "1402/001");

        return "rscl/callViaProxy";
    }

    @PostMapping(value = "1402/001")
    public String handle1402001(Model model, @RequestParam String path) {

        UserResource rcvUser = this.proxyRestClientService
                .confirmSimpleHttpClientProxy(path);

        model.addAttribute("resultDescription",
                "Proxyホスト/ポートをプロパティファイルに定義し、Proxy経由で、REST APIを呼び出し、JavaBeanを取得する");
        model.addAttribute("user", rcvUser);

        return "rscl/resultUserInf";
    }
}
