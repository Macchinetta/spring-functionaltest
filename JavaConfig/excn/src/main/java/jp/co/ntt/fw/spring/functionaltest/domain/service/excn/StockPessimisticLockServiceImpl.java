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
package jp.co.ntt.fw.spring.functionaltest.domain.service.excn;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.terasoluna.gfw.common.exception.ExceptionLogger;
import org.terasoluna.gfw.common.exception.ResourceNotFoundException;
import org.terasoluna.gfw.common.message.ResultMessages;
import jakarta.inject.Inject;
import jp.co.ntt.fw.spring.functionaltest.domain.model.Stock;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.excn.StockRepository;

@Service
@Transactional(value = "transactionManager")
public class StockPessimisticLockServiceImpl implements StockPessimisticLockService {

    @Inject
    StockRepository stockRepository;

    @Inject
    ExceptionLogger exceptionLogger;

    // 悲観ロックで別ブラウザがタイムアウトになってから更新するためのラッチ
    private CountDownLatch latchToupdate;

    @Override
    public Stock findOne(String itemCode) {
        Stock stock = stockRepository.findByItemCode(itemCode);
        if (stock == null) {
            ResultMessages messages = ResultMessages.danger().add("excn.result.datanotfound");
            throw new ResourceNotFoundException(messages);
        }
        latchToupdate = new CountDownLatch(1);
        return stock;
    }

    @Override
    public Stock buy(Stock stock, int purchasingQuantity) {
        Stock subject = null;
        try {
            subject = stockRepository.findByItemCodeWithPessimisticLock(stock.getItemCode());
        } catch (PessimisticLockingFailureException e) {
            latchToupdate.countDown();
            throw e;
        }
        try {
            // 別ブラウザでタイムアウトが発生するまで待機
            latchToupdate.await(30, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            exceptionLogger.log(e);
            Thread.currentThread().interrupt();
        }
        subject.setQuantity(subject.getQuantity() - purchasingQuantity);
        stockRepository.updateQuantity(subject);
        return subject;
    }
}
