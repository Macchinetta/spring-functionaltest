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

@Service
@Transactional
public class StockServiceImpl implements StockService {

    @Inject
    StockRepository stockRepository;

    @Override
    public Stock findOne(String itemCode) {
        Stock stock = stockRepository.selectByItemCode(itemCode);
        if (stock == null) {
            ResultMessages messages = ResultMessages.danger().add(
                    "excn.result.datanotfound");
            throw new ResourceNotFoundException(messages);
        }

        return stock;
    }

    @Override
    public Stock buy(Stock stock, int purchasingQuantity, long sleepMillis) {
        Stock subject = stockRepository.selectByItemCode(stock.getItemCode());

        subject.setQuantity(purchasingQuantity);
        if (stockRepository.updateQuantityWithRDBMSLock(subject) == 0) {
            ResultMessages messages = ResultMessages.danger().add(
                    "excn.result.exclusive");
            throw new BusinessException(messages);
        }

        // RDBMSによる行ロック中に、別スレッドの更新処理が実行されるようにするために、ロックを取得したスレッドを一定時間(リクエストパラメータで指定された時間)停止する。
        this.sleep(sleepMillis);

        return stockRepository.selectByItemCode(stock.getItemCode());
    }

    @Override
    public Stock buyWithOptimisticLock(Stock stock, int purchasingQuantity,
            long sleepMillis) {
        Stock subject = stockRepository.selectByItemCode(stock.getItemCode());

        // 排他制御チェックのため、スレッドを1秒ウエイトする
        this.sleep(sleepMillis);

        subject.setQuantity(subject.getQuantity() - purchasingQuantity);
        int updateCount = stockRepository
                .updateQuantityWithOptimisticLock(subject);
        if (updateCount == 0) {
            throw new ObjectOptimisticLockingFailureException(Stock.class, subject
                    .getItemCode());
        }

        subject.setVersion(subject.getVersion() + 1);
        return subject;
    }

    @Override
    public Stock buyWithOptimisticLockByHiddenVersion(Stock stock,
            int purchasingQuantity, long sleepMillis) {
        Stock subject = stockRepository.selectByItemCode(stock.getItemCode());

        // 排他制御チェックのため、スレッドを1秒ウエイトする
        this.sleep(sleepMillis);

        subject.setQuantity(subject.getQuantity() - purchasingQuantity);
        subject.setVersion(stock.getVersion());
        if (stockRepository.updateQuantityWithOptimisticLock(subject) == 0) {
            throw new ObjectOptimisticLockingFailureException(Stock.class, subject
                    .getItemCode());
        }

        subject.setVersion(subject.getVersion() + 1);
        return subject;
    }

    @Override
    public Stock buyWithPessimisticLock(Stock stock, int purchasingQuantity,
            long sleepMillis) {
        Stock subject = stockRepository
                .selectByItemCodeWithPessimisticLock(stock.getItemCode());

        // 排他制御チェックのため、スレッドを1秒ウエイトする
        this.sleep(sleepMillis);

        subject.setQuantity(subject.getQuantity() - purchasingQuantity);
        stockRepository.updateQuantity(subject);

        return subject;
    }

    @Override
    public Stock saleWithOptimisticLock(Stock stock, int purchasingQuantity,
            long sleepMillis) {
        Stock subject = stockRepository.selectByItemCode(stock.getItemCode());

        // 排他制御チェックのため、スレッドを1秒ウエイトする
        this.sleep(sleepMillis);

        subject.setQuantity(subject.getQuantity() + purchasingQuantity);
        int updateCount = stockRepository
                .updateQuantityWithOptimisticLock(subject);
        if (updateCount == 0) {
            throw new ObjectOptimisticLockingFailureException(Stock.class, subject
                    .getItemCode());
        }

        subject.setVersion(subject.getVersion() + 1);
        return subject;
    }

    @Override
    public Stock saleWithPessimisticLock(Stock stock, int purchasingQuantity,
            long sleepMillis) {
        Stock subject = stockRepository
                .selectByItemCodeWithPessimisticLock(stock.getItemCode());

        // 排他制御チェックのため、スレッドを1秒ウエイトする
        this.sleep(sleepMillis);

        subject.setQuantity(subject.getQuantity() + purchasingQuantity);
        stockRepository.updateQuantity(subject);

        return subject;
    }

    protected void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
