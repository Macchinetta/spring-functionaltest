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
package jp.co.ntt.fw.spring.functionaltest.app.fldw;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/fldw/0301")
@Controller
public class FLDW0301Controller {

    @Inject
    FileDownloadHelper fileDownloadHelper;

    @ModelAttribute
    ContentDownloadForm setupContentDownloadForm() {
        return new ContentDownloadForm();
    }

    @RequestMapping(value = "001", method = RequestMethod.GET)
    public String handle001(Model model) {
        return "fldw/CsvFileDownloadForm";
    }

    @RequestMapping(value = "001", method = RequestMethod.POST, params = "csv")
    public String handle001Csv(Model model,
            ContentDownloadForm form) throws UnsupportedEncodingException, IOException {
        model.addAttribute(fileDownloadHelper.getData(form));
        return "csvDownloadView";
    }
}
