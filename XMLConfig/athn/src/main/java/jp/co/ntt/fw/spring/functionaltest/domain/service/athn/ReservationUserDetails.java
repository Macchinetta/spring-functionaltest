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

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import jp.co.ntt.fw.spring.functionaltest.domain.model.Customer;

public class ReservationUserDetails implements UserDetails {
    private static final long serialVersionUID = 1L;

    private final Customer customer;

    private static final List<? extends GrantedAuthority> DEFAULT_AUTHORITIES =
            Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));

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
