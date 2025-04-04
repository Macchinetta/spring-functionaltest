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

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import jp.co.ntt.fw.spring.functionaltest.domain.model.JPAStock;

public interface JPAStockRepository extends JpaRepository<JPAStock, String> {
    @Modifying
    @Query("UPDATE JPAStock s" + " SET s.quantity = s.quantity - :quantity"
            + " WHERE s.itemCode = :itemCode" + " AND :quantity <= s.quantity")
    // (1)
    public int decrementQuantity(@Param("itemCode") String itemCode,
            @Param("quantity") int quantity);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT s FROM JPAStock s WHERE s.itemCode = :itemCode")
    JPAStock findOneForUpdateUsingPessimisticLock(@Param("itemCode") String itemCode);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT s FROM JPAStock s WHERE s.itemCode = :itemCode")
    @QueryHints(value = {@QueryHint(name = "jakarta.persistence.lock.timeout", value = "0")})
    JPAStock findOneForUpdateUsingPessimisticLockExcp(@Param("itemCode") String itemCode);

}
