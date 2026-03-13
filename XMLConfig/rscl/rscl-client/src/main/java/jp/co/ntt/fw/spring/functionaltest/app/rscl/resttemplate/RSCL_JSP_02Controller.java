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

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.inject.Inject;
import jp.co.ntt.fw.spring.functionaltest.app.cmmn.form.ConvertMessageForm;
import jp.co.ntt.fw.spring.functionaltest.app.cmmn.form.UserInfForm;
import jp.co.ntt.fw.spring.functionaltest.app.cmmn.model.User;
import jp.co.ntt.fw.spring.functionaltest.domain.service.rscl.resttemplate.ConverterRestTemplateService;

/**
 * ガイドライン：Overview - メッセージ変換（HttpMessageConverter）
 * 
 * 以下はもともとのテストから除外されているテスト項目
 * 内容的には、「ガイドラインでは紹介に留めており、主要機能でもないため、試験は省略する。」
 * とのことでConverterの種類を見ると妥当に思えるため、除外したままとする。
 * AtomFeedHttpMessageConverter
 * RssChannelHttpMessageConverter
 * MappingJackson2XmlHttpMessageConverter
 * GsonHttpMessageConverter
 */
@RequestMapping("jsp/rscl/restclient")
@Controller
public class RSCL_JSP_02Controller {

    @Inject
    private ConverterRestTemplateService converterRestTemplateService;

    @ModelAttribute(value = "convertMessageForm")
    public ConvertMessageForm setUpMessageForm() {
        return new ConvertMessageForm();
    }

    @ModelAttribute(value = "userInfForm")
    public UserInfForm setUpForm() {
        return new UserInfForm();
    }

    /**
     * テスト項目：02/01/01 HttpMessageConverterの変換の確認（ByteArrayHttpMessageConverter）
     * テスト画面表示
     * 
     * @param model Model
     * @return View name
     */
    @GetMapping(value = "0201001")
    public String handle0201001First(Model model) {

        model.addAttribute("testDescription", "HttpMessageConverterの変換の確認（ByteArrayHttpMessageConverter）");
        model.addAttribute("testId", "0201001");

        return "jsp/rscl/restclient/sendConvertMessage";
    }

    /**
     * テスト項目：02/01/01 HttpMessageConverterの変換の確認（ByteArrayHttpMessageConverter）
     * 送信処理
     * 
     * @param model Model
     * @param form  変換メッセージフォーム
     * @return View name
     */
    @PostMapping(value = "0201001")
    public String handle0201001(Model model, ConvertMessageForm form) {

        List<String> fileData = this.converterRestTemplateService
                .exchangeWithByteArrayHttpMessageConverter(form.getMessage());

        model.addAttribute("resultDescription", "HttpMessageConverterの変換の確認（ByteArrayHttpMessageConverter）");
        model.addAttribute("strList", fileData);

        return "jsp/rscl/restclient/resultStrList";
    }

    /**
     * テスト項目：02/02/01 HttpMessageConverterの変換の確認（StringHttpMessageConverter）
     * テスト画面表示
     * 
     * @param model Model
     * @return View name
     */
    @GetMapping(value = "0202001")
    public String handle0202001First(Model model) {

        model.addAttribute("testDescription", "HttpMessageConverterの変換の確認（StringHttpMessageConverter）");
        model.addAttribute("testId", "0202001");

        return "jsp/rscl/restclient/sendConvertMessage";
    }

    /**
     * テスト項目：02/02/01 HttpMessageConverterの変換の確認（StringHttpMessageConverter）
     * 送信処理
     * 
     * @param model Model
     * @param form  変換メッセージフォーム
     * @return View name
     */
    @PostMapping(value = "0202001")
    public String handle0202001After(Model model, ConvertMessageForm form) {

        String res = this.converterRestTemplateService.exchangeWithStringHttpMessageConverter(form.getMessage());

        model.addAttribute("resultDescription", "HttpMessageConverterの変換の確認（StringHttpMessageConverter）");
        model.addAttribute("rcvString", res);

        return "jsp/rscl/restclient/resultString";
    }

