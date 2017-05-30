/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.athn;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jp.co.ntt.fw.spring.functionaltest.domain.model.Account;

public class AccountUserDetails implements UserDetails {

    private static final long serialVersionUID = 4320378909988097259L;

    private final Account account;

    private final Collection<GrantedAuthority> authorities;

    public AccountUserDetails(Account account,
            Collection<GrantedAuthority> authorities) {
        this.account = account;
        this.authorities = authorities;
    }

    public String getPassword() {
        return account.getPassword();
    }

    public String getUsername() {
        return account.getUsername();
    }

    public boolean isEnabled() {
        return account.isEnabled();
    }

    public Collection<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public boolean isAccountNonExpired() {
        return account.isAccountNonExpired();
    }

    public boolean isAccountNonLocked() {
        return account.isAccountNonLocked();
    }

    public boolean isCredentialsNonExpired() {
        return account.isCredentialsNonExpired();
    }

    public Account getAccount() {
        return account;
    }

}
