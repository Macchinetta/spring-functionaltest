/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.vldt;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

public class UserInfoUseBeanInput implements Serializable {

    private static final long serialVersionUID = -8085762592536648165L;

    @NotNull
    @Past
    private Date visitDate;

    @NotNull
    private String visitMessage;

    private String userId;

    public Date getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }

    public String getVisitMessage() {
        return visitMessage;
    }

    public void setVisitMessage(String visitMessage) {
        this.visitMessage = visitMessage;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
