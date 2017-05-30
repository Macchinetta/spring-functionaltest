/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.repository.djpa;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;

import jp.co.ntt.fw.spring.functionaltest.domain.model.JPAStock;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;

public interface JPAStockRepository extends JpaRepository<JPAStock, String> {
    @Modifying
    @Query("UPDATE JPAStock s" + " SET s.quantity = s.quantity - :quantity"
            + " WHERE s.itemCode = :itemCode" + " AND :quantity <= s.quantity")
    // (1)
    public int decrementQuantity(@Param("itemCode") String itemCode,
            @Param("quantity") int quantity);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT s FROM JPAStock s WHERE s.itemCode = :itemCode")
    JPAStock findOneForUpdateUsingPessimisticLock(
            @Param("itemCode") String itemCode);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT s FROM JPAStock s WHERE s.itemCode = :itemCode")
    @QueryHints(value = { @QueryHint(name = "javax.persistence.lock.timeout", value = "0") })
    JPAStock findOneForUpdateUsingPessimisticLockExcp(
            @Param("itemCode") String itemCode);

    /**
     * タイムアウト値をゼロにする（DB2用）
     * @return
     */
    @Modifying
    @Query(value = "SET CURRENT LOCK TIMEOUT NOT WAIT", nativeQuery = true)
    void setCurrentLockTimeoutNotWait();

    /**
     * タイムアウト値をデフォルト値に戻す（DB2用）
     * @return
     */
    @Modifying
    @Query(value = "SET CURRENT LOCK TIMEOUT NULL", nativeQuery = true)
    void setCurrentLockTimeoutDefault();
}
