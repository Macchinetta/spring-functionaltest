/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.bnmp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jp.co.ntt.fw.spring.functionaltest.domain.service.bnmp.EmailDto;

public class AccountForm implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<EmailDto> emails;

    private String name;

    AccountForm() {
        emails = new ArrayList<EmailDto>();
        for (int i = 0; i < 3; i++) {
            emails.add(new EmailDto());
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<EmailDto> getEmails() {
        return emails;
    }

    public void setEmails(List<EmailDto> emails) {
        this.emails = emails;
    }
}
