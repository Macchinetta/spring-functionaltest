/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.oth2;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import jp.co.ntt.fw.spring.functionaltest.domain.service.oth2.OauthRevokeTokenClientService;

@RequestMapping("oth2/client/token")
@Controller
public class ClientTokenController {

    @Inject
    OauthRevokeTokenClientService revokeTokenClientService;

    /**
     * <ul>
     * <li>Calls Service to send revokeTokenRequest</li>
     * </ul>
     * @param model
     * @return path of the view
     */
    @RequestMapping(value = "/revoke/tokenDb", method = RequestMethod.GET)
    public String revokeTokenFromDb(Model model) {
        String result = revokeTokenClientService.revokeTokenFromDb();
        model.addAttribute("result", result);
        return "oth2/revokeResult";
    }

    /**
     * <ul>
     * <li>Calls Service to send revokeTokenRequest</li>
     * </ul>
     * @param model
     * @return path of the view
     */
    @RequestMapping(value = "/revoke/tokenMemory", method = RequestMethod.GET)
    public String revokeTokenFromMemory(Model model) {
        String result = revokeTokenClientService.revokeTokenFromMemory();
        model.addAttribute("result", result);
        return "oth2/revokeResult";
    }
}
