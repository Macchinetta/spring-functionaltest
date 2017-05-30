/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.repository.djpa;

import java.util.List;

import jp.co.ntt.fw.spring.functionaltest.domain.model.JPAOrder;
import jp.co.ntt.fw.spring.functionaltest.domain.model.OrderSummary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JPAOrderRepository extends JpaRepository<JPAOrder, Integer> {

    @Query("SELECT a FROM JPAOrder a" + " INNER JOIN FETCH a.orderItem"
            + " WHERE a.orderStatus.statusName = :statusName"
            + " ORDER BY a.orderId DESC")
    List<JPAOrder> findAllByOrderStatus(@Param("statusName") String statusName);

    @Query("SELECT NEW jp.co.ntt.fw.spring.functionaltest.domain.model."
            + "OrderSummary(o.orderId, SUM(i.itemPrice*oi.quantity),o.orderMemo,o.orderStatus.statusName,o.orderStatus.orderStatusCode)"
            + " FROM JPAOrder o LEFT JOIN o.orderItem oi LEFT JOIN oi.orderItem i"
            + " GROUP BY o.id,o.orderStatus.statusName,o.orderStatus.orderStatusCode,o.orderMemo ORDER BY o.id DESC")
    List<OrderSummary> findOrderSummaries();

}
