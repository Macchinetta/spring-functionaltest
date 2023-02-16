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
package jp.co.ntt.fw.spring.functionaltest.api.oth2.todo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.http.HttpStatus;
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
@RequestMapping("noauth")
public class TodoRestNoAuthController {

    @Inject
    TodoService todoService;

    @Inject
    TodoResourceMapper beanMapper;

    // all list
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<TodoResource> getTodos() {
        Collection<Todo> todos = this.todoService.findAll();
        List<TodoResource> todoResources = new ArrayList<>();
        for (Todo todo : todos) {
            todoResources.add(this.beanMapper.map(todo));
        }
        return todoResources;
    }

    // list
    @GetMapping("{todoId}")
    @ResponseStatus(HttpStatus.OK)
    public TodoResource getTodo(@PathVariable("todoId") String todoId) {
        Todo todo = this.todoService.findOne(todoId);
        TodoResource todoResource = this.beanMapper.map(todo);
        return todoResource;
    }

    // create
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public TodoResource postTodos(
            @RequestBody @Validated TodoResource todoResource) {
        Todo createdTodo = this.todoService.create(this.beanMapper.map(
                todoResource));
        TodoResource createdTodoResponse = this.beanMapper.map(createdTodo);
        return createdTodoResponse;
    }

    // finished
    @PutMapping("{todoId}")
    @ResponseStatus(HttpStatus.OK)
    public TodoResource putTodo(@PathVariable("todoId") String todoId) {
        Todo finishedTodo = this.todoService.finish(todoId);
        TodoResource finishedTodoResource = this.beanMapper.map(finishedTodo);
        return finishedTodoResource;
    }

    // delete
    @DeleteMapping("{todoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTodo(@PathVariable("todoId") String todoId) {
        this.todoService.delete(todoId);
    }

    // delete all
    @DeleteMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAll() {
        this.todoService.deleteAll();
    }

}
