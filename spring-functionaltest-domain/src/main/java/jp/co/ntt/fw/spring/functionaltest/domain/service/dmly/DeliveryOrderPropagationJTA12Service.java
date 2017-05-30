/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.dmly;

import java.util.List;

import jp.co.ntt.fw.spring.functionaltest.domain.model.DeliveryOrder;

public interface DeliveryOrderPropagationJTA12Service {

    void propagateRequiredRequired(List<DeliveryOrder> firstOrderList,
            List<DeliveryOrder> secondOrderList);

    void propagateRequiredRequiresNew(List<DeliveryOrder> firstOrderList,
            List<DeliveryOrder> secondOrderList);

    void propagateRequiredRequiresNew2(List<DeliveryOrder> firstOrderList,
            List<DeliveryOrder> secondOrderList);

    void propagateRequiredSupports(List<DeliveryOrder> firstOrderList,
            List<DeliveryOrder> secondOrderList);

    void propagateRequiredNotSupported(List<DeliveryOrder> firstOrderList,
            List<DeliveryOrder> secondOrderList);

    void propagateRequiredMandatory(List<DeliveryOrder> firstOrderList,
            List<DeliveryOrder> secondOrderList);

    void propagateRequiredNever(List<DeliveryOrder> firstOrderList,
            List<DeliveryOrder> secondOrderList);

}
