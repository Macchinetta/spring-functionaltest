/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.repository.dmly;

import java.util.List;

import jp.co.ntt.fw.spring.functionaltest.domain.model.DeliveryOrder;

import org.apache.ibatis.session.RowBounds;

public interface DeliveryOrderRepository {

    DeliveryOrder findOneByDeliveryNo(Integer DeliveryNo);

    List<DeliveryOrder> findAll();

    List<DeliveryOrder> findPage(RowBounds rowBounds);

    long count();

    boolean existsByDeliveryNo(Integer deliveryNo);

    void insert(DeliveryOrder deliveryOrder);

    void update(DeliveryOrder deliveryOrder);

    void delete(Integer deliveryNo);

    long countByCriteria(DeliveryOrderCriteria deliveryOrderCriteria);

    void updateByCriteria(DeliveryOrderCriteria deliveryOrderCriteria);

    void clear();
}