    /**
     * テスト項目：02/03/001 HttpMessageConverterの変換の確認（ResourceHttpMessageConverter）
     * テスト画面表示
     * 
     * @param model Model
     * @return View name
     */
    @GetMapping(value = "0203001")
    public String handle0203001First(Model model) {

        model.addAttribute("testDescription", "HttpMessageConverterの変換の確認（ResourceHttpMessageConverter）");
        model.addAttribute("testId", "0203001");

        return "jsp/rscl/restclient/sendConvertMessage";
    }

    /**
     * テスト項目：02/03/001 HttpMessageConverterの変換の確認（ResourceHttpMessageConverter）
     * 送信処理
     * 
     * @param model Model
     * @param form  変換メッセージフォーム
     * @return View name
     */
    @PostMapping(value = "0203001")
    public String handle0203001(Model model, ConvertMessageForm form) {

        List<String> retData = this.converterRestTemplateService
                .exchangeWithResourceHttpMessageConverter(form.getMessage());

        model.addAttribute("resultDescription", "HttpMessageConverterの変換の確認（ResourceHttpMessageConverter）");
        model.addAttribute("strList", retData);

        return "jsp/rscl/restclient/resultStrList";
    }

    /**
     * テスト項目：02/04/001 HttpMessageConverterの変換の確認（SourceHttpMessageConverter）
     * テスト画面表示
     * 
     * @param model Model
     * @return View name
     */
    @GetMapping(value = "0204001")
    public String handle0204001First(Model model) {

        model.addAttribute("testDescription", "HttpMessageConverterの変換の確認（SourceHttpMessageConverter）");
        model.addAttribute("testId", "0204001");

        return "jsp/rscl/restclient/sendConvertUserInf";
    }

    /**
     * テスト項目：02/04/001 HttpMessageConverterの変換の確認（SourceHttpMessageConverter）
     * 送信処理
     * 
     * @param model Model
     * @param form  ユーザ情報フォーム
     * @return View name
     */
    @PostMapping(value = "0204001")
    public String handle0204001(Model model, UserInfForm form) {

        User sendUser = new User();
        sendUser.setName(form.getName());
        sendUser.setAge(form.getAge());

        User rcvUser = this.converterRestTemplateService.exchangeWithSourceHttpMessageConverter(sendUser);

        model.addAttribute("resultDescription", "HttpMessageConverterの変換の確認（SourceHttpMessageConverter）");
        model.addAttribute("user", rcvUser);

        return "jsp/rscl/restclient/resultUserInf";
    }

    /**
     * テスト項目：02/05/001
     * HttpMessageConverterの変換の確認（AllEncompassingFormHttpMessageConverter-変換成功）
     * テスト画面表示
     * 
     * @param model Model
     * @return View name
     */
    @GetMapping(value = "0205001")
    public String handle0205001First(Model model) {

        model.addAttribute("testDescription",
                "HttpMessageConverterの変換の確認（AllEncompassingFormHttpMessageConverter-変換成功）");
        model.addAttribute("testId", "0205001");

        return "jsp/rscl/restclient/sendConvertUserInf";
    }

    /**
     * テスト項目：02/05/001
     * HttpMessageConverterの変換の確認（AllEncompassingFormHttpMessageConverter-変換成功）
     * 送信処理
     * 
     * @param model Model
     * @param form  ユーザ情報フォーム
     * @return View name
     */
    @PostMapping(value = "0205001")
    public String handle0205001(Model model, UserInfForm form) {

        User sendUser = new User();
        sendUser.setName(form.getName());
        sendUser.setAge(form.getAge());

        User rcvUser = this.converterRestTemplateService
                .exchangeApplicationWithAllEncompassingFormHttpMessageConverter(sendUser);

        model.addAttribute("resultDescription",
                "HttpMessageConverterの変換の確認（AllEncompassingFormHttpMessageConverter-変換成功）");
        model.addAttribute("user", rcvUser);

        return "jsp/rscl/restclient/resultUserInf";
    }

