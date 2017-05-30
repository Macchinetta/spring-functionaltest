/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.vldt;

import jp.co.ntt.fw.spring.functionaltest.domain.service.vldt.UserInfoUseBeanInput;

import org.springframework.stereotype.Component;

@Component
public class VLDT06Helper {

    public UserInfoUseBeanInput changeUserInfoFormToUserInfoUseBeanInput(
            UserInfoForm userInfoForm) {
        UserInfoUseBeanInput userInfoUseBeanInput = new UserInfoUseBeanInput();
        userInfoUseBeanInput.setUserId(userInfoForm.getUserId());
        userInfoUseBeanInput.setVisitDate(userInfoForm.getVisitDate());
        userInfoUseBeanInput.setVisitMessage(userInfoForm.getVisitMessage());
        if ("inputObjectNull".equals(userInfoForm.getVisitMessage())) {
            return null;
        }
        if ("visitDateNull".equals(userInfoForm.getVisitMessage())) {
            userInfoUseBeanInput.setVisitDate(null);
        }
        if ("visitMessageNull".equals(userInfoForm.getVisitMessage())) {
            userInfoUseBeanInput.setVisitMessage(null);
        }
        return userInfoUseBeanInput;
    }

}
