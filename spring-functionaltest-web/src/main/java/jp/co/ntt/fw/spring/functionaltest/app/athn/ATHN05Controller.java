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

import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
public class ATHN05Controller {

    @Inject
    AdministratorService administratorService;

    @GetMapping(value = "/0501/001")
    public String handle0501001(Model model, AdministratorForm form) {
        return "athn/createAdministratorUsingBCryptPassword";
    }

    @PostMapping(value = "0501/001/createAdminUsingBCrypt")
    public String createAdministratorUsingBCryptPassword(Model model,
            @Validated AdministratorForm form, BindingResult result,
            RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return handle0501001(model, form);
        }

        // Administratorテーブルに管理者情報登録
        Administrator administrator = new Administrator();
        administrator.setUsername(form.getUsername());
        administrator.setPassword(form.getPassword());
        try {
            administratorService.createUsingBCryptEncode(administrator);
        } catch (BusinessException e) {
            // 同じユーザ名が存在する場合エラー
            model.addAttribute(e.getResultMessages());
            return handle0501001(model, form);
        }

        // エンコード前のパスワード
        redirectAttributes.addFlashAttribute("beforeEncodePassword", form
                .getPassword());
        redirectAttributes.addFlashAttribute(administrator);

        return "redirect:/athn/0501/001/createCompleteAdminUsingBCrypt?complete";
    }

    @GetMapping(value = "0501/001/createCompleteAdminUsingBCrypt", params = "complete")
    public String createCompleteAdministratorUsingBCryptPassword(Model model) {
        ResultMessages messages = ResultMessages.info().add("i.sf.athn.0001");
        model.addAttribute(messages);

        return "athn/createCompleteAdministrator";
    }

    @GetMapping(value = "0501/002/afterLogin")
    public String afterLoginUsingBCryptPassword(
            @AuthenticationPrincipal UserDetails userDetails, Model model) {

        model.addAttribute("username", userDetails.getUsername());
        // principalからパスワードを取得できない為、DBから取得
        Administrator administrator = administratorService.findOneByUserName(
                userDetails.getUsername());
        model.addAttribute("administratorPassword", administrator
                .getPassword());

        return "athn/showAdministratorInfoUsingBCryptPassword";
    }

    @GetMapping(value = "/0501/003")
    public String handle0501003(Model model, AdministratorForm form) {
        return "athn/createAdministratorUsingPbkdf2Password";
    }

    @PostMapping(value = "0501/003/createAdminUsingPbkdf2")
    public String createAdministratorUsingPbkdf2Password(Model model,
            @Validated AdministratorForm form, BindingResult result,
            RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return handle0501003(model, form);
        }

        // Administratorテーブルに管理者情報登録
        Administrator administrator = new Administrator();
        administrator.setUsername(form.getUsername());
        administrator.setPassword(form.getPassword());
        try {
            administratorService.createUsingPbkdf2Encode(administrator);
        } catch (BusinessException e) {
            // 同じユーザ名が存在する場合エラー
            model.addAttribute(e.getResultMessages());
            return handle0501003(model, form);
        }

        // エンコード前のパスワード
        redirectAttributes.addFlashAttribute("beforeEncodePassword", form
                .getPassword());
        redirectAttributes.addFlashAttribute(administrator);

        return "redirect:/athn/0501/003/createCompleteAdminUsingPbkdf2?complete";
    }

    @GetMapping(value = "0501/003/createCompleteAdminUsingPbkdf2", params = "complete")
    public String createCompleteAdministratorUsingPbkdf2Password(Model model) {
        ResultMessages messages = ResultMessages.info().add("i.sf.athn.0001");
        model.addAttribute(messages);

        return "athn/createCompleteAdministrator";
    }

    @GetMapping(value = "0501/004/afterLogin")
    public String afterLoginUsingPbkdf2Password(
            @AuthenticationPrincipal UserDetails userDetails, Model model) {

        model.addAttribute("username", userDetails.getUsername());
        // principalからパスワードを取得できない為、DBから取得
        Administrator administrator = administratorService.findOneByUserName(
                userDetails.getUsername());
        model.addAttribute("administratorPassword", administrator
                .getPassword());

        return "athn/showAdministratorInfoUsingPbkdf2Password";
    }

    @GetMapping(value = "/0501/005")
    public String handle0501005(Model model, AdministratorForm form) {
        return "athn/createAdministratorUsingSCryptPassword";
    }

    @PostMapping(value = "0501/005/createAdminUsingSCrypt")
    public String createAdministratorUsingSCryptPassword(Model model,
            @Validated AdministratorForm form, BindingResult result,
            RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return handle0501005(model, form);
        }

        // Administratorテーブルに管理者情報登録
        Administrator administrator = new Administrator();
        administrator.setUsername(form.getUsername());
        administrator.setPassword(form.getPassword());
        try {
            administratorService.createUsingSCryptEncode(administrator);
        } catch (BusinessException e) {
            // 同じユーザ名が存在する場合エラー
            model.addAttribute(e.getResultMessages());
            return handle0501005(model, form);
        }

        // エンコード前のパスワード
        redirectAttributes.addFlashAttribute("beforeEncodePassword", form
                .getPassword());
        redirectAttributes.addFlashAttribute(administrator);

        return "redirect:/athn/0501/005/createCompleteAdminUsingSCrypt?complete";
    }

    @GetMapping(value = "0501/005/createCompleteAdminUsingSCrypt", params = "complete")
    public String createCompleteAdministratorUsingSCryptPassword(Model model) {
        ResultMessages messages = ResultMessages.info().add("i.sf.athn.0001");
        model.addAttribute(messages);

        return "athn/createCompleteAdministrator";
    }

    @GetMapping(value = "0501/006/afterLogin")
    public String afterLoginUsingSCryptPassword(
            @AuthenticationPrincipal UserDetails userDetails, Model model) {

        model.addAttribute("username", userDetails.getUsername());
        // principalからパスワードを取得できない為、DBから取得
        Administrator administrator = administratorService.findOneByUserName(
                userDetails.getUsername());
        model.addAttribute("administratorPassword", administrator
                .getPassword());

        return "athn/showAdministratorInfoUsingSCryptPassword";
    }

    @GetMapping(value = "/0501/007")
    public String handle0501007(Model model, AdministratorForm form) {
        return "athn/createAdministratorUsingArgon2Password";
    }

    @PostMapping(value = "0501/007/createAdminUsingArgon2")
    public String createAdministratorUsingArgon2Password(Model model,
            @Validated AdministratorForm form, BindingResult result,
            RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return handle0501007(model, form);
        }

        // Administratorテーブルに管理者情報登録
        Administrator administrator = new Administrator();
        administrator.setUsername(form.getUsername());
        administrator.setPassword(form.getPassword());
        try {
            administratorService.createUsingArgon2Encode(administrator);
        } catch (BusinessException e) {
            // 同じユーザ名が存在する場合エラー
            model.addAttribute(e.getResultMessages());
            return handle0501007(model, form);
        }

        // エンコード前のパスワード
        redirectAttributes.addFlashAttribute("beforeEncodePassword", form
                .getPassword());
        redirectAttributes.addFlashAttribute(administrator);

        return "redirect:/athn/0501/007/createCompleteAdminUsingArgon2?complete";
    }

    @GetMapping(value = "0501/007/createCompleteAdminUsingArgon2", params = "complete")
    public String createCompleteAdministratorUsingArgon2Password(Model model) {
        ResultMessages messages = ResultMessages.info().add("i.sf.athn.0001");
        model.addAttribute(messages);

        return "athn/createCompleteAdministrator";
    }

    @GetMapping(value = "0501/008/afterLogin")
    public String afterLoginUsingArgon2Password(
            @AuthenticationPrincipal UserDetails userDetails, Model model) {

        model.addAttribute("username", userDetails.getUsername());
        // principalからパスワードを取得できない為、DBから取得
        Administrator administrator = administratorService.findOneByUserName(
                userDetails.getUsername());
        model.addAttribute("administratorPassword", administrator
                .getPassword());

        return "athn/showAdministratorInfoUsingArgon2Password";
    }

    @GetMapping(value = "/0502/001")
    public String handle0502001(Model model, AdministratorForm form) {
        return "athn/createAdministratorUsingDelegatingPassword";
    }

    @PostMapping(value = "0502/001/createAdminUsingDelegating")
    public String createAdministratorUsingDelegatingPassword(Model model,
            @Validated AdministratorForm form, BindingResult result,
            RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return handle0502001(model, form);
        }

        // Administratorテーブルに管理者情報登録
        Administrator administrator = new Administrator();
        administrator.setUsername(form.getUsername());
        administrator.setPassword(form.getPassword());
        try {
            administratorService.createUsingDelegatingEncode(administrator);
        } catch (BusinessException e) {
            // 同じユーザ名が存在する場合エラー
            model.addAttribute(e.getResultMessages());
            return handle0502001(model, form);
        }

        // エンコード前のパスワード
        redirectAttributes.addFlashAttribute("beforeEncodePassword", form
                .getPassword());
        redirectAttributes.addFlashAttribute(administrator);

        return "redirect:/athn/0502/001/createCompleteAdminUsingDelegating?complete";
    }

    @GetMapping(value = "0502/001/createCompleteAdminUsingDelegating", params = "complete")
    public String createCompleteAdministratorUsingDelegatingPassword(
            Model model) {
        ResultMessages messages = ResultMessages.info().add("i.sf.athn.0001");
        model.addAttribute(messages);

        return "athn/createCompleteAdministrator";
    }

    @GetMapping(value = "0502/002/afterLogin")
    public String afterLoginUsingDelegatingPassword(
            @AuthenticationPrincipal UserDetails userDetails, Model model) {

        model.addAttribute("username", userDetails.getUsername());
        // principalからパスワードを取得できない為、DBから取得
        Administrator administrator = administratorService.findOneByUserName(
                userDetails.getUsername());
        model.addAttribute("administratorPassword", administrator
                .getPassword());

        return "athn/showAdministratorInfoUsingDelegatingPassword";
    }

}
