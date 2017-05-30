/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.oth2;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.ntt.fw.spring.functionaltest.domain.repository.oth2.ApproveRepository;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.oth2.TokenRepository;

@Service
@Transactional
public class OauthAuthorizationInitServiceImpl implements
                                              OauthAuthorizationInitService {

    @Inject
    ApproveRepository approveRepository;

    @Inject
    TokenRepository tokenRepository;

    @Override
    public void deleteApproveAndToken() {

        approveRepository.deleteAll();

        tokenRepository.deleteAllToken();

        tokenRepository.deleteAllRefreshToken();
    }

    @Override
    public long countToken() {

        return tokenRepository.countAllToken();
    }

    @Override
    public long countRefreshToken() {

        return tokenRepository.countAllRefreshToken();
    }

    @Override
    public long countApprove() {

        return approveRepository.countAll();
    }

}
