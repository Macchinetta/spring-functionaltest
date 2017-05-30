/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.excn;

import jp.co.ntt.fw.spring.functionaltest.domain.model.Stock;

public interface StockService {

    Stock findOne(String itemCode);

    Stock buy(Stock stock, int purchasingQuantity, long sleepMillis);

    Stock buyWithOptimisticLock(Stock stock, int purchasingQuantity,
            long sleepMillis);

    Stock buyWithOptimisticLockByHiddenVersion(Stock stock,
            int purchasingQuantity, long sleepMillis);

    Stock buyWithPessimisticLock(Stock stock, int purchasingQuantity,
            long sleepMillis);

    Stock saleWithOptimisticLock(Stock stock, int purchasingQuantity,
            long sleepMillis);

    Stock saleWithPessimisticLock(Stock stock, int purchasingQuantity,
            long sleepMillis);
}
