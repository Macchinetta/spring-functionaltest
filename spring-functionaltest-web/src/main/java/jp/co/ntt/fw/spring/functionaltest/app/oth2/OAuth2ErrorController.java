/*
 * Copyright 2014-2017 NTT Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package jp.co.ntt.fw.spring.functionaltest.app.oth2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/oauth")
public class OAuth2ErrorController {

    private static final Logger logger = LoggerFactory.getLogger(
            OAuth2ErrorController.class);

    @RequestMapping("accessDeniedError")
    public String forwardAccessDeniedErrorPage() {
        return OAuth2Constants.VIEW_NAME_ACCESS_DENIED_ERROR_PAGE;
    }

    @RequestMapping("systemError")
    public String forwardSystemErrorPage() {
        return OAuth2Constants.VIEW_NAME_SYSTEM_ERROR_PAGE;
    }

    @RequestMapping(path = "error", params = { "error=access_denied",
            "error_description=User denied access" })
    public String handleAccessDeniedError(Model model,
            @RequestParam("error") String error,
            @RequestParam("error_description") String description) {

        logger.debug("error={}, error_description={}", error, description);
        OAuth2Exception ex = OAuth2Exception.create(error, description);
        model.addAttribute("exceptionCode",
                OAuth2Constants.ACCESS_DENIED_ERROR_CODE);
        model.addAttribute("exception", ex);
        return OAuth2Constants.VIEW_NAME_ACCESS_DENIED_ERROR_PAGE;
    }

    /**
     * <ul>
     * <li>handle errors received by javascript with implicit grant.</li>
     * </ul>
     * @param model
     * @param error - error of OAuth2 Error Response
     * @param description - error_description of OAuth2 Error Response
     * @return path of the view for OAuth2 Error Response
     */
    @RequestMapping(path = "error")
    public String handleError(Model model,
            @RequestParam(name = "error", required = false) String error,
            @RequestParam(name = "error_description", required = false) String description) {

        logger.debug("error={}, error_description={}", error, description);
        OAuth2Exception ex = OAuth2Exception.create(error, description);
        model.addAttribute("exceptionCode", OAuth2Constants.SYSTEM_ERROR_CODE);
        model.addAttribute("exception", ex);
        return OAuth2Constants.VIEW_NAME_SYSTEM_ERROR_PAGE;
    }

}
