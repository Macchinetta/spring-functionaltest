/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.ajax;

import java.io.Serializable;
import java.util.List;

import jp.co.ntt.fw.spring.functionaltest.domain.model.PersonalComputer;

public class PersonalComputerSearchResult implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<PersonalComputer> personalComputerResult;

    public List<PersonalComputer> getPersonalComputerResult() {
        return personalComputerResult;
    }

    public void setPersonalComputerResult(
            List<PersonalComputer> personalComputerResult) {
        this.personalComputerResult = personalComputerResult;
    }

}
