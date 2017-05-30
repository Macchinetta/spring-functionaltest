/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.athr;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ATHR04Controller {

    @RequestMapping("0401/001")
    public String handle0401001loginForStaff() {
        return "athr/loginForRoleAdmin";
    }

    @RequestMapping(value = "0401/001/afterLogin")
    public String handle0401001afterLogin() {
        return "athr/showForRoleAdminDsp";
    }

}
