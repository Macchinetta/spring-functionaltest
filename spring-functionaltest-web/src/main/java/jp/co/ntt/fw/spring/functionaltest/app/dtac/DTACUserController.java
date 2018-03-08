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
package jp.co.ntt.fw.spring.functionaltest.app.dtac;

import javax.inject.Inject;

import jp.co.ntt.fw.spring.functionaltest.domain.model.User;
import jp.co.ntt.fw.spring.functionaltest.domain.service.dtac.UserListRoutingService;

import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequestMapping("dtac/user")
@Controller
public class DTACUserController {

    private static final Logger logger = LoggerFactory.getLogger(
            DTACUserController.class);

    @Inject
    Mapper beanMapper;

    @Inject
    UserListRoutingService userListRoutingService;

    @ModelAttribute
    public UserForm setUpForm() {
        return new UserForm();
    }

    @RequestMapping(value = "register", method = RequestMethod.GET, params = "complete")
    public String registerComplete(@RequestParam("username") String username,
            Model model) {

        User user = userListRoutingService.getUser(username);
        if (null == user) {
            user = new User();
        }
        model.addAttribute("user", user);

        return "dtac/registerComplete";
    }

    @RequestMapping(value = "register", method = RequestMethod.POST, params = "redo")
    public String registerRedo(UserForm form, Model model) {
        return "dtac/registerForm";
    }

    @RequestMapping(value = "register", method = RequestMethod.POST, params = "cancel")
    public String registerCancel(RedirectAttributes redirectAttrs,
            Model model) {
        return "redirect:/dtac/user/list";
    }

    @RequestMapping(value = "register", method = RequestMethod.POST, params = "back")
    public String registerBack(RedirectAttributes redirectAttrs, Model model) {
        return "redirect:/dtac/user/list";
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String register(@Validated UserForm form, BindingResult result,
            RedirectAttributes redirectAttrs, Model model) {
        if (result.hasErrors()) {
            return registerRedo(form, model);
        }

        try {
            User user = beanMapper.map(form, User.class);
            userListRoutingService.register(user);
        } catch (DuplicateKeyException e) {
            logger.error("DuplicateKeyExeption occur!!");
            throw e;
        }

        redirectAttrs.addAttribute("complete", "");
        redirectAttrs.addAttribute("username", form.getUsername());

        return "redirect:/dtac/user/register";
    }

    @RequestMapping(value = "register", params = "form")
    public String registerForm(Model model) {
        return "dtac/registerForm";
    }

}
