/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.ajax;

import java.util.List;

import jp.co.ntt.fw.spring.functionaltest.domain.model.PersonalComputer;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.ajax.PersonalComputerCriteria;

public interface PersonalComputerService {

    PersonalComputer getPersonalComputer(int personalComputerId);

    List<PersonalComputer> getPersonalComputers(
            PersonalComputerCriteria criteria);

    void update(PersonalComputer personalComputer);

}
