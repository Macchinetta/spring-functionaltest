/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.ws.webfault;

import java.util.ArrayList;
import java.util.List;

public class WebFaultBean {

    private WebFaultType type;

    private List<ErrorBean> errors = new ArrayList<ErrorBean>();

    public WebFaultBean() {

    }

    public WebFaultBean(WebFaultType type) {
        this.type = type;
    }

    public void addError(String code, String message) {
        addError(code, message, null);
    }

    public void addError(String code, String message, String path) {
        errors.add(new ErrorBean(code, message, path));
    }

    public WebFaultType getType() {
        return type;
    }

    public void setType(WebFaultType type) {
        this.type = type;
    }

    public List<ErrorBean> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorBean> errors) {
        this.errors = errors;
    }

}
