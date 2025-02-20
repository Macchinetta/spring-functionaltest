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

import jp.co.ntt.fw.spring.functionaltest.domain.model.JPAItem;
import jp.co.ntt.fw.spring.functionaltest.domain.model.JPAOrder;
import jp.co.ntt.fw.spring.functionaltest.domain.model.JPAOrderForCmnConditionNoBoolean;
import jp.co.ntt.fw.spring.functionaltest.domain.model.JPAOrderForCommonCondition;
import jp.co.ntt.fw.spring.functionaltest.domain.model.OrderSummary;

public interface JPAOrderService {

    JPAOrder getOrderDetail(Integer orderId);

    List<JPAOrder> getAll();

    JPAOrder placeOrder(List<JPAItem> itemList, String orderMemo);

    JPAOrder addtoOrder(List<JPAItem> itemList, Integer orderId, String comment);

    JPAOrder updateStatus(Integer orderId, String orderStatus);

    JPAOrder updateOrderItem(Integer orderId, Integer itemNum, Integer newQty);

    void delete(Integer orderId);

    void deleteItem(Integer orderId, Integer itemNum);

    List<JPAOrder> joinFetch(String statusName);

    List<OrderSummary> storeFetchResultInCustomObject();

    JPAOrderForCommonCondition findOrderDeatilUsingCommonConditionOnEntity(Integer orderId);

    JPAOrderForCmnConditionNoBoolean findOrderDeatilUsingCommonConditionOnEntityWithNoBooleanSupport(
            Integer orderId);

}
