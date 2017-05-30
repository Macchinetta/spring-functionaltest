/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.repository.pgnt;

import java.io.Serializable;

import org.springframework.data.domain.Pageable;

public class PageableCelebritySearchCriteria implements Serializable {

    private static final long serialVersionUID = 1L;

    private final CelebritySearchCriteria celebritySearchCriteria;

    private final Pageable pageable;

    public PageableCelebritySearchCriteria(
            CelebritySearchCriteria celebritySearchCriteria, Pageable pageable) {
        this.celebritySearchCriteria = celebritySearchCriteria;
        this.pageable = pageable;
    }

    public CelebritySearchCriteria getCelebritySearchCriteria() {
        return celebritySearchCriteria;
    }

    public Pageable getPageable() {
        return pageable;
    }
}
