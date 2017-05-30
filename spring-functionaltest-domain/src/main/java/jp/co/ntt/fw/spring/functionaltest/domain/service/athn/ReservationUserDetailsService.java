/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.athn;

import javax.inject.Inject;

import jp.co.ntt.fw.spring.functionaltest.domain.model.Customer;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.athn.CustomerRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class ReservationUserDetailsService implements UserDetailsService {
    @Inject
    CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepository.findOneByName(username);
        if (customer == null) {
            throw new UsernameNotFoundException(username + " is not found."); // TODO to property file
        }
        return new ReservationUserDetails(customer);
    }

}
