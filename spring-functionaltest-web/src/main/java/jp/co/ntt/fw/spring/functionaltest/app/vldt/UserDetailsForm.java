/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.vldt;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.joda.time.LocalDate;

public class UserDetailsForm implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    private int age;

    @NotNull
    private LocalDate dateOfBirth;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
