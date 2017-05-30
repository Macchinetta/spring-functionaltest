/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.djpa;

import java.util.List;

import jp.co.ntt.fw.spring.functionaltest.domain.model.JPAItem;
import jp.co.ntt.fw.spring.functionaltest.domain.model.JPAOrderItem;

public interface JPAOrderItemService {

    JPAOrderItem addItemToOrder(List<JPAItem> itemList);

    JPAOrderItem getOrderItemDetail(Integer orderId, Integer itemNumber);

    JPAOrderItem updateOrderItem(Integer orderItemId, Integer newQuantity);

    JPAOrderItem updateUsingQueryMethod(Integer orderId);

    JPAOrderItem updateUsingQueryMethodAndClear(Integer orderId);

    int updateUsingQueryMethodErr(Integer orderId);

    void directDeleteOrderItem(Integer orderItemId, Integer orderId);

    void deleteUsingQueryMethodAndClear(Integer orderId);

    void deleteOrderItemNoSuccess(Integer orderItemId, Integer orderId);
}
