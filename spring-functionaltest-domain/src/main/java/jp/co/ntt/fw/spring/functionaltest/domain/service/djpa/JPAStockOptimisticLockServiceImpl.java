/*
 * Copyright(c) 2014 NTT Corporation.
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
package jp.co.ntt.fw.spring.functionaltest.domain.service.djpa;

import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.terasoluna.gfw.common.exception.ExceptionLogger;
import jp.co.ntt.fw.spring.functionaltest.domain.model.JPAStock;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.djpa.JPAStockRepository;

@Service
@Transactional(value = "jpaTransactionManager")
public class JPAStockOptimisticLockServiceImpl implements JPAStockOptimisticLockService {

    @Inject
    protected JPAStockRepository jpaStockRepository;

    @Inject
    ExceptionLogger exceptionLogger;

    private final CyclicBarrier barrierToUpdate = new CyclicBarrier(2);

    @Override
    public JPAStock findOne(String itemCode) {
        return jpaStockRepository.findById(itemCode).orElse(null);
    }

    @Override
    public List<JPAStock> getAll() {
        return jpaStockRepository.findAll();
    }

    @Override
    public Integer decreamentQty(JPAStock jpaStock, Integer qty) {
        return jpaStockRepository.decrementQuantity(jpaStock.getItemCode(), qty);
    }

    @Override
    public JPAStock buy(JPAStock stock, int purchasingQuantity) {
        JPAStock subject = jpaStockRepository.findById(stock.getItemCode()).orElse(null);
        subject.setQuantity(subject.getQuantity() - purchasingQuantity);
        await();
        jpaStockRepository.saveAndFlush(subject);
        return subject;
    }

    private void await() {
        try {
            barrierToUpdate.await(30, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            exceptionLogger.log(e);
            Thread.currentThread().interrupt();
        } catch (BrokenBarrierException e) {
            exceptionLogger.log(e);
        } catch (TimeoutException e) {
            exceptionLogger.log(e);
        }
    }
}
