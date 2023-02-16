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
@RequestMapping("auth/todo/02")
public class OTH202Controller {

    // 命名規則は中項目ID + Controllerだが、中項目レベルではすべて同一のコントローラーとして処理できるのでまとめてしまう。

    // 大項目ID : 02 : トークンに関する試験
    // 中項目ID : 01 : アクセストークン（有効期限）

    // 小項目ID : 001 : アクセストークンの有効期限内（0秒 ～ アクセストークンの有効期限-60秒）
    // 小項目ID : 002 : リフレッシュトークンが実行される（アクセストークンの有効期限-60秒～リフレッシュトークン有効期限内）
    // 小項目ID : 003 : 再認可（リフレッシュトークン有効期限外）
    // 小項目ID : 004 : 誤ったアクセストークンを設定

    // 中項目ID : 01 : アクセストークン（スコープ）

    // 小項目ID : 001 : all
    // 小項目ID : 002 : read
    // 小項目ID : 003 : partial
    // 小項目ID : 004 : wrong ・・・ 大項目01の方でスコープが一致しない場合は認可エラーとなることを確認するため、こちらの項目は実施しない

    // アクセストークンの有効期限を90秒
    // リフレッシュトークンの有効期限を120秒

    // 小項目ID : 001 : 30秒以内に再アクセス
    // 小項目ID : 002 : 30秒～90秒以内に再アクセス
    // 小項目ID : 003 : 90秒以降に再アクセス
    // 小項目ID : 004 : 取得したアクセストークンを加工する

    // 何度も画面をたたくだけなので、GETのみでよい

    private static final Logger LOGGER = LoggerFactory.getLogger(
            OTH202Controller.class);

    private static final String MAJOR_ITEM_ID = "02";

    @Inject
    @Named("todoServiceImpl")
    TodoService todoService;

    @Inject
    TodoFormMapper beanMapper;

    @Inject
    OAuth2AuthorizedClientRepository authorizedClientRepository;

    @ModelAttribute
    public TodoForm setUpForm() {
        TodoForm form = new TodoForm();
        return form;
    }

    @GetMapping("index")
    public String index(Model model) {
        model.addAttribute("id", MAJOR_ITEM_ID);
        return "todo/authorizationCodeIndex";
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
        return "todo/accessTokenCheck";
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
