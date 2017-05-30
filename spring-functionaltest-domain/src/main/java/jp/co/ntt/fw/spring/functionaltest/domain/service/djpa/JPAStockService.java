/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.djpa;

import java.util.List;

import jp.co.ntt.fw.spring.functionaltest.domain.model.JPAStock;

public interface JPAStockService {

    JPAStock findOne(String itemCode);

    JPAStock buy(JPAStock stock, int purchasingQuantity, long sleepMillis);

    List<JPAStock> getAll();

    Integer decreamentQty(JPAStock jpaStock, Integer qty);

    JPAStock buyWithOptimisticLock(JPAStock stock, int purchasingQuantity,
            long sleepMillis);

    JPAStock buyWithPessimisticLock(JPAStock stock, int purchasingQuantity,
            long sleepMillis);

    JPAStock buyWithPessimisticLockExcp(JPAStock stock, int purchasingQuantity,
            long sleepMillis);
}
