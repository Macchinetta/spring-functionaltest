/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.flup;

import java.io.IOException;
import java.io.InputStream;

import jp.co.ntt.fw.spring.functionaltest.domain.model.UploadFile;

public interface FileUploadService {

    UploadFile saveFileToDisc(InputStream content, UploadFile newUploadFile) throws IOException;

    UploadFile saveFileToDisc(String temporaryFileId, UploadFile newUploadFile) throws IOException;

    UploadFile saveFileToDb(String temporaryFileId, UploadFile newUploadFile) throws IOException;

    UploadFile getUploadFile(String fileId);
}
