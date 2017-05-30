/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.djpa;

import java.util.List;

import javax.inject.Inject;

import jp.co.ntt.fw.spring.functionaltest.domain.model.JPAStock;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.djpa.JPAStockRepository;

import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.terasoluna.gfw.common.exception.BusinessException;
import org.terasoluna.gfw.common.message.ResultMessages;

@Service
@Transactional(value = "jpaTransactionManager")
public class JPAStockServiceImpl implements JPAStockService {

    @Inject
    protected JPAStockRepository jpaStockRepository;

    @Override
    public JPAStock findOne(String itemCode) {
        return jpaStockRepository.findOne(itemCode);
    }

    @Override
    public List<JPAStock> getAll() {
        return jpaStockRepository.findAll();
    }

    @Override
    public Integer decreamentQty(JPAStock jpaStock, Integer qty) {
        return jpaStockRepository
                .decrementQuantity(jpaStock.getItemCode(), qty);
    }

    @Override
    public JPAStock buy(JPAStock stock, int purchasingQuantity, long sleepMillis) {

        if (jpaStockRepository.decrementQuantity(stock.getItemCode(),
                purchasingQuantity) == 0) {
            ResultMessages messages = ResultMessages.danger().add(
                    "excn.result.stock.exclusive");
            throw new BusinessException(messages);
        }
        jpaStockRepository.flush();
        // this.sleep(sleepMillis);

        return jpaStockRepository.findOne(stock.getItemCode());
    }

    protected void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public JPAStock buyWithOptimisticLock(JPAStock stock,
            int purchasingQuantity, long sleepMillis) {
        JPAStock subject = jpaStockRepository.findOne(stock.getItemCode());

        // 排他制御チェックのため、スレッドを1秒ウエイトする
        this.sleep(sleepMillis);

        subject.setQuantity(subject.getQuantity() - purchasingQuantity);
        JPAStock jpaStckUpdt = jpaStockRepository.saveAndFlush(subject);
        if (null == jpaStckUpdt) {
            throw new ObjectOptimisticLockingFailureException(JPAStock.class, subject
                    .getItemCode());
        }

        return subject;
    }

    @Override
    public JPAStock buyWithPessimisticLock(JPAStock stock,
            int purchasingQuantity, long sleepMillis) {
        JPAStock jpaStock = jpaStockRepository
                .findOneForUpdateUsingPessimisticLock(stock.getItemCode()
                        .trim());
        System.out.println("Sleep sec =>" + sleepMillis + ": version=>"
                + jpaStock.getVersion());

        jpaStock.setQuantity(jpaStock.getQuantity() - purchasingQuantity);
        jpaStockRepository.saveAndFlush(jpaStock);
        // 排他制御チェックのため、スレッドを1秒ウエイトする
        this.sleep(sleepMillis);
        return jpaStock;
    }

    @Override
    public JPAStock buyWithPessimisticLockExcp(JPAStock stock,
            int purchasingQuantity, long sleepMillis) {
        JPAStock jpaStock = jpaStockRepository
                .findOneForUpdateUsingPessimisticLockExcp(stock.getItemCode());
        // 排他制御チェックのため、スレッドを1秒ウエイトする
        this.sleep(sleepMillis);

        jpaStock.setQuantity(jpaStock.getQuantity() - purchasingQuantity);
        jpaStockRepository.saveAndFlush(jpaStock);
        return jpaStock;
    }
}
