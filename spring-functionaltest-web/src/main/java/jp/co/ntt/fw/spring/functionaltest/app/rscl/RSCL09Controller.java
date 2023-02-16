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

import jakarta.inject.Inject;
import jp.co.ntt.fw.spring.functionaltest.domain.model.UserResource;
import jp.co.ntt.fw.spring.functionaltest.domain.service.rscl.RestClientService;

@RequestMapping("rscl")
@Controller
public class RSCL09Controller {

    @Inject
    RestClientService restClientService;

    @GetMapping(value = "0901/001")
    public String handle0901001First(Model model) {

        model.addAttribute("testDescription", "File Upload");
        model.addAttribute("testId", "0901/001");

        return "rscl/fileUpload";
    }

    @PostMapping(value = "0901/001")
    public String handle0901001(Model model) {

        UserResource rcvUser = this.restClientService.upload();

        model.addAttribute("resultDescription", "アップロードファイルの記述内容をユーザ情報に変換");
        model.addAttribute("user", rcvUser);

        return "rscl/resultUserInf";
    }

}
