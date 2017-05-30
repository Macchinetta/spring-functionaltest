/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.exhn;

import java.io.IOException;

public interface ArticleFileService {

    long countAll();

    void save(String uploadTemporaryFileId, String title) throws IOException;

    void deleteAll();
}
