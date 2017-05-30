/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.prmn;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("prmn")
public class PRMN04Controller {

    @Inject
    Prmn04Defines prmn04Defines;

    @RequestMapping(value = "0401/001")
    public String handle0201001(Model model) {
        model.addAttribute("prmn04Defines", prmn04Defines);
        return "prmn/decodePropertiesDsp";
    }

}
