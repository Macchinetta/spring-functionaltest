/*
 * Copyright 2014-2017 NTT Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.soap;

import java.util.List;

import javax.inject.Inject;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.MessageContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import jp.co.ntt.fw.spring.functionaltest.ws.soap.wsimport.TodoWebService;

@Service
public class WsimportTodoProxyServiceImpl implements WsimportTodoProxyService {

    private static final Logger logger = LoggerFactory.getLogger(
            WsimportTodoProxyServiceImpl.class);

    @Inject
    protected TodoWebService wsimportTodoWebService;

    @Override
    public List<jp.co.ntt.fw.spring.functionaltest.ws.soap.wsimport.Todo> getTodos() {

        List<jp.co.ntt.fw.spring.functionaltest.ws.soap.wsimport.Todo> todos = wsimportTodoWebService
                .getTodos();

        loggingHttpStatusCode(wsimportTodoWebService);

        return todos;
    }

    /**
     * HTTPステータスコードをログ出力する。
     * @param tws Web Service Proxyオブジェクト
     */
    private void loggingHttpStatusCode(TodoWebService tws) {
        logger.info("httpStatusCode={}", ((BindingProvider) tws)
                .getResponseContext().get(MessageContext.HTTP_RESPONSE_CODE));
    }

}
