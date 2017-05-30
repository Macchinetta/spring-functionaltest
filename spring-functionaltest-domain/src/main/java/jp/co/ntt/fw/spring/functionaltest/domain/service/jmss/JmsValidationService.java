/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.jmss;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import jp.co.ntt.fw.spring.functionaltest.domain.model.JmsTodo;

@Validated
public interface JmsValidationService {

    void validate(@Valid JmsTodo jmsTodo) throws IOException;

    void writeValidatedObjectToFile(String dir, String fileName,
            @Valid Object obj) throws IOException;
}
