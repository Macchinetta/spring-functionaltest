/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.excn;

import javax.inject.Inject;

import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.terasoluna.gfw.common.exception.BusinessException;
import org.terasoluna.gfw.common.exception.ResourceNotFoundException;
import org.terasoluna.gfw.common.message.ResultMessages;

import jp.co.ntt.fw.spring.functionaltest.domain.model.Stock;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.excn.StockRepository;

// DB2以外では使用しないので、component-scanさせない
//@Service
@Transactional
// DB2では、`SELECT FOR UPDATE`ステートメントに対する`NOWAIT`オプションが提供されておらず、
// 以下のように分離レベルRS(Repeatable Read)と`CURRENT LOCK TIMEOUT`値の操作で即時タイムアウトを実現する。
//
// `SET CURRENT LOCK TIMEOUT NOT WAIT;`
// `SELECT * FROM table FOR UPDATE WITH RS` * n
// `SET CURRENT LOCK TIMEOUT NULL;`
//
// タイムアウト値についてはステートメント単位ではなくセッション単位での操作となるため、
// 当該セッション中で発行するすべてのステートメントに影響がある点に注意が必要である。
public class StockServiceImplForDB2 extends StockServiceImpl {

    @Override
    public Stock buyWithPessimisticLock(Stock stock, int purchasingQuantity,
            long sleepMillis) {
        // 即時タイムアウトさせるため、タイムアウト値をセット
        stockRepository.setCurrentLockTimeoutNotWait();
        try {
            return super.buyWithPessimisticLock(stock, purchasingQuantity,
                    sleepMillis);
        } finally {
            // タイムアウト値をリセット
            stockRepository.setCurrentLockTimeoutDefault();
        }
    }

    @Override
    public Stock saleWithPessimisticLock(Stock stock, int purchasingQuantity,
            long sleepMillis) {
        // 即時タイムアウトさせるため、タイムアウト値をセット
        stockRepository.setCurrentLockTimeoutNotWait();
        try {
            return super.saleWithPessimisticLock(stock, purchasingQuantity,
                    sleepMillis);
        } finally {
            // タイムアウト値をリセット
            stockRepository.setCurrentLockTimeoutDefault();
        }
    }

}
