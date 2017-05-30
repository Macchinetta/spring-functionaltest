/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.rscl;

import javax.inject.Inject;

import jp.co.ntt.fw.spring.functionaltest.domain.model.UserResource;
import jp.co.ntt.fw.spring.functionaltest.domain.service.rscl.InterceptorsRestClientService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.terasoluna.gfw.common.exception.BusinessException;

@RequestMapping("rscl")
@Controller
public class RSCL11Controller {

    @Inject
    InterceptorsRestClientService interceptorsRestClientService;

    @RequestMapping(value = "1102/001", method = RequestMethod.GET)
    public String handle1102001First(Model model) {

        model.addAttribute("testDescription",
                "ClientHttpRequestInterceptorを使用したロギングおよびBasic認証の確認");
        model.addAttribute("testId", "1102/001");

        return "rscl/extensionOfRest";
    }

    @RequestMapping(value = "1102/001", method = RequestMethod.POST)
    public String handle1102001(Model model) {
        UserResource rcvUser = this.interceptorsRestClientService
                .confirmInterceptor01();

        model.addAttribute("resultDescription", "取得したユーザ情報");
        model.addAttribute("user", rcvUser);

        return "rscl/resultUserInf";
    }

    @RequestMapping(value = "1104/001", method = RequestMethod.GET)
    public String handle1104001First(Model model) {

        model.addAttribute("testDescription",
                "ClientHttpRequestInterceptorを使用したサーバとの通信の閉塞制御の確認");
        model.addAttribute("testId", "1104/001");

        return "rscl/extensionOfRest";
    }

    @RequestMapping(value = "1104/001", method = RequestMethod.POST)
    public void handle1104001(Model model) {

        try {
            this.interceptorsRestClientService.confirmInterceptor02();
        } catch (BusinessException e) {
            throw e;
        }
    }

    @RequestMapping(value = "1105/001", method = RequestMethod.GET)
    public String handle1105001First(Model model) {

        model.addAttribute("testDescription",
                "ClientHttpRequestInterceptorを使用した通信処理のリトライの確認");
        model.addAttribute("testId", "1105/001");

        return "rscl/extensionOfRest";
    }

    @RequestMapping(value = "1105/001", method = RequestMethod.POST)
    public String handle1105001(Model model) {

        UserResource rcvUser = this.interceptorsRestClientService
                .confirmInterceptor03();

        model.addAttribute("resultDescription", "取得したユーザ情報");
        model.addAttribute("user", rcvUser);

        return "rscl/resultUserInf";
    }

}
