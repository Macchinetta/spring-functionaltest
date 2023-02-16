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
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import jakarta.annotation.Resource;
import jakarta.inject.Named;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jp.co.ntt.fw.spring.functionaltest.domain.model.Todo;

@Service
@Transactional
public class TodoServiceImpl implements TodoService {

    @Value("${resource.server.base-uri:http://localhost:8080/spring-functionaltest-web-oauth2-resource}")
    private String resourceServerBaseUri;

    @Resource
    @Named("oauthRestTemplateMap")
    Map<String, RestTemplate> resttemplates;

    @Override
    @Transactional(readOnly = true)
    public Todo findOne(@NotEmpty String todoId,
            @NotEmpty String registrationId) {

        // @formatter:off
        RequestEntity<Void> requestEntity = RequestEntity
                .get(this.resourceServerBaseUri + "/auth/intercepturl/" + todoId)
                .build();
        // @formatter:on

        ResponseEntity<Todo> responseEntity = getRestTemplate(registrationId)
                .exchange(requestEntity, Todo.class);
        Todo todo = responseEntity.getBody();

        return todo;
    }

    @Override
    public Collection<Todo> findAll(@NotEmpty String registrationId) {

        // @formatter:off
        RequestEntity<Void> requestEntity = RequestEntity
                .get(this.resourceServerBaseUri + "/auth/intercepturl")
                .build();
        // @formatter:on

        // @formatter:off
		ResponseEntity<List<Todo>> responseEntity = getRestTemplate(registrationId).exchange(requestEntity,
				new ParameterizedTypeReference<List<Todo>>() {});
        // @formatter:on

        List<Todo> todos = responseEntity.getBody();

        return todos;
    }

    @Override
    public Todo create(@NotNull Todo todo, @NotEmpty String registrationId) {

        // @formatter:off
        RequestEntity<Todo> requestEntity = RequestEntity
                .post(this.resourceServerBaseUri + "/auth/intercepturl")
                .body(todo);
        // @formatter:on

        ResponseEntity<Todo> responseEntity = getRestTemplate(registrationId)
                .exchange(requestEntity, Todo.class);

        Todo createdTodo = responseEntity.getBody();

        return createdTodo;

    }

    @Override
    public Todo finish(@NotEmpty String todoId,
            @NotEmpty String registrationId) {

        // @formatter:off
        RequestEntity<Void> requestEntity = RequestEntity
                .put(this.resourceServerBaseUri + "/auth/intercepturl/" + todoId)
                .build();
        // @formatter:on

        ResponseEntity<Todo> responseEntity = getRestTemplate(registrationId)
                .exchange(requestEntity, Todo.class);
        Todo finishedTodo = responseEntity.getBody();

        return finishedTodo;
    }

    @Override
    public void delete(@NotEmpty String todoId,
            @NotEmpty String registrationId) {

        // @formatter:off
        RequestEntity<Void> requestEntity = RequestEntity
                .delete(this.resourceServerBaseUri + "/auth/intercepturl/" + todoId)
                .build();
        // @formatter:on

        getRestTemplate(registrationId).exchange(requestEntity, void.class);
    }

    private RestTemplate getRestTemplate(String registrationId) {

        RestTemplate template = this.resttemplates.get(registrationId);

        // エラーハンドリングを実施したいため切り出したが、一旦後回し

        return template;
    }
}
