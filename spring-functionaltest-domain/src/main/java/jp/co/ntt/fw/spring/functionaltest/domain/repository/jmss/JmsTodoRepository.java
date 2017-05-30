/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.repository.jmss;

import jp.co.ntt.fw.spring.functionaltest.domain.model.JmsTodo;

public interface JmsTodoRepository {

    JmsTodo findOneById(String jmsTodoId);

    void insert(JmsTodo jmsTodo) throws RuntimeException;

}
