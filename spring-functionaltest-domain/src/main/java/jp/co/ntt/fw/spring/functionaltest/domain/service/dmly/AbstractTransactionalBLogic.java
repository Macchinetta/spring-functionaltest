/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.dmly;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public abstract class AbstractTransactionalBLogic<I, O> implements BLogic<I, O> {

    protected void preExecute(I input) {
    }

    protected void postExecute() {
    }

    protected abstract O doExecute(I input);

    public O execute(I input) {
        try {
            preExecute(input);

            O output = doExecute(input);

            return output;
        } finally {
            postExecute();
        }
    }

}
