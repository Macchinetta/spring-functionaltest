/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.oth2;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.ntt.fw.spring.functionaltest.domain.service.oth2.OauthRemoteCoopService;
import jp.co.ntt.fw.spring.functionaltest.domain.service.oth2.OauthResource;

@RequestMapping("oth2/client/03")
@Controller
public class OTH203Controller {

    private static final Logger logger = LoggerFactory
            .getLogger(OTH203Controller.class);

    @Inject
    OauthRemoteCoopService oauthRemoteCoopService;

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

        OauthResource response = oauthRemoteCoopService.getResource(id);

        String tokenValue = oauthRemoteCoopService.getTokenValue();
        logger.debug("Result response={}, token={}", response, tokenValue);

        model.addAttribute("response", response.getResult());
        model.addAttribute("clientAdditionalValue", response
                .getClientAdditionalValue());
        model.addAttribute("userAdditionalValue", response
                .getUserAdditionalValue());
        model.addAttribute("title", "GETメソッドの結果");
        model.addAttribute("token", tokenValue);
        return "oth2/viewAdditionalValue";
    }

    /**
     * <ul>
     * <li>Calls Service to send request with the authorization code grant(POST).</li>
     * </ul>
     * @param id
     * @param model
     * @return path of the view
     */
    @RequestMapping(value = "/01/002/post/{id}", method = RequestMethod.GET)
    public String handle01002(@PathVariable("id") String id, Model model) {

        OauthResource response = oauthRemoteCoopService.postResource(id);

        String tokenValue = oauthRemoteCoopService.getTokenValue();
        logger.debug("Result response={}, token={}", response, tokenValue);

        model.addAttribute("response", response.getResult());
        model.addAttribute("clientAdditionalValue", response
                .getClientAdditionalValue());
        model.addAttribute("userAdditionalValue", response
                .getUserAdditionalValue());
        model.addAttribute("title", "POSTメソッドの結果");
        model.addAttribute("token", tokenValue);
        return "oth2/viewAdditionalValue";
    }

    /**
     * <ul>
     * <li>Calls Service to send request with the authorization code grant(PUT).</li>
     * </ul>
     * @param id
     * @param model
     * @return path of the view
     */
    @RequestMapping(value = "/01/003/put/{id}", method = RequestMethod.GET)
    public String handle01003(@PathVariable("id") String id, Model model) {

        oauthRemoteCoopService.putResource(id);

        String tokenValue = oauthRemoteCoopService.getTokenValue();
        logger.debug("Result response={}, token={}", "-", tokenValue);

        model.addAttribute("response", "Success");
        model.addAttribute("clientAdditionalValue", "-");
        model.addAttribute("userAdditionalValue", "-");
        model.addAttribute("title", "PUTメソッドの結果");
        model.addAttribute("token", tokenValue);
        return "oth2/viewAdditionalValue";
    }

    /**
     * <ul>
     * <li>Calls Service to send request with the authorization code grant(DELETE).</li>
     * </ul>
     * @param id
     * @param model
     * @return path of the view
     */
    @RequestMapping(value = "/01/004/delete/{id}", method = RequestMethod.GET)
    public String handle01004(@PathVariable("id") String id, Model model) {

        oauthRemoteCoopService.deleteResource(id);

        String tokenValue = oauthRemoteCoopService.getTokenValue();
        logger.debug("Result response={}, token={}", "-", tokenValue);

        model.addAttribute("response", "Success");
        model.addAttribute("clientAdditionalValue", "-");
        model.addAttribute("userAdditionalValue", "-");
        model.addAttribute("title", "DELETEメソッドの結果");
        model.addAttribute("token", tokenValue);
        return "oth2/viewAdditionalValue";
    }

    @RequestMapping(value = "/01/005/get/{id}", method = RequestMethod.GET)
    public String handle01005(@PathVariable("id") String id, Model model) {

        OauthResource response = oauthRemoteCoopService.getResourceDefault(id);

        String tokenValue = oauthRemoteCoopService.getTokenValue();
        logger.debug("Result response={}, token={}", response, tokenValue);

        model.addAttribute("response", response.getResult());
        model.addAttribute("name", response.getPrincipalString());
        model.addAttribute("title", "GETメソッドの結果");
        model.addAttribute("token", tokenValue);
        return "oth2/viewPrincipalName";
    }

}
