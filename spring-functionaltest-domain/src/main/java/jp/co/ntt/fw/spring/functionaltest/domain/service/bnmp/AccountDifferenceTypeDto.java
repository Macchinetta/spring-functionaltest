/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.bnmp;

import java.io.Serializable;
import java.util.List;

public class AccountDifferenceTypeDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<MailAddressDto> emails;

    private String name;

    public List<MailAddressDto> getEmails() {
        return emails;
    }

    public void setEmails(List<MailAddressDto> emails) {
        this.emails = emails;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
