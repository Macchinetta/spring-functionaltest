/*
 * Copyright(c) 2024 NTT Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.hlch;

import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.inject.Inject;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.hlch.HealthCheckRepository;

@Service
@Transactional
public class HealthCheckExceptionServiceImpl implements HealthCheckExceptionService {

    @Inject
    HealthCheckRepository healthcheckRepository;

    @Override
    public void healthcheckexception() {
        // 例外をthrowし、DB接続時失敗を疑似的に再現
        throw new DataAccessResourceFailureException("throw DB Error");
        // healthcheckRepository.healthCheck();
    }

}
