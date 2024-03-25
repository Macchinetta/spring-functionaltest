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
package jp.co.ntt.fw.spring.functionaltest.app.rscl;

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
import jp.co.ntt.fw.spring.functionaltest.domain.model.UserResource;
import jp.co.ntt.fw.spring.functionaltest.domain.service.rscl.HttpsRestClientService;
import jp.co.ntt.fw.spring.functionaltest.domain.service.rscl.HttpsRestClientService.DESTINATION;
import jp.co.ntt.fw.spring.functionaltest.domain.service.rscl.HttpsRestClientService.SERVERS;
import jp.co.ntt.fw.spring.functionaltest.domain.service.rscl.RestClientService;

@RequestMapping("jsp/rscl")
@Controller
public class RSCL_JSP_06Controller {

    @Inject
    RestClientService restClientService;

    @Inject
    HttpsRestClientService httpsRestClientService;

    @Named("defaultClockFactory")
    @Inject
    ClockFactory clockFactory;

    @GetMapping(value = "0601/001")
    public String handle0601001First(Model model) {

        model.addAttribute("screenTitle", "Restクライアントにおける自己署名証明書");
        model.addAttribute("testDescription", "Self-signed certificate");
        model.addAttribute("testId", "0601/001");

        return "jsp/rscl/setSelfSignedCertificate";
    }

    @PostMapping(value = "0601/001")
    public String handle0601001(Model model) {

        Clock clock = this.clockFactory.tick();
        long start = clock.millis();

        // 接続先 : HTTPSサーバ1 /api/v1/rscl1
        // サーバ側の待機時間 : 0秒
        // SoTimeout : 発生しない
        UserResource rcvUser = this.httpsRestClientService.connectHttps(
                SERVERS.SERVER1, DESTINATION.RSCL1, false);

        LocalDateTime dateTime = LocalDateTime.now(clock);
        long end = clock.millis();

        model.addAttribute("resultDescription", "自己署名証明書情報");
        model.addAttribute("user", rcvUser);
        model.addAttribute("compleatDateTime", dateTime);
        model.addAttribute("executeTime", (end - start));

        return "jsp/rscl/resultUserInf";
    }

    @GetMapping(value = "0601/002")
    public String handle0601002First(Model model) {

        model.addAttribute("screenTitle", "HTTPS接続");
        model.addAttribute("testDescription",
                "HTTPS server1 /api/v1/rscl2 サーバ側で6秒待機する");
        model.addAttribute("testId", "0601/002");

        return "jsp/rscl/setSelfSignedCertificate";
    }

    @PostMapping(value = "0601/002")
    public String handle0601002(Model model) {

        Clock clock = this.clockFactory.tick();
        long start = clock.millis();

        // 接続先 : HTTPSサーバ1 /api/v1/rscl2
        // サーバ側の待機時間 : 6秒
        // SoTimeout : 発生しない
        UserResource rcvUser = this.httpsRestClientService.connectHttps(
                SERVERS.SERVER1, DESTINATION.RSCL2, false);

        LocalDateTime dateTime = LocalDateTime.now(clock);
        long end = clock.millis();

        model.addAttribute("resultDescription", "自己署名証明書情報");
        model.addAttribute("user", rcvUser);
        model.addAttribute("compleatDateTime", dateTime);
        model.addAttribute("executeTime", (end - start));

        return "jsp/rscl/resultUserInf";
    }

    @GetMapping(value = "0601/003")
    public String handle0601003First(Model model) {

        model.addAttribute("screenTitle", "HTTPS接続");
        model.addAttribute("testDescription",
                "HTTPS server1 /api/v1/rscl3 サーバ側で6秒待機する");
        model.addAttribute("testId", "0601/003");

        return "jsp/rscl/setSelfSignedCertificate";
    }

