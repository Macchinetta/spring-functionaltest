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
import java.util.List;
import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import jp.co.ntt.fw.spring.functionaltest.domain.model.Todo;

@Service
@Transactional
public class TodoNoAuthServiceImpl implements TodoService {

    @Value("${oth2.resource.server.base-uri:http://localhost:8080/spring-functionaltest-web-oauth2-resource}")
    private String resourceServerBaseUri;

    @Inject
    @Named("noAuthRestTemplate")
    private RestTemplate restOperations;

    @Override
    @Transactional(readOnly = true)
    public Todo findOne(@NotEmpty String todoId, @Nullable String registrationId) {

        // @formatter:off
        RequestEntity<Void> requestEntity = RequestEntity
                .get(this.resourceServerBaseUri + "/noauth/" + todoId)
                .build();
        // @formatter:on

        ResponseEntity<Todo> responseEntity =
                this.restOperations.exchange(requestEntity, Todo.class);
        Todo todo = responseEntity.getBody();

        return todo;
    }

    @Override
    public Collection<Todo> findAll(@Nullable String registrationId) {

        RequestEntity<Void> requestEntity =
                RequestEntity.get(this.resourceServerBaseUri + "/noauth").build();

        ResponseEntity<List<Todo>> responseEntity =
                this.restOperations.exchange(requestEntity, new ParameterizedTypeReference<List<Todo>>() {});

        List<Todo> todos = responseEntity.getBody();

        return todos;
    }

    @Override
    public Todo create(@NotNull Todo todo, @Nullable String registrationId) {

        RequestEntity<Todo> requestEntity =
                RequestEntity.post(this.resourceServerBaseUri + "/noauth").body(todo);

        ResponseEntity<Todo> responseEntity =
                this.restOperations.exchange(requestEntity, Todo.class);
        Todo createdTodo = responseEntity.getBody();

        return createdTodo;
    }

    @Override
    public Todo finish(@NotEmpty String todoId, @Nullable String registrationId) {

        RequestEntity<Void> requestEntity =
                RequestEntity.put(this.resourceServerBaseUri + "/noauth/" + todoId).build();

        ResponseEntity<Todo> responseEntity =
                this.restOperations.exchange(requestEntity, Todo.class);
        Todo finishedTodo = responseEntity.getBody();

        return finishedTodo;
    }

    @Override
    public void delete(@NotEmpty String todoId, @Nullable String registrationId) {

        RequestEntity<Void> requestEntity =
                RequestEntity.delete(this.resourceServerBaseUri + "/noauth/" + todoId).build();

        this.restOperations.exchange(requestEntity, void.class);
    }

    @Override
    public void deleteAll() {

        RequestEntity<Void> requestEntity =
                RequestEntity.delete(this.resourceServerBaseUri + "/noauth").build();

        this.restOperations.exchange(requestEntity, void.class);
    }
}
