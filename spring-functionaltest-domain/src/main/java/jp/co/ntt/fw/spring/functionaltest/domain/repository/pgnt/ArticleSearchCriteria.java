/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.repository.pgnt;

import java.io.Serializable;

import javax.validation.constraints.Max;

public class ArticleSearchCriteria implements Serializable {

    private static final long serialVersionUID = 1L;

    @Max(100)
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
