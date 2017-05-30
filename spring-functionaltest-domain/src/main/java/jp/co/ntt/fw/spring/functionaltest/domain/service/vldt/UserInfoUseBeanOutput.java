/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.vldt;

import java.io.Serializable;
import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import jp.co.ntt.fw.spring.functionaltest.domain.model.UserInfo;

public class UserInfoUseBeanOutput implements Serializable {

    private static final long serialVersionUID = 3099410071218479878L;

    @NotNull
    @Past
    private Date acceptDate;

    @NotNull
    private String acceptMessage;

    @Valid
    private UserInfo userInfo;

    public Date getAcceptDate() {
        return acceptDate;
    }

    public void setAcceptDate(Date acceptDate) {
        this.acceptDate = acceptDate;
    }

    public String getAcceptMessage() {
        return acceptMessage;
    }

    public void setAcceptMessage(String acceptMessage) {
        this.acceptMessage = acceptMessage;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

}
