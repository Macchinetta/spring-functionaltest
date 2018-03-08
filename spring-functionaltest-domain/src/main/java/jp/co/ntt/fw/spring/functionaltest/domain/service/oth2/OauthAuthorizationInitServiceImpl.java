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
