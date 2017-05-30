/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.vldt;

import java.io.Serializable;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

public class DefineMessageByMessageSourceForm implements Serializable {

    private static final long serialVersionUID = 1L;

    @Size(min = 1, max = 20)
    private String userName;

    @Email
    private String email;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
