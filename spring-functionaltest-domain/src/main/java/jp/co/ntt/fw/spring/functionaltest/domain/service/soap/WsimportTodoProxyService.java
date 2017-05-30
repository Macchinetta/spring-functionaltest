/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.soap;

import java.util.List;

public interface WsimportTodoProxyService {

    List<jp.co.ntt.fw.spring.functionaltest.ws.soap.wsimport.Todo> getTodos();

}
