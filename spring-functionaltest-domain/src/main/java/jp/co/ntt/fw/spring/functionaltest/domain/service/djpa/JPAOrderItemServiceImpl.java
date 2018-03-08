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

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.ntt.fw.spring.functionaltest.domain.model.JPAItem;
import jp.co.ntt.fw.spring.functionaltest.domain.model.JPAOrder;
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
        return jpaOrderItemRepository.findOne(itemNumber);
    }

    @Override
    @Transactional(value = "jpaTransactionManager")
    public JPAOrderItem updateOrderItem(Integer orderItemId,
            Integer newQuantity) {
        JPAOrderItem jpaOrderItem = jpaOrderItemRepository.findOne(orderItemId);
        jpaOrderItem.setQuantity(newQuantity);
        return jpaOrderItem;
    }

    @Override
    @Transactional(value = "jpaTransactionManager")
    public JPAOrderItem updateUsingQueryMethod(Integer orderId) {
        // getting the item#1 from the given orderId under entityManager
        Integer itemNum = 1;
        JPAOrderItem jpaOrderItem = jpaOrderItemRepository.findOne(itemNum);

        jpaOrderItemRepository.updateByQueryNoClear(orderId);

        // Once again getting the same item#1 from the given orderId to check the state
        jpaOrderItem = jpaOrderItemRepository.findOne(itemNum);
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
        JPAOrderItem jpaOrderItem = jpaOrderItemRepository.findOne(itemNum);

        jpaOrderItemRepository.updateByQueryWithClear(orderId);

        // Once again getting the same item#1 from the given orderId to check
        // the state
        jpaOrderItem = jpaOrderItemRepository.findOne(itemNum);
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

        JPAOrder jpaOrder = jpaOrderRepository.findOne(orderId);

        jpaOrderItemRepository.delete(jpaOrderItem);
    }

    @Override
    @Transactional(value = "jpaTransactionManager")
    public void deleteUsingQueryMethodAndClear(Integer orderId) {

        jpaOrderItemRepository.deleteByQueryWithClear(orderId);

    }

}
