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

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import jakarta.inject.Inject;
import jp.co.ntt.fw.spring.functionaltest.domain.model.Customer;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.athn.CustomerRepository;

public class ReservationUserDetailsService implements UserDetailsService {
    @Inject
    CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(
            String username) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByName(username);
        if (customer == null) {
            throw new UsernameNotFoundException(username + " is not found."); // to property file
        }
        return new ReservationUserDetails(customer);
    }

}
