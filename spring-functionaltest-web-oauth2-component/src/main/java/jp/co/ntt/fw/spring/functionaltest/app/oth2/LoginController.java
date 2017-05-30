/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.oth2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    /**
     * @return path of login.jsp
     */
    @RequestMapping("/oth2/login")
    public String authserverLogin() {
        return "oth2/login";
    }

}
