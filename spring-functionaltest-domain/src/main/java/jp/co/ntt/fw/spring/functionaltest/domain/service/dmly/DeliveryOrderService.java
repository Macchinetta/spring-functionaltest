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

import jp.co.ntt.fw.spring.functionaltest.domain.model.DeliveryOrder;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DeliveryOrderService {

    long count();

    DeliveryOrder getOrder(Integer deliveryNo);

    DeliveryOrder getOrderExists(Integer deliveryNo);

    Page<DeliveryOrder> getOrders(Pageable pageable);

    void register(DeliveryOrder deliveryOrder);

    void update(DeliveryOrder deliveryOrder);

    void delete(Integer deliveryNo);

}
