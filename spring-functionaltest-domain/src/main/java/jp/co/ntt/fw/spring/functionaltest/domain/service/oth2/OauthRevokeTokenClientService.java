/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.oth2;

import org.springframework.web.client.RestOperations;

public interface OauthRevokeTokenClientService {

    String revokeTokenFromDb();

    String revokeTokenFromMemory();
}
