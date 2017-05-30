/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.dmly;

import java.util.List;

import jp.co.ntt.fw.spring.functionaltest.domain.model.DeliveryOrder;

public interface DeliveryOrderMandatorySharedService {

    void insert(List<DeliveryOrder> insertOrderList);

    void update(List<DeliveryOrder> updateOrderList);

}
