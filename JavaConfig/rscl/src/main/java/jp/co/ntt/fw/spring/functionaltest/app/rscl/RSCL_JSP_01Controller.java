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

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.inject.Inject;
import jp.co.ntt.fw.spring.functionaltest.domain.model.UserResource;
import jp.co.ntt.fw.spring.functionaltest.domain.service.rscl.CollectionRestClientService;
import jp.co.ntt.fw.spring.functionaltest.domain.service.rscl.RestClientService;

@RequestMapping("jsp/rscl")
@Controller
public class RSCL_JSP_01Controller {

    @ModelAttribute(value = "userInfForm")
    public UserInfForm setUpForm() {
        return new UserInfForm();
    }

    @Inject
    RestClientService restClientService;

    @Inject
    CollectionRestClientService collectionRestClientService;

    @GetMapping(value = "0101/001")
    public String handle0101001First(Model model) {

        model.addAttribute("testDescription", "RestTemplateeのgetForObjectメソッドにリクエストパス送信");
        model.addAttribute("testId", "0101/001");

        return "jsp/rscl/setRequestPass";
    }

    @PostMapping(value = "0101/001")
    public String handle0101001(Model model, @RequestParam("path") String path) {

        UserResource rcvUser = this.restClientService.getForObject(path);

        model.addAttribute("resultDescription", "取得したJavaBean情報確認");
        model.addAttribute("user", rcvUser);

        return "jsp/rscl/resultUserInf";
    }

    @GetMapping(value = "0102/001")
    public String handle0102001First(Model model) {

        model.addAttribute("testDescription", "RestTemplateのgetForEntityメソッドにリクエストパス送信");
        model.addAttribute("testId", "0102/001");

        return "jsp/rscl/setRequestPass";
    }

    @PostMapping(value = "0102/001")
    public String handle0102001(Model model, @RequestParam("path") String path) {

        UserResource rcvUser = this.restClientService.getForEntity(path);

        model.addAttribute("resultDescription", "取得したレスポンスデータとJavaBean情報確認");
        model.addAttribute("user", rcvUser);

        return "jsp/rscl/resultUserInf";
    }

    @GetMapping(value = "0103/001")
    public String handle0103001First(Model model) {

        model.addAttribute("testDescription", "RestTemplateのexchangeメソッドにリクエストパス送信");
        model.addAttribute("testId", "0103/001");

        return "jsp/rscl/setRequestPass";
    }

    @PostMapping(value = "0103/001")
    public String handle0103001(Model model, @RequestParam("path") String path) {

        UserResource rcvUser = this.restClientService.exchange(path);

        model.addAttribute("resultDescription", "取得したレスポンスデータとJavaBean情報確認");
        model.addAttribute("user", rcvUser);

        return "jsp/rscl/resultUserInf";
    }

    @GetMapping(value = "0104/001")
    public String handle0104001First(Model model) {

        model.addAttribute("testDescription", "RestTemplateのexchangeメソッドにリクエストパスを設定");
        model.addAttribute("testId", "0104/001");

        return "jsp/rscl/setRequestPass";
    }

    @PostMapping(value = "0104/001")
    public String handle0104001(Model model, @RequestParam("path") String path) {

        UserResource rcvUser = this.restClientService.exchangeJson(path);

        model.addAttribute("resultDescription", "取得したレスポンスデータとJavaBean情報確認");
        model.addAttribute("user", rcvUser);

        return "jsp/rscl/resultUserInf";
    }

    @GetMapping(value = "0105/001")
    public String handle0105001First(Model model) {

        model.addAttribute("testDescription", "RestTemplateのexchangeメソッドにリクエストパスを設定");
        model.addAttribute("testId", "0105/001");

        return "jsp/rscl/setRequestPass";
    }

    @PostMapping(value = "0105/001")
    public String handle0105001(Model model, @RequestParam("path") String path) {

        List<UserResource> rcvLst = this.collectionRestClientService.exchangeCollection(path);

        model.addAttribute("resultDescription", "取得したレスポンスデータとJavaBeanリスト情報確認");
        model.addAttribute("userList", rcvLst);

        return "jsp/rscl/resultUserList";
    }

    @GetMapping(value = "0106/001")
    public String handle0106001First(Model model) {

        model.addAttribute("testDescription", "RestTemplateのpostForObjectメソッドにリクエストパスとユーザ情報を設定");
        model.addAttribute("testId", "0106/001");

        return "jsp/rscl/sendUserInf";
    }

    @PostMapping(value = "0106/001")
    public String handle0106001(Model model, UserInfForm form, @RequestParam("path") String path) {

        UserResource sendUser = new UserResource();
        sendUser.setName(form.getName());
        sendUser.setAge(form.getAge());

        UserResource rcvUser = this.restClientService.postForObject(path, sendUser);

        model.addAttribute("resultDescription", "取得したJavaBean情報確認");
        model.addAttribute("user", rcvUser);

        return "jsp/rscl/resultUserInf";
    }

    @GetMapping(value = "0106/002")
    public String handle0106002First(Model model) {

        model.addAttribute("testDescription", "RestTemplateのpostForObjectメソッドにContent-Lengthを付与");
        model.addAttribute("testId", "0106/002");

        return "jsp/rscl/sendUserInf";
    }

