/*
 * Copyright(c) 2024 NTT Corporation.
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

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.inject.Inject;
import jp.co.ntt.fw.spring.functionaltest.domain.model.DeliveryOrder;
import jp.co.ntt.fw.spring.functionaltest.domain.model.DeliveryType;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.dmly.DeliveryOrderRepository;

@Transactional
@Service
public class DeliveryOrderInitializerServiceImpl implements
                                                 DeliveryOrderInitializerService {

    @Inject
    DeliveryOrderRepository deliveryOrderRepository;

    @Override
    public void init() {
        deliveryOrderRepository.clear();

        DeliveryOrder insertOrder = null;

        insertOrder = new DeliveryOrder();
        insertOrder.setDeliveryNo(1);
        insertOrder.setDeliveryType(new DeliveryType(1, "通常"));
        insertOrder.setSenderName("送り主名1");
        insertOrder.setSenderAddress("送り主住所1");
        insertOrder.setRecieverName("送り先名1");
        insertOrder.setRecieverAddress("送り先住所1");
        insertOrder.setAcceptDatetime(LocalDateTime.of(2014, 1, 1, 1, 1, 1));
        insertOrder.setCompletionDatetime(null);
        insertOrder.setDeliveryDriver("ドライバー1");
        insertOrder.setDeliveryStatus("受付");
        deliveryOrderRepository.insert(insertOrder);

        insertOrder = new DeliveryOrder();
        insertOrder.setDeliveryNo(2);
        insertOrder.setDeliveryType(new DeliveryType(1, "通常"));
        insertOrder.setSenderName("送り主名2");
        insertOrder.setSenderAddress("送り主住所2");
        insertOrder.setRecieverName("送り先名2");
        insertOrder.setRecieverAddress("送り先住所2");
        insertOrder.setAcceptDatetime(LocalDateTime.of(2014, 1, 2, 2, 2, 2));
        insertOrder.setCompletionDatetime(null);
        insertOrder.setDeliveryDriver("ドライバー2");
        insertOrder.setDeliveryStatus("受付");
        deliveryOrderRepository.insert(insertOrder);

        insertOrder = new DeliveryOrder();
        insertOrder.setDeliveryNo(3);
        insertOrder.setDeliveryType(new DeliveryType(1, "通常"));
        insertOrder.setSenderName("送り主名3");
        insertOrder.setSenderAddress("送り主住所3");
        insertOrder.setRecieverName("送り先名3");
        insertOrder.setRecieverAddress("送り先住所3");
        insertOrder.setAcceptDatetime(LocalDateTime.of(2014, 1, 3, 3, 3, 3));
        insertOrder.setCompletionDatetime(null);
        insertOrder.setDeliveryDriver("ドライバー3");
        insertOrder.setDeliveryStatus("受付");
        deliveryOrderRepository.insert(insertOrder);

        insertOrder = new DeliveryOrder();
        insertOrder.setDeliveryNo(4);
        insertOrder.setDeliveryType(new DeliveryType(1, "通常"));
        insertOrder.setSenderName("送り主名4");
        insertOrder.setSenderAddress("送り主住所4");
        insertOrder.setRecieverName("送り先名4");
        insertOrder.setRecieverAddress("送り先住所4");
        insertOrder.setAcceptDatetime(LocalDateTime.of(2014, 1, 4, 4, 4, 4));
        insertOrder.setCompletionDatetime(null);
        insertOrder.setDeliveryDriver("ドライバー4");
        insertOrder.setDeliveryStatus("受付");
        deliveryOrderRepository.insert(insertOrder);

        insertOrder = new DeliveryOrder();
        insertOrder.setDeliveryNo(5);
        insertOrder.setDeliveryType(new DeliveryType(1, "通常"));
        insertOrder.setSenderName("送り主名5");
        insertOrder.setSenderAddress("送り主住所5");
        insertOrder.setRecieverName("送り先名5");
        insertOrder.setRecieverAddress("送り先住所5");
        insertOrder.setAcceptDatetime(LocalDateTime.of(2014, 1, 5, 5, 5, 5));
        insertOrder.setCompletionDatetime(null);
        insertOrder.setDeliveryDriver("ドライバー5");
        insertOrder.setDeliveryStatus("受付");
        deliveryOrderRepository.insert(insertOrder);
    }

}
