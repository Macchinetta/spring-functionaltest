/*
 * Copyright(c) 2014 NTT Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package jp.co.ntt.fw.spring.functionaltest.app.o2sp;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2RefreshToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.ntt.fw.spring.functionaltest.domain.service.o2sp.ResourceService;

@RequestMapping("o2sp/resource")
@Controller
public class OAuth2AuthorizationCodeGrantController {

    private static final Logger LOGGER = LoggerFactory.getLogger(
            OAuth2AuthorizationCodeGrantController.class);

    @Inject
    private ResourceService resourceService;

    @Inject
    private OAuth2AuthorizedClientRepository authorizedClientRepository;

    @GetMapping("/")
    public String root(Model model) {
        return "redirect:/o2sp/resource/index";
    }

    @GetMapping("/index")
    public String index(Model model) {
        return "o2sp/resourceServer";
    }

    @GetMapping(value = "/authorize", params = {
            "grant_type=authorization_code", "scope=GET", "pattern=intercept" })
    public String scopeGetPatternInterceptUrl(
            HttpServletRequest httpServletRequest, Model model,
            @RequestParam(name = "registrationId", defaultValue = "dummy") String registrationId) {

        String message = this.resourceService.getRequestUsingInterceptUrl(
                registrationId);
        model.addAttribute("message", message);
        setClientInfo(httpServletRequest, model, registrationId);

        return "o2sp/resourceServer";

    }

    @GetMapping(value = "/authorize", params = {
            "grant_type=authorization_code", "scope=UPDATE",
            "pattern=intercept" })
    public String scopePutPatternInterceptUrl(
            HttpServletRequest httpServletRequest, Model model,
            @RequestParam(name = "registrationId", defaultValue = "dummy") String registrationId) {

        String message = this.resourceService.putRequestUsingInterceptUrl(
                registrationId);
        model.addAttribute("message", message);
        setClientInfo(httpServletRequest, model, registrationId);

        return "o2sp/resourceServer";
    }

    @GetMapping(value = "/authorize", params = {
            "grant_type=authorization_code", "scope=CREATE",
            "pattern=intercept" })
    public String scopePostPatternInterceptUrl(
            HttpServletRequest httpServletRequest, Model model,
            @RequestParam(name = "registrationId", defaultValue = "dummy") String registrationId) {

        String message = this.resourceService.postRequestUsingInterceptUrl(
                registrationId);
        model.addAttribute("message", message);
        setClientInfo(httpServletRequest, model, registrationId);

        return "o2sp/resourceServer";
    }

    @GetMapping(value = "/authorize", params = {
            "grant_type=authorization_code", "scope=DELETE",
            "pattern=intercept" })
    public String scopeDeletePatternInterceptUrl(
            HttpServletRequest httpServletRequest, Model model,
            @RequestParam(name = "registrationId", defaultValue = "dummy") String registrationId) {

        String message = this.resourceService.deleteRequestUsingInterceptUrl(
                registrationId);
        model.addAttribute("message", message);
        setClientInfo(httpServletRequest, model, registrationId);

        return "o2sp/resourceServer";
    }

    @GetMapping(value = "/authorize", params = {
            "grant_type=authorization_code", "scope=GET",
            "pattern=annotation" })
    public String scopeGetPatternPreAnnotationt(
            HttpServletRequest httpServletRequest, Model model,
            @RequestParam(name = "registrationId", defaultValue = "dummy") String registrationId) {

        String message = this.resourceService.getRequestUsingPreAuthorize(
                registrationId);
        model.addAttribute("message", message);
        setClientInfo(httpServletRequest, model, registrationId);

        return "o2sp/resourceServer";

    }

    @GetMapping(value = "/authorize", params = {
            "grant_type=authorization_code", "scope=UPDATE",
            "pattern=annotation" })
    public String scopePutPatternPreAnnotationt(
            HttpServletRequest httpServletRequest, Model model,
            @RequestParam(name = "registrationId", defaultValue = "dummy") String registrationId) {

        String message = this.resourceService.putRequestUsingPreAuthorize(
                registrationId);
        model.addAttribute("message", message);
        setClientInfo(httpServletRequest, model, registrationId);

        return "o2sp/resourceServer";
    }

    @GetMapping(value = "/authorize", params = {
            "grant_type=authorization_code", "scope=CREATE",
            "pattern=annotation" })
    public String scopePostPatternPreAnnotationt(
            HttpServletRequest httpServletRequest, Model model,
            @RequestParam(name = "registrationId", defaultValue = "dummy") String registrationId) {

        String message = this.resourceService.postRequestUsingPreAuthorize(
                registrationId);
        model.addAttribute("message", message);
        setClientInfo(httpServletRequest, model, registrationId);
        return "o2sp/resourceServer";
    }

    @GetMapping(value = "/authorize", params = {
            "grant_type=authorization_code", "scope=DELETE",
            "pattern=annotation" })
    public String scopeDeletePatternPreAnnotationt(
            HttpServletRequest httpServletRequest, Model model,
            @RequestParam(name = "registrationId", defaultValue = "dummy") String registrationId) {

        String message = this.resourceService.deleteRequestUsingPreAuthorize(
                registrationId);
        model.addAttribute("message", message);
        setClientInfo(httpServletRequest, model, registrationId);

        return "o2sp/resourceServer";
    }

    private void setClientInfo(HttpServletRequest httpServletRequest,
            Model model, String registrationId) {

        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();

        // トークン情報を確認したいためOAuth2AuthorizedClientRepositoryからOAuth2AuthorizedClientを取得している
        // 引数にHttpServletRequestが必要なためやむなくHttpServletRequestを設定しているが、HttpServletRequestを使用することは推奨しない
        OAuth2AuthorizedClient client = this.authorizedClientRepository
                .loadAuthorizedClient(registrationId, authentication,
                        httpServletRequest);

        if (client != null) {
            OAuth2AccessToken accessToken = client.getAccessToken();
            OAuth2RefreshToken refreshToken = client.getRefreshToken();

            // アクセストークン
            model.addAttribute("accessTokenIssued", accessToken.getIssuedAt());
            model.addAttribute("accessTokenExpires", accessToken
                    .getExpiresAt());
            model.addAttribute("accessTokenScopes", accessToken.getScopes());
            model.addAttribute("accessTokenType", accessToken.getTokenType()
                    .getValue());
            model.addAttribute("accessTokeValue", accessToken.getTokenValue());

            // リフレッシュトークン
            model.addAttribute("refreshTokenIssued", refreshToken
                    .getIssuedAt());
            model.addAttribute("refreshTokenExpires", refreshToken
                    .getExpiresAt());
            model.addAttribute("refreshTokeValue", refreshToken
                    .getTokenValue());

        } else {
            LOGGER.info("not exists client. registrationId = {}",
                    registrationId);
        }
    }
}
