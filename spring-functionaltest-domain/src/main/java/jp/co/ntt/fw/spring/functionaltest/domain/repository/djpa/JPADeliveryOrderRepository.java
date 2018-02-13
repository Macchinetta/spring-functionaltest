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

import java.util.List;

import javax.persistence.QueryHint;

import jp.co.ntt.fw.spring.functionaltest.domain.model.JPADeliveryOrder;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;

public interface JPADeliveryOrderRepository extends
                                            JpaRepository<JPADeliveryOrder, Integer>,
                                            JPADeliveryOrderRepositoryCustom {

    @Query("SELECT delOrder FROM JPADeliveryOrder delOrder WHERE "
            + "delOrder.deliveryStatus = :deliveryStatus ORDER BY delOrder.deliverNumber DESC")
    List<JPADeliveryOrder> searchUsingStatCode(
            @Param("deliveryStatus") String statusCode);

    @Query("SELECT delOrder FROM JPADeliveryOrder delOrder WHERE "
            + "delOrder.senderAddress LIKE :senderAddress% ORDER BY delOrder.deliverNumber ASC")
    List<JPADeliveryOrder> forwardSearch(
            @Param("senderAddress") String senderAddress);

    @Query("SELECT delOrder FROM JPADeliveryOrder delOrder WHERE "
            + "delOrder.receiverAddress LIKE %:receiverAddress ORDER BY delOrder.deliverNumber ASC")
    List<JPADeliveryOrder> backwardSearch(
            @Param("receiverAddress") String receiverAddress);

    @Query("SELECT delOrder FROM JPADeliveryOrder delOrder WHERE "
            + "delOrder.senderName LIKE %:senderName% ORDER BY delOrder.deliverNumber ASC")
    List<JPADeliveryOrder> partialSearch(
            @Param("senderName") String senderName);

    Page<JPADeliveryOrder> findByDeliveryStatus(String deliveryStatus,
            Pageable pageable);

    @Query(name = "OrderRepository.findAllByStatusCode", nativeQuery = true)
    List<JPADeliveryOrder> findAllByDeliveryStatus(
            @Param("statusCode") String statusCode);

    @Query("SELECT delOrder FROM JPADeliveryOrder delOrder WHERE "
            + "delOrder.deliveryStatus = :deliveryStatus ORDER BY delOrder.deliverNumber")
    @QueryHints(value = {
            @QueryHint(name = "javax.persistence.query.timeout", value = "1001") })
    List<JPADeliveryOrder> getByDeliveryStatus(
            @Param("deliveryStatus") String statusCode);

    @Query("SELECT delOrder FROM JPADeliveryOrder delOrder WHERE delOrder.deliveryStatus = :deliveryStatus")
    Page<JPADeliveryOrder> findEntityPageMatchingConditionByDeliveryStatus(
            @Param("deliveryStatus") String delStatus, Pageable pageable);

    @Query("SELECT jdo FROM JPADeliveryOrder jdo WHERE"
            + " (jdo.senderAddress LIKE %:word% ESCAPE '~' OR jdo.deliveryDriver LIKE %:word% ESCAPE '~')")
    Page<JPADeliveryOrder> findPageByMod(@Param("word") String word,
            Pageable pageable);

    @Query("SELECT jdo FROM JPADeliveryOrder jdo WHERE"
            + " (jdo.senderAddress LIKE :word ESCAPE '~' OR jdo.deliveryDriver LIKE :word ESCAPE '~')"
            + " ORDER BY jdo.deliverNumber ASC)")
    Page<JPADeliveryOrder> findPageByDash(@Param("word") String word,
            Pageable pageable);

    /*
     * @Query("SELECT jdo FROM JPADeliveryOrder jdo WHERE" +
     * " (jdo.senderAddress LIKE _:word_ ESCAPE '~' OR jdo.deliveryDriver LIKE _:word_ ESCAPE '~')") Page<JPADeliveryOrder>
     * findPageByDash(@Param("word") String word, Pageable pageable);
     */

    @Query("SELECT jdo FROM JPADeliveryOrder jdo WHERE"
            + " (jdo.senderAddress LIKE :word ESCAPE '~' OR jdo.deliveryDriver LIKE :word ESCAPE '~')"
            + " ORDER BY jdo.deliverNumber ASC)")
    Page<JPADeliveryOrder> findPageByMatchTypeInLogic(
            @Param("word") String word, Pageable pageable);

    @Modifying
    @Query("DELETE FROM JPADeliveryOrder jdo WHERE jdo.deliverNumber > :deliverNumber")
    void deleteGreaterThan(@Param("deliverNumber") int deliverNumber);
}
