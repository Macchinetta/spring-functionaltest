/*
 * Copyright(c) 2014-2017 NTT Corporation.
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
