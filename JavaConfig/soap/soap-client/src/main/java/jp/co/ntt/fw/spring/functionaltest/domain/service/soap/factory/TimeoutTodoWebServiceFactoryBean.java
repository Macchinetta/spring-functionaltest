/*
 * Copyright(c) 2025 NTT Corporation.
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
package jp.co.ntt.fw.spring.functionaltest.domain.service.soap.factory;

import java.net.URI;
import java.util.Map;
import javax.xml.namespace.QName;
import org.springframework.beans.factory.FactoryBean;
import jakarta.xml.ws.BindingProvider;
import jakarta.xml.ws.Service;
import jp.co.ntt.fw.spring.functionaltest.ws.soap.TodoWebService;

/**
 * TodoWebServiceのFactoryBean（タイムアウト設定用） ※XML表現が複雑な為、FactoryBeanにて定義
 */
public class TimeoutTodoWebServiceFactoryBean implements FactoryBean<TodoWebService> {

    private String wsdlDocumentResource;

    private String timeoutKey;

    private long timeoutValue;

    @Override
    public TodoWebService getObject() throws Exception {
        QName serviceName =
                new QName("http://functionaltest.spring.fw.ntt.co.jp/todo", "TodoWebService");
        QName portName = new QName("http://functionaltest.spring.fw.ntt.co.jp/todo", "TodoWebPort");

        Service service = Service.create(new URI(wsdlDocumentResource).toURL(), serviceName);
        TodoWebService todoWebService = service.getPort(portName, TodoWebService.class);

        BindingProvider bindingProvider = (BindingProvider) todoWebService;
        Map<String, Object> requestContext = bindingProvider.getRequestContext();
        requestContext.put(timeoutKey, timeoutValue);

        return todoWebService;
    }

    public void setWsdlDocumentResource(String wsdlDocumentResource) {
        this.wsdlDocumentResource = wsdlDocumentResource;
    }

    public void setTimeoutKey(String timeoutKey) {
        this.timeoutKey = timeoutKey;
    }

    public void setTimeoutValue(long timeoutValue) {
        this.timeoutValue = timeoutValue;
    }

    @Override
    public Class<?> getObjectType() {
        return TodoWebService.class;
    }
}
