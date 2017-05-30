/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.repository.pgnt;

import java.io.Serializable;

import org.springframework.data.domain.Pageable;

public class PageableArticleSearchCriteria implements Serializable {

    private static final long serialVersionUID = 1L;

    private final ArticleSearchCriteria articleSearchCriteria;

    private final Pageable pageable;

    public PageableArticleSearchCriteria(
            ArticleSearchCriteria articleSearchCriteria, Pageable pageable) {
        this.articleSearchCriteria = articleSearchCriteria;
        this.pageable = pageable;
    }

    public ArticleSearchCriteria getArticleSearchCriteria() {
        return articleSearchCriteria;
    }

    public Pageable getPageable() {
        return pageable;
    }
}
