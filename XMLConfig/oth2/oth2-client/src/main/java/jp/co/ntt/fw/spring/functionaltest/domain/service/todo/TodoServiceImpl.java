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

    @Value("${oth2.resource.server.base-uri}")
    private String resourceServerBaseUri;

    @Resource
    @Named("oauthRestTemplateMap")
    private Map<String, RestTemplate> resttemplates;

    @Override
    @Transactional(readOnly = true)
    public Todo findOne(@NotEmpty String todoId, @NotEmpty String registrationId) {

        RequestEntity<Void> requestEntity = RequestEntity
                .get(this.resourceServerBaseUri + "/auth/intercepturl/" + todoId).build();

        ResponseEntity<Todo> responseEntity =
                getRestTemplate(registrationId).exchange(requestEntity, Todo.class);

        return responseEntity.getBody();
    }

    @Override
    public Collection<Todo> findAll(@NotEmpty String registrationId) {

        RequestEntity<Void> requestEntity =
                RequestEntity.get(this.resourceServerBaseUri + "/auth/intercepturl").build();

        ResponseEntity<List<Todo>> responseEntity = getRestTemplate(registrationId)
                .exchange(requestEntity, new ParameterizedTypeReference<>() {});

        return responseEntity.getBody();
    }

    @Override
    public Todo create(@NotNull Todo todo, @NotEmpty String registrationId) {

        RequestEntity<Todo> requestEntity =
                RequestEntity.post(this.resourceServerBaseUri + "/auth/intercepturl").body(todo);

        ResponseEntity<Todo> responseEntity =
                getRestTemplate(registrationId).exchange(requestEntity, Todo.class);

        return responseEntity.getBody();

    }

    @Override
    public Todo finish(@NotEmpty String todoId, @NotEmpty String registrationId) {

        RequestEntity<Void> requestEntity = RequestEntity
                .put(this.resourceServerBaseUri + "/auth/intercepturl/" + todoId).build();

        ResponseEntity<Todo> responseEntity =
                getRestTemplate(registrationId).exchange(requestEntity, Todo.class);

        return responseEntity.getBody();
    }

    @Override
    public void delete(@NotEmpty String todoId, @NotEmpty String registrationId) {

        RequestEntity<Void> requestEntity = RequestEntity
                .delete(this.resourceServerBaseUri + "/auth/intercepturl/" + todoId).build();

        getRestTemplate(registrationId).exchange(requestEntity, void.class);
    }

    private RestTemplate getRestTemplate(String registrationId) {
        return this.resttemplates.get(registrationId);
    }
}
