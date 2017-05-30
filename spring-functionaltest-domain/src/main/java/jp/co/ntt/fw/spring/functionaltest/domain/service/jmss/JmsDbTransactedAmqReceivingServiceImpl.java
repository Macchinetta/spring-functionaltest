/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.jmss;

import java.io.IOException;

import javax.inject.Inject;

import jp.co.ntt.fw.spring.functionaltest.domain.model.JmsTodo;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.jmss.JmsTodoRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class JmsDbTransactedAmqReceivingServiceImpl implements
                                                   JmsDbTransactedAmqReceivingService {
    private static final Logger logger = LoggerFactory
            .getLogger(JmsDbTransactedAmqReceivingServiceImpl.class);

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

    @Override
    public void receiveInputValidationIsolatedChaindTransactionJmsCommitDbRollback(
            JmsTodo jmsTodo) throws IOException {
        jmsTodoRepository.insert(jmsTodo);
        jmsValidationService.validate(jmsTodo);
    }
}
