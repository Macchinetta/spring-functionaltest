/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.repository.ajax;

import java.util.List;

import jp.co.ntt.fw.spring.functionaltest.domain.model.PersonalComputer;

public interface PersonalComputerRepository {

    PersonalComputer findOneByID(int personalComputerId);

    long countByCriteria(PersonalComputerCriteria criteria);

    long countByNameAndNotEqualID(PersonalComputer personalComputer);

    List<PersonalComputer> findPageByCriteria(PersonalComputerCriteria criteria);

    void update(PersonalComputer personalComputer);

}
