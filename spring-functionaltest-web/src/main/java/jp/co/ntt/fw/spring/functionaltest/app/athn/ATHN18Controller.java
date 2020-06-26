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
package jp.co.ntt.fw.spring.functionaltest.app.athn;

import javax.inject.Inject;

import jp.co.ntt.fw.spring.functionaltest.domain.model.Administrator;
import jp.co.ntt.fw.spring.functionaltest.domain.service.athn.AdministratorService;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.terasoluna.gfw.common.exception.BusinessException;
import org.terasoluna.gfw.common.message.ResultMessages;

@Controller
public class ATHN18Controller {

    @Inject
    AdministratorService administratorService;

    @RequestMapping(value = "/1801/001", method = RequestMethod.GET)
    public String handle1801001(Model model, AdministratorForm form) {
        return "athn/createAdministratorUsingCustomPbkdf2Password";
    }

    @RequestMapping(value = "1801/001/createAdminUsingCustomPbkdf2", method = RequestMethod.POST)
    public String createAdministratorUsingCustomPbkdf2Password(Model model,
            @Validated AdministratorForm form, BindingResult result,
            RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return handle1801001(model, form);
        }

        // Administratorテーブルに管理者情報登録
        Administrator administrator = new Administrator();
        administrator.setUsername(form.getUsername());
        administrator.setPassword(form.getPassword());
        try {
            administratorService.createUsingCustomPbkdf2Encode(administrator);
        } catch (BusinessException e) {
            // 同じユーザ名が存在する場合エラー
            model.addAttribute(e.getResultMessages());
            return handle1801001(model, form);
        }

        // エンコード前のパスワード
        redirectAttributes.addFlashAttribute("beforeEncodePassword", form
                .getPassword());
        redirectAttributes.addFlashAttribute(administrator);

        return "redirect:/athn/1801/001/createCompleteAdminUsingCustomPbkdf2?complete";
    }

    @RequestMapping(value = "1801/001/createCompleteAdminUsingCustomPbkdf2", method = RequestMethod.GET, params = "complete")
    public String createCompleteAdministratorUsingCustomPbkdf2Password(
            Model model) {
        ResultMessages messages = ResultMessages.info().add("i.sf.athn.0001");
        model.addAttribute(messages);

        return "athn/createCompleteAdministrator";
    }

    @RequestMapping(value = "1801/002/afterLogin")
    public String afterLoginUsingCustomPbkdf2Password(
            @AuthenticationPrincipal UserDetails userDetails, Model model) {

        model.addAttribute("username", userDetails.getUsername());
        // principalからパスワードを取得できない為、DBから取得
        Administrator administrator = administratorService.findOneByUserName(
                userDetails.getUsername());
        model.addAttribute("administratorPassword", administrator
                .getPassword());

        return "athn/showAdministratorInfoUsingCustomPbkdf2Password";
    }

}
