/*
 * Copyright 2014-2018 NTT Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.dmly;

import java.util.List;

import jp.co.ntt.fw.spring.functionaltest.domain.model.DeliveryOrder;

public interface DeliveryOrderPropagationService {

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

    void propagateRequiredNested(List<DeliveryOrder> firstOrderList,
            List<DeliveryOrder> secondOrderList);

    void propagateRequiredNested2(List<DeliveryOrder> firstOrderList,
            List<DeliveryOrder> secondOrderList);

    void propagateRequiredNested3(List<DeliveryOrder> firstOrderList,
            List<DeliveryOrder> secondOrderList);

}
