/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.vldt;

import java.io.Serializable;

import org.terasoluna.gfw.common.codelist.ExistInCodeList;

/**
 * Customer form object.
 */
public class ExistInCheckDefaultForm implements Serializable {

    private static final long serialVersionUID = 1L;

    @ExistInCodeList(codeListId = "CL_GENDER")
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

}
