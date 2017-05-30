/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.repository.excn;

import jp.co.ntt.fw.spring.functionaltest.domain.model.Stock;

public interface StockRepository {

    /**
     * 単一検索<br>
     * <p>
     * itemCodeからレコードを検索
     * </p>
     * @param itemCode
     * @return
     */
    Stock selectByItemCode(String itemCode);

    /**
     * 単一検索（悲観的ロック）<br>
     * <p>
     * itemCodeからレコードを検索する際、FOR UPDATEで行ロック
     * </p>
     * @param itemCode
     * @return
     */
    Stock selectByItemCodeWithPessimisticLock(String itemCode);

    /**
     * 数量更新<br>
     * <p>
     * itemCodeから、レコードの数量を更新
     * </p>
     * @param stock
     * @return
     */
    int updateQuantity(Stock stock);

    /**
     * 数量更新（RDBMSロック）<br>
     * <p>
     * itemCodeとquantityから、レコードの数量を更新
     * </p>
     * @param stock
     * @return
     */
    int updateQuantityWithRDBMSLock(Stock stock);

    /**
     * 数量更新（楽観的ロック）<br>
     * <p>
     * itemCodeとversionから、レコードの数量を更新する際、versionをインクリメント
     * </p>
     * @param stock
     * @return
     */
    int updateQuantityWithOptimisticLock(Stock stock);

    /**
     * タイムアウト値をゼロにする（DB2用）
     * @return
     */
    int setCurrentLockTimeoutNotWait();

    /**
     * タイムアウト値をデフォルト値に戻す（DB2用）
     * @return
     */
    int setCurrentLockTimeoutDefault();

}
