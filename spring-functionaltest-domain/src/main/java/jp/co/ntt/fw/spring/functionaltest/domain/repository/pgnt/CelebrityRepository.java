/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.repository.pgnt;

import java.util.List;

import jp.co.ntt.fw.spring.functionaltest.domain.model.Celebrity;

public interface CelebrityRepository {

    long countByCriteria(PageableCelebritySearchCriteria criteria);

    List<Celebrity> findPage(PageableCelebritySearchCriteria criteria);

}
