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
package jp.co.ntt.fw.spring.functionaltest.app.athn;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.terasoluna.gfw.common.exception.BusinessException;
import org.terasoluna.gfw.common.message.ResultMessages;

import jakarta.inject.Inject;
import jp.co.ntt.fw.spring.functionaltest.domain.model.Administrator;
import jp.co.ntt.fw.spring.functionaltest.domain.service.athn.AdministratorService;

@Controller
public class ATHN_JSP_19Controller {

    @Inject
    AdministratorService administratorService;

    @GetMapping(value = "/1901/001")
    public String handle19001(Model model, AdministratorForm form) {
        return "jsp/athn/createAdministratorUsingMessagePassword";
    }

    @PostMapping(value = "1901/001/createAdminUsingMessage")
    public String createAdministratorUsingMessagePassword(Model model,
            @Validated AdministratorForm form, BindingResult result,
            RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return handle19001(model, form);
        }

        // Administratorテーブルに管理者情報登録
        Administrator administrator = new Administrator();
        administrator.setUsername(form.getUsername());
        administrator.setPassword(form.getPassword());
        try {
            administratorService.createUsingMessageDigestEncode(administrator);
        } catch (BusinessException e) {
            // 同じユーザ名が存在する場合エラー
            model.addAttribute(e.getResultMessages());
            return handle19001(model, form);
        }

        // エンコード前のパスワード
        redirectAttributes.addFlashAttribute("beforeEncodePassword", form.getPassword());
        redirectAttributes.addFlashAttribute(administrator);

        return "redirect:/jsp/1901/001/createCompleteAdminUsingMessage?complete";
    }

    @GetMapping(value = "1901/001/createCompleteAdminUsingMessage", params = "complete")
    public String createCompleteAdministratorUsingMessagePassword(Model model) {
        ResultMessages messages = ResultMessages.info().add("i.sf.ah.0001");
        model.addAttribute(messages);

        return "jsp/athn/createCompleteAdministrator";
    }

    @GetMapping(value = "/1901/002")
    public String handle19002(Model model, AdministratorForm form) {
        return "jsp/athn/loginAdministratorUsingMessagePassword";
    }

    @GetMapping(value = "1901/002/afterLogin")
    public String afterLoginUsingMessagePassword(Principal principal, Model model) {
        Authentication authentication = (Authentication) principal;
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        model.addAttribute("username", userDetails.getUsername());
        // principalからパスワードを取得できない為、DBから取得
        Administrator administrator =
                administratorService.findOneByUserName(userDetails.getUsername());
        model.addAttribute("administratorPassword", administrator.getPassword());

        return "jsp/athn/showAdministratorInfoUsingMessagePassword";
    }

    @GetMapping(value = "/1901/003")
    public String handle19003(Model model, AdministratorForm form) {
        return "jsp/athn/createAdministratorUsingMessageDelegatingPassword";
    }

    @PostMapping(value = "1901/003/createAdminUsingDelegating")
    public String createAdministratorUsingMessageDelegatingPassword(Model model,
            @Validated AdministratorForm form, BindingResult result,
            RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return handle19003(model, form);
        }

        // Administratorテーブルに管理者情報登録
        Administrator administrator = new Administrator();
        administrator.setUsername(form.getUsername());
        administrator.setPassword(form.getPassword());
        try {
            administratorService.createUsingDelegatingMessageDigestEncode(administrator);
        } catch (BusinessException e) {
            // 同じユーザ名が存在する場合エラー
            model.addAttribute(e.getResultMessages());
            return handle19003(model, form);
        }

        // エンコード前のパスワード
        redirectAttributes.addFlashAttribute("beforeEncodePassword", form.getPassword());
        redirectAttributes.addFlashAttribute(administrator);

        return "redirect:/jsp/1901/003/createCompleteAdminUsingDelegating?complete";
    }

    @GetMapping(value = "1901/003/createCompleteAdminUsingDelegating", params = "complete")
    public String createCompleteAdministratorUsingMessageDelegatingPassword(Model model) {
        ResultMessages messages = ResultMessages.info().add("i.sf.ah.0001");
        model.addAttribute(messages);

        return "jsp/athn/createCompleteAdministrator";
    }

    @GetMapping(value = "/1901/004")
    public String handle19004(Model model, AdministratorForm form) {
        return "jsp/athn/loginAdministratorUsingMessageDelegatingPassword";
    }

    @GetMapping(value = "1901/004/afterLogin")
    public String afterLoginUsingMessageDelegatingPassword(Principal principal, Model model) {
        Authentication authentication = (Authentication) principal;
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        model.addAttribute("username", userDetails.getUsername());
        // principalからパスワードを取得できない為、DBから取得
        Administrator administrator =
                administratorService.findOneByUserName(userDetails.getUsername());
        model.addAttribute("administratorPassword", administrator.getPassword());

        return "jsp/athn/showAdministratorInfoUsingMessageDelegatingPassword";
    }

}
