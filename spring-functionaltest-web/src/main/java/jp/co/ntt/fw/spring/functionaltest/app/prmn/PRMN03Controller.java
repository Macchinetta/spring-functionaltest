/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.prmn;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("prmn")
public class PRMN03Controller {

    @Value("${item.upload.title}")
    private String uploadTitle;

    @Value("${item.upload.dir}")
    private String uploadDir;

    @Value("${item.upload.maxUpdateFileNum}")
    private int maxUpdateFileNum;

    @Value("${i.sf.prmn.0300:4000}")
    private String prmnDefaultValue;

    @RequestMapping(value = "0301/001")
    public String handle0301001(Model model) {
        model.addAttribute("uploadTitle", uploadTitle);
        model.addAttribute("uploadDir", uploadDir);
        model.addAttribute("maxUpdateFileNum", maxUpdateFileNum);
        return "prmn/controllerPropertiesDsp";
    }

    @RequestMapping(value = "0302/001")
    public String handle0302001(Model model) {
        model.addAttribute("prmnDefaultValue", prmnDefaultValue);
        return "prmn/controllerPropertiesDefaultValueDsp";
    }

}
