/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.djpa;

import jp.co.ntt.fw.spring.functionaltest.domain.model.JPAStock;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//@Service
@Transactional(value = "jpaTransactionManager")
public class JPAStockServiceImplForDB2 extends JPAStockServiceImpl {

    @Override
    public JPAStock buyWithPessimisticLockExcp(JPAStock stock,
            int purchasingQuantity, long sleepMillis) {
        jpaStockRepository.setCurrentLockTimeoutNotWait();
        JPAStock jpaStock = jpaStockRepository
                .findOneForUpdateUsingPessimisticLockExcp(stock.getItemCode());
        jpaStockRepository.setCurrentLockTimeoutDefault();
        // 排他制御チェックのため、スレッドを1秒ウエイトする
        this.sleep(sleepMillis);

        jpaStock.setQuantity(jpaStock.getQuantity() - purchasingQuantity);
        jpaStockRepository.saveAndFlush(jpaStock);
        return jpaStock;
    }
}
