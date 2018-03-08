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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import org.springframework.util.StreamUtils;

public class UploadedContent implements Serializable {

    private static final long serialVersionUID = 1L;

    private String fileId;

    private String fileName;

    private String fileContent;

    private long size;

    private String description;

    private Date uploadedAt;

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileContent() {
        return fileContent;
    }

    public void setFileContent(String fileContent) {
        this.fileContent = fileContent;
    }

    public void setFileContent(InputStream stream) throws IOException {
        setFileContent(StreamUtils.copyToByteArray(stream));
    }

    public void setFileContent(File file) throws IOException {
        setFileContent(new FileInputStream(file));
    }

    public void setFileContent(byte[] data) {
        if (data == null) {
            setFileContent((String) null);
        } else {
            try {
                setFileContent(new String(data, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                throw new IllegalStateException(e);
            }
        }
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getUploadedAt() {
        return uploadedAt;
    }

    public void setUploadedAt(Date uploadedAt) {
        this.uploadedAt = uploadedAt;
    }

}
