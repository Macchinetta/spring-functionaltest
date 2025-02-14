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
package jp.co.ntt.fw.spring.functionaltest.config.ws;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.xml.namespace.QName;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import jakarta.xml.ws.Service;
import jp.co.ntt.fw.spring.functionaltest.domain.service.soap.factory.AuthTodoWebServiceFactoryBean;
import jp.co.ntt.fw.spring.functionaltest.domain.service.soap.factory.TimeoutTodoWebServiceFactoryBean;
import jp.co.ntt.fw.spring.functionaltest.domain.service.soap.factory.WsdlFileTodoWebServiceFactoryBean;
import jp.co.ntt.fw.spring.functionaltest.ws.soap.TodoWebService;

/**
 * Bean definitions for domain layer(Client Proxy).
 */
@Configuration
public class SpringFunctionaltestWebSoapDomainWsConfig {

    /**
     * Location of wsdl Since JAX-WS does not have a function equivalent to lookupServiceOnStartup,
     * specify a local file. If you want to use the same functionality as
     * lookupServiceOnStartup(true), please specify soap.wsdlDocumentResource.url.
     */
    @Value("${soap.wsdlDocumentResource.file}")
    private String wsdlDocumentResource;

    /** endpoint */
    @Value("${soap.endpointAddress}")
    private String endpointAddress;

    /** username(user) */
    @Value("${soap.user.username}")
    private String username;

    /** password(user) */
    @Value("${soap.user.password}")
    private String password;

    /** username(anonymous) */
    @Value("${soap.anonymous.username}")
    private String anonymousUsername;

    /** password(anonymous) */
    @Value("${soap.anonymous.password}")
    private String anonymousPassword;

    /** username(admin) */
    @Value("${soap.admin.username}")
    private String adminUsername;

    /** password(admin) */
    @Value("${soap.admin.password}")
    private String adminPassword;

    /** Timeout key string */
    @Value("${soap.request.timeout.key}")
    private String timeoutKey;

    /** Timeout value */
    @Value("${soap.request.timeout.value}")
    private long timeoutValue;

    /**
     * Configure {@link TodoWebService} beans.
     * @return Map of configured {@link TodoWebService}
     */
    @Bean("todoWebServices")
    public Map<String, TodoWebService> todoWebServices() throws Exception {
        Map<String, TodoWebService> beans = new LinkedHashMap<>();
        beans.put("todo", todoWebService());
        beans.put("wsdl", wsdlFileTodoWebService().getObject());
        beans.put("user", userTodoWebService().getObject());
        beans.put("anonymous", anonymousTodoWebService().getObject());
        beans.put("admin", adminTodoWebService().getObject());
        beans.put("timeout", timeoutTodoWebService().getObject());
        return beans;
    }

    /**
     * Configure {@link Service} bean.
     * @return Bean of configured {@link Service}
     * @throws MalformedURLException, URISyntaxException
     */
    @Bean("wsService")
    public Service wsService() throws MalformedURLException, URISyntaxException {
        QName serviceName =
                new QName("http://functionaltest.spring.fw.ntt.co.jp/todo", "TodoWebService");
        return Service.create(new URI(wsdlDocumentResource).toURL(), serviceName);
    }

    /**
     * Configure {@link TodoWebService} bean.
     * @return Bean of configured {@link TodoWebService}
     * @throws MalformedURLException, URISyntaxExceptio
     */
    @Bean("todoWebService")
    public TodoWebService todoWebService()
            throws MalformedURLException, MalformedURLException, URISyntaxException {
        QName portName = new QName("http://functionaltest.spring.fw.ntt.co.jp/todo", "TodoWebPort");
        return wsService().getPort(portName, TodoWebService.class);
    }

    /**
     * Configure {@link WsdlFileTodoWebServiceFactoryBean} bean.
     * @return Bean of configured {@link WsdlFileTodoWebServiceFactoryBean}
     */
    @Bean("wsdlFileTodoWebService")
    public WsdlFileTodoWebServiceFactoryBean wsdlFileTodoWebService() {
        WsdlFileTodoWebServiceFactoryBean factory = new WsdlFileTodoWebServiceFactoryBean();
        factory.setWsdlDocumentResource(wsdlDocumentResource);
        factory.setEndpointAddress(endpointAddress);
        return factory;
    }

    /**
     * Configure {@link AuthTodoWebServiceFactoryBean} bean.
     * @return Bean of configured {@link AuthTodoWebServiceFactoryBean}
     */
    @Bean("userTodoWebService")
    public AuthTodoWebServiceFactoryBean userTodoWebService() {
        AuthTodoWebServiceFactoryBean factory = new AuthTodoWebServiceFactoryBean();
        factory.setWsdlDocumentResource(wsdlDocumentResource);
        factory.setUserName(username);
        factory.setPassword(password);
        return factory;
    }

    /**
     * Configure {@link AuthTodoWebServiceFactoryBean} bean.
     * @return Bean of configured {@link AuthTodoWebServiceFactoryBean}
     */
    @Bean("anonymousTodoWebService")
    public AuthTodoWebServiceFactoryBean anonymousTodoWebService() {
        AuthTodoWebServiceFactoryBean factory = new AuthTodoWebServiceFactoryBean();
        factory.setWsdlDocumentResource(wsdlDocumentResource);
        factory.setUserName(anonymousUsername);
        factory.setPassword(anonymousPassword);
        return factory;
    }

    /**
     * Configure {@link AuthTodoWebServiceFactoryBean} bean.
     * @return Bean of configured {@link AuthTodoWebServiceFactoryBean}
     */
    @Bean("adminTodoWebService")
    public AuthTodoWebServiceFactoryBean adminTodoWebService() {
        AuthTodoWebServiceFactoryBean factory = new AuthTodoWebServiceFactoryBean();
        factory.setWsdlDocumentResource(wsdlDocumentResource);
        factory.setUserName(adminUsername);
        factory.setPassword(adminPassword);
        return factory;
    }

    /**
     * Configure {@link TimeoutTodoWebServiceFactoryBean} bean.
     * @return Bean of configured {@link TimeoutTodoWebServiceFactoryBean}
     */
    @Bean("timeoutTodoWebService")
    public TimeoutTodoWebServiceFactoryBean timeoutTodoWebService() {
        TimeoutTodoWebServiceFactoryBean factory = new TimeoutTodoWebServiceFactoryBean();
        factory.setWsdlDocumentResource(wsdlDocumentResource);
        factory.setTimeoutKey(timeoutKey);
        factory.setTimeoutValue(timeoutValue);
        return factory;
    }
}
