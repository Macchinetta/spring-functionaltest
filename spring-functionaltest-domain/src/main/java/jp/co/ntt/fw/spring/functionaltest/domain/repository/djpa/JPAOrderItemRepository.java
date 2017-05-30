/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.repository.djpa;

import java.util.List;

import jp.co.ntt.fw.spring.functionaltest.domain.model.JPAOrderItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JPAOrderItemRepository extends
                                       JpaRepository<JPAOrderItem, Integer> {

    /**
     * Updating the flag to true for given orderId.
     * @param orderId
     * @return
     */
    @Modifying
    @Query("UPDATE JPAOrderItem oi SET oi.logicalDelete = true WHERE oi.orderId = :orderId ")
    int updateByQueryNoClear(@Param("orderId") Integer orderId);

    /**
     * This is error scenario as no @Modifying annotation is specified.
     * @param orderId
     * @return
     */
    @Query("UPDATE JPAOrderItem oi SET oi.logicalDelete = true WHERE oi.orderId = :orderId ")
    int updateToLogicalDeleteNoModifyingAnnot(@Param("orderId") Integer orderId);

    List<JPAOrderItem> findByOrderId(Integer orderId);

    /**
     * Updating the flag to false for given orderId.
     * @param orderId
     * @return
     */
    @Modifying(clearAutomatically = true)
    @Query("UPDATE JPAOrderItem oi SET oi.logicalDelete = false WHERE oi.orderId = :orderId ")
    int updateByQueryWithClear(@Param("orderId") Integer orderId);

    /**
     * delete
     * @param orderId
     * @return
     */
    @Modifying(clearAutomatically = true)
    @Query("DELETE FROM JPAOrderItem oi WHERE oi.orderId = :orderId ")
    int deleteByQueryWithClear(@Param("orderId") Integer orderId);

}
