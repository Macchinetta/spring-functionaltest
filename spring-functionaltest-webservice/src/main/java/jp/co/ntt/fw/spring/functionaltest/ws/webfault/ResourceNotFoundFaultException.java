/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.ws.webfault;

import javax.xml.ws.WebFault;

@WebFault(name = "ResourceNotFoundFault", targetNamespace = "http://functionaltest.spring.fw.ntt.co.jp/todo")
public class ResourceNotFoundFaultException extends WebFaultException {

    private static final long serialVersionUID = 1L;

    public ResourceNotFoundFaultException(String message, WebFaultBean faultInfo) {
        super(message, faultInfo);
    }

    public ResourceNotFoundFaultException(String message,
            WebFaultBean faultInfo, Throwable e) {
        super(message, faultInfo, e);
    }

}
