/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.hlch;

import jp.co.ntt.fw.spring.functionaltest.domain.repository.hlch.HealthCheckRepository;

import javax.inject.Inject;

import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class HealthCheckExceptionServiceImpl implements
                                            HealthCheckExceptionService {

    @Inject
    HealthCheckRepository healthcheckRepository;

    @Override
    public void healthcheckexception() {
        // 例外をthrowし、DB接続時失敗を疑似的に再現
        throw new DataAccessResourceFailureException("throw DB Error");
        // healthcheckRepository.healthCheck();
    }

}
