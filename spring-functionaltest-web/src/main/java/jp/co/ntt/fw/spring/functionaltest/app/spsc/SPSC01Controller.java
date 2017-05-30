/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.spsc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SPSC01Controller {

    @RequestMapping("0102/001")
    public String handle0102001() {
        return "spsc/result";
    }

    @RequestMapping("0102/002")
    public String handle0102002() {
        return "spsc/result";
    }

    @RequestMapping("0102/003")
    public String handle0102003() {
        return "spsc/result";
    }

    @RequestMapping("0102/004")
    public String handle0102004() {
        return "spsc/result";
    }

    @RequestMapping("0102/005")
    public String handle0102005() {
        return "spsc/result";
    }

    @RequestMapping("0102/006")
    public String handle0102006() {
        return "spsc/result";
    }

    @RequestMapping("0103/001")
    public String handle0103001() {
        return "spsc/result";
    }

    @RequestMapping("0104/001/secure/001")
    public String handle0104001secure() {
        return "spsc/result";
    }

    @RequestMapping("0104/001/notsecure/001")
    public String handle0104001notsecure() {
        return "spsc/result";
    }

    @RequestMapping("0105/001")
    public String handle0105001() {
        return "spsc/result";
    }

    @RequestMapping("0105/002")
    public String handle0105002() {
        return "spsc/result";
    }

    @RequestMapping("0105/003")
    public String handle0105003() {
        return "spsc/result";
    }

    @RequestMapping("0106/001")
    public String handle0106001() {
        return "spsc/result";
    }

    @RequestMapping("0106/002")
    public String handle0106002() {
        return "spsc/result";
    }

    @RequestMapping("0106/003")
    public String handle0106003() {
        return "spsc/result";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String handle() {
        return "spsc/index";
    }
}
