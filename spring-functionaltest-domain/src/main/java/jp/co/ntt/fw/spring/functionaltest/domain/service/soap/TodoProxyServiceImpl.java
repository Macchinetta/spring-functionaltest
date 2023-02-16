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

import java.io.File;
import java.net.SocketTimeoutException;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.terasoluna.gfw.common.exception.BusinessException;
import org.terasoluna.gfw.common.exception.SystemException;

import jakarta.activation.DataHandler;
import jakarta.activation.FileDataSource;
import jakarta.annotation.Resource;
import jakarta.xml.ws.BindingProvider;
import jakarta.xml.ws.handler.MessageContext;
import jp.co.ntt.fw.spring.functionaltest.domain.model.Todo;
import jp.co.ntt.fw.spring.functionaltest.ws.soap.TodoWebService;
import jp.co.ntt.fw.spring.functionaltest.ws.webfault.ErrorBean;
import jp.co.ntt.fw.spring.functionaltest.ws.webfault.WebFaultException;
import jp.co.ntt.fw.spring.functionaltest.ws.webfault.WebFaultType;

@Service
public class TodoProxyServiceImpl implements TodoProxyService {

    private static final Logger logger = LoggerFactory.getLogger(
            TodoProxyServiceImpl.class);

    // @Resource
    private Map<String, TodoWebService> todoWebServices = MapUtils.EMPTY_MAP;

    @Override
    public List<Todo> getTodos(String webServiceKey) {
        TodoWebService todoWebService = getWebService(webServiceKey);

        // Webサービスの実行
        List<Todo> todos = todoWebService.getTodos();
        loggingHttpStatusCode(todoWebService);
        return todos;
    }

    @Override
    public Todo createTodo(String webServiceKey, Todo forCreateTodo) {
        TodoWebService todoWebService = getWebService(webServiceKey);

        forCreateTodo.setFinished(false);
        forCreateTodo.setCreatedAt(null);
        try {
            return todoWebService.createTodo(forCreateTodo);
        } catch (WebFaultException e) {
            loggingWebFault(e);
            throw new SystemException("e.sf.cmmn.9001", e);
            // TODO remoting deleted
            // } catch (RemoteAccessException e) {
            // throw e;
        } finally {
            loggingHttpStatusCode(todoWebService);
        }
    }

    @Override
    public Todo getTodo(String webServiceKey, String todoId) {
        TodoWebService todoWebService = getWebService(webServiceKey);

        try {
            return todoWebService.getTodo(todoId);
        } catch (WebFaultException e) {
            loggingWebFault(e);
            throw new SystemException("e.sf.cmmn.9001", e);
        } finally {
            loggingHttpStatusCode(todoWebService);
        }
    }

    @Override
    public void deleteTodos(String webServiceKey) {
        TodoWebService todoWebService = getWebService(webServiceKey);

        todoWebService.deleteTodos();
        loggingHttpStatusCode(todoWebService);
        return;
    }

    @Override
    public Todo updateTodo(String webServiceKey, Todo forUpdateTodo) {
        TodoWebService todoWebService = getWebService(webServiceKey);

        try {
            return todoWebService.updateTodo(forUpdateTodo);
        } catch (WebFaultException e) {
            loggingWebFault(e);
            throw new SystemException("e.sf.cmmn.9001", e);
        } finally {
            loggingHttpStatusCode(todoWebService);
        }
    }

    @Override
    public void deleteTodo(String webServiceKey, String todoId) {
        TodoWebService todoWebService = getWebService(webServiceKey);

        try {
            todoWebService.deleteTodo(todoId);
        } catch (WebFaultException e) {
            loggingWebFault(e);
            throw new SystemException("e.sf.cmmn.9001", e);
        } finally {
            loggingHttpStatusCode(todoWebService);
        }
    }

    @Override
    public void confirmHandler(String webServiceKey) {
        TodoWebService todoWebService = getWebService(webServiceKey);

        try {
            todoWebService.handlerTest();
        } catch (WebFaultException e) {
            loggingWebFault(e);
            throw new SystemException("e.sf.cmmn.9001", e);
            // TODO remoting deleted
            // } catch (JaxWsSoapFaultException e) {
            // throw e;
        } finally {
            loggingHttpStatusCode(todoWebService);
        }
    }

    @Override
    public void requestTimeout(
            String webServiceKey) throws InterruptedException {
        TodoWebService todoWebService = getWebService(webServiceKey);

        try {
            todoWebService.timeoutTest();
        } catch (WebFaultException e) {
            loggingWebFault(e);
            throw new SystemException("e.sf.cmmn.9001", e);
            // TODO remoting deleted
            // } catch (RemoteAccessException e) {
            // Throwable cause = e;
            // while ((cause = cause.getCause()) != null) {
            // if (cause instanceof SocketTimeoutException) {
            // throw new SystemException("e.sf.cmmn.9001", cause);
            // }
            // }
            // throw e;
        } finally {
            loggingHttpStatusCode(todoWebService);
        }
    }

    @Override
    public void uploadFile(String webServiceKey, File temporaryFile) {
        TodoWebService todoWebService = getWebService(webServiceKey);

        try {
            FileDataSource fdSource = new FileDataSource(temporaryFile);
            DataHandler handler = new DataHandler(fdSource);
            // ファイルを扱うWebサービスの実行
            if (!todoWebService.uploadFile(handler)) {
                throw new BusinessException("Did not match Uploaded file and server file.");
            }

        } catch (WebFaultException e) {
            loggingWebFault(e);
            throw new SystemException("e.sf.cmmn.9001", e);
        } finally {
            loggingHttpStatusCode(todoWebService);
        }

    }

    /**
     * Web Service Proxyを取得する。
     * @param webServiceKey Web Service Proxyを取得するキー
     * @return Web Service Proxyオブジェクト
     * @exception SystemException キーに該当するWeb Service Proxyオブジェクトが存在しない。
     */
    private TodoWebService getWebService(String webServiceKey) {
        if (todoWebServices.containsKey(webServiceKey)) {
            return todoWebServices.get(webServiceKey);
        } else {
            throw new SystemException("e.sf.cmmn.9001", "web service "
                    + webServiceKey + " is not found...");
        }
    }

    /**
     * HTTPステータスコードをログ出力する。
     * @param tws Web Service Proxyオブジェクト
     */
    private void loggingHttpStatusCode(TodoWebService tws) {
        logger.info("httpStatusCode={}", ((BindingProvider) tws)
                .getResponseContext().get(MessageContext.HTTP_RESPONSE_CODE));
    }

    /**
     * WebFaultExceptionをログ出力する。
     * @param e WebFaultException
     */
    private void loggingWebFault(WebFaultException e) {
        WebFaultType type = e.getType();
        logger.error("webFaultType={}", type);

        List<ErrorBean> errors = e.getErrors();
        logger.error("errorBeanSize={}", errors.size());
        for (ErrorBean error : errors) {
            logger.error("code:message:path={}:{}:{}", error.getCode(), error
                    .getMessage(), error.getPath());
        }
    }

}
