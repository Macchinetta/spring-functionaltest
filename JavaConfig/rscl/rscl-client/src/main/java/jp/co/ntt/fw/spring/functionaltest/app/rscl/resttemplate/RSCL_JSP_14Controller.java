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
package jp.co.ntt.fw.spring.functionaltest.app.rscl.resttemplate;

import java.time.Clock;
import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.terasoluna.gfw.common.time.ClockFactory;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jp.co.ntt.fw.spring.functionaltest.app.cmmn.model.User;
import jp.co.ntt.fw.spring.functionaltest.domain.service.rscl.resttemplate.HttpsRestTemplateService;
import jp.co.ntt.fw.spring.functionaltest.domain.service.rscl.resttemplate.HttpsRestTemplateService.DESTINATION;
import jp.co.ntt.fw.spring.functionaltest.domain.service.rscl.resttemplate.HttpsRestTemplateService.SERVERS;

/**
 * ガイドライン：HowToExtends関連 - 自己署名証明書とSSL設定
 * 本クラスでは、接続先サーバ、待機時間が異なるパターンの定義を行っている。
 * この組み合わせをもって、自己署名証明書の設定とSSL接続設定の確認を行う。
 */
@RequestMapping("jsp/rscl/restclient")
@Controller
public class RSCL_JSP_14Controller {

    @Inject
    private HttpsRestTemplateService httpsRestTemplateService;

    @Named("defaultClockFactory")
    @Inject
    private ClockFactory clockFactory;

    /**
     * 自己署名証明書の設定とSSL接続の確認（HTTPSサーバ1のrscl1 - 0秒待機）
     * テスト画面表示
     * 
     * @param model Model
     * @return View name
     */
    @GetMapping(value = "1401001")
    public String handle_Server1_rscl1_Osec_First(Model model) {

        model.addAttribute("screenTitle", "Restクライアントにおける自己署名証明書");
        model.addAttribute("testDescription", "Self-signed certificate");
        model.addAttribute("testId", "1401001");

        return "jsp/rscl/restclient/setSelfSignedCertificate";
    }

    /**
     * 自己署名証明書の設定とSSL接続の確認（HTTPSサーバ1のrscl1 - 0秒待機）
     * 送信処理
     * 
     * @param model Model
     * @return View name
     */
    @PostMapping(value = "1401001")
    public String handle_Server1_rscl1_Osec(Model model) {

        Clock clock = this.clockFactory.tick();
        long start = clock.millis();

        // 接続先 : HTTPSサーバ1 /api/v1/rscl1
        // サーバ側の待機時間 : 0秒
        // SoTimeout : 発生しない
        User rcvUser = this.httpsRestTemplateService.connectHttps(SERVERS.SERVER1, DESTINATION.RSCL1, false);

        LocalDateTime dateTime = LocalDateTime.now(clock);
        long end = clock.millis();

        model.addAttribute("resultDescription", "自己署名証明書情報");
        model.addAttribute("user", rcvUser);
        model.addAttribute("completionDateTime", dateTime);
        model.addAttribute("executeTime", (end - start));

        return "jsp/rscl/restclient/resultUserInf";
    }

    /**
     * 自己署名証明書の設定とSSL接続の確認（HTTPSサーバ1のrscl2 - 6秒待機）
     * テスト画面表示処理
     * 
     * @param model Model
     * @return View name
     */
    @GetMapping(value = "1401002")
    public String handle_Server1_rscl2_6Sec_First(Model model) {

        model.addAttribute("screenTitle", "HTTPS接続");
        model.addAttribute("testDescription", "HTTPS server1 /api/v1/rscl2 サーバ側で6秒待機する");
        model.addAttribute("testId", "1401002");

        return "jsp/rscl/restclient/setSelfSignedCertificate";
    }

