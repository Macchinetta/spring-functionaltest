/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.bnmp;

import java.io.Serializable;
import java.util.Set;

public class UserDifferenceCollectionDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Set<EmailDto> emailAddresses;

    private String name;

    public Set<EmailDto> getEmailAddresses() {
        return emailAddresses;
    }

    public void setEmailAddresses(Set<EmailDto> emailAddresses) {
        this.emailAddresses = emailAddresses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
