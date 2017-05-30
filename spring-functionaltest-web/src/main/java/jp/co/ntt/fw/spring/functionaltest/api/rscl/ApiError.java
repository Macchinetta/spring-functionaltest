/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.api.rscl;

import java.io.Serializable;

public class ApiError implements Serializable {

    /**
     * シリアルID
     */
    private static final long serialVersionUID = -584278858357486872L;

    private final String message;

    public ApiError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
