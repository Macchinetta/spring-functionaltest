/*
 * Copyright(c) 2014-2017 NTT Corporation.
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

    long countById();

    int findMaxOrderId();

    OrderMB3 findOne(int id);

    OrderMB3 findOneCondSts(@Param("id") int id,
            @Param("statusCode") String statusCode);

    List<OrderMB3> findAll();

    List<OrderMB3> findPageMyBatis3(RowBounds rowBounds);

    List<OrderMB3> findPageMyBatis3Scroll(RowBounds rowBounds);

    void insertEntity(OrderMB3 orderMB3);

    void insertItem(@Param("orderId") Integer orderId,
            @Param("itemCode") String itemCode, @Param("quantity") int quantity);

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
