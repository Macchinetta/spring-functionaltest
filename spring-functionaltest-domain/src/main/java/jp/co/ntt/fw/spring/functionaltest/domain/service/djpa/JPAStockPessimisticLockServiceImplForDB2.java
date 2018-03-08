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
package jp.co.ntt.fw.spring.functionaltest.domain.service.djpa;

import jp.co.ntt.fw.spring.functionaltest.domain.model.JPAStock;

import java.util.concurrent.TimeUnit;

import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.transaction.annotation.Transactional;

//@Service
@Transactional(value = "jpaTransactionManager")
public class JPAStockPessimisticLockServiceImplForDB2 extends
                                                      JPAStockPessimisticLockServiceImpl {

    @Override
    public JPAStock buyExcp(JPAStock stock, int purchasingQuantity,
            long sleepMillis) {

        jpaStockRepository.setCurrentLockTimeoutNotWait();
        JPAStock jpaStock = null;
        try {
            jpaStock = jpaStockRepository
                    .findOneForUpdateUsingPessimisticLockExcp(stock
                            .getItemCode());
            jpaStockRepository.setCurrentLockTimeoutDefault();
        } catch (PessimisticLockingFailureException e) {
            latchToUpdate.countDown();
            throw e;
        }
        try {
            // 別ブラウザでタイムアウトが発生するまで待機
            latchToUpdate.await(30, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            exceptionLogger.log(e);
            Thread.currentThread().interrupt();
        }
        jpaStock.setQuantity(jpaStock.getQuantity() - purchasingQuantity);
        jpaStockRepository.saveAndFlush(jpaStock);
        return jpaStock;
    }
}