    @PostMapping(value = "0601/003")
    public String handle0601003(Model model) {

        Clock clock = this.clockFactory.tick();
        long start = clock.millis();

        // 接続先 : HTTPSサーバ1 /api/v1/rscl3
        // サーバ側の待機時間 : 6秒
        // SoTimeout : 発生しない
        UserResource rcvUser = this.httpsRestClientService.connectHttps(
                SERVERS.SERVER1, DESTINATION.RSCL3, false);

        LocalDateTime dateTime = LocalDateTime.now(clock);
        long end = clock.millis();

        model.addAttribute("resultDescription", "自己署名証明書情報");
        model.addAttribute("user", rcvUser);
        model.addAttribute("compleatDateTime", dateTime);
        model.addAttribute("executeTime", (end - start));

        return "jsp/rscl/resultUserInf";
    }

    @GetMapping(value = "0601/004")
    public String handle0601004First(Model model) {

        model.addAttribute("screenTitle", "HTTPS接続");
        model.addAttribute("testDescription",
                "HTTPS server1 /api/v1/rscl4 サーバ側で20秒待機する");
        model.addAttribute("testId", "0601/004");

        return "jsp/rscl/setSelfSignedCertificate";
    }

    @PostMapping(value = "0601/004")
    public String handle0601004(Model model) {

        Clock clock = this.clockFactory.tick();
        long start = clock.millis();

        // 接続先 : HTTPSサーバ1 /api/v1/rscl4
        // サーバ側の待機時間 : 20秒
        // SoTimeout : 発生しない
        UserResource rcvUser = this.httpsRestClientService.connectHttps(
                SERVERS.SERVER1, DESTINATION.RSCL4, false);

        model.addAttribute("resultDescription", "自己署名証明書情報");
        model.addAttribute("user", rcvUser);

        LocalDateTime dateTime = LocalDateTime.now(clock);
        long end = clock.millis();

        model.addAttribute("resultDescription", "自己署名証明書情報");
        model.addAttribute("user", rcvUser);
        model.addAttribute("compleatDateTime", dateTime);
        model.addAttribute("executeTime", (end - start));

        return "jsp/rscl/resultUserInf";
    }

    @GetMapping(value = "0601/005")
    public String handle0601005First(Model model) {

        model.addAttribute("screenTitle", "HTTPS接続");
        model.addAttribute("testDescription",
                "HTTPS server2 /api/v1/rscl2 サーバ側で6秒待機する");
        model.addAttribute("testId", "0601/005");

        return "jsp/rscl/setSelfSignedCertificate";
    }

    @PostMapping(value = "0601/005")
    public String handle0601005(Model model) {

        Clock clock = this.clockFactory.tick();
        long start = clock.millis();

        // 接続先 : HTTPSサーバ2 /api/v1/rscl2
        // サーバ側の待機時間 : 6秒
        // SoTimeout : 発生しない
        UserResource rcvUser = this.httpsRestClientService.connectHttps(
                SERVERS.SERVER2, DESTINATION.RSCL2, false);

        LocalDateTime dateTime = LocalDateTime.now(clock);
        long end = clock.millis();

        model.addAttribute("resultDescription", "自己署名証明書情報");
        model.addAttribute("user", rcvUser);
        model.addAttribute("compleatDateTime", dateTime);
        model.addAttribute("executeTime", (end - start));

        return "jsp/rscl/resultUserInf";
    }

    @GetMapping(value = "0601/006")
    public String handle0601006First(Model model) {

        model.addAttribute("screenTitle", "HTTPS接続");
        model.addAttribute("testDescription",
                "HTTPS server3 /api/v1/rscl2 サーバ側で6秒待機する");
        model.addAttribute("testId", "0601/006");

        return "jsp/rscl/setSelfSignedCertificate";
    }

    @PostMapping(value = "0601/006")
    public String handle0601006(Model model) {

        Clock clock = this.clockFactory.tick();
        long start = clock.millis();

        // 接続先 : HTTPSサーバ3 /api/v1/rscl2
        // サーバ側の待機時間 : 6秒
        // SoTimeout : 発生しない
        UserResource rcvUser = this.httpsRestClientService.connectHttps(
                SERVERS.SERVER3, DESTINATION.RSCL2, false);

        LocalDateTime dateTime = LocalDateTime.now(clock);
        long end = clock.millis();

        model.addAttribute("resultDescription", "自己署名証明書情報");
        model.addAttribute("user", rcvUser);
        model.addAttribute("compleatDateTime", dateTime);
        model.addAttribute("executeTime", (end - start));

        return "jsp/rscl/resultUserInf";
    }

