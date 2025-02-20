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
package jp.co.ntt.fw.spring.functionaltest.domain.service.todo;

import java.util.Collection;
import jp.co.ntt.fw.spring.functionaltest.domain.model.Todo;

public interface TodoService {
    Todo findOne(String todoId, String registrationId);

    Collection<Todo> findAll(String registrationId);

    Todo create(Todo todo, String registrationId);

    Todo finish(String todoId, String registrationId);

    void delete(String todoId, String registrationId);

    // NoAuth Only
    default void deleteAll() {
        throw new UnsupportedOperationException();
    };
}
