/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.oth2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/oth2/error")
public class OAuth2ErrorController {

    /**
     * @return path of the view for oauthError
     */
    @RequestMapping
    public String handleError() {
        // omitted
        return "oth2/oauthError";
    }

    /**
     * <ul>
     * <li>add attribute the parameter "cause" to the model.</li>
     * </ul>
     * @param cause - cause of oauthError
     * @param model
     * @return path of the view for oauthError
     */
    @RequestMapping("{cause}")
    public String handleError(@PathVariable("cause") String cause, Model model) {

        model.addAttribute("errorMsg", cause);
        return "oth2/oauthError";
    }
}
