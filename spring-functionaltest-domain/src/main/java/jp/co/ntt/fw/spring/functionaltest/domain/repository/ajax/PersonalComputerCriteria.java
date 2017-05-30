/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.repository.ajax;

import java.io.Serializable;

public class PersonalComputerCriteria implements Serializable {

    private static final long serialVersionUID = 1L;

    private String personalComputerName;

    public String getPersonalComputerName() {
        return personalComputerName;
    }

    public void setPersonalComputerName(String personalComputerName) {
        this.personalComputerName = personalComputerName;
    }

}
