/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.ws.webfault;

import java.util.List;

import javax.xml.ws.WebFault;

@WebFault(name = "WebFault", targetNamespace = "http://functionaltest.spring.fw.ntt.co.jp/todo")
public class WebFaultException extends Exception {

    private static final long serialVersionUID = 1L;

    private WebFaultBean faultInfo;

    public WebFaultException(String message, WebFaultBean faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    public WebFaultException(String message, WebFaultBean faultInfo, Throwable e) {
        super(message, e);
        this.faultInfo = faultInfo;
    }

    public List<ErrorBean> getErrors() {
        return this.faultInfo.getErrors();
    }

    public WebFaultType getType() {
        return this.faultInfo.getType();
    }

    public WebFaultBean getFaultInfo() {
        return faultInfo;
    }

    public void setFaultInfo(WebFaultBean faultInfo) {
        this.faultInfo = faultInfo;
    }

}
