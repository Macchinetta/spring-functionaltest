/*
 * Copyright(c) 2014 NTT Corporation.
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
package jp.co.ntt.fw.spring.functionaltest.domain.service.jmss;

import java.io.IOException;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.ntt.fw.spring.functionaltest.domain.model.JmsTodo;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.jmss.JmsTodoRepository;

@Transactional
@Service
public class JmsDbTransactedAmqReceivingServiceImpl implements
                                                    JmsDbTransactedAmqReceivingService {

    @Inject
    JmsTodoRepository jmsTodoRepository;

    @Inject
    JmsValidationService jmsValidationService;

    @Override
    public void receiveInputValidationIsolatedTransactionJmsCommitDbRollback(
            JmsTodo jmsTodo) throws IOException {
        jmsTodoRepository.insert(jmsTodo);
        jmsValidationService.validate(jmsTodo);
    }

    @Override
    public void receiveInputValidationIsolatedTransactionJmsAndDbCommit(
            JmsTodo jmsTodo) throws IOException {
        jmsTodoRepository.insert(jmsTodo);
    }
}
