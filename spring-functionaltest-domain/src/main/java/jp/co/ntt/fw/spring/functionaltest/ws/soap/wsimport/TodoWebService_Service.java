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
package jp.co.ntt.fw.spring.functionaltest.ws.soap.wsimport;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;

/**
 * This class was generated by the JAX-WS RI. JAX-WS RI 2.2.9-b130926.1035 Generated source version: 2.2
 */
@WebServiceClient(name = "TodoWebService", targetNamespace = "http://functionaltest.spring.fw.ntt.co.jp/todo", wsdlLocation = "http://localhost:8080/spring-functionaltest-web/ws/TodoWebService?wsdl")
public class TodoWebService_Service extends Service {

    private final static URL TODOWEBSERVICE_WSDL_LOCATION;

    private final static WebServiceException TODOWEBSERVICE_EXCEPTION;

    private final static QName TODOWEBSERVICE_QNAME = new QName("http://functionaltest.spring.fw.ntt.co.jp/todo", "TodoWebService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8080/spring-functionaltest-web/ws/TodoWebService?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        TODOWEBSERVICE_WSDL_LOCATION = url;
        TODOWEBSERVICE_EXCEPTION = e;
    }

    public TodoWebService_Service() {
        super(__getWsdlLocation(), TODOWEBSERVICE_QNAME);
    }

    public TodoWebService_Service(WebServiceFeature... features) {
        super(__getWsdlLocation(), TODOWEBSERVICE_QNAME, features);
    }

    public TodoWebService_Service(URL wsdlLocation) {
        super(wsdlLocation, TODOWEBSERVICE_QNAME);
    }

    public TodoWebService_Service(URL wsdlLocation,
            WebServiceFeature... features) {
        super(wsdlLocation, TODOWEBSERVICE_QNAME, features);
    }

    public TodoWebService_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public TodoWebService_Service(URL wsdlLocation, QName serviceName,
            WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * @return returns TodoWebService
     */
    @WebEndpoint(name = "TodoWebPort")
    public TodoWebService getTodoWebPort() {
        return super.getPort(
                new QName("http://functionaltest.spring.fw.ntt.co.jp/todo", "TodoWebPort"),
                TodoWebService.class);
    }

    /**
     * @param features A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy. Supported features not in the
     *            <code>features</code> parameter will have their default values.
     * @return returns TodoWebService
     */
    @WebEndpoint(name = "TodoWebPort")
    public TodoWebService getTodoWebPort(WebServiceFeature... features) {
        return super.getPort(
                new QName("http://functionaltest.spring.fw.ntt.co.jp/todo", "TodoWebPort"),
                TodoWebService.class, features);
    }

    private static URL __getWsdlLocation() {
        if (TODOWEBSERVICE_EXCEPTION != null) {
            throw TODOWEBSERVICE_EXCEPTION;
        }
        return TODOWEBSERVICE_WSDL_LOCATION;
    }

}
