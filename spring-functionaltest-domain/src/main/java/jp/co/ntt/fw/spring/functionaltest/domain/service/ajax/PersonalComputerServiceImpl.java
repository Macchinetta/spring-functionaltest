/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.ajax;

import java.util.List;

import javax.inject.Inject;

import jp.co.ntt.fw.spring.functionaltest.domain.model.PersonalComputer;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.ajax.PersonalComputerCriteria;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.ajax.PersonalComputerRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.terasoluna.gfw.common.exception.BusinessException;
import org.terasoluna.gfw.common.message.ResultMessages;

@Transactional
@Service
public class PersonalComputerServiceImpl implements PersonalComputerService {

    @Inject
    PersonalComputerRepository personalComputerRepository;

    @Override
    public PersonalComputer getPersonalComputer(int personalComputerId) {

        PersonalComputer personalComputer = personalComputerRepository
                .findOneByID(personalComputerId);

        return personalComputer;
    }

    @Override
    public List<PersonalComputer> getPersonalComputers(
            PersonalComputerCriteria criteria) {

        List<PersonalComputer> personalComputers = personalComputerRepository
                .findPageByCriteria(criteria);
        return personalComputers;

    }

    @Override
    public void update(PersonalComputer personalComputer) {

        long count = personalComputerRepository
                .countByNameAndNotEqualID(personalComputer);

        if (0 < count) {
            ResultMessages messages = ResultMessages.danger();
            messages.add("e.sf.ajax.8001");
            throw new BusinessException(messages);
        }

        personalComputerRepository.update(personalComputer);
    }

}