    /**
     * 自己署名証明書の設定とSSL接続の確認（HTTPSサーバ1のrscl2 - 6秒待機）
     * 送信処理
     * 
     * @param model Model
     * @return View name
     */
    @PostMapping(value = "1401002")
    public String handle_Server1_rscl2_6Sec(Model model) {

        Clock clock = this.clockFactory.tick();
        long start = clock.millis();

        // 接続先 : HTTPSサーバ1 /api/v1/rscl2
        // サーバ側の待機時間 : 6秒
        // SoTimeout : 発生しない
        User rcvUser = this.httpsRestTemplateService.connectHttps(SERVERS.SERVER1, DESTINATION.RSCL2, false);

        LocalDateTime dateTime = LocalDateTime.now(clock);
        long end = clock.millis();

        model.addAttribute("resultDescription", "自己署名証明書情報");
        model.addAttribute("user", rcvUser);
        model.addAttribute("completionDateTime", dateTime);
        model.addAttribute("executeTime", (end - start));

        return "jsp/rscl/restclient/resultUserInf";
    }

    /**
     * 自己署名証明書の設定とSSL接続の確認（HTTPSサーバ1のrscl3 - 6秒待機）
     * テスト画面表示処理
     * 
     * @param model Model
     * @return View name
     */
    @GetMapping(value = "1401003")
    public String handle_Server1_rscl3_6Sec_First(Model model) {

        model.addAttribute("screenTitle", "HTTPS接続");
        model.addAttribute("testDescription", "HTTPS server1 /api/v1/rscl3 サーバ側で6秒待機する");
        model.addAttribute("testId", "1401003");

        return "jsp/rscl/restclient/setSelfSignedCertificate";
    }

    /**
     * 自己署名証明書の設定とSSL接続の確認（HTTPSサーバ1のrscl3 - 6秒待機）
     * 送信処理
     * 
     * @param model Model
     * @return View name
     */
    @PostMapping(value = "1401003")
    public String handle_Server1_rscl3_6Sec(Model model) {

        Clock clock = this.clockFactory.tick();
        long start = clock.millis();

        // 接続先 : HTTPSサーバ1 /api/v1/rscl3
        // サーバ側の待機時間 : 6秒
        // SoTimeout : 発生しない
        User rcvUser = this.httpsRestTemplateService.connectHttps(SERVERS.SERVER1, DESTINATION.RSCL3, false);

        LocalDateTime dateTime = LocalDateTime.now(clock);
        long end = clock.millis();

        model.addAttribute("resultDescription", "自己署名証明書情報");
        model.addAttribute("user", rcvUser);
        model.addAttribute("completionDateTime", dateTime);
        model.addAttribute("executeTime", (end - start));

        return "jsp/rscl/restclient/resultUserInf";
    }

    /**
     * 自己署名証明書の設定とSSL接続の確認（HTTPSサーバ1のrscl4 - 20秒待機）
     * テスト画面表示処理
     * 
     * @param model Model
     * @return View name
     */
    @GetMapping(value = "1401004")
    public String handle_Server1_rscl4_20Sec_First(Model model) {

        model.addAttribute("screenTitle", "HTTPS接続");
        model.addAttribute("testDescription", "HTTPS server1 /api/v1/rscl4 サーバ側で20秒待機する");
        model.addAttribute("testId", "1401004");

        return "jsp/rscl/restclient/setSelfSignedCertificate";
    }

    /**
     * 自己署名証明書の設定とSSL接続の確認（HTTPSサーバ1のrscl4 - 20秒待機）
     * 送信処理
     * 
     * @param model Model
     * @return View name
     */
    @PostMapping(value = "1401004")
    public String handle_Server1_rscl4_20Sec(Model model) {

        Clock clock = this.clockFactory.tick();
        long start = clock.millis();

        // 接続先 : HTTPSサーバ1 /api/v1/rscl4
        // サーバ側の待機時間 : 20秒
        // SoTimeout : 発生しない
        User rcvUser = this.httpsRestTemplateService.connectHttps(SERVERS.SERVER1, DESTINATION.RSCL4, false);

        model.addAttribute("resultDescription", "自己署名証明書情報");
        model.addAttribute("user", rcvUser);

        LocalDateTime dateTime = LocalDateTime.now(clock);
        long end = clock.millis();

        model.addAttribute("resultDescription", "自己署名証明書情報");
        model.addAttribute("user", rcvUser);
        model.addAttribute("completionDateTime", dateTime);
        model.addAttribute("executeTime", (end - start));

        return "jsp/rscl/restclient/resultUserInf";
    }

