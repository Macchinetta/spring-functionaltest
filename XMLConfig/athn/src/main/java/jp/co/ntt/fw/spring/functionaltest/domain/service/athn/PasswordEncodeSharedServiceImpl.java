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

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Service
public class PasswordEncodeSharedServiceImpl implements PasswordEncodeSharedService {

    @Inject
    @Named("athnPasswordEncoderBCrypt")
    PasswordEncoder passwordEncoderBCrypt;

    @Inject
    @Named("athnPasswordEncoderPbkdf2")
    PasswordEncoder passwordEncoderPbkdf2;

    @Inject
    @Named("athnPasswordEncoderSCrypt")
    PasswordEncoder passwordEncoderSCrypt;

    @Inject
    @Named("athnPasswordEncoderArgon2")
    PasswordEncoder passwordEncoderArgon2;

    @Inject
    @Named("passwordEncoder")
    PasswordEncoder passwordEncoderDelegating;

    @Inject
    @Named("athnPasswordEncoderCustomPbkdf2")
    PasswordEncoder passwordEncoderCustomPbkdf2;

    @Inject
    @Named("athnPasswordEncoderMessageDigest")
    PasswordEncoder passwordEncoderMessageDigest;

    @Inject
    @Named("athnPasswordEncoderMessageDigestDelegating")
    PasswordEncoder passwordEncoderMessageDigestDelegating;

    @Override
    public String passwordEncodeBCrypt(String rawPassword) {
        // passwordをBCryptPasswordEncoderでエンコードする。
        return passwordEncoderBCrypt.encode(rawPassword);
    }

    @Override
    public String passwordEncodePbkdf2(String rawPassword) {
        // passwordをPbkdf2PasswordEncoderでエンコードする。
        return passwordEncoderPbkdf2.encode(rawPassword);
    }

    @Override
    public String passwordEncodeSCrypt(String rawPassword) {
        // passwordをSCryptPasswordEncoderでエンコードする。
        return passwordEncoderSCrypt.encode(rawPassword);
    }

    @Override
    public String passwordEncodeArgon2(String rawPassword) {
        // passwordをArgon2PasswordEncoderでエンコードする。
        return passwordEncoderArgon2.encode(rawPassword);
    }

    @Override
    public String passwordEncodeDelegating(String rawPassword) {
        // passwordをDelegatingPasswordEncoder(Pbkdf2アルゴリズム)でエンコードする。
        return passwordEncoderDelegating.encode(rawPassword);
    }

    @Override
    public String passwordEncodeCustomPbkdf2(String rawPassword) {
        // passwordをDelegatingPasswordEncoder(Pbkdf2アルゴリズム)でエンコードする。
        return passwordEncoderCustomPbkdf2.encode(rawPassword);
    }

    @Override
    public String passwordEncodeMessageDigest(String rawPassword, String userSalt) {
        // passwordをPasswordEncoderMessageDigestでエンコードする。
        return passwordEncoderMessageDigest.encode(rawPassword);
    }

    @Override
    public String passwordEncodeMessageDigestDelegating(String rawPassword, String userSalt) {
        // passwordをDelegatingPasswordEncoder（PasswordEncoderMessageDigestに委譲）でエンコードする。
        return passwordEncoderMessageDigestDelegating.encode(rawPassword);
    }

}
