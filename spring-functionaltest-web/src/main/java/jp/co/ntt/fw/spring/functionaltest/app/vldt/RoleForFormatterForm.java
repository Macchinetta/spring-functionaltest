/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.vldt;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import jp.co.ntt.fw.spring.functionaltest.domain.model.Role;

public class RoleForFormatterForm {

    @NotNull
    @Valid
    private List<Role> roles;

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
