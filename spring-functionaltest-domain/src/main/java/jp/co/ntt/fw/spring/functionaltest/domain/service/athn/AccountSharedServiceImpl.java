/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.athn;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.terasoluna.gfw.common.exception.ResourceNotFoundException;

import jp.co.ntt.fw.spring.functionaltest.domain.model.Account;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.athn.AccountRepository;

@Service
@Transactional
public class AccountSharedServiceImpl implements AccountSharedService {

    @Inject
    AccountRepository accountRepository;

    // (2)
    @Override
    public Account findOne(String username) {
        Account account = accountRepository.findOneByUsername(username);
        if (account == null) {
            throw new ResourceNotFoundException("The given account is not found! username="
                    + username);
        }
        return account;
    }
}
