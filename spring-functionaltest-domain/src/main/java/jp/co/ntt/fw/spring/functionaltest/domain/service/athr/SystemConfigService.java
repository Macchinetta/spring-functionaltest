/*
 * Copyright 2014-2017 NTT Corporation.
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
 *
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
