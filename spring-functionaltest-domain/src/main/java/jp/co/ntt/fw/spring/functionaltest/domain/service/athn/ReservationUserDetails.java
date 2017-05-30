/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.athn;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import jp.co.ntt.fw.spring.functionaltest.domain.model.Customer;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class ReservationUserDetails implements UserDetails {
    private static final long serialVersionUID = 1L;

    private final Customer customer;

    private static final List<? extends GrantedAuthority> DEFAULT_AUTHORITIES = Collections
            .singletonList(new SimpleGrantedAuthority("ROLE_USER"));

    public ReservationUserDetails(Customer customer) {
        this.customer = customer;
    }

    public String getCustomerName() {
        return this.customer.getCustomerName();
    }

    public String getCustomerAddress() {
        return this.customer.getCustomerAddress();
    }

    public String getCompanyId() {
        return this.customer.getCompanyId();
    }

    @Override
    public boolean isEnabled() {
        return this.customer.isEnabled();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return DEFAULT_AUTHORITIES;
    }

    @Override
    public String getPassword() {
        return this.customer.getPassword();
    }

    @Override
    public String getUsername() {
        return this.customer.getCustomerName();
    }

    public String getDisplayName() {
        return this.customer.getCustomerName();
    }

    public Customer getCustomer() {
        return customer;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
}
