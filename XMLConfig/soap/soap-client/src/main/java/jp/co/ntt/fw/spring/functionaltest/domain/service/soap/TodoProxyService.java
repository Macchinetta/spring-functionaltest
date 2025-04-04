/*
 * Copyright(c) 2025 NTT Corporation.
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

    void requestTimeout(String webServiceKey) throws InterruptedException;

    void uploadFile(String webServiceKey, File file);

}
