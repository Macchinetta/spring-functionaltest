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
package jp.co.ntt.fw.spring.functionaltest.domain.service.dam3mb3;

import java.util.Collections;
import java.util.List;
import org.apache.ibatis.session.RowBounds;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.inject.Inject;
import jp.co.ntt.fw.spring.functionaltest.domain.model.CategoryMB3;
import jp.co.ntt.fw.spring.functionaltest.domain.model.ItemMB3;
import jp.co.ntt.fw.spring.functionaltest.domain.model.OrderMB3;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.dam3mb3.OrderMB3Repository;

@Service
@Transactional
public class OrderMB3ServiceImpl implements OrderMB3Service {

    @Inject
    OrderMB3Repository orderMB3Repository;

    @Override
    @Transactional(readOnly = true)
    public int findMaxOrderId() {
        return orderMB3Repository.findMaxOrderId();
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderMB3> findAll() {
        return orderMB3Repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public OrderMB3 findOne(int id) {
        return orderMB3Repository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public OrderMB3 findOneCondSts(int id, String statusCode) {
        return orderMB3Repository.findByCondSts(id, statusCode);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<OrderMB3> findPageMyBatis3(Pageable pageable) {

        long total = orderMB3Repository.count();
        List<OrderMB3> todos;
        if (0 < total) {
            RowBounds rowBounds = new RowBounds((int) pageable.getOffset(), pageable.getPageSize());
            todos = orderMB3Repository.findPageMyBatis3(rowBounds);
        } else {
            todos = Collections.emptyList();
        }
        return new PageImpl<>(todos, pageable, total);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<OrderMB3> findPageMyBatis3Scroll(Pageable pageable) {

        long total = orderMB3Repository.count();
        List<OrderMB3> todos;
        if (0 < total) {
            RowBounds rowBounds = new RowBounds((int) pageable.getOffset(), pageable.getPageSize());
            todos = orderMB3Repository.findPageMyBatis3Scroll(rowBounds);
        } else {
            todos = Collections.emptyList();
        }
        return new PageImpl<>(todos, pageable, total);
    }

    @Override
    public List<CategoryMB3> findAllCategoryByItemCode(String itemCode) {
        return orderMB3Repository.findAllCategoryByItemCode(itemCode);
    }

    @Override
    public List<CategoryMB3> findAllCategoryByItemCodeLazy(String itemCode) {
        return orderMB3Repository.findAllCategoryByItemCodeLazy(itemCode);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ItemMB3> findAllItemCode() {
        return orderMB3Repository.findAllItemCode();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<OrderMB3> findPageByItemcode(List<String> searchItemCode, Pageable pageable) {

        long total = orderMB3Repository.countPageByItemcode(searchItemCode);

        List<OrderMB3> orders;
        orders = orderMB3Repository.findPageByItemcode(searchItemCode, pageable);

        return new PageImpl<>(orders, pageable, total);

    }

}
