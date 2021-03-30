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

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.ntt.fw.spring.functionaltest.domain.model.JPAItem;
import jp.co.ntt.fw.spring.functionaltest.domain.model.JPAOrderItem;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.djpa.JPAOrderItemRepository;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.djpa.JPAOrderRepository;

@Service
public class JPAOrderItemServiceImpl implements JPAOrderItemService {

    @Inject
    JPAOrderItemRepository jpaOrderItemRepository;

    @Inject
    JPAOrderRepository jpaOrderRepository;

    @Override
    public JPAOrderItem addItemToOrder(List<JPAItem> itemList) {
        JPAItem jpaItem = itemList.get(0);
        JPAOrderItem jpaOrderItem = new JPAOrderItem();

        // Adding item directly to order with order id 2.
        jpaOrderItem.setOrderId(2);
        jpaOrderItem.setOrderItem(jpaItem);
        jpaOrderItem.setQuantity(jpaItem.getQuantity());
        return jpaOrderItemRepository.save(jpaOrderItem);
    }

    @Override
    public JPAOrderItem getOrderItemDetail(Integer orderId,
            Integer itemNumber) {
        return jpaOrderItemRepository.findById(itemNumber).orElse(null);
    }

    @Override
    @Transactional(value = "jpaTransactionManager")
    public JPAOrderItem updateOrderItem(Integer orderItemId,
            Integer newQuantity) {
        JPAOrderItem jpaOrderItem = jpaOrderItemRepository.findById(orderItemId)
                .orElse(null);
        jpaOrderItem.setQuantity(newQuantity);
        return jpaOrderItem;
    }

    @Override
    @Transactional(value = "jpaTransactionManager")
    public JPAOrderItem updateUsingQueryMethod(Integer orderId) {
        // getting the item#1 from the given orderId under entityManager
        Integer itemNum = 1;
        JPAOrderItem jpaOrderItem = jpaOrderItemRepository.findById(itemNum)
                .orElse(null);

        jpaOrderItemRepository.updateByQueryNoClear(orderId);

        // Once again getting the same item#1 from the given orderId to check the state
        jpaOrderItem = jpaOrderItemRepository.findById(itemNum).orElse(null);
        return jpaOrderItem;
    }

    @Override
    @Transactional(value = "jpaTransactionManager")
    public int updateUsingQueryMethodErr(Integer orderId) {
        return jpaOrderItemRepository.updateToLogicalDeleteNoModifyingAnnot(
                orderId);
    }

    @Override
    @Transactional(value = "jpaTransactionManager")
    public JPAOrderItem updateUsingQueryMethodAndClear(Integer orderId) {
        // getting the item#1 from the given orderId under entityManager
        Integer itemNum = 1;
        JPAOrderItem jpaOrderItem = jpaOrderItemRepository.findById(itemNum)
                .orElse(null);

        jpaOrderItemRepository.updateByQueryWithClear(orderId);

        // Once again getting the same item#1 from the given orderId to check
        // the state
        jpaOrderItem = jpaOrderItemRepository.findById(itemNum).orElse(null);
        return jpaOrderItem;
    }

    @Override
    @Transactional(value = "jpaTransactionManager")
    public void directDeleteOrderItem(Integer orderItemId, Integer orderId) {

        JPAOrderItem jpaOrderItem = new JPAOrderItem();
        jpaOrderItem.setItemNumber(orderItemId);
        jpaOrderItem.setOrderId(orderId);

        jpaOrderItemRepository.delete(jpaOrderItem);
    }

    @Override
    @Transactional(value = "jpaTransactionManager")
    public void deleteOrderItemNoSuccess(Integer orderItemId, Integer orderId) {

        JPAOrderItem jpaOrderItem = jpaOrderItemRepository.getOne(orderItemId);
        // Need to get JpaOrder.
        jpaOrderRepository.findById(orderId);
        jpaOrderItemRepository.delete(jpaOrderItem);
    }

    @Override
    @Transactional(value = "jpaTransactionManager")
    public void deleteUsingQueryMethodAndClear(Integer orderId) {

        jpaOrderItemRepository.deleteByQueryWithClear(orderId);

    }

}
