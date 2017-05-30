/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.repository.athn;

import jp.co.ntt.fw.spring.functionaltest.domain.model.Administrator;

public interface AdministratorRepository {

    void insert(Administrator administrator);

    Administrator findOneByUserName(String userName);
}
