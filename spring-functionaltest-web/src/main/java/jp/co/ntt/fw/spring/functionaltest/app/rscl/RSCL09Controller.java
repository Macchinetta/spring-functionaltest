/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.rscl;

import javax.inject.Inject;

import jp.co.ntt.fw.spring.functionaltest.domain.model.UserResource;
import jp.co.ntt.fw.spring.functionaltest.domain.service.rscl.RestClientService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("rscl")
@Controller
public class RSCL09Controller {

    @Inject
    RestClientService restClientService;

    @RequestMapping(value = "0901/001", method = RequestMethod.GET)
    public String handle0901001First(Model model) {

        model.addAttribute("testDescription", "File Upload");
        model.addAttribute("testId", "0901/001");

        return "rscl/fileUpload";
    }

    @RequestMapping(value = "0901/001", method = RequestMethod.POST)
    public String handle0901001(Model model) {

        UserResource rcvUser = this.restClientService.upload();

        model.addAttribute("resultDescription", "アップロードファイルの記述内容をユーザ情報に変換");
        model.addAttribute("user", rcvUser);

        return "rscl/resultUserInf";
    }

}
