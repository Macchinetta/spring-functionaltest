/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.soap;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

import org.springframework.validation.annotation.Validated;

import jp.co.ntt.fw.spring.functionaltest.domain.model.Todo;

@Validated
public interface TodoService {

    List<Todo> getTodos();

    Todo getTodo(@NotNull String todoId);

    Todo createTodo(@Valid Todo todo);

    @Validated({ Default.class, Todo.Update.class })
    Todo updateTodo(@Valid Todo todo);

    void deleteTodo(@NotNull String todoId);

    void deleteTodos();

    boolean uploadFile(InputStream stream) throws IOException;

}
