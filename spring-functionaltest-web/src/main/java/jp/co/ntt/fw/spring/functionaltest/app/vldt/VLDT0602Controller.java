/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.vldt;

import javax.inject.Inject;

import jp.co.ntt.fw.spring.functionaltest.domain.service.vldt.UserInfoUseBeanInput;
import jp.co.ntt.fw.spring.functionaltest.domain.service.vldt.UserInfoUseBeanOutput;
import jp.co.ntt.fw.spring.functionaltest.domain.service.vldt.UserInfoUseBeanService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("vldt")
@Controller
public class VLDT0602Controller {

    @Inject
    UserInfoUseBeanService userInfoUseBeanService;

    @Inject
    VLDT06Helper vLDT02Helper;

    @ModelAttribute
    public UserInfoForm setUserInfoForm() {
        return new UserInfoForm();
    }

    @RequestMapping(value = { "0602/001", "0602/002", "0602/003", "0602/004",
            "0602/005", "0602/006", "0602/007", "0602/008", "0602/009" }, method = RequestMethod.GET)
    public String handle0201001Init() {
        return "vldt/userInfoUseBeanRequestForm";
    }

    @RequestMapping(value = "0602/regist", method = RequestMethod.POST)
    public String handle0201001Regist(@Validated UserInfoForm form,
            BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "vldt/userInfoUseBeanRequestForm";
        }

        UserInfoUseBeanInput userInfoUseBeanInput = vLDT02Helper
                .changeUserInfoFormToUserInfoUseBeanInput(form);
        UserInfoUseBeanOutput userInfoUseBeanOutput = userInfoUseBeanService
                .convertUserInfo(userInfoUseBeanInput);

        model.addAttribute("userInfo", userInfoUseBeanOutput.getUserInfo());
        return "vldt/userInfoUseBeanComplete";
    }
}
