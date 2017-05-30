/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.dmly;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("dmly")
public class DMLY02Controller {

    @RequestMapping(value = "0201/001")
    public String handle0201001() {
        return "redirect:/dmly/deliveryorder/initlist";
    }

    @RequestMapping(value = "0201/002")
    public String handle0201002() {
        return "redirect:/dmly/deliveryorder/initlist";
    }

    @RequestMapping(value = "0201/003")
    public String handle0201003() {
        return "redirect:/dmly/deliveryorder/initlist";
    }

    @RequestMapping(value = "0201/004")
    public String handle0201004() {
        return "redirect:/dmly/deliveryorder/initlist";
    }

    @RequestMapping(value = "0201/005")
    public String handle0201005() {
        return "redirect:/dmly/deliveryorder/initlist";
    }

    @RequestMapping(value = "0201/006")
    public String handle0201006() {
        return "redirect:/dmly/deliveryorder/initlist";
    }

    @RequestMapping(value = "0201/007")
    public String handle0201007() {
        return "redirect:/dmly/deliveryorder/initlist";
    }

    @RequestMapping(value = "0201/008")
    public String handle0201008() {
        return "redirect:/dmly/deliveryorder/initlist";
    }

}
