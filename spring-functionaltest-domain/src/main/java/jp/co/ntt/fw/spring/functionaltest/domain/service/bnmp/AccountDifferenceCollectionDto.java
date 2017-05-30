/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.bnmp;

import java.io.Serializable;
import java.util.Set;

public class AccountDifferenceCollectionDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Set<EmailDto> emails;

    private String name;

    public Set<EmailDto> getEmails() {
        return emails;
    }

    public void setEmails(Set<EmailDto> emails) {
        this.emails = emails;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
