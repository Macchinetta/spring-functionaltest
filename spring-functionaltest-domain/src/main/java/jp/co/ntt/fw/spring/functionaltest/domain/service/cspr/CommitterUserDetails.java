/*
 * Copyright(c) 2014-2017 NTT Corporation.
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
