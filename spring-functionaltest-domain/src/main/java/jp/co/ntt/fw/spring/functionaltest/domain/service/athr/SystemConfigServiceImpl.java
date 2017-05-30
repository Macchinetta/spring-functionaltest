/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.athr;

import javax.inject.Inject;

import jp.co.ntt.fw.spring.functionaltest.domain.model.SystemConfig;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.athr.SystemConfigRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SystemConfigServiceImpl implements SystemConfigService {

    @Inject
    SystemConfigRepository systemConfigRepository;

    public void insertForAdmin(SystemConfig systemConfig) {
        systemConfigRepository.insert(systemConfig);
        return;
    }

    public void insertForStaff(SystemConfig systemConfig) {
        systemConfigRepository.insert(systemConfig);
        return;
    }

    @Override
    public SystemConfig findDeviceForAdmin(String device, String userName) {
        SystemConfig systemConfig = systemConfigRepository
                .findOneByDevice(device);
        return systemConfig;
    }

    @Override
    public SystemConfig findDeviceForStaff(String device, String userName) {
        SystemConfig systemConfig = systemConfigRepository
                .findOneByDevice(device);
        return systemConfig;
    }
}
