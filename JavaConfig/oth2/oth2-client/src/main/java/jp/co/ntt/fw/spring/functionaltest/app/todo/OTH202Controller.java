/*
 * Copyright(c) 2024 NTT Corporation.
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
package jp.co.ntt.fw.spring.functionaltest.app.todo;

import java.util.Collection;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletRequest;
import jp.co.ntt.fw.spring.functionaltest.domain.model.Todo;
import jp.co.ntt.fw.spring.functionaltest.domain.service.todo.TodoService;

@Controller
@RequestMapping("jsp/auth/todo/02")
public class OTH202Controller {

    private static final Logger LOGGER = LoggerFactory.getLogger(OTH202Controller.class);

    private static final String MAJOR_ITEM_ID = "02";

    @Inject
    @Named("todoServiceImpl")
    TodoService todoService;

    @Inject
    @Named("todoChangedTokenServiceImpl")
    TodoService todoChangedTokenService;

    @Inject
    TodoFormMapper beanMapper;

    @Inject
    OAuth2AuthorizedClientRepository authorizedClientRepository;

    @ModelAttribute
    public TodoForm setUpForm() {
        return new TodoForm();
    }

    @GetMapping("index")
    public String index(Model model) {
        model.addAttribute("id", MAJOR_ITEM_ID);
        return "jsp/todo/authorizationCodeIndex";
    }

    @GetMapping("list")
    public String list(HttpServletRequest httpServletRequest, Model model,
            @RequestParam(name = "registrationId") String registrationId,
            @RequestParam(name = "resourceProtect") String resourceProtect) {
        Collection<Todo> todos = todoService.findAll(registrationId);
        model.addAttribute("todos", todos);
        model.addAttribute("registrationId", registrationId);
        model.addAttribute("resourceProtect", resourceProtect);
        model.addAttribute("id", MAJOR_ITEM_ID);
        setClientInfo(httpServletRequest, model, registrationId);
        return "jsp/todo/accessTokenCheck";
    }

    private void setClientInfo(HttpServletRequest httpServletRequest, Model model,
            String registrationId) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // トークン情報を確認したいためOAuth2AuthorizedClientRepositoryからOAuth2AuthorizedClientを取得している
        // 引数にHttpServletRequestが必要なためやむなくHttpServletRequestを設定しているが、HttpServletRequestを使用することは推奨しない
        OAuth2AuthorizedClient client = this.authorizedClientRepository
                .loadAuthorizedClient(registrationId, authentication, httpServletRequest);

        if (client != null) {
            // アクセストークン
            OAuth2AccessToken accessToken = client.getAccessToken();
            if (accessToken != null) {
                model.addAttribute("accessTokenIssued", accessToken.getIssuedAt());
                model.addAttribute("accessTokenExpires", accessToken.getExpiresAt());
                model.addAttribute("accessTokenScopes", accessToken.getScopes());
                model.addAttribute("accessTokenType", accessToken.getTokenType().getValue());
                model.addAttribute("accessTokeValue", accessToken.getTokenValue());
            }

            // リフレッシュトークン
            OAuth2RefreshToken refreshToken = client.getRefreshToken();
            if (refreshToken != null) {
                model.addAttribute("refreshTokenIssued", refreshToken.getIssuedAt());
                model.addAttribute("refreshTokenExpires", refreshToken.getExpiresAt());
                model.addAttribute("refreshTokeValue", refreshToken.getTokenValue());
            }
        } else {
            LOGGER.info("not exists client. registrationId = {}", registrationId);
        }
    }

    @GetMapping("02/001")
    public void handler02_001(Model model,
            @RequestParam(name = "registrationId") String registrationId) {
        todoChangedTokenService.findAll(registrationId);
        // Bussiness Error画面へ遷移する
    }

}
