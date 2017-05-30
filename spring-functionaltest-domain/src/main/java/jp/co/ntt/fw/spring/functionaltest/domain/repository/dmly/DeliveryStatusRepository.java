/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.repository.dmly;

import java.util.List;

import jp.co.ntt.fw.spring.functionaltest.domain.model.DeliveryStatus;

public interface DeliveryStatusRepository {

    List<DeliveryStatus> findAll();

}
