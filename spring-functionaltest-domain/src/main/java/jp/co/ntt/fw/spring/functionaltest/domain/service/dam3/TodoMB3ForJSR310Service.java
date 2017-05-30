/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.dam3;

import java.util.List;

import jp.co.ntt.fw.spring.functionaltest.domain.model.DateMB3;
import jp.co.ntt.fw.spring.functionaltest.domain.model.TodoMB3;

public interface TodoMB3ForJSR310Service {

    List<TodoMB3> findAllTodos();

    /**
     * mapping is specified in Mapper
     * @param todoId
     * @return
     */
    DateMB3 findCreatedAtOne(String todoId);

}
