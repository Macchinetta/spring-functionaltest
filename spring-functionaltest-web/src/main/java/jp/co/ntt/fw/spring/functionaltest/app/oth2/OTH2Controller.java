/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.oth2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("oth2")
@Controller
public class OTH2Controller {

    /**
     * @return path of index.jsp
     */
    @RequestMapping(value = "/")
    public String index() {
        return "oth2/index";
    }

    /**
     * @return path of login.jsp
     */
    @RequestMapping(value = "/login")
    public String login() {
        return "oth2/login";
    }

}
