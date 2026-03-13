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

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.validation.constraints.NotEmpty;
import jp.co.ntt.fw.spring.functionaltest.domain.model.Todo;

@Service
@Transactional
public class TodoNoIdSpecifiedServiceImpl implements TodoService {

    // @Value("${resource.serverUrl}/api/v1/todos/")
    @Value("${oth2.resource.server.base-uri}/auth/intercepturl/")
    private String resourceServerUri;

    @Inject
    @Named("readOnlyRestTemplate")
    RestTemplate restTemplate;

    @Override
    @Transactional(readOnly = true)
    public Todo findOne(@NotEmpty String todoId) {

        RequestEntity<Void> requestEntity =
                RequestEntity.get(this.resourceServerUri + todoId).build();

        ResponseEntity<Todo> responseEntity = restTemplate.exchange(requestEntity, Todo.class);

        return responseEntity.getBody();
    }

}
