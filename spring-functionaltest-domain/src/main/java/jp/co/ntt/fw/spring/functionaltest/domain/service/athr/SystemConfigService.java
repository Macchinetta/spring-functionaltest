/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.athr;

import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;

import jp.co.ntt.fw.spring.functionaltest.domain.model.SystemConfig;

public interface SystemConfigService {

    @PreAuthorize("hasRole('ADMIN')")
    void insertForAdmin(SystemConfig systemConfig);

    @PreAuthorize("hasRole('STAFF')")
    void insertForStaff(SystemConfig systemConfig);

    @PreAuthorize("hasRole('ADMIN') or (#username == principal.username)")
    SystemConfig findDeviceForAdmin(String device,
            @P("username") String username);

    @PreAuthorize("hasRole('STAFF')")
    @PostAuthorize("(returnObject == null) or (returnObject.owner == principal.username)")
    SystemConfig findDeviceForStaff(String device, String username);

}
