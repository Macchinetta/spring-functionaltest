/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.repository.athr;

import jp.co.ntt.fw.spring.functionaltest.domain.model.SystemConfig;

public interface SystemConfigRepository {

    SystemConfig findOneByDevice(String device);

    void insert(SystemConfig systemConfig);

}
