/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.bnmp;

import java.io.Serializable;
import java.util.List;

public class UserDifferenceTypeDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<MailAddressDto> emailAddresses;

    private String name;

    public List<MailAddressDto> getEmailAddresses() {
        return emailAddresses;
    }

    public void setEmailAddresses(List<MailAddressDto> emailAddresses) {
        this.emailAddresses = emailAddresses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
