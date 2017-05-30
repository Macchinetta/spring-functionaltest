/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.prmn;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("prmn")
@Controller
public class PRMN01Controller {

    @Inject
    PrmnDefines prmnDefines;

    @RequestMapping(value = "0101/001")
    public String handle0101001(Model model) {
        model.addAttribute("prmnDefines", prmnDefines);
        return "prmn/ownProjectPropertiesDsp";
    }

    @RequestMapping(value = "0101/002")
    public String handle0101002(Model model) {
        model.addAttribute("prmnDefines", prmnDefines);
        return "prmn/otherProjectPropertiesDsp";
    }

    @RequestMapping(value = "0102/001")
    public String handle0102001(Model model) {
        model.addAttribute("prmnDefines", prmnDefines);
        return "prmn/orderJvmEnvValDsp";
    }

    @RequestMapping(value = "0102/002")
    public String handle0102002(Model model) {
        model.addAttribute("prmnDefines", prmnDefines);
        return "prmn/orderAppEnvValDsp";
    }

    @RequestMapping(value = "0102/003")
    public String handle0102003(Model model) {
        model.addAttribute("prmnDefines", prmnDefines);
        return "prmn/orderOrderEnvValDsp";
    }

    @RequestMapping(value = "0103/001")
    public String handle0103001(Model model) {
        model.addAttribute("prmnDefines", prmnDefines);
        return "prmn/orderOsEnvValThanAppDsp";
    }

}
