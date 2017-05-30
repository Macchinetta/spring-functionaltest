/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.dmly;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("dmly")
public class DMLY01Controller {

    @RequestMapping(value = "0101/001")
    public String handle0101001() {
        return "redirect:/dmly/deliveryorder/initlist";
    }

    @RequestMapping(value = "0101/002")
    public String handle0101002() {
        return "redirect:/dmly/deliveryorder/initlist";
    }

    @RequestMapping(value = "0101/003")
    public String handle0101003() {
        return "redirect:/dmly/deliveryorder/initlist";
    }

}
