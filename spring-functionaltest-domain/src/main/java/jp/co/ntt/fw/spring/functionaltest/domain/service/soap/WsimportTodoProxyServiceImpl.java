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
package jp.co.ntt.fw.spring.functionaltest.domain.service.soap;

import java.util.List;

import javax.inject.Inject;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.MessageContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import jp.co.ntt.fw.spring.functionaltest.ws.soap.wsimport.Todo;
import jp.co.ntt.fw.spring.functionaltest.ws.soap.wsimport.TodoWebService;
import jp.co.ntt.fw.spring.functionaltest.ws.soap.wsimport.TodoWebService_Service;

@Service
public class WsimportTodoProxyServiceImpl implements WsimportTodoProxyService {

    private static final Logger logger =
            LoggerFactory.getLogger(WsimportTodoProxyServiceImpl.class);

    @Inject
    protected TodoWebService wsimportTodoWebService;

    @Override
    public List<Todo> getTodosJaxWsPortProxy() {
        // JaxWsPortProxyFactoryBeanを使用してアクセス（wsimportで出力したソースを一部使用:WebService, Model）
        List<Todo> todos = wsimportTodoWebService.getTodos();

        loggingHttpStatusCode(wsimportTodoWebService);
        loggingHttpRespnseHeader(wsimportTodoWebService);

        return todos;
    }

    @Override
    public List<Todo> getTodosWsimport() {
        // wsdlファイルをもとに、wsimportで生成したクラスを使いアクセス
        TodoWebService_Service service = new TodoWebService_Service();
        TodoWebService todoWebServicePort = service.getTodoWebPort();
        List<Todo> todos = todoWebServicePort.getTodos();

        loggingHttpStatusCode(todoWebServicePort);
        loggingHttpRespnseHeader(todoWebServicePort);

        return todos;
    }

    /**
     * HTTPステータスコードをログ出力する。
     * @param tws Web Service Proxyオブジェクト
     */
    private void loggingHttpStatusCode(TodoWebService tws) {
        logger.info("httpStatusCode={}", ((BindingProvider) tws).getResponseContext()
                .get(MessageContext.HTTP_RESPONSE_CODE));
    }

    /**
     * HTTPレスポンスヘッダをログ出力する。
     *
     * @param tws Web Service Proxyオブジェクト
     */
    private void loggingHttpRespnseHeader(TodoWebService tws) {
        logger.info("RespnseHeader={}", ((BindingProvider) tws).getResponseContext()
                .get(MessageContext.HTTP_RESPONSE_HEADERS));
    }
}
