/*
 * Copyright(c) 2014-2017 NTT Corporation.
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
