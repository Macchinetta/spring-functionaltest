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

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jp.co.ntt.fw.spring.functionaltest.domain.service.todo.TodoService;

@Controller
@RequestMapping("jsp/auth/todo/03")
public class OTH203Controller {

    @Inject
    @Named("todoServiceImpl")
    private TodoService todoService;

    @Inject
    @Named("todoWrongJWTEndpointServiceImpl")
    private TodoService todoWrongJWTEndpointService;

    @Inject
    @Named("todoNotThroughAnAuthorizedServerServiceImpl")
    private TodoService todoNotThroughAnAuthorizedServerService;

    @ModelAttribute
    public TodoForm setUpForm() {
        return new TodoForm();
    }

    @GetMapping("index")
    public String index(Model model) {
        return "jsp/todo/anomalySystem";
    }

    @GetMapping("01/001")
    public String handler01_001(Model model) {

        // トークンエンドポイントが間違っているため
        // 例外発生 → キャッチ → 認可サーバ(認証) → リダイレクト → 認可コードでトークン取得 → 接続先誤り
        // となるため、 Resource not found となる
        this.todoService.findAll("registration_token_wrong");

        // list画面には遷移しないがvoidにしてしまうと試験失敗なのか遷移先不明なのかの判断がつかなくなるため、あえて遷移先は残してある
        return "jsp/todo/list";
    }

    @GetMapping("01/002")
    public String handler01_002(Model model) {

        // 認可エンドポイントが間違っているため
        // 例外発生 → キャッチ → 認可サーバ（認証）への接続失敗
        // となるため、IP等が正しい場合は認可サーバのエラー画面が出力される
        this.todoService.findAll("registration_auth_wrong");

        // list画面には遷移しないがvoidにしてしまうと試験失敗なのか遷移先不明なのかの判断がつかなくなるため、あえて遷移先は残してある
        return "jsp/todo/list";
    }

    @GetMapping("01/003")
    public String handler01_003(Model model) {

        // JWTエンドポイントが間違っているためリソースサーバ側でエラーが発生する（404エラー）
        this.todoWrongJWTEndpointService.findAll("registration_all");

        return "jsp/todo/list";
    }

    @GetMapping("02/001")
    public String handler02_001(Model model) {

        // トークン取得後、トークンの有効期限が切れてから送信する
        // そのため、リソースサーバ側でトークンの有効期限切れを検知する
        this.todoService.findAll("registration_wait");

        return "jsp/todo/list";
    }

    @GetMapping("03/001")
    public String handler03_001(Model model) {

        // 認可サーバーを経由せずにアクセストークンが必要なリソースサーバーにアクセスする
        this.todoNotThroughAnAuthorizedServerService.findAll(null);

        return "jsp/todo/list";
    }
}
