/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.repository.pgnt;

import java.io.Serializable;

import javax.validation.constraints.Max;

public class CelebritySearchCriteria implements Serializable {

    private static final long serialVersionUID = 1L;

    @Max(30)
    private String firstName;

    @Max(30)
    private String lastName;

    private String sort;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

}
