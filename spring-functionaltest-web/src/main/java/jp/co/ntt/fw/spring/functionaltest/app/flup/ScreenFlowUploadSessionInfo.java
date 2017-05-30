/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.flup;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ScreenFlowUploadSessionInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String temporaryFileId;

    public String getTemporaryFileId() {
        return temporaryFileId;
    }

    public void setTemporaryFileId(String temporaryFileId) {
        this.temporaryFileId = temporaryFileId;
    }

}
