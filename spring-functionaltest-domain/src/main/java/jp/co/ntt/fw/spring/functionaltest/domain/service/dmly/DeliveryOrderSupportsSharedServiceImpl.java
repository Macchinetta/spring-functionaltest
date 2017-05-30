/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.dmly;

import java.util.List;

import javax.inject.Inject;

import jp.co.ntt.fw.spring.functionaltest.domain.model.DeliveryOrder;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.dmly.DeliveryOrderRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.SUPPORTS)
@Service
public class DeliveryOrderSupportsSharedServiceImpl implements
                                                   DeliveryOrderSupportsSharedService {

    @Inject
    DeliveryOrderRepository deliveryOrderRepository;

    @Override
    public void insert(List<DeliveryOrder> insertOrderList) {
        for (DeliveryOrder insertOrder : insertOrderList) {
            deliveryOrderRepository.insert(insertOrder);
        }
        return;
    }

    @Override
    public void update(List<DeliveryOrder> updateOrderList) {
        for (DeliveryOrder updateOrder : updateOrderList) {
            deliveryOrderRepository.update(updateOrder);
        }
        return;
    }

}
