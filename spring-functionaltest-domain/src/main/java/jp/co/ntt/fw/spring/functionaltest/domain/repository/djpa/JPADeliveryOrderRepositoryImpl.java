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
package jp.co.ntt.fw.spring.functionaltest.domain.repository.djpa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import jp.co.ntt.fw.spring.functionaltest.domain.model.JPADeliveryOrder;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public class JPADeliveryOrderRepositoryImpl implements
                                            JPADeliveryOrderRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Page<JPADeliveryOrder> findByCriteria(DeliveryOrderCriteria criteria,
            Pageable pageable) {
        final StringBuilder queryString = new StringBuilder();
        final Map<String, Object> bindParameters = new HashMap<String, Object>();

        queryString.append("SELECT o FROM JPADeliveryOrder o");
        queryString.append(" WHERE o.deliveryStatus IN :deliveryStatus");
        queryString.append(" ORDER BY o.deliverNumber");
        bindParameters.put("deliveryStatus", criteria.getDeliveryStatus());

        final TypedQuery<JPADeliveryOrder> findQuery = entityManager
                .createQuery(queryString.toString(), JPADeliveryOrder.class);
        // Bind parameters.
        for (Map.Entry<String, Object> bindParameter : bindParameters
                .entrySet()) {
            findQuery.setParameter(bindParameter.getKey(), bindParameter
                    .getValue());
        }

        List<JPADeliveryOrder> jpaOrders = findQuery.getResultList();
        return new PageImpl<JPADeliveryOrder>(jpaOrders, pageable, jpaOrders
                .size());
    }

}
