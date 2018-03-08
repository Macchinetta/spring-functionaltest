/*
 * Copyright 2014-2018 NTT Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.cspr;

import java.util.Collections;
import java.util.List;

import jp.co.ntt.fw.spring.functionaltest.domain.model.Committer;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class CommitterUserDetails extends User {

    private static final long serialVersionUID = 1L;

    private final Committer committer;

    private static final List<? extends GrantedAuthority> DEFAULT_AUTHORITIES = Collections
            .singletonList(new SimpleGrantedAuthority("ROLE_USER"));

    public CommitterUserDetails(Committer committer) {
        super(committer.getUsername(), committer.getPassword(), true, true,
                true, true, DEFAULT_AUTHORITIES);
        this.committer = committer;
    }

    public Committer getCommitter() {
        return committer;
    }
}
