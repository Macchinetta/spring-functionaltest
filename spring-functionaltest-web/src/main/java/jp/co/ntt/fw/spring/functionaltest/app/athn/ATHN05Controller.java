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

import jp.co.ntt.fw.spring.functionaltest.domain.model.Administrator;
import jp.co.ntt.fw.spring.functionaltest.domain.service.athn.AdministratorService;

@Controller
public class ATHN05Controller {

    @Inject
    AdministratorService administratorService;

    @RequestMapping(value = "/0501/001", method = RequestMethod.GET)
    public String handle0501001(Model model, AdministratorForm form) {
        return "athn/createAdministratorUsingBCryptPassword";
    }

    @RequestMapping(value = "0501/001/createAdminUsingBCrypt", method = RequestMethod.POST)
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
        redirectAttributes.addFlashAttribute("beforeEncodePassword", form.getPassword());
        redirectAttributes.addFlashAttribute(administrator);

        return "redirect:/athn/0501/001/createCompleteAdminUsingBCrypt?complete";
    }

    @RequestMapping(value = "0501/001/createCompleteAdminUsingBCrypt", method = RequestMethod.GET,
            params = "complete")
    public String createCompleteAdministratorUsingBCryptPassword(Model model) {
        ResultMessages messages = ResultMessages.info().add("i.sf.ah.0001");
        model.addAttribute(messages);

        return "athn/createCompleteAdministrator";
    }

    @RequestMapping(value = "0501/002/afterLogin")
    public String afterLoginUsingBCryptPassword(@AuthenticationPrincipal UserDetails userDetails,
            Model model) {

        model.addAttribute("username", userDetails.getUsername());
        // principalからパスワードを取得できない為、DBから取得
        Administrator administrator =
                administratorService.findOneByUserName(userDetails.getUsername());
        model.addAttribute("administratorPassword", administrator.getPassword());

        return "athn/showAdministratorInfoUsingBCryptPassword";
    }

    @RequestMapping(value = "/0501/003", method = RequestMethod.GET)
    public String handle0501003(Model model, AdministratorForm form) {
        return "athn/createAdministratorUsingPbkdf2Password";
    }

    @RequestMapping(value = "0501/003/createAdminUsingPbkdf2", method = RequestMethod.POST)
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
        redirectAttributes.addFlashAttribute("beforeEncodePassword", form.getPassword());
        redirectAttributes.addFlashAttribute(administrator);

        return "redirect:/athn/0501/003/createCompleteAdminUsingPbkdf2?complete";
    }

    @RequestMapping(value = "0501/003/createCompleteAdminUsingPbkdf2", method = RequestMethod.GET,
            params = "complete")
    public String createCompleteAdministratorUsingPbkdf2Password(Model model) {
        ResultMessages messages = ResultMessages.info().add("i.sf.ah.0001");
        model.addAttribute(messages);

        return "athn/createCompleteAdministrator";
    }

    @RequestMapping(value = "0501/004/afterLogin")
    public String afterLoginUsingPbkdf2Password(@AuthenticationPrincipal UserDetails userDetails,
            Model model) {

        model.addAttribute("username", userDetails.getUsername());
        // principalからパスワードを取得できない為、DBから取得
        Administrator administrator =
                administratorService.findOneByUserName(userDetails.getUsername());
        model.addAttribute("administratorPassword", administrator.getPassword());

        return "athn/showAdministratorInfoUsingPbkdf2Password";
    }

    @RequestMapping(value = "/0501/005", method = RequestMethod.GET)
    public String handle0501005(Model model, AdministratorForm form) {
        return "athn/createAdministratorUsingSCryptPassword";
    }

    @RequestMapping(value = "0501/005/createAdminUsingSCrypt", method = RequestMethod.POST)
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
        redirectAttributes.addFlashAttribute("beforeEncodePassword", form.getPassword());
        redirectAttributes.addFlashAttribute(administrator);

        return "redirect:/athn/0501/005/createCompleteAdminUsingSCrypt?complete";
    }

    @RequestMapping(value = "0501/005/createCompleteAdminUsingSCrypt", method = RequestMethod.GET,
            params = "complete")
    public String createCompleteAdministratorUsingSCryptPassword(Model model) {
        ResultMessages messages = ResultMessages.info().add("i.sf.ah.0001");
        model.addAttribute(messages);

        return "athn/createCompleteAdministrator";
    }

    @RequestMapping(value = "0501/006/afterLogin")
    public String afterLoginUsingSCryptPassword(@AuthenticationPrincipal UserDetails userDetails,
            Model model) {

        model.addAttribute("username", userDetails.getUsername());
        // principalからパスワードを取得できない為、DBから取得
        Administrator administrator =
                administratorService.findOneByUserName(userDetails.getUsername());
        model.addAttribute("administratorPassword", administrator.getPassword());

        return "athn/showAdministratorInfoUsingSCryptPassword";
    }

    @RequestMapping(value = "/0501/007", method = RequestMethod.GET)
    public String handle0501007(Model model, AdministratorForm form) {
        return "athn/createAdministratorUsingArgon2Password";
    }

    @RequestMapping(value = "0501/007/createAdminUsingArgon2", method = RequestMethod.POST)
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
        redirectAttributes.addFlashAttribute("beforeEncodePassword", form.getPassword());
        redirectAttributes.addFlashAttribute(administrator);

        return "redirect:/athn/0501/007/createCompleteAdminUsingArgon2?complete";
    }

    @RequestMapping(value = "0501/007/createCompleteAdminUsingArgon2", method = RequestMethod.GET,
            params = "complete")
    public String createCompleteAdministratorUsingArgon2Password(Model model) {
        ResultMessages messages = ResultMessages.info().add("i.sf.ah.0001");
        model.addAttribute(messages);

        return "athn/createCompleteAdministrator";
    }

    @RequestMapping(value = "0501/008/afterLogin")
    public String afterLoginUsingArgon2Password(@AuthenticationPrincipal UserDetails userDetails,
            Model model) {

        model.addAttribute("username", userDetails.getUsername());
        // principalからパスワードを取得できない為、DBから取得
        Administrator administrator =
                administratorService.findOneByUserName(userDetails.getUsername());
        model.addAttribute("administratorPassword", administrator.getPassword());

        return "athn/showAdministratorInfoUsingArgon2Password";
    }

    @RequestMapping(value = "/0502/001", method = RequestMethod.GET)
    public String handle0502001(Model model, AdministratorForm form) {
        return "athn/createAdministratorUsingDelegatingPassword";
    }

    @RequestMapping(value = "0502/001/createAdminUsingDelegating", method = RequestMethod.POST)
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
        redirectAttributes.addFlashAttribute("beforeEncodePassword", form.getPassword());
        redirectAttributes.addFlashAttribute(administrator);

        return "redirect:/athn/0502/001/createCompleteAdminUsingDelegating?complete";
    }

    @RequestMapping(value = "0502/001/createCompleteAdminUsingDelegating",
            method = RequestMethod.GET, params = "complete")
    public String createCompleteAdministratorUsingDelegatingPassword(Model model) {
        ResultMessages messages = ResultMessages.info().add("i.sf.ah.0001");
        model.addAttribute(messages);

        return "athn/createCompleteAdministrator";
    }

    @RequestMapping(value = "0502/002/afterLogin")
    public String afterLoginUsingDelegatingPassword(
            @AuthenticationPrincipal UserDetails userDetails, Model model) {

        model.addAttribute("username", userDetails.getUsername());
        // principalからパスワードを取得できない為、DBから取得
        Administrator administrator =
                administratorService.findOneByUserName(userDetails.getUsername());
        model.addAttribute("administratorPassword", administrator.getPassword());

        return "athn/showAdministratorInfoUsingDelegatingPassword";
    }

}
