/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.dmly;

import javax.inject.Inject;

import jp.co.ntt.fw.spring.functionaltest.domain.repository.dmly.DeliveryOrderCriteria;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.dmly.DeliveryOrderRepository;

import org.springframework.stereotype.Service;

@Service
public class DeliveryOrderUpdateBLogicImpl
                                          extends
                                          AbstractTransactionalBLogic<DeliveryOrderCriteria, Long>
                                                                                                  implements
                                                                                                  DeliveryOrderUpdateBLogic {

    @Inject
    DeliveryOrderRepository deliveryOrderRepository;

    protected Long doExecute(DeliveryOrderCriteria input) {
        long count = deliveryOrderRepository.countByCriteria(input);
        if (count > 0) {
            deliveryOrderRepository.updateByCriteria(input);
        }

        return count;
    }
}
