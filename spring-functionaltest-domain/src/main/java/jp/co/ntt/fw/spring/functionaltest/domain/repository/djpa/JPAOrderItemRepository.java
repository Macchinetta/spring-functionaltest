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
    int updateToLogicalDeleteNoModifyingAnnot(
            @Param("orderId") Integer orderId);

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
