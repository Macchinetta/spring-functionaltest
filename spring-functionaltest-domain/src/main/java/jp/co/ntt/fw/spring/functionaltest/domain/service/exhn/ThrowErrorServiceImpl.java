/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.exhn;

import org.springframework.stereotype.Service;

@Service
public class ThrowErrorServiceImpl implements ThrowErrorService {

    @Override
    public void throwAssertionError() {

        throw new AssertionError();

    }

}
