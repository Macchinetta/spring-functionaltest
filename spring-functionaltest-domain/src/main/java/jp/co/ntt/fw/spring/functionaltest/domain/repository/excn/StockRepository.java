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
