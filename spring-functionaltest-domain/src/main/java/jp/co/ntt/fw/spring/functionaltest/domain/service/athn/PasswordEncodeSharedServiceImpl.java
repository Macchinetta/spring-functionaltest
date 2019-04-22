/*
 * Copyright(c) 2014 NTT Corporation.
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

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordEncodeSharedServiceImpl implements
                                             PasswordEncodeSharedService {

    @Inject
    @Named("athnPasswordEncoderBCrypt")
    PasswordEncoder passwordEncoderBCrypt;

    @SuppressWarnings("deprecation")
    @Inject
    @Named("athnPasswordEncoderMessageDigest")
    org.springframework.security.authentication.encoding.PasswordEncoder passwordEncodeMessageDigest;

    @Override
    public String passwordEncodeBCrypt(String rawPassword) {
        // passwordをBCryptPasswordEncoderでエンコードする。
        return passwordEncoderBCrypt.encode(rawPassword);
    }

    @SuppressWarnings("deprecation")
    @Override
    public String passwordEncodeMessageDigest(String rawPassword,
            String userSalt) {
        // passwordをPasswordEncodeMessageDigestでエンコードする。
        return passwordEncodeMessageDigest.encodePassword(rawPassword,
                userSalt);
    }
}
