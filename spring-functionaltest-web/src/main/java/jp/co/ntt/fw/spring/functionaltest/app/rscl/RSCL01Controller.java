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
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.ntt.fw.spring.functionaltest.domain.model.UserResource;
import jp.co.ntt.fw.spring.functionaltest.domain.service.rscl.CollectionRestClientService;
import jp.co.ntt.fw.spring.functionaltest.domain.service.rscl.RestClientService;

@RequestMapping("rscl")
@Controller
public class RSCL01Controller {

    @ModelAttribute(value = "userInfForm")
    public UserInfForm setUpForm() {
        return new UserInfForm();
    }

    @Inject
    RestClientService restClientService;

    @Inject
    CollectionRestClientService collectionRestClientService;

    @RequestMapping(value = "0101/001", method = RequestMethod.GET)
    public String handle0101001First(Model model) {

        model.addAttribute("testDescription", "RestTemplateeのgetForObjectメソッドにリクエストパス送信");
        model.addAttribute("testId", "0101/001");

        return "rscl/setRequestPass";
    }

    @RequestMapping(value = "0101/001", method = RequestMethod.POST)
    public String handle0101001(Model model, @RequestParam String path) {

        UserResource rcvUser = this.restClientService.getForObject(path);

        model.addAttribute("resultDescription", "取得したJavaBean情報確認");
        model.addAttribute("user", rcvUser);

        return "rscl/resultUserInf";
    }

    @RequestMapping(value = "0102/001", method = RequestMethod.GET)
    public String handle0102001First(Model model) {

        model.addAttribute("testDescription", "RestTemplateのgetForEntityメソッドにリクエストパス送信");
        model.addAttribute("testId", "0102/001");

        return "rscl/setRequestPass";
    }

    @RequestMapping(value = "0102/001", method = RequestMethod.POST)
    public String handle0102001(Model model, @RequestParam String path) {

        UserResource rcvUser = this.restClientService.getForEntity(path);

        model.addAttribute("resultDescription", "取得したレスポンスデータとJavaBean情報確認");
        model.addAttribute("user", rcvUser);

        return "rscl/resultUserInf";
    }

    @RequestMapping(value = "0103/001", method = RequestMethod.GET)
    public String handle0103001First(Model model) {

        model.addAttribute("testDescription", "RestTemplateのexchangeメソッドにリクエストパス送信");
        model.addAttribute("testId", "0103/001");

        return "rscl/setRequestPass";
    }

    @RequestMapping(value = "0103/001", method = RequestMethod.POST)
    public String handle0103001(Model model, @RequestParam String path) {

        UserResource rcvUser = this.restClientService.exchange(path);

        model.addAttribute("resultDescription", "取得したレスポンスデータとJavaBean情報確認");
        model.addAttribute("user", rcvUser);

        return "rscl/resultUserInf";
    }

    @RequestMapping(value = "0104/001", method = RequestMethod.GET)
    public String handle0104001First(Model model) {

        model.addAttribute("testDescription", "RestTemplateのexchangeメソッドにリクエストパスを設定");
        model.addAttribute("testId", "0104/001");

        return "rscl/setRequestPass";
    }

    @RequestMapping(value = "0104/001", method = RequestMethod.POST)
    public String handle0104001(Model model, @RequestParam String path) {

        UserResource rcvUser = this.restClientService.exchangeJson(path);

        model.addAttribute("resultDescription", "取得したレスポンスデータとJavaBean情報確認");
        model.addAttribute("user", rcvUser);

        return "rscl/resultUserInf";
    }

    @RequestMapping(value = "0105/001", method = RequestMethod.GET)
    public String handle0105001First(Model model) {

        model.addAttribute("testDescription", "RestTemplateのexchangeメソッドにリクエストパスを設定");
        model.addAttribute("testId", "0105/001");

        return "rscl/setRequestPass";
    }

    @RequestMapping(value = "0105/001", method = RequestMethod.POST)
    public String handle0105001(Model model, @RequestParam String path) {

        List<UserResource> rcvLst = this.collectionRestClientService.exchangeCollection(path);

        model.addAttribute("resultDescription", "取得したレスポンスデータとJavaBeanリスト情報確認");
        model.addAttribute("userList", rcvLst);

        return "rscl/resultUserList";
    }

    @RequestMapping(value = "0106/001", method = RequestMethod.GET)
    public String handle0106001First(Model model) {

        model.addAttribute("testDescription", "RestTemplateのpostForObjectメソッドにリクエストパスとユーザ情報を設定");
        model.addAttribute("testId", "0106/001");

        return "rscl/sendUserInf";
    }

    @RequestMapping(value = "0106/001", method = RequestMethod.POST)
    public String handle0106001(Model model, UserInfForm form, @RequestParam String path) {

        UserResource sendUser = new UserResource();
        sendUser.setName(form.getName());
        sendUser.setAge(form.getAge());

        UserResource rcvUser = this.restClientService.postForObject(path, sendUser);

        model.addAttribute("resultDescription", "取得したJavaBean情報確認");
        model.addAttribute("user", rcvUser);

        return "rscl/resultUserInf";
    }