    /**
     * 自己署名証明書の設定とSSL接続の確認（HTTPSサーバ2のrscl2 - 6秒待機）
     * テスト画面表示処理
     * 
     * @param model Model
     * @return View name
     */
    @GetMapping(value = "1401005")
    public String handle_Server2_rscl2_6Sec_First(Model model) {

        model.addAttribute("screenTitle", "HTTPS接続");
        model.addAttribute("testDescription", "HTTPS server2 /api/v1/rscl2 サーバ側で6秒待機する");
        model.addAttribute("testId", "1401005");

        return "jsp/rscl/restclient/setSelfSignedCertificate";
    }

    /**
     * 自己署名証明書の設定とSSL接続の確認（HTTPSサーバ2のrscl2 - 6秒待機）
     * 送信処理
     * 
     * @param model Model
     * @return View name
     */
    @PostMapping(value = "1401005")
    public String handle_Server2_rscl2_6Sec(Model model) {

        Clock clock = this.clockFactory.tick();
        long start = clock.millis();

        // 接続先 : HTTPSサーバ2 /api/v1/rscl2
        // サーバ側の待機時間 : 6秒
        // SoTimeout : 発生しない
        User rcvUser = this.httpsRestTemplateService.connectHttps(SERVERS.SERVER2, DESTINATION.RSCL2, false);

        LocalDateTime dateTime = LocalDateTime.now(clock);
        long end = clock.millis();

        model.addAttribute("resultDescription", "自己署名証明書情報");
        model.addAttribute("user", rcvUser);
        model.addAttribute("completionDateTime", dateTime);
        model.addAttribute("executeTime", (end - start));

        return "jsp/rscl/restclient/resultUserInf";
    }

    /**
     * 自己署名証明書の設定とSSL接続の確認（HTTPSサーバ3のrscl2 - 6秒待機）
     * テスト画面表示処理
     * 
     * @param model Model
     * @return View name
     */
    @GetMapping(value = "1401006")
    public String handle_Server3_rscl2_6Sec_First(Model model) {

        model.addAttribute("screenTitle", "HTTPS接続");
        model.addAttribute("testDescription", "HTTPS server3 /api/v1/rscl2 サーバ側で6秒待機する");
        model.addAttribute("testId", "1401006");

        return "jsp/rscl/restclient/setSelfSignedCertificate";
    }

    /**
     * 自己署名証明書の設定とSSL接続の確認（HTTPSサーバ3のrscl2 - 6秒待機）
     * 送信処理
     * 
     * @param model Model
     * @return View name
     */
    @PostMapping(value = "1401006")
    public String handle_Server3_rscl2_6Sec(Model model) {

        Clock clock = this.clockFactory.tick();
        long start = clock.millis();

        // 接続先 : HTTPSサーバ3 /api/v1/rscl2
        // サーバ側の待機時間 : 6秒
        // SoTimeout : 発生しない
        User rcvUser = this.httpsRestTemplateService.connectHttps(SERVERS.SERVER3, DESTINATION.RSCL2, false);

        LocalDateTime dateTime = LocalDateTime.now(clock);
        long end = clock.millis();

        model.addAttribute("resultDescription", "自己署名証明書情報");
        model.addAttribute("user", rcvUser);
        model.addAttribute("completionDateTime", dateTime);
        model.addAttribute("executeTime", (end - start));

        return "jsp/rscl/restclient/resultUserInf";
    }

    /**
     * 自己署名証明書の設定とSSL接続の確認（存在しないサーバ - xx秒待機）
     * テスト画面表示処理
     * 
     * @param model Model
     * @return View name
     */
    @GetMapping(value = "1401007")
    public String handle_noServer_First(Model model) {

        model.addAttribute("screenTitle", "HTTPS接続");
        model.addAttribute("testDescription", "HTTPS serverXX（存在しないサーバ） /api/v1/rscl");
        model.addAttribute("testId", "1401007");

        return "jsp/rscl/restclient/setSelfSignedCertificate";
    }

