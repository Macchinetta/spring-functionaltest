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
package jp.co.ntt.fw.spring.functionaltest.domain.service.excn;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.terasoluna.gfw.common.exception.BusinessException;
import org.terasoluna.gfw.common.exception.ExceptionLogger;
import org.terasoluna.gfw.common.exception.ResourceNotFoundException;
import org.terasoluna.gfw.common.message.ResultMessages;

import jp.co.ntt.fw.spring.functionaltest.domain.model.Stock;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.excn.StockRepository;

@Service
@Transactional
public class StockDBLockServiceImpl implements StockDBLockService {

    @Inject
    StockRepository stockRepository;

    @Inject
    ExceptionLogger exceptionLogger;

    // 各ブラウザを更新前に同期させるためのバリア
    private final CyclicBarrier barrierToUpdate = new CyclicBarrier(2);

    @Override
    public Stock findOne(String itemCode) {
        Stock stock = stockRepository.findByItemCode(itemCode);
        if (stock == null) {
            ResultMessages messages = ResultMessages.danger().add(
                    "excn.result.datanotfound");
            throw new ResourceNotFoundException(messages);
        }
        return stock;
    }

    @Override
    public Stock buy(Stock stock, int purchasingQuantity, long sleepMillis) {
        Stock subject = stockRepository.findByItemCode(stock.getItemCode());

        subject.setQuantity(purchasingQuantity);
        // できるだけ同時にDBアクセスするように同期する
        await();
        if (stockRepository.updateQuantityWithRDBMSLock(subject) == 0) {
            ResultMessages messages = ResultMessages.danger().add(
                    "excn.result.exclusive");
            throw new BusinessException(messages);
        }
        // RDBMSによる行ロック中に、別スレッドの更新処理が実行されるようにするために、ロックを取得したスレッドを一定時間停止する。
        sleep(sleepMillis);
        return stockRepository.findByItemCode(stock.getItemCode());
    }

    private void await() {
        // 全てのブラウザがバージョンを取得するまで待機
        try {
            barrierToUpdate.await(30, TimeUnit.SECONDS);
        } catch (BrokenBarrierException e) {
            exceptionLogger.log(e);
        } catch (InterruptedException e) {
            exceptionLogger.log(e);
            Thread.currentThread().interrupt();
        } catch (TimeoutException e) {
            exceptionLogger.log(e);
        }
    }

    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            exceptionLogger.log(e);
            Thread.currentThread().interrupt();
        }
    }
}
