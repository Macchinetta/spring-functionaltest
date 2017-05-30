/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.djpa;

import java.util.List;

import jp.co.ntt.fw.spring.functionaltest.domain.model.JPAItem;
import jp.co.ntt.fw.spring.functionaltest.domain.model.JPAOrder;
import jp.co.ntt.fw.spring.functionaltest.domain.model.JPAOrderForCmnConditionNoBoolean;
import jp.co.ntt.fw.spring.functionaltest.domain.model.JPAOrderForCommonCondition;
import jp.co.ntt.fw.spring.functionaltest.domain.model.OrderSummary;

public interface JPAOrderService {

    JPAOrder getOrderDetail(Integer orderId);

    List<JPAOrder> getAll();

    JPAOrder placeOrder(List<JPAItem> itemList, String orderMemo);

    JPAOrder addtoOrder(List<JPAItem> itemList, Integer orderId, String comment);

    JPAOrder updateStatus(Integer orderId, String orderStatus);

    JPAOrder updateOrderItem(Integer orderId, Integer itemNum, Integer newQty);

    void delete(Integer orderId);

    void deleteItem(Integer orderId, Integer itemNum);

    List<JPAOrder> joinFetch(String statusName);

    List<OrderSummary> storeFetchResultInCustomObject();

    JPAOrderForCommonCondition findOrderDeatilUsingCommonConditionOnEntity(
            Integer orderId);

    JPAOrderForCmnConditionNoBoolean findOrderDeatilUsingCommonConditionOnEntityWithNoBooleanSupport(
            Integer orderId);

}
