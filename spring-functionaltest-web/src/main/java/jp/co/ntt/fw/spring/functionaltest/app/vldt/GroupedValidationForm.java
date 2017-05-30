/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.vldt;

import java.io.Serializable;

import javax.validation.constraints.Min;

public class GroupedValidationForm implements Serializable {

    private static final long serialVersionUID = 1L;

    public static interface Japanese {
    };

    public static interface Singaporean {
    };

    private String userName;

    private String email;

    @Min.List({ @Min(value = 20, groups = Japanese.class),
            @Min(value = 21, groups = Singaporean.class) })
    private int age;

    private String country;

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
