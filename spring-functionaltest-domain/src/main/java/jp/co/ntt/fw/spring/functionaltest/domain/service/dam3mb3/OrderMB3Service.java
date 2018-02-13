/*
 * Copyright 2014-2017 NTT Corporation.
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
 *
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.dam3mb3;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import jp.co.ntt.fw.spring.functionaltest.domain.model.CategoryMB3;
import jp.co.ntt.fw.spring.functionaltest.domain.model.ItemMB3;
import jp.co.ntt.fw.spring.functionaltest.domain.model.OrderMB3;

public interface OrderMB3Service {

    int findMaxOrderId();

    OrderMB3 findOne(int id);

    OrderMB3 findOneCondSts(int id, String statusCode);

    List<OrderMB3> findAll();

    Page<OrderMB3> findPageMyBatis3(Pageable pageable);

    Page<OrderMB3> findPageMyBatis3Scroll(Pageable pageable);

    List<CategoryMB3> findAllCategoryByItemCode(String itemCode);

    List<CategoryMB3> findAllCategoryByItemCodeLazy(String itemCode);

    List<ItemMB3> findAllItemCode();

    Page<OrderMB3> findPageByItemcode(List<String> searchItemCode,
            Pageable pageable);

}