    @PostMapping(value = "0106/002")
    public String handle0106002(Model model, UserInfForm form, @RequestParam("path") String path) {

        UserResource sendUser = new UserResource();
        sendUser.setName(form.getName());
        sendUser.setAge(form.getAge());

        UserResource rcvUser = this.restClientService.postForObjectAddContentLength(path, sendUser);

        model.addAttribute("resultDescription", "取得したJavaBean情報確認");
        model.addAttribute("user", rcvUser);

        return "jsp/rscl/resultUserInf";
    }

    @GetMapping(value = "0107/001")
    public String handle0107001First(Model model) {

        model.addAttribute("testDescription", "RestTemplateのpostForEntityメソッドにリクエストパスとユーザ情報を設定");
        model.addAttribute("testId", "0107/001");

        return "jsp/rscl/sendUserInf";
    }

    @PostMapping(value = "0107/001")
    public String handle0107001(Model model, UserInfForm form, @RequestParam("path") String path) {

        UserResource sendUser = new UserResource();
        sendUser.setName(form.getName());
        sendUser.setAge(form.getAge());

        UserResource rcvUser = this.restClientService.postForEntity(path, sendUser);

        model.addAttribute("resultDescription", "取得したレスポンスデータとJavaBean情報確認");
        model.addAttribute("user", rcvUser);

        return "jsp/rscl/resultUserInf";
    }

    @GetMapping(value = "0108/001")
    public String handle0108001First(Model model) {

        model.addAttribute("testDescription", "RestTemplateのexchangeメソッドにリクエストパスとユーザ情報を設定");
        model.addAttribute("testId", "0108/001");

        return "jsp/rscl/sendUserInf";
    }

    @PostMapping(value = "0108/001")
    public String handle0108001(Model model, UserInfForm form, @RequestParam("path") String path) {

        UserResource sendUser = new UserResource();
        sendUser.setName(form.getName());
        sendUser.setAge(form.getAge());

        UserResource rcvUser = this.restClientService.exchangeByPost(path, sendUser);

        model.addAttribute("resultDescription", "取得したレスポンスデータとJavaBean情報確認");
        model.addAttribute("user", rcvUser);

        return "jsp/rscl/resultUserInf";
    }

    @GetMapping(value = "0109/001")
    public String handle0109001First(Model model) {

        model.addAttribute("testDescription", "RestTemplateのexchangeメソッドにリクエストパスとユーザ情報を設定");
        model.addAttribute("testId", "0109/001");

        return "jsp/rscl/sendUserInf";
    }

    @PostMapping(value = "0109/001")
    public String handle0109001(Model model, UserInfForm form, @RequestParam("path") String path) {

        UserResource sendUser = new UserResource();
        sendUser.setName(form.getName());
        sendUser.setAge(form.getAge());

        UserResource rcvUser = this.restClientService.exchangeJsonByPost(path, sendUser);

        model.addAttribute("resultDescription", "取得したレスポンスデータとJavaBean情報確認");
        model.addAttribute("user", rcvUser);

        return "jsp/rscl/resultUserInf";
    }

    @GetMapping(value = "0110/001")
    public String handle0110001First(Model model) {

        model.addAttribute("testDescription", "RestTemplateのdeleteメソッドにリクエストパスを設定");
        model.addAttribute("testId", "0110/001");

        return "jsp/rscl/setDeleteRequestPass";
    }

    @PostMapping(value = "0110/001")
    public String handle0110001(Model model, @RequestParam("path") String path) {

        this.restClientService.delete(path);

        model.addAttribute("resultDescription", "出力情報なし。エラーが出ていなければ良い");

        return "jsp/rscl/result";
    }

    @GetMapping(value = "0111/001")
    public String handle0111001First(Model model) {

        model.addAttribute("testDescription", "RestTemplateのexchangeメソッドにリクエストパスを設定");
        model.addAttribute("testId", "0111/001");

        return "jsp/rscl/setDeleteRequestPass";
    }

    @PostMapping(value = "0111/001")
    public String handle0111001(Model model, @RequestParam("path") String path) {

        this.restClientService.exchangeByDelete(path);

        model.addAttribute("resultDescription", "取得したレスポンスデータ確認。ログでアサート。");

        return "jsp/rscl/result";
    }

    @GetMapping(value = "0112/001")
    public String handle0112001First(Model model) {

        model.addAttribute("testDescription", "RestTemplateのexchangeメソッドにリクエストパスを設定");
        model.addAttribute("testId", "0112/001");

        return "jsp/rscl/sendUserInf";
    }

    @PostMapping(value = "0112/001")
    public String handle0112001(Model model, UserInfForm form, @RequestParam("path") String path) {

        UserResource sendUser = new UserResource();
        sendUser.setName(form.getName());
        sendUser.setAge(form.getAge());

        this.restClientService.put(path, sendUser);

        model.addAttribute("resultDescription", "出力情報なし。エラーが出ていなければ良い");

        return "jsp/rscl/result";
    }

    @GetMapping(value = "0113/001")
    public String handle0113001First(Model model) {

        model.addAttribute("testDescription", "RestTemplateのexchangeメソッドにリクエストパスを設定");
        model.addAttribute("testId", "0113/001");

        return "jsp/rscl/sendUserInf";
    }

    @PostMapping(value = "0113/001")
    public String handle0113001(Model model, UserInfForm form, @RequestParam("path") String path) {

        UserResource sendUser = new UserResource();
        sendUser.setName(form.getName());
        sendUser.setAge(form.getAge());

        this.restClientService.exchangeByPut(path, sendUser);

        model.addAttribute("resultDescription", "取得したレスポンスデータ確認");

        return "jsp/rscl/result";
    }

}
