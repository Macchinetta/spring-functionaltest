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
package jp.co.ntt.fw.spring.functionaltest.domain.repository.djpa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import jp.co.ntt.fw.spring.functionaltest.domain.model.JPADeliveryOrder;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.util.StringUtils;
import org.terasoluna.gfw.common.query.QueryEscapeUtils;

public class TDeliveryOrderRepositoryImpl implements
                                          TDeliveryOrderRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<JPADeliveryOrder> findAllByCriteria(
            DeliveryOrderCriteria criteria) {
        final List<String> andConditions = new ArrayList<String>();
        final List<String> joinConditions = new ArrayList<String>();
        final Map<String, Object> bindParameters = new HashMap<String, Object>();

        // (7)
        if (criteria.getDeliveryNumber() != null) {
            andConditions.add("o.deliverNumber = :deliverNumber");
            bindParameters.put("deliverNumber", criteria.getDeliveryNumber());
        }
        if (!CollectionUtils.isEmpty(criteria.getDeliveryStatus())) {
            andConditions.add("o.deliveryStatus IN :statusCodes");
            bindParameters.put("statusCodes", criteria.getDeliveryStatus());
        }
        if (StringUtils.hasLength(criteria.getDeliveryType())) {
            joinConditions.add("o.jpaDeliveryType dt");
            andConditions.add("dt.deliveryTypeName LIKE :delType ESCAPE '~'");
            bindParameters.put("delType", QueryEscapeUtils.toLikeCondition(
                    criteria.getDeliveryType()));
        }

        // (8)
        if (andConditions.isEmpty()) {
            return Collections.emptyList();
        }

        // (9)
        // Create dynamic query.
        final StringBuilder queryString = new StringBuilder();

        // (10)
        queryString.append("SELECT o FROM JPADeliveryOrder o");

        // (11)
        // add join conditions.
        for (String joinCondition : joinConditions) {
            queryString.append(" LEFT JOIN ").append(joinCondition);
        }
        // add conditions.
        Iterator<String> andConditionsIt = andConditions.iterator();
        if (andConditionsIt.hasNext()) {
            queryString.append(" WHERE ").append(andConditionsIt.next());
        }
        while (andConditionsIt.hasNext()) {
            queryString.append(" AND ").append(andConditionsIt.next());
        }

        // (12)
        // add order by condition.
        queryString.append(" ORDER BY o.id");

        // (13)
        // Create typed query.
        final TypedQuery<JPADeliveryOrder> findQuery = entityManager
                .createQuery(queryString.toString(), JPADeliveryOrder.class);
        // Bind parameters.
        for (Map.Entry<String, Object> bindParameter : bindParameters
                .entrySet()) {
            findQuery.setParameter(bindParameter.getKey(), bindParameter
                    .getValue());
        }

        // (14)
        // Execute query.
        return findQuery.getResultList();
    }

}