    /**
     * 自己署名証明書の設定とSSL接続の確認（存在しないサーバ - xx秒待機）
     * テスト画面表示処理
     * 
     * @param model Model
     * @return View name
     */
    @PostMapping(value = "1401007")
    public String handle_noServer(Model model) {

        Clock clock = this.clockFactory.tick();
        long start = clock.millis();

        // 接続先 : 存在しないサーバ /api/v1/rscl1
        // SoTimeout : 発生しない
        User rcvUser = this.httpsRestTemplateService.connectHttps(SERVERS.NOTEXISTS,
                DESTINATION.RSCL1, false);

        LocalDateTime dateTime = LocalDateTime.now(clock);
        long end = clock.millis();

        model.addAttribute("resultDescription", "自己署名証明書情報");
        model.addAttribute("user", rcvUser);
        model.addAttribute("completionDateTime", dateTime);
        model.addAttribute("executeTime", (end - start));

        return "jsp/rscl/restclient/resultUserInf";
    }

    /**
     * 自己署名証明書の設定とSSL接続の確認（HTTPSサーバ4のrscl1 - 0秒待機）
     * テスト画面表示処理
     * 
     * @param model Model
     * @return View name
     */
    @GetMapping(value = "1401008")
    public String handle_Server4_rscl1_0Sec_First(Model model) {

        model.addAttribute("screenTitle", "HTTPS接続");
        model.addAttribute("testDescription", "HTTPS server4 /api/v1/rscl1 SoTimeoutの発生なし");
        model.addAttribute("testId", "1401008");

        return "jsp/rscl/restclient/setSelfSignedCertificate";
    }

    /**
     * 自己署名証明書の設定とSSL接続の確認（HTTPSサーバ4のrscl1 - 0秒待機）
     * 送信処理
     * 
     * @param model Model
     * @return View name
     */
    @PostMapping(value = "1401008")
    public String handle_Server4_rscl1_0Sec(Model model) {

        Clock clock = this.clockFactory.tick();
        long start = clock.millis();

        // 接続先 : HTTPSサーバ4 /api/v1/rscl1
        // サーバ側の待機時間 : 0秒
        // SoTimeout : 発生しない
        User rcvUser = this.httpsRestTemplateService.connectHttps(SERVERS.SERVER4, DESTINATION.RSCL1, false);

        LocalDateTime dateTime = LocalDateTime.now(clock);
        long end = clock.millis();

        model.addAttribute("resultDescription", "自己署名証明書情報");
        model.addAttribute("user", rcvUser);
        model.addAttribute("completionDateTime", dateTime);
        model.addAttribute("executeTime", (end - start));

        return "jsp/rscl/restclient/resultUserInf";
    }

    /**
     * 自己署名証明書の設定とSSL接続の確認(HTTPSサーバ4のrscl1 - 0秒待機)
     * テスト画面表示処理
     * 
     * @param model Model
     * @return View name
     */
    @GetMapping(value = "1401009")
    public String handle_Server4_rscl1_0SecTimeout_First(Model model) {

        model.addAttribute("screenTitle", "HTTPS接続");
        model.addAttribute("testDescription", "HTTPS server4 /api/v1/rscl1 SoTimeoutが発生する");
        model.addAttribute("testId", "1401009");

        return "jsp/rscl/restclient/setSelfSignedCertificate";
    }

    /**
     * 自己署名証明書の設定とSSL接続の確認（HTTPSサーバ4のrscl1 - 0秒待機）
     * 送信処理
     * 
     * @param model Model
     * @return View name
     */
    @PostMapping(value = "1401009")
    public String handle_Server4_rscl1_0SecTimeout(Model model) {

        Clock clock = this.clockFactory.tick();
        long start = clock.millis();

        // 接続先 : HTTPSサーバ4 /api/v1/rscl1
        // サーバ側の待機時間 : 0秒
        // SoTimeout : 発生する
        User rcvUser = this.httpsRestTemplateService.connectHttps(SERVERS.SERVER4, DESTINATION.RSCL1, true);

        LocalDateTime dateTime = LocalDateTime.now(clock);
        long end = clock.millis();

        model.addAttribute("resultDescription", "自己署名証明書情報");
        model.addAttribute("user", rcvUser);
        model.addAttribute("completionDateTime", dateTime);
        model.addAttribute("executeTime", (end - start));

        return "jsp/rscl/resultUserInf";
    }

}
