/*
 * Copyright 2014-2018 NTT Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jp.co.ntt.fw.spring.functionaltest.app.flup;

import java.io.Serializable;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import jp.co.ntt.fw.spring.functionaltest.app.cmmn.validation.UploadFileRequired;

public class ScreenFlowUploadForm implements Serializable {

    private static final long serialVersionUID = 1L;

    public interface Confirm {
    }

    public interface Upload {
    }

    @UploadFileRequired(groups = Confirm.class)
    private transient MultipartFile multipartFile;

    @Size(max = 1024, groups = Upload.class)
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NotEmpty(groups = Upload.class)
    @Size(min = 1, max = 256, groups = Upload.class)
    private String fileName;

    @NotEmpty(groups = Upload.class)
    @Size(min = 1, max = 64, groups = Upload.class)
    private String contentType;

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}
