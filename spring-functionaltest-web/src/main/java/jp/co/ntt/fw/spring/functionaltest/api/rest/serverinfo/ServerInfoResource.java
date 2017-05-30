/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.api.rest.serverinfo;

import java.io.Serializable;

public class ServerInfoResource implements Serializable {
    private static final long serialVersionUID = 1L;

    private int javaVersion;

    public int getJavaVersion() {
        return javaVersion;
    }

    public void setJavaVersion(int javaVersion) {
        this.javaVersion = javaVersion;
    }

}
