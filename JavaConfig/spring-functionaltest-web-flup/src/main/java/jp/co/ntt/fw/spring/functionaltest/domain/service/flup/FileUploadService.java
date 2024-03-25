/*
 * Copyright(c) 2024 NTT Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.flup;

import java.io.IOException;
import java.io.InputStream;

import jp.co.ntt.fw.spring.functionaltest.domain.model.UploadFile;

public interface FileUploadService {

    UploadFile saveFileToDisc(InputStream content,
            UploadFile newUploadFile) throws IOException;

    UploadFile saveFileToDisc(String temporaryFileId,
            UploadFile newUploadFile) throws IOException;

    UploadFile saveFileToDb(String temporaryFileId,
            UploadFile newUploadFile) throws IOException;

    UploadFile getUploadFile(String fileId);
}
