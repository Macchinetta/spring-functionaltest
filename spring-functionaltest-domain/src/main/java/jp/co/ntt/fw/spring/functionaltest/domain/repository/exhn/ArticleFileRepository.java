/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.repository.exhn;

import jp.co.ntt.fw.spring.functionaltest.domain.model.ArticleFile;

public interface ArticleFileRepository {

    long countAll();

    void register(ArticleFile articleFile);

    void deleteAll();

}
