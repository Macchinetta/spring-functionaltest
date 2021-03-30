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
package jp.co.ntt.fw.spring.functionaltest.app.rscl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.ntt.fw.spring.functionaltest.domain.model.UserResource;
import jp.co.ntt.fw.spring.functionaltest.domain.service.rscl.RestClientService;

@RequestMapping("rscl")
@Controller
public class RSCL02Controller {

    @Inject
    RestClientService restClientService;

    @ModelAttribute(value = "convertMessageForm")
    public ConvertMessageForm setUpMessageForm() {
        return new ConvertMessageForm();
    }

    @ModelAttribute(value = "userInfForm")
    public UserInfForm setUpUserForm() {
        return new UserInfForm();
    }

    @RequestMapping(value = "0201/001", method = RequestMethod.GET)
    public String handle0201001First(Model model) {

        model.addAttribute("testDescription",
                "ByteArrayHttpMessageConverterが適用されたことを確認");
        model.addAttribute("testId", "0201/001");

        return "rscl/sendConvertMessage";
    }

    @RequestMapping(value = "0201/001", method = RequestMethod.POST)
    public String handle0201001(Model model, ConvertMessageForm form) {

        List<String> fileData = this.restClientService
                .exchangeWithByteArrayHttpMessageConverter(form.getMessage());

        model.addAttribute("resultDescription",
                "クライアントが受信したbyte配列をファイルに変換し、内容を確認");
        model.addAttribute("strList", fileData);

        return "rscl/resultStrList";
    }

    @RequestMapping(value = "0202/001", method = RequestMethod.GET)
    public String handle0202001First(Model model) {

        model.addAttribute("testDescription",
                "StringHttpMessageConverterが適用されたことを確認");
        model.addAttribute("testId", "0202/001");

        return "rscl/sendConvertMessage";
    }

    @RequestMapping(value = "0202/001", method = RequestMethod.POST)
    public String handle0202001After(Model model, ConvertMessageForm form) {

        String res = this.restClientService
                .exchangeWithStringHttpMessageConverter(form.getMessage());

        model.addAttribute("resultDescription",
                "レスポンスデータ(Content-Type, ステータス)確認。受信文字列確認。");
        model.addAttribute("rcvString", res);

        return "rscl/resultString";
    }

    @RequestMapping(value = "0203/001", method = RequestMethod.GET)
    public String handle0203001First(Model model) {

        model.addAttribute("testDescription",
                "ResourceHttpMessageConverterが適用されたことを確認");
        model.addAttribute("testId", "0203/001");

        return "rscl/sendConvertMessage";
    }

    @RequestMapping(value = "0203/001", method = RequestMethod.POST)
    public String handle0203001(Model model, ConvertMessageForm form) {

        List<String> retData = this.restClientService
                .exchangeWithResourceHttpMessageConverter(form.getMessage());

        model.addAttribute("resultDescription",
                "レスポンスデータ(ステータス)確認。受信ファイルの内容文字列確認。");
        model.addAttribute("strList", retData);

        return "rscl/resultStrList";
    }

    @RequestMapping(value = "0204/001", method = RequestMethod.GET)
    public String handle0204001First(Model model) {

        model.addAttribute("testDescription",
                "SourceHttpMessageConverterが適用されたことを確認");
        model.addAttribute("testId", "0204/001");

        return "rscl/sendConvertUserInf";
    }

    @RequestMapping(value = "0204/001", method = RequestMethod.POST)
    public String handle0204001(Model model, UserInfForm form) {

        UserResource sendUser = new UserResource();
        sendUser.setName(form.getName());
        sendUser.setAge(form.getAge());

        UserResource rcvUser = this.restClientService
                .exchangeWithSourceHttpMessageConverter(sendUser);

        model.addAttribute("resultDescription",
                "レスポンスデータ(Content-Type, ステータス)確認。受信したSourceのXMLの内容確認");
        model.addAttribute("user", rcvUser);

        return "rscl/resultUserInf";
    }

    @RequestMapping(value = "0205/001", method = RequestMethod.GET)
    public String handle0205001First(Model model) {

        model.addAttribute("testDescription",
                "MultiValueMap<String, String>を送受信することで、AllEncompassingFormHttpMessageConverterが適用されたことを確認");
        model.addAttribute("testId", "0205/001");

        return "rscl/sendConvertUserInf";
    }

    @RequestMapping(value = "0205/001", method = RequestMethod.POST)
    public String handle0205001(Model model, UserInfForm form) {

        UserResource sendUser = new UserResource();
        sendUser.setName(form.getName());
        sendUser.setAge(form.getAge());

        UserResource rcvUser = this.restClientService
                .exchangeWithAllEncompassingFormHttpMessageConverter(sendUser);

        model.addAttribute("resultDescription",
                "レスポンスデータ(Content-Type, ステータス)確認。受信したMultiValueMap<String, String>の内容確認");
        model.addAttribute("user", rcvUser);

        return "rscl/resultUserInf";
    }

    @RequestMapping(value = "0209/001", method = RequestMethod.GET)
    public String handle0209001First(Model model) {

        model.addAttribute("testDescription",
                "JSONでJavaBeanの送受信を行うことで、MappingJackson2HttpMessageConverterが適用されたことを確認");
        model.addAttribute("testId", "0209/001");

        return "rscl/sendConvertUserInf";
    }

    @RequestMapping(value = "0209/001", method = RequestMethod.POST)
    public String handle0209001(Model model, UserInfForm form) {

        UserResource sendUser = new UserResource();
        sendUser.setName(form.getName());
        sendUser.setAge(form.getAge());

        UserResource rcvUser = this.restClientService
                .exchangeWithMappingJackson2HttpMessageConverter(sendUser);

        model.addAttribute("resultDescription",
                "レスポンスデータ(Content-Type, ステータス)確認。取得したJavabeanの内容確認");
        model.addAttribute("user", rcvUser);

        return "rscl/resultUserInf";
    }

    @RequestMapping(value = "0211/001", method = RequestMethod.GET)
    public String handle0211001First(Model model) {

        model.addAttribute("testDescription",
                "XMLでJavaBeanの送受信を行うことで、Jaxb2RootElementHttpMessageConverterが適用されたことを確認");
        model.addAttribute("testId", "0211/001");

        return "rscl/sendConvertUserInf";
    }

    @RequestMapping(value = "0211/001", method = RequestMethod.POST)
    public String handle0211001(Model model, UserInfForm form) {

        UserResource sendUser = new UserResource();
        sendUser.setName(form.getName());
        sendUser.setAge(form.getAge());

        UserResource rcvUser = this.restClientService
                .exchangeWithJaxb2RootElementHttpMessageConverter(sendUser);

        model.addAttribute("resultDescription",
                "レスポンスデータ(Content-Type, ステータス)確認。取得したJavabeanの内容確認");
        model.addAttribute("user", rcvUser);

        return "rscl/resultUserInf";
    }

}
