/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.vldt;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import jp.co.ntt.fw.spring.functionaltest.app.vldt.vallidation.ExistInCodeListForTypeArgument;

public class RoleForExAnnotationForm {
    @NotNull
    @Valid
    private List<@ExistInCodeListForTypeArgument(codeListId = "CL_ROLE") String> roles;

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
