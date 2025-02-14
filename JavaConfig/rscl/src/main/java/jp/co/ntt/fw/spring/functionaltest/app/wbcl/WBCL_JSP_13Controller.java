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
package jp.co.ntt.fw.spring.functionaltest.app.wbcl;

import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.inject.Inject;
import jp.co.ntt.fw.spring.functionaltest.domain.model.UserResource;
import jp.co.ntt.fw.spring.functionaltest.domain.service.wbcl.WebClientService;

@RequestMapping("jsp/wbcl")
@Controller
public class WBCL_JSP_13Controller {

    @Inject
    WebClientService webClientService;

    @GetMapping(value = "1301/001")
    public String handle1301001First(Model model) {

        model.addAttribute("testDescription",
                "WebClientを非同期処理の代わりとして使用し、REST APIを呼び出してJavaBeanを取得する");
        model.addAttribute("testId", "1301/001");

        return "jsp/wbcl/setAsyncRestRequestPass";
    }

    @PostMapping(value = "1301/001")
    public String handle1301001(Model model, @RequestParam String path) {

        UserResource rcvUser = this.webClientService.confirmAsync01(path);

        model.addAttribute("resultDescription", "WebClientを非同期処理の代わりとして使用し、想定される結果が返却されているか確認");
        model.addAttribute("user", rcvUser);

        return "jsp/wbcl/resultUserInf";
    }

    @GetMapping(value = "1302/001")
    public String handle1302001First(Model model) {

        model.addAttribute("testDescription",
                "WebClientを使用して、REST APIを複数回連続で呼び出し、スレッドプールのキューを溢れさせ、スレッド数の制限が行えていることを確認");
        model.addAttribute("testId", "1302/001");

        return "jsp/wbcl/sendAsyncRestRequest";
    }

    @PostMapping(value = "1302/001")
    public String handle1302001(Model model) {

        Map<String, Integer> map = this.webClientService.confirmAsync02();

        // count : "success" and "error"
        model.addAllAttributes(map);

        return "jsp/wbcl/resultMaxRequest";
    }

    @GetMapping(value = "1302/002")
    public String handle1302002First(Model model) {

        // TODO 当項目不要の認識
        // コアプールサイズの確認

        return "jsp/wbcl/sendAsyncRestRequest";
    }

    @GetMapping(value = "1302/003")
    public String handle1302003(Model model) {

        // TODO 当項目不要の認識
        // マックスプールサイズまでスレッドが作成されること → 項目がおかしい（スレッド数なのでプールサイズには依存しない）

        return "jsp/wbcl/sendAsyncRestRequest";
    }

    @GetMapping(value = "1303/001")
    public String handle1303001First(Model model) {

        // リクエスト前の処理はFilter
        // 処理完了後はdoOnSuccessで処理する

        model.addAttribute("testDescription", "WebClientにFilterを設定する");
        model.addAttribute("testId", "1303/001");

        return "jsp/wbcl/setAsyncRestRequestPass";
    }

    @PostMapping(value = "1303/001")
    public String handle1303001(Model model, @RequestParam String path) {
        UserResource rcvUser = this.webClientService.confirmAsync03(path);

        model.addAttribute("resultDescription", "WebClientにフィルターを設定し、リクエスト処理前に呼び出されることを確認");
        model.addAttribute("user", rcvUser);

        return "jsp/wbcl/resultUserInf";
    }

    @GetMapping(value = "1303/002")
    public String handle1303002(Model model) {
        // 例外発生時はdoOnErrorで確認可能
        // TODO WECL1302001で確認可能な為、試験不要
        return "jsp/wbcl/sendAsyncRestRequest";
    }

    @GetMapping(value = "1303/003")
    public String handle1303003First(Model model) {

        // 存在しない接続先へのアクセス

        model.addAttribute("testDescription", "WebClientで存在しない接続先にアクセスする");
        model.addAttribute("testId", "1303/003");

        return "jsp/wbcl/setAsyncRestRequestPass";
    }

    @PostMapping(value = "1303/003")
    public String handle1303003(Model model, @RequestParam String path) {
        UserResource rcvUser = this.webClientService.confirmAsync04(path);

        model.addAttribute("resultDescription", "WebClientで存在しない接続先にアクセスした際の挙動を確認する");
        model.addAttribute("user", rcvUser);

        return "jsp/wbcl/resultUserInf";
    }

    @GetMapping(value = "1303/004")
    public String handle1303004First(Model model) {

        // 存在しない接続先へのアクセス
        // タイムアウト値を極端に短くし、強制的にタイムアウトを発生させる

        model.addAttribute("testDescription", "WebClientでレスポンスを待っている際にタイムアウトが発生する");
        model.addAttribute("testId", "1303/004");

        return "jsp/wbcl/setAsyncRestRequestPass";
    }

    @PostMapping(value = "1303/004")
    public String handle1303004(Model model, @RequestParam String path) {
        UserResource rcvUser = this.webClientService.confirmAsync05(path);

        model.addAttribute("resultDescription", "WebClientでタイムアウトが発生した際の挙動を確認する");
        model.addAttribute("user", rcvUser);

        return "jsp/wbcl/resultUserInf";
    }

    // TODO プロキシを設定する方法は次年度以降

}
