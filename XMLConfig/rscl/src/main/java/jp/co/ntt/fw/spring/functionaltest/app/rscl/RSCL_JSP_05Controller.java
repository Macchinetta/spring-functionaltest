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

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.inject.Inject;
import jp.co.ntt.fw.spring.functionaltest.domain.service.rscl.TimeoutRestClientService;

@RequestMapping("jsp/rscl")
@Controller
public class RSCL_JSP_05Controller {

    @Inject
    TimeoutRestClientService timeoutRestClientService;

    @GetMapping(value = "0501/001")
    public String handle0501001First(Model model) {

        model.addAttribute("testDescription", "Case [Conection Timeout]");
        model.addAttribute("testId", "0501/001");

        return "jsp/rscl/occurTimeout";
    }

    @PostMapping(value = "0501/001")
    public void handle0501001(Model model) {

        this.timeoutRestClientService.confirmTimeout01();

    }

    @GetMapping(value = "0502/001")
    public String handle0502001First(Model model) {

        model.addAttribute("testDescription", "Case [Read Timeout]");
        model.addAttribute("testId", "0502/001");

        return "jsp/rscl/occurTimeout";
    }

    @PostMapping(value = "0502/001")
    public void handle0502001(Model model) {

        this.timeoutRestClientService.confirmTimeout02();
    }

}
