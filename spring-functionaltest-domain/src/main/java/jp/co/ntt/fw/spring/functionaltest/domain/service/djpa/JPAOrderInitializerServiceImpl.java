/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.djpa;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import jp.co.ntt.fw.spring.functionaltest.domain.model.JPAItem;
import jp.co.ntt.fw.spring.functionaltest.domain.model.JPAOrder;
import jp.co.ntt.fw.spring.functionaltest.domain.model.JPAOrderItem;
import jp.co.ntt.fw.spring.functionaltest.domain.model.JPAOrderStatus;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.djpa.JPAOrderRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(value = "jpaTransactionManager")
public class JPAOrderInitializerServiceImpl implements
                                           JPAOrderInitializerService {

    @Inject
    JPAOrderRepository jpaOrderRepository;

    @Override
    public void initByBatch() {

        List<JPAOrder> ordeList = new ArrayList<JPAOrder>();
        JPAOrderStatus jpaOrderStatus = null;
        List<JPAOrderItem> orderItemList = null;
        JPAItem orderItem = null;
        JPAOrderItem jpaOrderItem = null;
        JPAOrder jpaOrder = null;

        // order 1
        jpaOrder = new JPAOrder();
        jpaOrderStatus = new JPAOrderStatus();
        jpaOrderStatus.setOrderStatusCode("accepted");
        // jpaOrderStatus.setStatusName("Order accepted");
        orderItemList = new ArrayList<JPAOrderItem>();
        orderItem = new JPAItem();
        orderItem.setItemCode("ITM0000001");
        jpaOrderItem = new JPAOrderItem();
        jpaOrderItem.setLogicalDelete(false);
        int qty = 5;
        int rate = 100;
        jpaOrderItem.setQuantity(qty);
        jpaOrderItem.setOrderItem(orderItem);
        orderItemList.add(jpaOrderItem);
        jpaOrder.setOrderMemo("dummy comment1");
        jpaOrder.setOrderStatus(jpaOrderStatus);
        jpaOrder.setOrderItem(orderItemList);
        jpaOrder.setOrderAmount(qty * rate);
        ordeList.add(jpaOrder);

        // order 2
        jpaOrder = new JPAOrder();
        jpaOrderStatus = new JPAOrderStatus();
        jpaOrderStatus.setOrderStatusCode("checking");
        // jpaOrderStatus.setStatusName("Order accepted");
        orderItemList = new ArrayList<JPAOrderItem>();
        orderItem = new JPAItem();
        orderItem.setItemCode("ITM0000002");
        jpaOrderItem = new JPAOrderItem();
        jpaOrderItem.setLogicalDelete(false);
        qty = 2;
        rate = 1000;
        jpaOrderItem.setQuantity(qty);
        jpaOrderItem.setOrderItem(orderItem);
        orderItemList.add(jpaOrderItem);
        jpaOrder.setOrderMemo("dummy comment2");
        jpaOrder.setOrderStatus(jpaOrderStatus);
        jpaOrder.setOrderItem(orderItemList);
        jpaOrder.setOrderAmount(qty * rate);
        ordeList.add(jpaOrder);

        jpaOrderRepository.save(ordeList);

    }

    @Override
    public void clear() {
        jpaOrderRepository.deleteAllInBatch();

    }

}