    @RequestMapping(value = "0107/001", method = RequestMethod.GET)
    public String handle0107001First(Model model) {

        model.addAttribute("testDescription", "RestTemplateのpostForEntityメソッドにリクエストパスとユーザ情報を設定");
        model.addAttribute("testId", "0107/001");

        return "rscl/sendUserInf";
    }

    @RequestMapping(value = "0107/001", method = RequestMethod.POST)
    public String handle0107001(Model model, UserInfForm form, @RequestParam String path) {

        UserResource sendUser = new UserResource();
        sendUser.setName(form.getName());
        sendUser.setAge(form.getAge());

        UserResource rcvUser = this.restClientService.postForEntity(path, sendUser);

        model.addAttribute("resultDescription", "取得したレスポンスデータとJavaBean情報確認");
        model.addAttribute("user", rcvUser);

        return "rscl/resultUserInf";
    }

    @RequestMapping(value = "0108/001", method = RequestMethod.GET)
    public String handle0108001First(Model model) {

        model.addAttribute("testDescription", "RestTemplateのexchangeメソッドにリクエストパスとユーザ情報を設定");
        model.addAttribute("testId", "0108/001");

        return "rscl/sendUserInf";
    }

    @RequestMapping(value = "0108/001", method = RequestMethod.POST)
    public String handle0108001(Model model, UserInfForm form, @RequestParam String path) {

        UserResource sendUser = new UserResource();
        sendUser.setName(form.getName());
        sendUser.setAge(form.getAge());

        UserResource rcvUser = this.restClientService.exchangeByPost(path, sendUser);

        model.addAttribute("resultDescription", "取得したレスポンスデータとJavaBean情報確認");
        model.addAttribute("user", rcvUser);

        return "rscl/resultUserInf";
    }

    @RequestMapping(value = "0109/001", method = RequestMethod.GET)
    public String handle0109001First(Model model) {

        model.addAttribute("testDescription", "RestTemplateのexchangeメソッドにリクエストパスとユーザ情報を設定");
        model.addAttribute("testId", "0109/001");

        return "rscl/sendUserInf";
    }

    @RequestMapping(value = "0109/001", method = RequestMethod.POST)
    public String handle0109001(Model model, UserInfForm form, @RequestParam String path) {

        UserResource sendUser = new UserResource();
        sendUser.setName(form.getName());
        sendUser.setAge(form.getAge());

        UserResource rcvUser = this.restClientService.exchangeJsonByPost(path, sendUser);

        model.addAttribute("resultDescription", "取得したレスポンスデータとJavaBean情報確認");
        model.addAttribute("user", rcvUser);

        return "rscl/resultUserInf";
    }

    @RequestMapping(value = "0110/001", method = RequestMethod.GET)
    public String handle0110001First(Model model) {

        model.addAttribute("testDescription", "RestTemplateのdeleteメソッドにリクエストパスを設定");
        model.addAttribute("testId", "0110/001");

        return "rscl/setDeleteRequestPass";
    }

    @RequestMapping(value = "0110/001", method = RequestMethod.POST)
    public String handle0110001(Model model, @RequestParam String path) {

        this.restClientService.delete(path);

        model.addAttribute("resultDescription", "出力情報なし。エラーが出ていなければ良い");

        return "rscl/result";
    }

    @RequestMapping(value = "0111/001", method = RequestMethod.GET)
    public String handle0111001First(Model model) {

        model.addAttribute("testDescription", "RestTemplateのexchangeメソッドにリクエストパスを設定");
        model.addAttribute("testId", "0111/001");

        return "rscl/setDeleteRequestPass";
    }

    @RequestMapping(value = "0111/001", method = RequestMethod.POST)
    public String handle0111001(Model model, @RequestParam String path) {

        this.restClientService.exchangeByDelete(path);

        model.addAttribute("resultDescription", "取得したレスポンスデータ確認。ログでアサート。");

        return "rscl/result";
    }

    @RequestMapping(value = "0112/001", method = RequestMethod.GET)
    public String handle0112001First(Model model) {

        model.addAttribute("testDescription", "RestTemplateのexchangeメソッドにリクエストパスを設定");
        model.addAttribute("testId", "0112/001");

        return "rscl/sendUserInf";
    }

    @RequestMapping(value = "0112/001", method = RequestMethod.POST)
    public String handle0112001(Model model, UserInfForm form, @RequestParam String path) {

        UserResource sendUser = new UserResource();
        sendUser.setName(form.getName());
        sendUser.setAge(form.getAge());

        this.restClientService.put(path, sendUser);

        model.addAttribute("resultDescription", "出力情報なし。エラーが出ていなければ良い");

        return "rscl/result";
    }

    @RequestMapping(value = "0113/001", method = RequestMethod.GET)
    public String handle0113001First(Model model) {

        model.addAttribute("testDescription", "RestTemplateのexchangeメソッドにリクエストパスを設定");
        model.addAttribute("testId", "0113/001");

        return "rscl/sendUserInf";
    }

    @RequestMapping(value = "0113/001", method = RequestMethod.POST)
    public String handle0113001(Model model, UserInfForm form, @RequestParam String path) {

        UserResource sendUser = new UserResource();
        sendUser.setName(form.getName());
        sendUser.setAge(form.getAge());

        this.restClientService.exchangeByPut(path, sendUser);

        model.addAttribute("resultDescription", "取得したレスポンスデータ確認");

        return "rscl/result";
    }

}
