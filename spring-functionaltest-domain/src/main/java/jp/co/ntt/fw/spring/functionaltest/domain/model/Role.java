/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.model;

import org.terasoluna.gfw.common.codelist.ExistInCodeList;

public class Role {

    @ExistInCodeList(codeListId = "CL_ROLE")
    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return getRole();
    }
}
