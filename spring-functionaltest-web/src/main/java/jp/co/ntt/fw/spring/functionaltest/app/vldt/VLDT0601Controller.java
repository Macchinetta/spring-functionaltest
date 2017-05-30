/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.vldt;

import javax.inject.Inject;

import jp.co.ntt.fw.spring.functionaltest.domain.service.vldt.UserIdUseService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("vldt")
@Controller
public class VLDT0601Controller {

    @Inject
    UserIdUseService userIdUseService;

    @ModelAttribute
    public UserIdForm setUpUserIdForm() {
        return new UserIdForm();
    }

    @RequestMapping(value = { "0601/001", "0601/002", "0601/003", "0601/004", }, method = RequestMethod.GET)
    public String handle0101Init() {
        return "vldt/userIdUseRequestForm";
    }

    @RequestMapping(value = "0601/regist", method = RequestMethod.POST)
    public String handle0101001Regist(UserIdForm form, Model model) {

        String userId = "beforeNull".equals(form.getUserId()) ? null : form
                .getUserId();
        String convertUserId = userIdUseService.convertUserId(userId);
        model.addAttribute("userId", convertUserId);

        return "vldt/userIdUseComplete";
    }

}
