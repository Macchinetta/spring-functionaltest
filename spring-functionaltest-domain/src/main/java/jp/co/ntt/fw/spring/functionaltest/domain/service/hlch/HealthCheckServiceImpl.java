/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.hlch;

import jp.co.ntt.fw.spring.functionaltest.domain.repository.hlch.HealthCheckRepository;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class HealthCheckServiceImpl implements HealthCheckService {

    @Inject
    HealthCheckRepository healthcheckRepository;

    @Override
    public void healthcheck() {
        healthcheckRepository.healthCheck();
    }

}
