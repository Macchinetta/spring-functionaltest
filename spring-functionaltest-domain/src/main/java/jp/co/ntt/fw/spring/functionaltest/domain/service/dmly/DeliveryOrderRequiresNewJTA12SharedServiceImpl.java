/*
 * Copyright 2014-2017 NTT Corporation.
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
 *
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
