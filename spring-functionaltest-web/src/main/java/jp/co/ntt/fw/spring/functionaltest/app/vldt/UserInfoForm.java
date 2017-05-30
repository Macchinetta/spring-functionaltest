/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.vldt;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class UserInfoForm implements Serializable {

    private static final long serialVersionUID = 3155432673599012718L;

    @DateTimeFormat(pattern = "yyyyMMdd")
    private Date visitDate;

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
