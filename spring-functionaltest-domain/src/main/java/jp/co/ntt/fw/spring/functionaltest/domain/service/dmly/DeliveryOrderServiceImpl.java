/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.dmly;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import jp.co.ntt.fw.spring.functionaltest.domain.model.DeliveryOrder;
import jp.co.ntt.fw.spring.functionaltest.domain.model.DeliveryType;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.dmly.DeliveryOrderRepository;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.dmly.DeliveryTypeRepository;

import org.apache.ibatis.session.RowBounds;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class DeliveryOrderServiceImpl implements DeliveryOrderService {

    @Inject
    DeliveryTypeRepository deliveryTypeRepository;

    @Inject
    DeliveryOrderRepository deliveryOrderRepository;

    public long count() {
        return deliveryOrderRepository.count();
    }

    public DeliveryOrder getOrder(Integer deliveryNo) {
        return deliveryOrderRepository.findOneByDeliveryNo(deliveryNo);
    }

    public DeliveryOrder getOrderExists(Integer deliveryNo) {
        DeliveryOrder deliveryOrder = null;
        if (deliveryOrderRepository.existsByDeliveryNo(deliveryNo)) {
            deliveryOrder = deliveryOrderRepository
                    .findOneByDeliveryNo(deliveryNo);
        }

        return deliveryOrder;
    }

    public Page<DeliveryOrder> getOrders(Pageable pageable) {
        List<DeliveryOrder> deliveryOrders = null;
        long total = deliveryOrderRepository.count();
        if (0 < total) {
            RowBounds rowBounds = new RowBounds(pageable.getOffset(), pageable
                    .getPageSize());
            deliveryOrders = deliveryOrderRepository.findPage(rowBounds);
        } else {
            deliveryOrders = new ArrayList<DeliveryOrder>();
        }
        return new PageImpl<DeliveryOrder>(deliveryOrders, pageable, total);
    }

    public void register(DeliveryOrder deliveryOrder) {
        DeliveryType deliveryType = deliveryTypeRepository
                .findOneByTypeName(deliveryOrder.getDeliveryType()
                        .getDeliveryTypeName());
        deliveryOrder.getDeliveryType().setDeliveryTypeId(
                deliveryType.getDeliveryTypeId());
        deliveryOrderRepository.insert(deliveryOrder);

        return;
    }

    public void update(DeliveryOrder deliveryOrder) {
        DeliveryType deliveryType = deliveryTypeRepository
                .findOneByTypeName(deliveryOrder.getDeliveryType()
                        .getDeliveryTypeName());
        deliveryOrder.getDeliveryType().setDeliveryTypeId(
                deliveryType.getDeliveryTypeId());
        deliveryOrderRepository.update(deliveryOrder);

        return;
    }

    public void delete(Integer deliveryNo) {
        deliveryOrderRepository.delete(deliveryNo);

        return;
    }

}
