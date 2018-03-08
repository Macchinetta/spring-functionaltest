/*
 * Copyright 2014-2018 NTT Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jp.co.ntt.fw.spring.functionaltest.app.athn;

import java.security.Principal;

import javax.inject.Inject;

import jp.co.ntt.fw.spring.functionaltest.domain.model.Administrator;
import jp.co.ntt.fw.spring.functionaltest.domain.service.athn.AdministratorService;

import org.springframework.security.core.Authentication;
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
    public String handle18001(Model model, AdministratorForm form) {
        return "athn/createAdministratorUsingShaPassword";
    }

    @RequestMapping(value = "/1801/002", method = RequestMethod.GET)
    public String handle18002(Model model, AdministratorForm form) {
        return "athn/loginAdministratorUsingShaPassword";
    }

    @RequestMapping(value = "1801/001/createAdminUsingSha", method = RequestMethod.POST)
    public String createAdministratorUsingShaPassword(Model model,
            @Validated AdministratorForm form, BindingResult result,
            RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return handle18001(model, form);
        }

        // Administratorテーブルに管理者情報登録
        Administrator administrator = new Administrator();
        administrator.setUsername(form.getUsername());
        administrator.setPassword(form.getPassword());
        try {
            administratorService.createUsingShaEncode(administrator);
        } catch (BusinessException e) {
            // 同じユーザ名が存在する場合エラー
            model.addAttribute(e.getResultMessages());
            return handle18001(model, form);
        }

        // エンコード前のパスワード
        redirectAttributes.addFlashAttribute("beforeEncodePassword", form
                .getPassword());
        redirectAttributes.addFlashAttribute(administrator);

        return "redirect:/athn/1801/001/createCompleteAdminUsingSha?complete";
    }

    @RequestMapping(value = "1801/001/createCompleteAdminUsingSha", method = RequestMethod.GET, params = "complete")
    public String createCompleteAdministratorUsingShaPassword(Model model) {
        ResultMessages messages = ResultMessages.info().add("i.sf.athn.0001");
        model.addAttribute(messages);

        return "athn/createCompleteAdministrator";
    }

    @RequestMapping(value = "1801/002/afterLogin")
    public String afterLoginUsingShaPassword(Principal principal, Model model) {
        Authentication authentication = (Authentication) principal;
        UserDetails userDetails = null;
        if (authentication != null) {
            userDetails = (UserDetails) authentication.getPrincipal();
        }
        if (userDetails != null) {
            model.addAttribute("username", userDetails.getUsername());
            // principalからパスワードを取得できない為、DBから取得
            Administrator administrator = administratorService
                    .findOneByUserName(userDetails.getUsername());
            model.addAttribute("administratorPassword", administrator
                    .getPassword());
        }

        return "athn/showAdministratorInfoUsingShaPassword";
    }
}
