/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.pgnt;

import jp.co.ntt.fw.spring.functionaltest.domain.model.Celebrity;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.pgnt.CelebritySearchCriteria;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CelebrityService {

    Page<Celebrity> getNames(CelebritySearchCriteria criteria, Pageable pageable);

}
