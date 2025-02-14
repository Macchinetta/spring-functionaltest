/*
 * Copyright(c) 2024 NTT Corporation.
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
package jp.co.ntt.fw.spring.functionaltest.api.oth2.todo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import jakarta.inject.Inject;
import jp.co.ntt.fw.spring.functionaltest.domain.model.oth2.Todo;
import jp.co.ntt.fw.spring.functionaltest.domain.service.oth2.TodoService;

@RestController
@RequestMapping("auth")
public class TodoRestController {

    @Inject
    TodoService todoService;

    @Inject
    TodoResourceMapper beanMapper;

    // Controlled by intercept-url

    @GetMapping("intercepturl")
    @ResponseStatus(HttpStatus.OK)
    public List<TodoResource> getTodosWithInterceptUrl() {
        return getTodos();
    }

    @PostMapping("intercepturl")
    @ResponseStatus(HttpStatus.CREATED)
    public TodoResource postTodosWithInterceptUrl(
            @RequestBody @Validated TodoResource todoResource) {
        return postTodos(todoResource);
    }

    @PutMapping("intercepturl/{todoId}")
    @ResponseStatus(HttpStatus.OK)
    public TodoResource putTodoWithInterceptUrl(@PathVariable("todoId") String todoId) {
        return putTodo(todoId);
    }

    @DeleteMapping("intercepturl/{todoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTodoWithInterceptUrl(@PathVariable("todoId") String todoId) {
        deleteTodo(todoId);
    }

    // Controlled by @PreAuthorize

    @GetMapping("annotation")
    @PreAuthorize("hasAuthority('SCOPE_READ')")
    @ResponseStatus(HttpStatus.OK)
    public List<TodoResource> getTodosWithPreAuthorize() {
        return getTodos();
    }

    @PostMapping("annotation")
    @PreAuthorize("hasAuthority('SCOPE_CREATE')")
    @ResponseStatus(HttpStatus.CREATED)
    public TodoResource postTodosWithPreAuthorize(
            @RequestBody @Validated TodoResource todoResource) {
        return postTodos(todoResource);
    }

    @PutMapping("annotation/{todoId}")
    @PreAuthorize("hasAuthority('SCOPE_UPDATE')")
    @ResponseStatus(HttpStatus.OK)
    public TodoResource putTodoWithPreAuthorize(@PathVariable("todoId") String todoId) {
        return putTodo(todoId);
    }

    @DeleteMapping("annotation/{todoId}")
    @PreAuthorize("hasAuthority('SCOPE_DELETE')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTodoWithPreAuthorize(@PathVariable("todoId") String todoId) {
        deleteTodo(todoId);
    }

    // main processing

    private List<TodoResource> getTodos() {
        Collection<Todo> todos = this.todoService.findAll();
        List<TodoResource> todoResources = new ArrayList<>();
        for (Todo todo : todos) {
            todoResources.add(this.beanMapper.map(todo));
        }
        return todoResources;
    }

    private TodoResource postTodos(TodoResource todoResource) {
        Todo createdTodo = this.todoService.create(this.beanMapper.map(todoResource));
        TodoResource createdTodoResponse = this.beanMapper.map(createdTodo);
        return createdTodoResponse;
    }

    private TodoResource putTodo(String todoId) {
        Todo finishedTodo = this.todoService.finish(todoId);
        TodoResource finishedTodoResource = this.beanMapper.map(finishedTodo);
        return finishedTodoResource;
    }

    private void deleteTodo(String todoId) {
        this.todoService.delete(todoId);
    }

}
