/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.athr;

import org.springframework.security.access.prepost.PreAuthorize;

public interface AuthorizationService {

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    String preAuthorize();

    @PreAuthorize("hasRole('ROLE_STAFF')")
    String preAuthorizeRoleStaff();

}
