/*
 * Copyright(c) 2014 NTT Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.dmly;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.IllegalTransactionStateException;

import jp.co.ntt.fw.spring.functionaltest.domain.model.DeliveryOrder;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.dmly.DeliveryOrderRepository;

@Transactional(value = Transactional.TxType.REQUIRED)
@Service
public class DeliveryOrderPropagationJTA12ServiceImpl implements
                                                      DeliveryOrderPropagationJTA12Service {

    private static final Logger logger = LoggerFactory.getLogger(
            DeliveryOrderPropagationJTA12ServiceImpl.class);

    @Inject
    DeliveryOrderRepository deliveryOrderRepository;

    @Inject
    DeliveryOrderRequiredJTA12SharedService deliveryOrderRequiredJTA12SharedService;

    @Inject
    DeliveryOrderRequiresNewJTA12SharedService deliveryOrderRequiresNewJTA12SharedService;

    @Inject
    DeliveryOrderSupportsJTA12SharedService deliveryOrderSupportsJTA12SharedService;

    @Inject
    DeliveryOrderNotSupportedJTA12SharedService deliveryOrderNotSupportedJTA12SharedService;

    @Inject
    DeliveryOrderMandatoryJTA12SharedService deliveryOrderMandatoryJTA12SharedService;

    @Inject
    DeliveryOrderNeverJTA12SharedService deliveryOrderNeverJTA12SharedService;

    @Override
    public void propagateRequiredRequired(List<DeliveryOrder> firstOrderList,
            List<DeliveryOrder> secondOrderList) {
        for (DeliveryOrder firstOrder : firstOrderList) {
            deliveryOrderRepository.insert(firstOrder);
        }
        deliveryOrderRequiredJTA12SharedService.insert(secondOrderList);
    }

    @Override
    public void propagateRequiredRequiresNew(List<DeliveryOrder> firstOrderList,
            List<DeliveryOrder> secondOrderList) {
        try {
            for (DeliveryOrder firstOrder : firstOrderList) {
                deliveryOrderRepository.insert(firstOrder);
            }
            deliveryOrderRequiresNewJTA12SharedService.insert(secondOrderList);
        } catch (DataAccessException e) {
            logger.error("PropagationService exception catch.", e);
        } catch (IllegalTransactionStateException e) {
            logger.error("PropagationService exception catch.", e);
        }
    }

    @Override
    public void propagateRequiredRequiresNew2(
            List<DeliveryOrder> firstOrderList,
            List<DeliveryOrder> secondOrderList) {
        int idx = 0;
        int maxNum = firstOrderList.size();

        for (idx = 0; idx < maxNum - 1; idx++) {
            deliveryOrderRepository.insert(firstOrderList.get(idx));
        }
        deliveryOrderRequiresNewJTA12SharedService.insert(secondOrderList);

        deliveryOrderRepository.insert(firstOrderList.get(maxNum - 1));
    }

    @Override
    public void propagateRequiredSupports(List<DeliveryOrder> firstOrderList,
            List<DeliveryOrder> secondOrderList) {
        for (DeliveryOrder firstOrder : firstOrderList) {
            deliveryOrderRepository.insert(firstOrder);
        }
        deliveryOrderSupportsJTA12SharedService.insert(secondOrderList);
    }

    @Override
    public void propagateRequiredNotSupported(
            List<DeliveryOrder> firstOrderList,
            List<DeliveryOrder> secondOrderList) {
        try {
            for (DeliveryOrder firstOrder : firstOrderList) {
                deliveryOrderRepository.insert(firstOrder);
            }
            deliveryOrderNotSupportedJTA12SharedService.insert(secondOrderList);
        } catch (DataAccessException e) {
            logger.error("PropagationService exception catch.", e);
        } catch (IllegalTransactionStateException e) {
            logger.error("PropagationService exception catch.", e);
        }
    }

    @Override
    public void propagateRequiredMandatory(List<DeliveryOrder> firstOrderList,
            List<DeliveryOrder> secondOrderList) {
        for (DeliveryOrder firstOrder : firstOrderList) {
            deliveryOrderRepository.insert(firstOrder);
        }
        deliveryOrderMandatoryJTA12SharedService.insert(secondOrderList);
    }

    @Override
    public void propagateRequiredNever(List<DeliveryOrder> firstOrderList,
            List<DeliveryOrder> secondOrderList) {
        try {
            for (DeliveryOrder firstOrder : firstOrderList) {
                deliveryOrderRepository.insert(firstOrder);
            }
            deliveryOrderNeverJTA12SharedService.insert(secondOrderList);
        } catch (DataAccessException e) {
            logger.error("PropagationService exception catch.", e);
        } catch (IllegalTransactionStateException e) {
            logger.error("PropagationService exception catch.", e);
        }
    }
}
