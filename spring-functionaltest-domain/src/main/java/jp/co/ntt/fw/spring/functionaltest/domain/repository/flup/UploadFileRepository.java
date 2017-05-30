/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.repository.flup;

import jp.co.ntt.fw.spring.functionaltest.domain.model.UploadFile;

public interface UploadFileRepository {

    UploadFile findOne(String fileId);

    void insert(UploadFile uoloadFile);

}
