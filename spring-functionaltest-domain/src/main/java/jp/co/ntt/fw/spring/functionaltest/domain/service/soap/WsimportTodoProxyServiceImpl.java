/*
 * Copyright(c) 2014-2017 NTT Corporation.
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

    private static final Logger logger = LoggerFactory
            .getLogger(WsimportTodoProxyServiceImpl.class);

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
