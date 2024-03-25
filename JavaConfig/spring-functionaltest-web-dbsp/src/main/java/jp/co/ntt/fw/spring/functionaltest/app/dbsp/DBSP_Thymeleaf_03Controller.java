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
package jp.co.ntt.fw.spring.functionaltest.app.dbsp;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.terasoluna.gfw.web.token.transaction.TransactionTokenCheck;
import org.terasoluna.gfw.web.token.transaction.TransactionTokenType;

import jakarta.inject.Inject;

@Controller
@RequestMapping("thymeleaf")
public class DBSP_Thymeleaf_03Controller {
    @ModelAttribute
    public UserCreateForm setUpForm() {
        return new UserCreateForm();
    }

    @GetMapping(value = "0301/001")
    public String first() {
        return "thymeleaf/dbsp/firstView";
    }

    @PostMapping(value = "0301/001", params = "second")
    @TransactionTokenCheck(value = "create", type = TransactionTokenType.BEGIN)
    public String second() {
        return "thymeleaf/dbsp/secondView";
    }

    @PostMapping(value = "0301/001", params = "third")
    @TransactionTokenCheck(value = "create")
    public String third() {
        return "thymeleaf/dbsp/thirdView";
    }

    @PostMapping(value = "0301/001", params = "fourth")
    @TransactionTokenCheck(value = "create")
    public String fourth() {
        return "thymeleaf/dbsp/fourthView";
    }

    @PostMapping(value = "0301/001", params = "fifth")
    @TransactionTokenCheck(value = "create")
    public String fifth() {
        return "redirect:/thymeleaf/0301/001?complete";
    }

    @GetMapping(value = "0301/001", params = "complete")
    public String complete() {
        return "thymeleaf/dbsp/fifthView";
    }

    @GetMapping(value = "0301/003")
    public String thirdDummy() {
        return "thymeleaf/dbsp/thirdViewDummy";
    }

    @Inject
    DownloadHelper downloadHelper;

    @ModelAttribute
    DownloadForm setupDownloadForm() {
        return new DownloadForm();
    }

    @GetMapping(value = "0301/005")
    @TransactionTokenCheck(value = "create", type = TransactionTokenType.BEGIN)
    public String handle(Model model) {
        return "thymeleaf/dbsp/fileDownloadForm";
    }

    @PostMapping(value = "0301/005", params = "file")
    @TransactionTokenCheck(value = "create", type = TransactionTokenType.CHECK)
    public String handlePdf(Model model,
            DownloadForm form) throws UnsupportedEncodingException, IOException {
        downloadHelper.bindToModel(model, form);
        return "fileDownloadView";
    }

    @PostMapping(value = "0301/005", params = "fourth")
    @TransactionTokenCheck(value = "create")
    public String handleToFourth() {
        return "thymeleaf/dbsp/fourthView";
    }

    @GetMapping(value = "0302/002")
    public String first0302() {
        return "thymeleaf/dbsp/firstView0302";
    }

    @PostMapping(value = "0302/002", params = "second")
    @TransactionTokenCheck(type = TransactionTokenType.BEGIN)
    public String second0302() {
        return "thymeleaf/dbsp/secondView0302";
    }

    @PostMapping(value = "0302/002", params = "third")
    @TransactionTokenCheck
    public String third0302() {
        return "thymeleaf/dbsp/thirdView0302";
    }

    @PostMapping(value = "0302/002", params = "fourth")
    @TransactionTokenCheck
    public String fourth0302() {
        return "thymeleaf/dbsp/fourthView0302";
    }

    @PostMapping(value = "0302/002", params = "fifth")
    @TransactionTokenCheck
    public String fifth0302() {
        return "redirect:/thymeleaf/0302/002?complete";
    }

    @GetMapping(value = "0302/002", params = "complete")
    public String complete0302() {
        return "thymeleaf/dbsp/fifthView";
    }
}
