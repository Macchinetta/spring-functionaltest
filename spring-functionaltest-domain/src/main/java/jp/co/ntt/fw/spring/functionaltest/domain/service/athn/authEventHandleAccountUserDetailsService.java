/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.athn;

import java.util.Collection;

import jp.co.ntt.fw.spring.functionaltest.domain.model.Account;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.athn.AccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class authEventHandleAccountUserDetailsService implements
                                                     UserDetailsService {
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

        Account account = accountRepository.findOneByUsername(username);
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
            return AuthorityUtils
                    .createAuthorityList("ROLE_USER", "ROLE_ADMIN");
        } else {
            return AuthorityUtils.createAuthorityList("ROLE_USER");
        }
    }
}
