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
package jp.co.ntt.fw.spring.functionaltest.app.dtop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.inject.Inject;
import jp.co.ntt.fw.spring.functionaltest.domain.service.dtop.DateOperationService;

@RequestMapping("dtop")
@Controller
public class DTOP06Controller {

    @Inject
    public DateOperationService dateOperationService;

    @GetMapping(value = "0601/001")
    public String handle05001(Model model) {
        model.addAttribute("resultDate", dateOperationService
                .getJapaneseDateStr("Gy.MM.dd"));
        return "dtop/showJapaneseDate";
    }

    @GetMapping(value = "0601/002")
    public String handle05002(Model model) {
        model.addAttribute("resultDate", dateOperationService
                .getJapaneseDateStr("GGGGyy/MM/dd"));
        return "dtop/showJapaneseDate";
    }

    @GetMapping(value = "0601/003")
    public String handle05003(Model model) {
        model.addAttribute("resultDate", dateOperationService.parseJapaneseDate(
                "H25.12.09", "Gy.MM.dd"));
        return "dtop/showJapaneseDate";
    }

    @GetMapping(value = "0601/004")
    public String handle05004(Model model) {
        model.addAttribute("resultDate", dateOperationService.parseJapaneseDate(
                "平成25/12/09", "GGGGyy/MM/dd"));
        return "dtop/showJapaneseDate";
    }

}
