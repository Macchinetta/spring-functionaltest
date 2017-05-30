/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.ajax;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jp.co.ntt.fw.spring.functionaltest.app.cmmn.bean.ErrorResult;

public class PersonalComputerWithErrorResults implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<String> messages;

    private List<ErrorResult> errorResults = new ArrayList<ErrorResult>();

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public List<ErrorResult> getErrorResults() {
        return errorResults;
    }

    public void setErrorResults(List<ErrorResult> errorResults) {
        this.errorResults = errorResults;
    }

    public PersonalComputerWithErrorResults addErrorResults(String code,
            String message) {
        ErrorResult errorResult = new ErrorResult();
        errorResult.setCode(code);
        errorResult.setMessage(message);
        errorResults.add(errorResult);
        return this;
    }

    public PersonalComputerWithErrorResults addErrorResults(String code,
            String message, String itemPath) {
        ErrorResult errorResult = new ErrorResult();
        errorResult.setCode(code);
        errorResult.setMessage(message);
        errorResult.setItemPath(itemPath);
        errorResults.add(errorResult);
        return this;
    }

}
