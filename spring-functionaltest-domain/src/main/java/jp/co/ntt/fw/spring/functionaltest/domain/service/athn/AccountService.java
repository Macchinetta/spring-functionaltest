/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.athn;

import javax.inject.Inject;

import jp.co.ntt.fw.spring.functionaltest.domain.model.Account;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.athn.AccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccountService {

    @Inject
    AccountRepository accountRepository;

    @Inject
    PasswordEncoder passwordEncoder; // (1)

    public Account register(Account account) {
        String encodedPassword = passwordEncoder.encode(account.getPassword()); // (2)
        account.setPassword(encodedPassword);
        // omitted
        return accountRepository.save(account);
    }

    @Transactional
    public Account findOneByUserName(String username) {
        Account account = accountRepository.findOneByUsername(username);
        if (account == null) {
            throw new UsernameNotFoundException("user not found.");
        }
        // (3)
        return account;
    }

}
