/*
 * Copyright(c) 2014-2017 NTT Corporation.
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
    public String handle001Csv(Model model, ContentDownloadForm form) throws UnsupportedEncodingException, IOException {
        model.addAttribute(fileDownloadHelper.getData(form));
        return "csvDownloadView";
    }
}
