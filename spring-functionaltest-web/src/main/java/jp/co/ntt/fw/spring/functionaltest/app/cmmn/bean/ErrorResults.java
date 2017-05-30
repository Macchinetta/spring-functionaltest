/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.cmmn.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ErrorResults implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<ErrorResult> errorResults = new ArrayList<ErrorResult>();

    public List<ErrorResult> getErrorResults() {
        return errorResults;
    }

    public void setErrorResults(List<ErrorResult> errorResults) {
        this.errorResults = errorResults;
    }

    public ErrorResults add(String code, String message) {
        ErrorResult errorResult = new ErrorResult();
        errorResult.setCode(code);
        errorResult.setMessage(message);
        errorResults.add(errorResult);
        return this;
    }

    public ErrorResults add(String code, String message, String itemPath) {
        ErrorResult errorResult = new ErrorResult();
        errorResult.setCode(code);
        errorResult.setMessage(message);
        errorResult.setItemPath(itemPath);
        errorResults.add(errorResult);
        return this;
    }
}
