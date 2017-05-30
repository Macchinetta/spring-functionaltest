/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.jmss;

import java.io.IOException;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import jp.co.ntt.fw.spring.functionaltest.domain.model.JmsTodo;

@Service
public class JmsValidationServiceImpl implements JmsValidationService {

    @Inject
    JmsSharedService jmsSharedService;

    @Override
    public void validate(JmsTodo jmsTodo) throws IOException {

    }

    @Override
    public void writeValidatedObjectToFile(String dir, String fileName,
            Object obj) throws IOException {
        jmsSharedService.writeObjectToFile(dir, fileName, obj);
    }
}
