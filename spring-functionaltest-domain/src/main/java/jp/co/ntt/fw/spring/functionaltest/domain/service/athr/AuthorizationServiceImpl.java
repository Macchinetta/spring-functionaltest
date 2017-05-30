/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.athr;

import org.springframework.stereotype.Service;

@Service
public class AuthorizationServiceImpl implements AuthorizationService {

    @Override
    public String preAuthorize() {
        return "Allowed ROLE_ADMIN Staff";
    }

    @Override
    public String preAuthorizeRoleStaff() {
        return "Allowed ROLE_STAFF Staff";
    }
}
