/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.oth2;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.ntt.fw.spring.functionaltest.domain.service.oth2.OauthIllegalParamAuthCodeService;
import jp.co.ntt.fw.spring.functionaltest.domain.service.oth2.OauthResource;

@RequestMapping("oth2/client/11")
@Controller
public class OTH211Controller {

    private static final Logger logger = LoggerFactory
            .getLogger(OTH211Controller.class);

    @Inject
    OauthIllegalParamAuthCodeService oauthIllegalParamAuthCodeService;

    @Inject
    ResourceOwnerPasswordResourceDetails passGrantResource;

    /**
     * <ul>
     * <li>Calls Service to send request with the authorization code grant(GET).</li>
     * </ul>
     * @param id
     * @param model
     * @return path of the view
     */
    @RequestMapping(value = "/01/001/get/{id}", method = RequestMethod.GET)
    public String handle01001(@PathVariable("id") String id, Model model) {

        OauthResource response = oauthIllegalParamAuthCodeService
                .getResourceByIllegalId(id);

        logger.debug("Result response={}", response);

        model.addAttribute("response", response.getResult());
        model.addAttribute("title", "GETメソッドの結果");
        return "oth2/view";
    }

    /**
     * <ul>
     * <li>Calls Service to send request with the authorization code grant(GET).</li>
     * </ul>
     * @param id
     * @param model
     * @return path of the view
     */
    @RequestMapping(value = "/01/002/get/{id}", method = RequestMethod.GET)
    public String handle03001(@PathVariable("id") String id, Model model) {

        OauthResource response = oauthIllegalParamAuthCodeService
                .getResourceByIllegalSecret(id);

        logger.debug("Result response={}", response);

        model.addAttribute("response", response.getResult());
        model.addAttribute("title", "GETメソッドの結果");
        return "oth2/view";
    }

    /**
     * <ul>
     * <li>Calls Service to send request with the authorization code grant(GET).</li>
     * </ul>
     * @param id
     * @param model
     * @return path of the view
     */
    @RequestMapping(value = "/01/004/get/{id}", method = RequestMethod.GET)
    public String handle01004(@PathVariable("id") String id, Model model) {

        OauthResource response = oauthIllegalParamAuthCodeService
                .getResourceByIllegalResourceId(id);

        logger.debug("Result response={}", response);

        model.addAttribute("response", response.getResult());
        model.addAttribute("title", "GETメソッドの結果");
        return "oth2/view";
    }

    /**
     * <ul>
     * <li>Calls Service to send request with the authorization code grant(GET).</li>
     * </ul>
     * @param id
     * @param model
     * @return path of the view
     */
    @RequestMapping(value = "/01/005/get/{id}", method = RequestMethod.GET)
    public String handle01005(@PathVariable("id") String id, Model model) {

        OauthResource response = oauthIllegalParamAuthCodeService
                .getResourceByIllegalUri(id);

        logger.debug("Result response={}", response);

        model.addAttribute("response", response.getResult());
        model.addAttribute("title", "GETメソッドの結果");
        return "oth2/view";
    }
}
