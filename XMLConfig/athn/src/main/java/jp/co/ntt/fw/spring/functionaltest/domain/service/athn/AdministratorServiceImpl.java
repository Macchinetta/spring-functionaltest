/*
 * Copyright(c) 2024 NTT Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.athn;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.terasoluna.gfw.common.exception.BusinessException;
import org.terasoluna.gfw.common.message.ResultMessages;
import jakarta.inject.Inject;
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
            throw new BusinessException(ResultMessages.danger().add("e.sf.ah.0001"));
        }

        administrator.setPassword(
                passwordEncodeSharedService.passwordEncodeBCrypt(administrator.getPassword()));
        setAdministratorConfig(administrator);

        administratorRepository.insert(administrator);
    }

    @Override
    public void createUsingPbkdf2Encode(Administrator administrator) {

        if (findOneByUserName(administrator.getUsername()) != null) {
            throw new BusinessException(ResultMessages.danger().add("e.sf.ah.0001"));
        }

        administrator.setPassword(
                passwordEncodeSharedService.passwordEncodePbkdf2(administrator.getPassword()));
        setAdministratorConfig(administrator);

        administratorRepository.insert(administrator);
    }

    @Override
    public void createUsingSCryptEncode(Administrator administrator) {

        if (findOneByUserName(administrator.getUsername()) != null) {
            throw new BusinessException(ResultMessages.danger().add("e.sf.ah.0001"));
        }

        administrator.setPassword(
                passwordEncodeSharedService.passwordEncodeSCrypt(administrator.getPassword()));
        setAdministratorConfig(administrator);

        administratorRepository.insert(administrator);
    }

    @Override
    public void createUsingArgon2Encode(Administrator administrator) {

        if (findOneByUserName(administrator.getUsername()) != null) {
            throw new BusinessException(ResultMessages.danger().add("e.sf.ah.0001"));
        }

        administrator.setPassword(
                passwordEncodeSharedService.passwordEncodeArgon2(administrator.getPassword()));
        setAdministratorConfig(administrator);

        administratorRepository.insert(administrator);
    }

    @Override
    public void createUsingDelegatingEncode(Administrator administrator) {

        if (findOneByUserName(administrator.getUsername()) != null) {
            throw new BusinessException(ResultMessages.danger().add("e.sf.ah.0001"));
        }

        administrator.setPassword(
                passwordEncodeSharedService.passwordEncodeDelegating(administrator.getPassword()));
        setAdministratorConfig(administrator);

        administratorRepository.insert(administrator);
    }

    @Override
    public void createUsingCustomPbkdf2Encode(Administrator administrator) {

        if (findOneByUserName(administrator.getUsername()) != null) {
            throw new BusinessException(ResultMessages.danger().add("e.sf.ah.0001"));
        }

        administrator.setPassword(passwordEncodeSharedService
                .passwordEncodeCustomPbkdf2(administrator.getPassword()));
        setAdministratorConfig(administrator);

        administratorRepository.insert(administrator);
    }

    @Override
    public void createUsingMessageDigestEncode(Administrator administrator) {

        if (findOneByUserName(administrator.getUsername()) != null) {
            throw new BusinessException(ResultMessages.danger().add("e.sf.ah.0001"));
        }

        administrator.setPassword(passwordEncodeSharedService.passwordEncodeMessageDigest(
                administrator.getPassword(), administrator.getUsername()));
        setAdministratorConfig(administrator);

        administratorRepository.insert(administrator);
    }

    @Override
    public void createUsingDelegatingMessageDigestEncode(Administrator administrator) {

        if (findOneByUserName(administrator.getUsername()) != null) {
            throw new BusinessException(ResultMessages.danger().add("e.sf.ah.0001"));
        }

        administrator.setPassword(passwordEncodeSharedService.passwordEncodeMessageDigestDelegating(
                administrator.getPassword(), administrator.getUsername()));
        setAdministratorConfig(administrator);

        administratorRepository.insert(administrator);
    }

    @Override
    public Administrator findOneByUserName(String userName) {
        Administrator administrator = administratorRepository.findByUserName(userName);
        return administrator;
    }

    private void setAdministratorConfig(Administrator administrator) {
        administrator.setEnabled(true);
        administrator.setAuthority("ROLE_ADMIN");
    }
}
