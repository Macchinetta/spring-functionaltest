/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.repository.dmly;

import java.util.List;

import jp.co.ntt.fw.spring.functionaltest.domain.model.DeliveryType;

public interface DeliveryTypeRepository {

    List<DeliveryType> findAll();

    DeliveryType findOneByTypeName(String typeName);

}