    /**
     * テスト項目：02/06/001
     * HttpMessageConverterの変換の確認（AllEncompassingFormHttpMessageConverter-変換失敗）
     * テスト画面表示
     * 
     * @param model Model
     * @return View name
     */
    @GetMapping(value = "0206001")
    public String handle0206001First(Model model) {

        model.addAttribute("testDescription",
                "HttpMessageConverterの変換の確認（AllEncompassingFormHttpMessageConverter-変換失敗）");
        model.addAttribute("testId", "0206001");

        return "jsp/rscl/restclient/sendConvertUserInf";
    }

    /**
     * テスト項目：02/06/001
     * HttpMessageConverterの変換の確認（AllEncompassingFormHttpMessageConverter-変換失敗）
     * 送信処理
     * 
     * @param model Model
     * @param form  ユーザ情報フォーム
     * @return View name
     */
    @PostMapping(value = "0206001")
    public String handle0206001(Model model, UserInfForm form) {

        User sendUser = new User();
        sendUser.setName(form.getName());
        sendUser.setAge(form.getAge());

        User rcvUser = this.converterRestTemplateService
                .exchangeMultipartWithAllEncompassingFormHttpMessageConverter(sendUser);

        model.addAttribute("resultDescription",
                "HttpMessageConverterの変換の確認（AllEncompassingFormHttpMessageConverter-変換失敗）");
        model.addAttribute("user", rcvUser);

        return "jsp/rscl/restclient/resultUserInf";
    }

    /**
     * テスト項目：02/07/001
     * HttpMessageConverterの変換の確認（MappingJackson2HttpMessageConverter）
     * テスト画面表示
     * 
     * @param model Model
     * @return View name
     */
    @GetMapping(value = "0207001")
    public String handle0207001First(Model model) {

        model.addAttribute("testDescription",
                "HttpMessageConverterの変換の確認（MappingJackson2HttpMessageConverter）");
        model.addAttribute("testId", "0207001");

        return "jsp/rscl/restclient/sendConvertUserInf";
    }

    /**
     * テスト項目：02/07/001
     * HttpMessageConverterの変換の確認（MappingJackson2HttpMessageConverter）
     * 送信処理
     * 
     * @param model Model
     * @param form  ユーザ情報フォーム
     * @return View name
     */
    @PostMapping(value = "0207001")
    public String handle0207001(Model model, UserInfForm form) {

        User sendUser = new User();
        sendUser.setName(form.getName());
        sendUser.setAge(form.getAge());

        User rcvUser = this.converterRestTemplateService.exchangeWithMappingJackson2HttpMessageConverter(sendUser);

        model.addAttribute("resultDescription",
                "HttpMessageConverterの変換の確認（MappingJackson2HttpMessageConverter）");
        model.addAttribute("user", rcvUser);

        return "jsp/rscl/restclient/resultUserInf";
    }

    /**
     * テスト項目：02/08/001
     * HttpMessageConverterの変換の確認（Jaxb2RootElementHttpMessageConverter）
     * テスト画面表示
     * 
     * @param model Model
     * @return View name
     */
    @GetMapping(value = "0208001")
    public String handle0208001First(Model model) {

        model.addAttribute("testDescription",
                "HttpMessageConverterの変換の確認（Jaxb2RootElementHttpMessageConverter）");
        model.addAttribute("testId", "0208001");

        return "jsp/rscl/restclient/sendConvertUserInf";
    }

    /**
     * テスト項目：02/08/001
     * HttpMessageConverterの変換の確認（Jaxb2RootElementHttpMessageConverter）
     * 送信処理
     * 
     * @param model Model
     * @param form  ユーザ情報フォーム
     * @return View name
     */
    @PostMapping(value = "0208001")
    public String handle0208001(Model model, UserInfForm form) {

        User sendUser = new User();
        sendUser.setName(form.getName());
        sendUser.setAge(form.getAge());

        User rcvUser = this.converterRestTemplateService.exchangeWithJaxb2RootElementHttpMessageConverter(sendUser);

        model.addAttribute("resultDescription",
                "HttpMessageConverterの変換の確認（Jaxb2RootElementHttpMessageConverter）");
        model.addAttribute("user", rcvUser);

        return "jsp/rscl/restclient/resultUserInf";
    }

}
