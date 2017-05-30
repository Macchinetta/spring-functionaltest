/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.athn;

import jp.co.ntt.fw.spring.functionaltest.domain.service.athn.AccountUserDetails;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ATHN09Controller {

    @RequestMapping(value = "0901/001", params = "loginSuccess")
    public String handle0901LoginSuccess(Model model) {

        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication(); // (1)
        String userUuid = null;

        if (authentication.getPrincipal() instanceof AccountUserDetails) {
            AccountUserDetails userDetails = AccountUserDetails.class
                    .cast(authentication.getPrincipal()); // (2)
            userUuid = userDetails.getAccount().getUserUuid(); // (3)

            if (userDetails != null) {
                model.addAttribute("userUuid", userUuid);
            }
        }

        model.addAttribute("testNo", "0901/001");
        return "athn/topForDispAuthentication";
    }

}
