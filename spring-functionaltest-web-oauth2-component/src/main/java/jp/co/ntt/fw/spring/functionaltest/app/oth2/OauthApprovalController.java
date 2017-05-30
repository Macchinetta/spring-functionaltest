/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.oth2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OauthApprovalController {

    /**
     * @return path of oauthConfirm.jsp
     */
    @RequestMapping("/oth2/confirm-access")
    public String confirmAccess() {
        return "oth2/oauthConfirm";
    }
}
