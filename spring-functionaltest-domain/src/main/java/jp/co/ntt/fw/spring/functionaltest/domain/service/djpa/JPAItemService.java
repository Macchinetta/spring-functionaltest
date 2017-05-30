/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.djpa;

import java.util.List;

import jp.co.ntt.fw.spring.functionaltest.domain.model.JPAItem;

public interface JPAItemService {

    JPAItem getItemDetail(String itemCode);

    List<JPAItem> getAll();

}
