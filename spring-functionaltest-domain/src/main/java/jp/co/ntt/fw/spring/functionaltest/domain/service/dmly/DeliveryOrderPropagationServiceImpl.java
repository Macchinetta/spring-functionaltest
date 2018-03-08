/*
 * Copyright 2014-2018 NTT Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.dmly;

import java.util.List;

import javax.inject.Inject;

import jp.co.ntt.fw.spring.functionaltest.domain.model.DeliveryOrder;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.dmly.DeliveryOrderRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.IllegalTransactionStateException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRED)
@Service
public class DeliveryOrderPropagationServiceImpl implements
                                                 DeliveryOrderPropagationService {

    private static final Logger logger = LoggerFactory.getLogger(
            DeliveryOrderPropagationServiceImpl.class);

    @Inject
    DeliveryOrderRepository deliveryOrderRepository;

    @Inject
    DeliveryOrderRequiredSharedService deliveryOrderRequiredSharedService;

    @Inject
    DeliveryOrderRequiresNewSharedService deliveryOrderRequiresNewSharedService;

    @Inject
    DeliveryOrderSupportsSharedService deliveryOrderSupportsSharedService;

    @Inject
    DeliveryOrderNotSupportedSharedService deliveryOrderNotSupportedSharedService;

    @Inject
    DeliveryOrderMandatorySharedService deliveryOrderMandatorySharedService;

    @Inject
    DeliveryOrderNeverSharedService deliveryOrderNeverSharedService;

    @Inject
    DeliveryOrderNestedSharedService deliveryOrderNestedSharedService;

    @Override
    public void propagateRequiredRequired(List<DeliveryOrder> firstOrderList,
            List<DeliveryOrder> secondOrderList) {
        for (DeliveryOrder firstOrder : firstOrderList) {
            deliveryOrderRepository.insert(firstOrder);
        }
        deliveryOrderRequiredSharedService.insert(secondOrderList);
    }

    @Override
    public void propagateRequiredRequiresNew(List<DeliveryOrder> firstOrderList,
            List<DeliveryOrder> secondOrderList) {
        try {
            for (DeliveryOrder firstOrder : firstOrderList) {
                deliveryOrderRepository.insert(firstOrder);
            }
            deliveryOrderRequiresNewSharedService.insert(secondOrderList);
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
        deliveryOrderRequiresNewSharedService.insert(secondOrderList);

        deliveryOrderRepository.insert(firstOrderList.get(maxNum - 1));
    }

    @Override
    public void propagateRequiredSupports(List<DeliveryOrder> firstOrderList,
            List<DeliveryOrder> secondOrderList) {
        for (DeliveryOrder firstOrder : firstOrderList) {
            deliveryOrderRepository.insert(firstOrder);
        }
        deliveryOrderSupportsSharedService.insert(secondOrderList);
    }

    @Override
    public void propagateRequiredNotSupported(
            List<DeliveryOrder> firstOrderList,
            List<DeliveryOrder> secondOrderList) {
        try {
            for (DeliveryOrder firstOrder : firstOrderList) {
                deliveryOrderRepository.insert(firstOrder);
            }
            deliveryOrderNotSupportedSharedService.insert(secondOrderList);
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
        deliveryOrderMandatorySharedService.insert(secondOrderList);
    }

    @Override
    public void propagateRequiredNever(List<DeliveryOrder> firstOrderList,
            List<DeliveryOrder> secondOrderList) {
        try {
            for (DeliveryOrder firstOrder : firstOrderList) {
                deliveryOrderRepository.insert(firstOrder);
            }
            deliveryOrderNeverSharedService.insert(secondOrderList);
        } catch (DataAccessException e) {
            logger.error("PropagationService exception catch.", e);
        } catch (IllegalTransactionStateException e) {
            logger.error("PropagationService exception catch.", e);
        }
    }

    @Override
    public void propagateRequiredNested(List<DeliveryOrder> firstOrderList,
            List<DeliveryOrder> secondOrderList) {
        try {
            for (DeliveryOrder firstOrder : firstOrderList) {
                deliveryOrderRepository.insert(firstOrder);
            }
            deliveryOrderNestedSharedService.insert(secondOrderList);
        } catch (DataAccessException e) {
            logger.error("PropagationService exception catch.", e);
        } catch (IllegalTransactionStateException e) {
            logger.error("PropagationService exception catch.", e);
        }
    }

    @Override
    public void propagateRequiredNested2(List<DeliveryOrder> firstOrderList,
            List<DeliveryOrder> secondOrderList) {
        int idx = 0;
        int maxNum = firstOrderList.size();

        for (idx = 0; idx < maxNum - 1; idx++) {
            deliveryOrderRepository.insert(firstOrderList.get(idx));
        }
        deliveryOrderNestedSharedService.insert(secondOrderList);

        deliveryOrderRepository.insert(firstOrderList.get(maxNum - 1));
    }

    @Override
    public void propagateRequiredNested3(List<DeliveryOrder> firstOrderList,
            List<DeliveryOrder> secondOrderList) {
        int idx = 0;
        int maxNum = firstOrderList.size();

        for (idx = 0; idx < maxNum - 1; idx++) {
            deliveryOrderRepository.insert(firstOrderList.get(idx));
        }
        try {
            deliveryOrderNestedSharedService.insert(secondOrderList);
        } catch (DataAccessException e) {
            logger.error("PropagationService exception catch.", e);
        } catch (IllegalTransactionStateException e) {
            logger.error("PropagationService exception catch.", e);
        }

        deliveryOrderRepository.insert(firstOrderList.get(maxNum - 1));
    }

}
