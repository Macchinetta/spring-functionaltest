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
package jp.co.ntt.fw.spring.functionaltest.domain.service.djpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import jp.co.ntt.fw.spring.functionaltest.domain.model.JPADeliveryOrder;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.djpa.DeliveryOrderCriteria;

public interface JPADeliveryOrderService {

    List<JPADeliveryOrder> searchUsingStatCode(String statusCode);

    JPADeliveryOrder findById(Integer deliveryNumber);

    Page<JPADeliveryOrder> findByStatusCode(String statusCode,
            Pageable pageable);

    List<JPADeliveryOrder> findUsingNativeQuery(String statusCode);

    JPADeliveryOrder save(JPADeliveryOrder jpaDeliveryOrder);

    void delete(Integer id);

    List<JPADeliveryOrder> queryHint(String statusCode, Integer sleepTime);

    List<JPADeliveryOrder> forwardSearch(String senderAddress);

    List<JPADeliveryOrder> backwardSearch(String receiverAddress);

    List<JPADeliveryOrder> partialSearch(String senderName);

    Page<JPADeliveryOrder> findEntityPageMatchingCondition(String delStatus,
            Pageable pageable);

    List<JPADeliveryOrder> searchUsingGiveSearchType(String searchVal,
            String serachCriteria[]);

    List<JPADeliveryOrder> searchUsingDynamicCond(
            DeliveryOrderCriteria deliveryOrderCriteria);

    Page<JPADeliveryOrder> findByEscapeSearchMod(String keyword,
            Pageable pageable);

    Page<JPADeliveryOrder> findByEscapeSearchDash(String keyword,
            Pageable pageable);

    Page<JPADeliveryOrder> findByEscapeSearchMatchInLogic(String keyword,
            Pageable pageable);

    Page<JPADeliveryOrder> search(DeliveryOrderCriteria criteria,
            Pageable pageable);

}
