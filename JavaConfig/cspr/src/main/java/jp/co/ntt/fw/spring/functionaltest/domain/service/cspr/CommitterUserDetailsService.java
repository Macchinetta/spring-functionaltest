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
package jp.co.ntt.fw.spring.functionaltest.domain.service.cspr;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import jakarta.inject.Inject;
import jp.co.ntt.fw.spring.functionaltest.domain.model.Committer;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.cspr.CommitterRepository;

public class CommitterUserDetailsService implements UserDetailsService {

    @Inject
    private CommitterRepository committerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Committer committer = committerRepository.findByName(username);

        if (committer == null) {
            throw new UsernameNotFoundException(username + " is not found.");
        }

        return new CommitterUserDetails(committer);
    }

}
