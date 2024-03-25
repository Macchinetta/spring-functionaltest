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
package jp.co.ntt.fw.spring.functionaltest.domain.repository.dam3mb3;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.data.domain.Pageable;

import jp.co.ntt.fw.spring.functionaltest.domain.model.CategoryMB3;
import jp.co.ntt.fw.spring.functionaltest.domain.model.ItemMB3;
import jp.co.ntt.fw.spring.functionaltest.domain.model.OrderMB3;

public interface OrderMB3Repository {

    long count();

    int findMaxOrderId();

    OrderMB3 findById(int id);

    OrderMB3 findByCondSts(@Param("id") int id,
            @Param("statusCode") String statusCode);

    List<OrderMB3> findAll();

    List<OrderMB3> findPageMyBatis3(RowBounds rowBounds);

    List<OrderMB3> findPageMyBatis3Scroll(RowBounds rowBounds);

    void insertEntity(OrderMB3 orderMB3);

    void insertItem(@Param("orderId") Integer orderId,
            @Param("itemCode") String itemCode,
            @Param("quantity") int quantity);

    void insertAll(List<OrderMB3> orderMB3List);

    int update(@Param("id") int id, @Param("statusCode") String statusCode,
            @Param("memo") String memo);

    int updateHistory(@Param("id") int id, @Param("historyId") int historyId);

    /**
     * New addition
     */
    List<CategoryMB3> findAllCategoryByItemCode(String itemCode);

    List<CategoryMB3> findAllCategoryByItemCodeLazy(String itemCode);

    List<ItemMB3> findAllItemCode();

    long countPageByItemcode(
            @Param("searchItemCode") List<String> searchItemCode);

    List<OrderMB3> findPageByItemcode(
            @Param("searchItemCode") List<String> searchItemCode,
            @Param("pageable") Pageable pageable);

}
