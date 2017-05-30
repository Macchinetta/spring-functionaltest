/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.dmly;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import jp.co.ntt.fw.spring.functionaltest.domain.model.DeliveryOrder;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.dmly.DeliveryOrderRepository;

import org.springframework.stereotype.Service;

@Transactional(value = Transactional.TxType.REQUIRES_NEW)
@Service
public class DeliveryOrderRequiresNewJTA12SharedServiceImpl implements
                                                           DeliveryOrderRequiresNewJTA12SharedService {

    @Inject
    DeliveryOrderRepository deliveryOrderRepository;

    @Override
    public void insert(List<DeliveryOrder> insertOrderList) {
        for (DeliveryOrder insertOrder : insertOrderList) {
            deliveryOrderRepository.insert(insertOrder);
        }
        return;
    }

}
