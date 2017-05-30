/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.spsm;

import java.io.Serializable;

public class SessionForm implements Serializable {

    private static final long serialVersionUID = 1L;

    private String oeratorName;

    public String getOeratorName() {
        return oeratorName;
    }

    public void setOeratorName(String oeratorName) {
        this.oeratorName = oeratorName;
    }
}
