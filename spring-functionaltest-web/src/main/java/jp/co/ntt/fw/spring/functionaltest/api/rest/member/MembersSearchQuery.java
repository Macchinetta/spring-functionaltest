/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.api.rest.member;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotEmpty;

public class MembersSearchQuery implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotEmpty
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
