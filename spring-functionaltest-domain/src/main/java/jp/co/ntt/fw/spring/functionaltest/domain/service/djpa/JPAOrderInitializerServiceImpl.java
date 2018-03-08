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
package jp.co.ntt.fw.spring.functionaltest.domain.service.djpa;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import jp.co.ntt.fw.spring.functionaltest.domain.model.JPAItem;
import jp.co.ntt.fw.spring.functionaltest.domain.model.JPAOrder;
import jp.co.ntt.fw.spring.functionaltest.domain.model.JPAOrderItem;
import jp.co.ntt.fw.spring.functionaltest.domain.model.JPAOrderStatus;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.djpa.JPAOrderRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(value = "jpaTransactionManager")
public class JPAOrderInitializerServiceImpl implements
                                            JPAOrderInitializerService {

    @Inject
    JPAOrderRepository jpaOrderRepository;

    @Override
    public void initByBatch() {

        List<JPAOrder> ordeList = new ArrayList<JPAOrder>();
        JPAOrderStatus jpaOrderStatus = null;
        List<JPAOrderItem> orderItemList = null;
        JPAItem orderItem = null;
        JPAOrderItem jpaOrderItem = null;
        JPAOrder jpaOrder = null;

        // order 1
        jpaOrder = new JPAOrder();
        jpaOrderStatus = new JPAOrderStatus();
        jpaOrderStatus.setOrderStatusCode("accepted");
        // jpaOrderStatus.setStatusName("Order accepted");
        orderItemList = new ArrayList<JPAOrderItem>();
        orderItem = new JPAItem();
        orderItem.setItemCode("ITM0000001");
        jpaOrderItem = new JPAOrderItem();
        jpaOrderItem.setLogicalDelete(false);
        int qty = 5;
        int rate = 100;
        jpaOrderItem.setQuantity(qty);
        jpaOrderItem.setOrderItem(orderItem);
        orderItemList.add(jpaOrderItem);
        jpaOrder.setOrderMemo("dummy comment1");
        jpaOrder.setOrderStatus(jpaOrderStatus);
        jpaOrder.setOrderItem(orderItemList);
        jpaOrder.setOrderAmount(qty * rate);
        ordeList.add(jpaOrder);

        // order 2
        jpaOrder = new JPAOrder();
        jpaOrderStatus = new JPAOrderStatus();
        jpaOrderStatus.setOrderStatusCode("checking");
        // jpaOrderStatus.setStatusName("Order accepted");
        orderItemList = new ArrayList<JPAOrderItem>();
        orderItem = new JPAItem();
        orderItem.setItemCode("ITM0000002");
        jpaOrderItem = new JPAOrderItem();
        jpaOrderItem.setLogicalDelete(false);
        qty = 2;
        rate = 1000;
        jpaOrderItem.setQuantity(qty);
        jpaOrderItem.setOrderItem(orderItem);
        orderItemList.add(jpaOrderItem);
        jpaOrder.setOrderMemo("dummy comment2");
        jpaOrder.setOrderStatus(jpaOrderStatus);
        jpaOrder.setOrderItem(orderItemList);
        jpaOrder.setOrderAmount(qty * rate);
        ordeList.add(jpaOrder);

        jpaOrderRepository.save(ordeList);

    }

    @Override
    public void clear() {
        jpaOrderRepository.deleteAllInBatch();

    }

}
