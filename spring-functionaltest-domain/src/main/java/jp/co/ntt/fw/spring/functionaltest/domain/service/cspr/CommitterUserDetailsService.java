/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.cspr;

import javax.inject.Inject;

import jp.co.ntt.fw.spring.functionaltest.domain.model.Committer;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.cspr.CommitterRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CommitterUserDetailsService implements UserDetailsService {

    @Inject
    private CommitterRepository committerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Committer committer = committerRepository.findOneByName(username);

        if (committer == null) {
            // TODO get property
            throw new UsernameNotFoundException(username + " is not found.");
        }

        return new CommitterUserDetails(committer);
    }

}
