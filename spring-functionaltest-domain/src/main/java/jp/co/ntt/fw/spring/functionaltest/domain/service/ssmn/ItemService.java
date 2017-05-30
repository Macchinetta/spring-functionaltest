/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.ssmn;

import jp.co.ntt.fw.spring.functionaltest.domain.model.Item;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItemService {

    Page<Item> getItems(Pageable pageable);

    Item getItem(String itemId);

}
