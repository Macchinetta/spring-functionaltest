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
package jp.co.ntt.fw.spring.functionaltest.domain.service.djpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.terasoluna.gfw.common.query.QueryEscapeUtils;

import jakarta.inject.Inject;
import jp.co.ntt.fw.spring.functionaltest.domain.model.JPADeliveryOrder;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.djpa.DeliveryOrderCriteria;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.djpa.JPADeliveryOrderRepository;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.djpa.TDeliveryOrderRepository;

@Service
@Transactional(value = "jpaTransactionManager")
public class JPADeliveryOrderServiceImpl implements JPADeliveryOrderService {

    @Inject
    JPADeliveryOrderRepository jpaDeliveryOrderRepository;

    @Inject
    TDeliveryOrderRepository tDeliveryOrderRepository;

    @Override
    public List<JPADeliveryOrder> searchUsingStatCode(String statusCode) {
        return jpaDeliveryOrderRepository.searchUsingStatCode(statusCode);
    }

    @Override
    public JPADeliveryOrder findById(Integer deliveryNumber) {
        return jpaDeliveryOrderRepository.findById(deliveryNumber).orElse(null);
    }

    @Override
    public Page<JPADeliveryOrder> findByStatusCode(String statusCode,
            Pageable pageable) {
        return jpaDeliveryOrderRepository.findByDeliveryStatus(statusCode,
                pageable);
    }

    @Override
    public List<JPADeliveryOrder> findUsingNativeQuery(String statusCode) {
        return jpaDeliveryOrderRepository.findAllByDeliveryStatus(statusCode);
    }

    @Override
    public JPADeliveryOrder save(JPADeliveryOrder jpaDeliveryOrder) {
        return jpaDeliveryOrderRepository.save(jpaDeliveryOrder);
    }

    @Override
    public void delete(Integer id) {
        jpaDeliveryOrderRepository.deleteById(id);

    }

    @Override
    public List<JPADeliveryOrder> queryHint(String statusCode,
            Integer sleepTime) {
        List<JPADeliveryOrder> orderList = jpaDeliveryOrderRepository
                .getByDeliveryStatus(statusCode);
        return orderList;
    }

    @Override
    public List<JPADeliveryOrder> forwardSearch(String senderAddress) {
        List<JPADeliveryOrder> orderList = jpaDeliveryOrderRepository
                .forwardSearch(senderAddress);
        return orderList;
    }

    @Override
    public List<JPADeliveryOrder> backwardSearch(String receiverAddress) {
        List<JPADeliveryOrder> orderList = jpaDeliveryOrderRepository
                .backwardSearch(receiverAddress);
        return orderList;
    }

    @Override
    public List<JPADeliveryOrder> partialSearch(String senderName) {
        List<JPADeliveryOrder> orderList = jpaDeliveryOrderRepository
                .partialSearch(senderName);
        return orderList;
    }

    @Override
    public List<JPADeliveryOrder> searchUsingGiveSearchType(String searchVal,
            String serachCriteria[]) {
        List<JPADeliveryOrder> orderList = null;
        if (serachCriteria[0].equals("senderAddress") && serachCriteria[1]
                .equals("FW")) {
            orderList = forwardSearch(searchVal);
        } else if (serachCriteria[0].equals("receiverAddress")
                && serachCriteria[1].equals("BK")) {
            orderList = backwardSearch(searchVal);
        } else if (serachCriteria[0].equals("senderName") && serachCriteria[1]
                .equals("PT")) {
            orderList = partialSearch(searchVal);
        }
        return orderList;
    }

    @Override
    public List<JPADeliveryOrder> searchUsingDynamicCond(
            DeliveryOrderCriteria deliveryOrderCriteria) {
        return tDeliveryOrderRepository.findAllByCriteria(
                deliveryOrderCriteria);
    }

    @Override
    public Page<JPADeliveryOrder> findEntityPageMatchingCondition(
            String delStatus, Pageable pageable) {
        return jpaDeliveryOrderRepository
                .findEntityPageMatchingConditionByDeliveryStatus(delStatus,
                        pageable);
    }

    @Override
    public Page<JPADeliveryOrder> findByEscapeSearchMod(String keyword,
            Pageable pageable) {
        String escapedWord = QueryEscapeUtils.toLikeCondition(keyword);
        return jpaDeliveryOrderRepository.findPageByMod(escapedWord, pageable);
    }

    @Override
    public Page<JPADeliveryOrder> findByEscapeSearchDash(String keyword,
            Pageable pageable) {
        return jpaDeliveryOrderRepository.findPageByDash(keyword, pageable);
    }

    @Override
    public Page<JPADeliveryOrder> findByEscapeSearchMatchInLogic(String keyword,
            Pageable pageable) {
        String escapedWord = QueryEscapeUtils.toContainingCondition(keyword);
        return jpaDeliveryOrderRepository.findPageByMatchTypeInLogic(
                escapedWord, pageable);
    }

    @Override
    public Page<JPADeliveryOrder> search(DeliveryOrderCriteria criteria,
            Pageable pageable) {
        return jpaDeliveryOrderRepository.findByCriteria(criteria, pageable);
    }

}
