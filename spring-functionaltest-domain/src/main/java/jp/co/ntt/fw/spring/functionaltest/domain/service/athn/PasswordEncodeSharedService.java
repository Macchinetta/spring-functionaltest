/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.athn;

public interface PasswordEncodeSharedService {

    String passwordEncodeBCrypt(String rawPassword);

    String passwordEncodeSha(String rawPassword, String userSalt);
}
