/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.soap;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import javax.inject.Inject;

import jp.co.ntt.fw.spring.functionaltest.domain.model.Todo;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.terasoluna.gfw.common.date.jodatime.JodaTimeDateFactory;
import org.terasoluna.gfw.common.exception.BusinessException;
import org.terasoluna.gfw.common.exception.ResourceNotFoundException;
import org.terasoluna.gfw.common.message.ResultMessages;

@Service
@Transactional
public class TodoServiceImpl implements TodoService {

    private static final int TODO_MAX_COUNT = 5;

    @Inject
    JodaTimeDateFactory dateFactory;

    private final ConcurrentHashMap<String, Todo> todos = new ConcurrentHashMap<String, Todo>();

    public List<Todo> getTodos() {
        return new ArrayList<>(todos.values());
    }

    public Todo getTodo(String todoId) {
        if (!todos.containsKey(todoId)) {
            throw new ResourceNotFoundException(ResultMessages.error().add(
                    "e.sf.soap.5001", todoId));
        }
        return todos.get(todoId);
    }

    public Todo createTodo(Todo todo) {
        if (todos.size() >= TODO_MAX_COUNT) {
            throw new BusinessException(ResultMessages.error().add(
                    "e.sf.soap.8001"));
        }
        todo.setTodoId(UUID.randomUUID().toString());
        todo.setCreatedAt(dateFactory.newDate());
        todos.put(todo.getTodoId(), todo);
        return todo;
    }

    public Todo updateTodo(Todo todo) {
        Todo targetTodo = getTodo(todo.getTodoId());
        targetTodo.setTitle(todo.getTitle());
        targetTodo.setDescription(todo.getDescription());
        targetTodo.setFinished(todo.isFinished());
        return targetTodo;
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteTodo(String todoId) {
        todos.remove(todoId);
    }

    @Override
    public void deleteTodos() {
        todos.clear();
    }

    @Override
    public boolean uploadFile(InputStream stream) throws IOException {
        InputStream checkStream = new ClassPathResource("testdata/soap/test.png")
                .getInputStream();
        return IOUtils.contentEquals(stream, checkStream);
    }

}
