/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.dmly;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("dmly")
public class DMLY03Controller {

    @RequestMapping(value = "0301/001")
    public String handle0301001() {
        return "redirect:/dmly/deliveryorder/initlist";
    }

    @RequestMapping(value = "0302/001")
    public String handle0302001() {
        return "redirect:/dmly/deliveryorder/initlist";
    }

    @RequestMapping(value = "0303/001")
    public String handle0303001() {
        return "redirect:/dmly/deliveryorder/initlist";
    }

}
