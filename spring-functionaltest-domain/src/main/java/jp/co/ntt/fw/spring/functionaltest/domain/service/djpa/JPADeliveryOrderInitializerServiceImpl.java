/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.djpa;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.ntt.fw.spring.functionaltest.domain.model.JPADeliveryOrder;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.djpa.JPADeliveryOrderRepository;

@Service
@Transactional(value = "jpaTransactionManager")
public class JPADeliveryOrderInitializerServiceImpl implements
                                                   JPADeliveryOrderInitializerService {

    @Inject
    JPADeliveryOrderRepository jpaDeliveryOrderRepository;

    @Override
    public void initByBatch() {
        Calendar cal = Calendar.getInstance();
        cal.set(2013, 11, 24);
        Date utilDate = cal.getTime();
        java.sql.Date recDate = new java.sql.Date(utilDate.getTime());

        List<JPADeliveryOrder> orderList = new ArrayList<JPADeliveryOrder>();

        for (int i = 6; i < 16; i++) {
            JPADeliveryOrder jpaDeliveryOrder = new JPADeliveryOrder();
            String delStatus = "";
            Integer delType = null;
            if (i % 2 == 0) {
                delStatus = "配達中";
                delType = 1;
            } else {
                delStatus = "完了";
                delType = 2;
            }
            jpaDeliveryOrder.setAcceptDateTime(recDate);
            jpaDeliveryOrder.setCompletionDateTime(recDate);
            jpaDeliveryOrder.setDeliveryDriver("deliveryDriver" + i);
            jpaDeliveryOrder.setDeliveryStatus(delStatus);
            jpaDeliveryOrder.setDeliveryType(delType);
            jpaDeliveryOrder.setReceiverAddress("receiverAddress" + i);
            jpaDeliveryOrder.setReceiverName("receiverName" + i);
            jpaDeliveryOrder.setSenderAddress("senderAddress" + i);
            jpaDeliveryOrder.setSenderName("senderName" + i);
            orderList.add(jpaDeliveryOrder);

        }
        for (JPADeliveryOrder jpaDeliveryOrder : orderList) {
            jpaDeliveryOrderRepository.save(jpaDeliveryOrder);
        }

    }

    @Override
    public void clear() {
        jpaDeliveryOrderRepository.deleteGreaterThan(5);
    }
}
