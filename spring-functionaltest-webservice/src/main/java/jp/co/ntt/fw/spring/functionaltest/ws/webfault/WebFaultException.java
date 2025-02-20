/*
 * Copyright(c) 2014 NTT Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package jp.co.ntt.fw.spring.functionaltest.ws.webfault;

import java.util.List;

import javax.xml.ws.WebFault;

@WebFault(name = "WebFault", targetNamespace = "http://functionaltest.spring.fw.ntt.co.jp/todo")
public class WebFaultException extends Exception {

    private static final long serialVersionUID = 1L;

    private transient WebFaultBean faultInfo;

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
        return this.faultInfo;
    }

    public void setFaultInfo(WebFaultBean faultInfo) {
        this.faultInfo = faultInfo;
    }

}
