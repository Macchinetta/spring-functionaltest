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

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.ntt.fw.spring.functionaltest.domain.model.Account;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.athn.AccountRepository;

@Service
public class authEventHandleAccountUserDetailsService implements UserDetailsService {
    @Autowired
    AccountRepository accountRepository;

    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if ("connectionerror".equals(username)) {
            // 強制的にシステムエラーを発生させる
            throw new NullPointerException();
        }

        if ("serviceexception".equals(username)) {
            // 強制的にシステムエラーを発生させる
            return null;
        }

        Account account = accountRepository.findByUsername(username);
        if (account == null) {
            throw new UsernameNotFoundException("user not found.");
        }

        switch (username) {
            case "Edword":
                account.setAccountNonExpired(false);
                break;
            case "Jenkins":
                account.setCredentialsNonExpired(false);
                break;
            case "Rock":
                account.setAccountNonLocked(false);
                break;
            default:
                break;
        }

        return new AccountUserDetails(account, getAuthorities(account));
    }

    private Collection<GrantedAuthority> getAuthorities(Account account) {
        if (account.isAdmin()) {
            return AuthorityUtils.createAuthorityList("ROLE_USER", "ROLE_ADMIN");
        } else {
            return AuthorityUtils.createAuthorityList("ROLE_USER");
        }
    }
}
