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
package jp.co.ntt.fw.spring.functionaltest.domain.service.djpa;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import jp.co.ntt.fw.spring.functionaltest.domain.model.JPAItem;
import jp.co.ntt.fw.spring.functionaltest.domain.model.JPAOrder;
import jp.co.ntt.fw.spring.functionaltest.domain.model.JPAOrderForCmnConditionNoBoolean;
import jp.co.ntt.fw.spring.functionaltest.domain.model.JPAOrderForCommonCondition;
import jp.co.ntt.fw.spring.functionaltest.domain.model.JPAOrderItem;
import jp.co.ntt.fw.spring.functionaltest.domain.model.JPAOrderStatus;
import jp.co.ntt.fw.spring.functionaltest.domain.model.OrderSummary;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.djpa.JPAOrderForCommonConditionNoBooleanRepository;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.djpa.JPAOrderForCommonConditionRepository;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.djpa.JPAOrderRepository;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.djpa.JPAOrderStatusRepository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.terasoluna.gfw.common.exception.ResourceNotFoundException;

@Service
@Transactional(value = "jpaTransactionManager")
public class JPAOrderServiceImpl implements JPAOrderService {

    @Inject
    JPAOrderRepository jpaOrderRepository;

    @Inject
    JPAOrderForCommonConditionRepository jpaOrderForCommonConditionRepository;

    @Inject
    JPAOrderForCommonConditionNoBooleanRepository jpaOrderForCommonConditionNoBooleanRepository;

    @Inject
    JPAOrderStatusRepository jpaOrderStatusRepository;

    @Value("${order.not.available}")
    protected String message;

    @Override
    @Transactional(value = "jpaTransactionManager")
    public JPAOrder getOrderDetail(Integer orderId) {
        JPAOrder jpaOrder = jpaOrderRepository.findOne(orderId);
        if (null == jpaOrder) {
            throw new ResourceNotFoundException(message);
        }
        return jpaOrder;
    }

    @Override
    public List<JPAOrder> getAll() {
        return jpaOrderRepository.findAll();
    }

    @Override
    public JPAOrder placeOrder(List<JPAItem> itemList, String orderMemo) {
        Integer orderAmount = 0;
        List<JPAOrderItem> orderItemList = new ArrayList<JPAOrderItem>();
        for (JPAItem jpaItem : itemList) {
            if (null != jpaItem.getQuantity() && jpaItem.getQuantity() > 0) {
                orderAmount = orderAmount + (jpaItem.getItemPrice() * jpaItem
                        .getQuantity());
            }
        }

        JPAOrder jpaOrder = new JPAOrder();

        jpaOrder.setOrderAmount(orderAmount);
        jpaOrder.setOrderMemo(orderMemo);
        JPAOrderStatus orderStatus = new JPAOrderStatus();
        orderStatus.setOrderStatusCode("accepted");
        orderStatus.setStatusName("Order accepted");
        jpaOrder.setOrderStatus(orderStatus);

        jpaOrder = jpaOrderRepository.save(jpaOrder);

        for (JPAItem jpaItem : itemList) {
            if (null != jpaItem.getQuantity() && jpaItem.getQuantity() > 0) {
                JPAOrderItem jpaOrderItem = new JPAOrderItem();
                jpaOrderItem.setOrderItem(jpaItem);
                jpaOrderItem.setQuantity(jpaItem.getQuantity());
                jpaOrderItem.setOrderId(jpaOrder.getOrderId());
                orderItemList.add(jpaOrderItem);
            }
        }

        jpaOrder.setOrderItem(orderItemList);

        return jpaOrder;
    }

