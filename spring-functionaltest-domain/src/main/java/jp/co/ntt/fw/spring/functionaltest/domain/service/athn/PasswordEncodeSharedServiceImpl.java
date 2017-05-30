/*
 * Copyright(c) 2014-2017 NTT Corporation.
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
    @Named("athnPasswordEncoderSha")
    org.springframework.security.authentication.encoding.PasswordEncoder passwordEncoderSha;

    @Override
    public String passwordEncodeBCrypt(String rawPassword) {
        // passwordをBCryptPasswordEncoderでエンコードする。
        return passwordEncoderBCrypt.encode(rawPassword);
    }

    @SuppressWarnings("deprecation")
    @Override
    public String passwordEncodeSha(String rawPassword, String userSalt) {
        // passwordをShaPasswordEncoderでエンコードする。
        return passwordEncoderSha.encodePassword(rawPassword, userSalt);
    }
}
