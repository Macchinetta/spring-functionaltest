/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.athn;

import jp.co.ntt.fw.spring.functionaltest.domain.model.Administrator;

public interface AdministratorService {

    void createUsingBCryptEncode(Administrator administrator);

    void createUsingShaEncode(Administrator administrator);

    Administrator findOneByUserName(String userName);
}
