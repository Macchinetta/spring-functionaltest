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
package jp.co.ntt.fw.spring.functionaltest.app.dbsp;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.terasoluna.gfw.web.token.transaction.TransactionTokenCheck;
import org.terasoluna.gfw.web.token.transaction.TransactionTokenType;

@Controller
@RequestMapping("dbsp")
public class DBSP03Controller {
    @ModelAttribute
    public UserCreateForm setUpForm() {
        return new UserCreateForm();
    }

    @RequestMapping(value = "0301/001", method = RequestMethod.GET)
    public String first() {
        return "dbsp/firstView";
    }

    @RequestMapping(value = "0301/001", params = "second", method = RequestMethod.POST)
    @TransactionTokenCheck(value = "create", type = TransactionTokenType.BEGIN)
    public String second() {
        return "dbsp/secondView";
    }

    @RequestMapping(value = "0301/001", params = "third", method = RequestMethod.POST)
    @TransactionTokenCheck(value = "create")
    public String third() {
        return "dbsp/thirdView";
    }

    @RequestMapping(value = "0301/001", params = "fourth", method = RequestMethod.POST)
    @TransactionTokenCheck(value = "create")
    public String fourth() {
        return "dbsp/fourthView";
    }

    @RequestMapping(value = "0301/001", params = "fifth", method = RequestMethod.POST)
    @TransactionTokenCheck(value = "create")
    public String fifth() {
        return "redirect:/dbsp/0301/001?complete";
    }

    @RequestMapping(value = "0301/001", params = "complete", method = RequestMethod.GET)
    public String complete() {
        return "dbsp/fifthView";
    }

    @RequestMapping(value = "0301/003", method = RequestMethod.GET)
    public String thirdDummy() {
        return "dbsp/thirdViewDummy";
    }

    @Inject
    DownloadHelper downloadHelper;

    @ModelAttribute
    DownloadForm setupDownloadForm() {
        return new DownloadForm();
    }

    @RequestMapping(value = "0301/005", method = RequestMethod.GET)
    @TransactionTokenCheck(value = "create", type = TransactionTokenType.BEGIN)
    public String handle(Model model) {
        return "dbsp/fileDownloadForm";
    }

    @RequestMapping(value = "0301/005", params = "file", method = RequestMethod.POST)
    @TransactionTokenCheck(value = "create", type = TransactionTokenType.CHECK)
    public String handlePdf(Model model,
            DownloadForm form) throws UnsupportedEncodingException, IOException {
        downloadHelper.bindToModel(model, form);
        return "fileDownloadView";
    }

    @RequestMapping(value = "0301/005", params = "fourth", method = RequestMethod.POST)
    @TransactionTokenCheck(value = "create")
    public String handleToFourth() {
        return "dbsp/fourthView";
    }

    @RequestMapping(value = "0302/002", method = RequestMethod.GET)
    public String first0302() {
        return "dbsp/firstView0302";
    }

    @RequestMapping(value = "0302/002", params = "second", method = RequestMethod.POST)
    @TransactionTokenCheck(type = TransactionTokenType.BEGIN)
    public String second0302() {
        return "dbsp/secondView0302";
    }

    @RequestMapping(value = "0302/002", params = "third", method = RequestMethod.POST)
    @TransactionTokenCheck
    public String third0302() {
        return "dbsp/thirdView0302";
    }

    @RequestMapping(value = "0302/002", params = "fourth", method = RequestMethod.POST)
    @TransactionTokenCheck
    public String fourth0302() {
        return "dbsp/fourthView0302";
    }

    @RequestMapping(value = "0302/002", params = "fifth", method = RequestMethod.POST)
    @TransactionTokenCheck
    public String fifth0302() {
        return "redirect:/dbsp/0302/002?complete";
    }

    @RequestMapping(value = "0302/002", params = "complete", method = RequestMethod.GET)
    public String complete0302() {
        return "dbsp/fifthView";
    }
}