    @Override
    public JPAOrder addtoOrder(List<JPAItem> itemList, Integer orderId,
            String comment) {

        JPAOrder jpaOrder = jpaOrderRepository.findOne(orderId);
        Integer orderAmount = jpaOrder.getOrderAmount();
        List<JPAOrderItem> orderItemList = new ArrayList<JPAOrderItem>();
        for (JPAItem jpaItem : itemList) {
            if (null != jpaItem.getQuantity() && jpaItem.getQuantity() > 0) {
                orderAmount = orderAmount + (jpaItem.getItemPrice() * jpaItem
                        .getQuantity());
            }
        }

        for (JPAItem jpaItem : itemList) {
            if (null != jpaItem.getQuantity() && jpaItem.getQuantity() > 0) {
                JPAOrderItem jpaOrderItem = new JPAOrderItem();
                jpaOrderItem.setOrderItem(jpaItem);
                jpaOrderItem.setQuantity(jpaItem.getQuantity());
                jpaOrderItem.setOrderId(jpaOrder.getOrderId());
                orderItemList.add(jpaOrderItem);
            }
        }
        jpaOrder.getOrderItem().addAll(orderItemList);
        jpaOrder.setOrderAmount(orderAmount);
        jpaOrder.setOrderMemo(jpaOrder.getOrderMemo().concat("\n").concat(
                comment));
        return jpaOrder;
    }

    @Override
    public JPAOrder updateStatus(Integer orderId, String statusName) {
        JPAOrder jpaOrder = jpaOrderRepository.findOne(orderId);
        JPAOrderStatus orderStatus = jpaOrderStatusRepository.findByStatusName(
                statusName);
        jpaOrder.setOrderStatus(orderStatus);
        return jpaOrder;
    }

    @Override
    public JPAOrder updateOrderItem(Integer orderId, Integer itemNum,
            Integer newQty) {
        JPAOrder jpaOrder = jpaOrderRepository.findOne(orderId);
        List<JPAOrderItem> orderItemList = jpaOrder.getOrderItem();
        for (JPAOrderItem jpaOrderItem : orderItemList) {
            if (itemNum.equals(jpaOrderItem.getItemNumber())) {
                Integer initOrderAmt = jpaOrder.getOrderAmount();
                Integer oldQty = jpaOrderItem.getQuantity();
                Integer oldItemAmt = oldQty * jpaOrderItem.getOrderItem()
                        .getItemPrice();
                Integer ordAmtAdj = initOrderAmt - oldItemAmt;
                Integer newOrderAmt = ordAmtAdj + (newQty * jpaOrderItem
                        .getOrderItem().getItemPrice());
                jpaOrderItem.setQuantity(newQty);
                jpaOrder.setOrderAmount(newOrderAmt);
            }
        }
        return jpaOrder;
    }

    @Override
    public void delete(Integer orderId) {
        jpaOrderRepository.delete(orderId);
    }

    @Override
    public void deleteItem(Integer orderId, Integer itemNum) {
        JPAOrder jpaOrder = jpaOrderRepository.findOne(orderId);
        List<JPAOrderItem> orderItems = jpaOrder.getOrderItem();

        List<JPAOrderItem> orderItemsToremove = new ArrayList<JPAOrderItem>();

        for (JPAOrderItem jpaOrderItem : orderItems) {

            if (itemNum.equals(jpaOrderItem.getItemNumber())) {
                orderItemsToremove.add(jpaOrderItem);
            }

        }
        orderItems.removeAll(orderItemsToremove);

        jpaOrder.setOrderItem(orderItems);
        jpaOrder.setOrderAmount(0);
    }

    @Override
    public List<JPAOrder> joinFetch(String statusName) {
        return jpaOrderRepository.findAllByOrderStatus(statusName);
    }

    @Override
    public List<OrderSummary> storeFetchResultInCustomObject() {
        return jpaOrderRepository.findOrderSummaries();
    }

    @Override
    public JPAOrderForCommonCondition findOrderDeatilUsingCommonConditionOnEntity(
            Integer orderId) {
        JPAOrderForCommonCondition jpaOrder = jpaOrderForCommonConditionRepository
                .findOne(orderId);
        if (null == jpaOrder) {
            throw new ResourceNotFoundException(message);
        }
        return jpaOrder;
    }

    @Override
    public JPAOrderForCmnConditionNoBoolean findOrderDeatilUsingCommonConditionOnEntityWithNoBooleanSupport(
            Integer orderId) {
        JPAOrderForCmnConditionNoBoolean jpaOrder = jpaOrderForCommonConditionNoBooleanRepository
                .findOne(orderId);
        if (null == jpaOrder) {
            throw new ResourceNotFoundException(message);
        }
        return jpaOrder;
    }

}
