/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.exhn;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ArticleSessionInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String uploadTemporaryFileId;

    public String getUploadTemporaryFileId() {
        return uploadTemporaryFileId;
    }

    public void setUploadTemporaryFileId(String uploadTemporaryFileId) {
        this.uploadTemporaryFileId = uploadTemporaryFileId;
    }

}
