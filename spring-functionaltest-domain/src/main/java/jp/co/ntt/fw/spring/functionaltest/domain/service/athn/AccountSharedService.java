/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.athn;

import jp.co.ntt.fw.spring.functionaltest.domain.model.Account;

public interface AccountSharedService {
    Account findOne(String username);
}
