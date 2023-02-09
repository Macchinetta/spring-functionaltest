/*
 * Copyright(c) 2014 NTT Corporation.
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

import javax.inject.Inject;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.ntt.fw.spring.functionaltest.domain.model.Account;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.athn.AccountRepository;

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
        Account account = accountRepository.findByUsername(username);
        if (account == null) {
            throw new UsernameNotFoundException("user not found.");
        }
        // (3)
        return account;
    }

}
