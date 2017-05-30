/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.soap;

import java.io.File;
import java.util.List;

import jp.co.ntt.fw.spring.functionaltest.domain.model.Todo;

public interface TodoProxyService {

    List<Todo> getTodos(String webServiceKey);

    Todo getTodo(String webServiceKey, String todoId);

    Todo createTodo(String webServiceKey, Todo todo);

    Todo updateTodo(String webServiceKey, Todo todo);

    void deleteTodo(String webServiceKey, String todoId);

    void deleteTodos(String webServiceKey);

    void confirmHandler(String webServiceKey);

    void requestTimeout(String webServiceKey);

    void uploadFile(String webServiceKey, File file);

}
