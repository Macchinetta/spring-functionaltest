/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.bnmp;

import java.io.Serializable;
import java.util.List;

public class UserDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<EmailDto> emailAddresses;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<EmailDto> getEmailAddresses() {
        return emailAddresses;
    }

    public void setEmailAddresses(List<EmailDto> emailAddresses) {
        this.emailAddresses = emailAddresses;
    }
}
