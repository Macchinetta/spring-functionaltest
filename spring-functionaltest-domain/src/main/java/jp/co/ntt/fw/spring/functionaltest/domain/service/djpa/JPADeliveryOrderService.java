/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.djpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import jp.co.ntt.fw.spring.functionaltest.domain.model.JPADeliveryOrder;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.djpa.DeliveryOrderCriteria;

public interface JPADeliveryOrderService {

    List<JPADeliveryOrder> searchUsingStatCode(String statusCode);

    JPADeliveryOrder findById(Integer deliveryNumber);

    Page<JPADeliveryOrder> findByStatusCode(String statusCode, Pageable pageable);

    List<JPADeliveryOrder> findUsingNativeQuery(String statusCode);

    JPADeliveryOrder save(JPADeliveryOrder jpaDeliveryOrder);

    void delete(Integer id);

    List<JPADeliveryOrder> queryHint(String statusCode, Integer sleepTime);

    List<JPADeliveryOrder> forwardSearch(String senderAddress);

    List<JPADeliveryOrder> backwardSearch(String receiverAddress);

    List<JPADeliveryOrder> partialSearch(String senderName);

    Page<JPADeliveryOrder> findEntityPageMatchingCondition(String delStatus,
            Pageable pageable);

    List<JPADeliveryOrder> searchUsingGiveSearchType(String searchVal,
            String serachCriteria[]);

    List<JPADeliveryOrder> searchUsingDynamicCond(
            DeliveryOrderCriteria deliveryOrderCriteria);

    Page<JPADeliveryOrder> findByEscapeSearchMod(String keyword,
            Pageable pageable);

    Page<JPADeliveryOrder> findByEscapeSearchDash(String keyword,
            Pageable pageable);

    Page<JPADeliveryOrder> findByEscapeSearchMatchInLogic(String keyword,
            Pageable pageable);

    Page<JPADeliveryOrder> search(DeliveryOrderCriteria criteria,
            Pageable pageable);

}
