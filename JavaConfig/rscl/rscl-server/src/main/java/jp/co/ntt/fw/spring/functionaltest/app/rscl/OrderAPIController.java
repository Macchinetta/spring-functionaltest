/*
 * Copyright(c) 2024 NTT Corporation.
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
package jp.co.ntt.fw.spring.functionaltest.app.rscl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jp.co.ntt.fw.spring.functionaltest.app.cmmn.model.Order;

@RestController
@RequestMapping(value = "/api/v1/orders")
public class OrderAPIController {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderAPIController.class);

    /**
     * <ul>
     * <li>Orderを返却する。</li> <br>
     * Url変換の確認用。
     * </ul>
     * 
     * @param orderDate 注文日
     * @return 注文情報
     */
    @GetMapping(value = "{orderDate}")
    public Order getOrderByDate(@PathVariable("orderDate") String orderDate) {
        LOGGER.info("Call API:getOrderByDate", orderDate);
        LOGGER.info("orderDate:{})", orderDate);
        var order = new Order();
        order.setId("00001");
        order.setOrderDate(orderDate);
        order.setItemName("Sample Item");

        return order;
    }
}
