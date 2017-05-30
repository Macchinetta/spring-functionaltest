/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.repository.dam3;

import java.util.List;

import jp.co.ntt.fw.spring.functionaltest.domain.model.TodoMB3;

public interface TodoReUseRepository {

    List<TodoMB3> findAllTodos();

    int insert(TodoMB3 todoMB3);

    int delete(String todoId);

    int update(TodoMB3 todoMB3);

    TodoMB3 findOneByTodoId(String todoId);

}
