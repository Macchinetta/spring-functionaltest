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
package jp.co.ntt.fw.spring.functionaltest.domain.repository.djpa;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import jp.co.ntt.fw.spring.functionaltest.domain.model.JPAOrder;
import jp.co.ntt.fw.spring.functionaltest.domain.model.OrderSummary;

public interface JPAOrderRepository extends JpaRepository<JPAOrder, Integer> {

    @Query("SELECT a FROM JPAOrder a" + " INNER JOIN FETCH a.orderItem"
            + " WHERE a.orderStatus.statusName = :statusName" + " ORDER BY a.orderId DESC")
    List<JPAOrder> findAllByOrderStatus(@Param("statusName") String statusName);

    @Query("SELECT NEW jp.co.ntt.fw.spring.functionaltest.domain.model."
            + "OrderSummary(o.orderId, SUM(i.itemPrice*oi.quantity),o.orderMemo,o.orderStatus.statusName,o.orderStatus.orderStatusCode)"
            + " FROM JPAOrder o LEFT JOIN o.orderItem oi LEFT JOIN oi.orderItem i"
            + " GROUP BY o.id,o.orderStatus.statusName,o.orderStatus.orderStatusCode,o.orderMemo ORDER BY o.id DESC")
    List<OrderSummary> findOrderSummaries();

}
