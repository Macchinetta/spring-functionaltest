/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.athn;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.terasoluna.gfw.common.exception.BusinessException;
import org.terasoluna.gfw.common.message.ResultMessages;

import jp.co.ntt.fw.spring.functionaltest.domain.model.Administrator;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.athn.AdministratorRepository;

@Transactional
@Service
public class AdministratorServiceImpl implements AdministratorService {

    @Inject
    AdministratorRepository administratorRepository;

    @Inject
    PasswordEncodeSharedService passwordEncodeSharedService;

    @Override
    public void createUsingBCryptEncode(Administrator administrator) {

        if (findOneByUserName(administrator.getUsername()) != null) {
            throw new BusinessException(ResultMessages.danger().add(
                    "e.sf.athn.0001"));
        }

        administrator.setPassword(passwordEncodeSharedService
                .passwordEncodeBCrypt(administrator.getPassword()));
        setAdministratorConfig(administrator);

        administratorRepository.insert(administrator);
    }

    @Override
    public void createUsingShaEncode(Administrator administrator) {

        if (findOneByUserName(administrator.getUsername()) != null) {
            throw new BusinessException(ResultMessages.danger().add(
                    "e.sf.pshs.0001"));
        }

        administrator.setPassword(passwordEncodeSharedService
                .passwordEncodeSha(administrator.getPassword(), administrator
                        .getUsername()));
        setAdministratorConfig(administrator);

        administratorRepository.insert(administrator);
    }

    @Override
    public Administrator findOneByUserName(String userName) {
        Administrator administrator = administratorRepository
                .findOneByUserName(userName);
        return administrator;
    }

    private void setAdministratorConfig(Administrator administrator) {
        administrator.setEnabled(true);
        administrator.setAuthority("ROLE_ADMIN");
    }
}
