/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.repository.ssmn;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import jp.co.ntt.fw.spring.functionaltest.domain.model.Item;

public interface ItemRepository {

    Item findOne(String itemId);

    long count();

    List<Item> findPage(RowBounds rowBounds);

}
