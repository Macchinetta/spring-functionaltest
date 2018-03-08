/*
 * Copyright 2014-2018 NTT Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
