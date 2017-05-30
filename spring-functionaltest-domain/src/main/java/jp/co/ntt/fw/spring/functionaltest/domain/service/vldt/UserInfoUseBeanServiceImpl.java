/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.vldt;

import java.util.Calendar;

import jp.co.ntt.fw.spring.functionaltest.domain.model.UserInfo;

import org.springframework.stereotype.Service;

@Service
public class UserInfoUseBeanServiceImpl implements UserInfoUseBeanService {

    @Override
    public UserInfoUseBeanOutput convertUserInfo(
            UserInfoUseBeanInput userInfoUseBeanInput) {

        // return nullの試験用の特殊設定
        if ("outputObjectNull".equals(userInfoUseBeanInput.getVisitMessage())) {
            return null;
        }

        UserInfo userInfo = new UserInfo();
        userInfo.setDateOfBirth(userInfoUseBeanInput.getVisitDate());
        userInfo.setUserId(userInfoUseBeanInput.getUserId());
        userInfo.setUserName(userInfoUseBeanInput.getUserId());

        // User.userId=nullの試験用の特殊設定
        if ("userIdNull".equals(userInfoUseBeanInput.getVisitMessage())) {
            userInfo.setUserId(null);
        }

        // User.userName=nullの試験用の特殊設定
        if ("userNameNull".equals(userInfoUseBeanInput.getVisitMessage())) {
            userInfo.setUserName(null);
        }

        // User.udateOfBirthが未来日の試験用の特殊設定
        if ("dateOfBirthFuture".equals(userInfoUseBeanInput.getVisitMessage())) {
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.YEAR, 1);
            userInfo.setDateOfBirth(cal.getTime());
        }

        UserInfoUseBeanOutput UserInfoUseBeanOutput = new UserInfoUseBeanOutput();
        UserInfoUseBeanOutput.setUserInfo(userInfo);
        UserInfoUseBeanOutput
                .setAcceptDate(userInfoUseBeanInput.getVisitDate());
        UserInfoUseBeanOutput.setAcceptMessage(userInfoUseBeanInput
                .getVisitMessage());

        return UserInfoUseBeanOutput;
    }

}