    @GetMapping(value = "0601/007")
    public String handle0601007First(Model model) {

        model.addAttribute("screenTitle", "HTTPS接続");
        model.addAttribute("testDescription",
                "HTTPS serverXX（存在しないサーバ） /api/v1/rscl");
        model.addAttribute("testId", "0601/007");

        return "jsp/rscl/setSelfSignedCertificate";
    }

    @PostMapping(value = "0601/007")
    public String handle0601007(Model model) {

        Clock clock = this.clockFactory.tick();
        long start = clock.millis();

        // 接続先 : 存在しないサーバ /api/v1/rscl1
        // SoTimeout : 発生しない
        UserResource rcvUser = this.httpsRestClientService.connectHttps(
                SERVERS.NOTEXISTS, DESTINATION.RSCL1, false);

        LocalDateTime dateTime = LocalDateTime.now(clock);
        long end = clock.millis();

        model.addAttribute("resultDescription", "自己署名証明書情報");
        model.addAttribute("user", rcvUser);
        model.addAttribute("compleatDateTime", dateTime);
        model.addAttribute("executeTime", (end - start));

        return "jsp/rscl/resultUserInf";
    }

    @GetMapping(value = "0601/008")
    public String handle0601008First(Model model) {

        model.addAttribute("screenTitle", "HTTPS接続");
        model.addAttribute("testDescription",
                "HTTPS server4 /api/v1/rscl1 SoTimeoutの発生なし");
        model.addAttribute("testId", "0601/008");

        return "jsp/rscl/setSelfSignedCertificate";
    }

    @PostMapping(value = "0601/008")
    public String handle0601008(Model model) {

        Clock clock = this.clockFactory.tick();
        long start = clock.millis();

        // 接続先 : HTTPSサーバ4 /api/v1/rscl1
        // サーバ側の待機時間 : 0秒
        // SoTimeout : 発生しない
        UserResource rcvUser = this.httpsRestClientService.connectHttps(
                SERVERS.SERVER4, DESTINATION.RSCL1, false);

        LocalDateTime dateTime = LocalDateTime.now(clock);
        long end = clock.millis();

        model.addAttribute("resultDescription", "自己署名証明書情報");
        model.addAttribute("user", rcvUser);
        model.addAttribute("compleatDateTime", dateTime);
        model.addAttribute("executeTime", (end - start));

        return "jsp/rscl/resultUserInf";
    }

    @GetMapping(value = "0601/009")
    public String handle0601009First(Model model) {

        model.addAttribute("screenTitle", "HTTPS接続");
        model.addAttribute("testDescription",
                "HTTPS server4 /api/v1/rscl1 SoTimeoutが発生する");
        model.addAttribute("testId", "0601/009");

        return "jsp/rscl/setSelfSignedCertificate";
    }

    @PostMapping(value = "0601/009")
    public String handle0601009(Model model) {

        Clock clock = this.clockFactory.tick();
        long start = clock.millis();

        // 接続先 : HTTPSサーバ4 /api/v1/rscl1
        // サーバ側の待機時間 : 0秒
        // SoTimeout : 発生する
        UserResource rcvUser = this.httpsRestClientService.connectHttps(
                SERVERS.SERVER4, DESTINATION.RSCL1, true);

        LocalDateTime dateTime = LocalDateTime.now(clock);
        long end = clock.millis();

        model.addAttribute("resultDescription", "自己署名証明書情報");
        model.addAttribute("user", rcvUser);
        model.addAttribute("compleatDateTime", dateTime);
        model.addAttribute("executeTime", (end - start));

        return "jsp/rscl/resultUserInf";
    }
}
