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
public class PRMN02Controller {

    @Inject
    Prmn0201BeanDefine prmn0201BeanDefine;

    @Inject
    Prmn0202BeanDefine prmn0202BeanDefine;

    @RequestMapping(value = "0201/001")
    public String handle0201001(Model model) {
        model.addAttribute("prmn0201BeanDefine", prmn0201BeanDefine);
        return "prmn/beanDefineDsp";
    }

    @RequestMapping(value = "0202/001")
    public String handle0202001(Model model) {
        model.addAttribute("prmn0202BeanDefine", prmn0202BeanDefine);
        return "prmn/beanDefineDefaultValueDsp";
    }

}
