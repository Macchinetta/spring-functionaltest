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
package jp.co.ntt.fw.spring.functionaltest.domain.service.oth2;

import java.time.Clock;
import java.time.LocalDate;
import java.util.Collection;
import java.util.UUID;
import javax.inject.Inject;
import javax.inject.Named;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.terasoluna.gfw.common.date.jodatime.JodaTimeDateTimeFactory;
import org.terasoluna.gfw.common.exception.BusinessException;
import org.terasoluna.gfw.common.exception.ResourceNotFoundException;
import org.terasoluna.gfw.common.message.ResultMessages;
import jp.co.ntt.fw.spring.functionaltest.domain.model.oth2.Todo;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.oth2.TodoRepository;

@Service
@Transactional
public class TodoServiceImpl implements TodoService {

    private static final long MAX_UNFINISHED_COUNT = 1;

    @Inject
    TodoRepository todoRepository;

    @Inject
    @Named("defaultJodaTimeDateFactory")
    JodaTimeDateTimeFactory factory;

    @Override
    @Transactional(readOnly = true)
    public Todo findOne(String todoId) {
        return this.todoRepository.findById(todoId).orElseThrow(() -> {
            ResultMessages messages = ResultMessages.error();
            messages.add("E404", todoId);
            return new ResourceNotFoundException(messages);
        });
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Todo> findAll() {
        return this.todoRepository.findAll();
    }

    @Override
    public Todo create(Todo todo) {
        long unfinishedCount = this.todoRepository.countByFinished(false);
        if (unfinishedCount >= MAX_UNFINISHED_COUNT) {
            ResultMessages messages = ResultMessages.error();
            messages.add("E001", MAX_UNFINISHED_COUNT);
            throw new BusinessException(messages);
        }

        String todoId = UUID.randomUUID().toString();
        LocalDate createdAt = LocalDate.now(Clock.systemDefaultZone());

        todo.setTodoId(todoId);
        todo.setCreatedAt(createdAt);
        todo.setFinished(false);

        this.todoRepository.create(todo);

        return todo;
    }

    @Override
    public Todo finish(String todoId) {
        Todo todo = findOne(todoId);
        if (todo.isFinished()) {
            ResultMessages messages = ResultMessages.error();
            messages.add("E002", todoId);
            throw new BusinessException(messages);
        }
        todo.setFinished(true);
        this.todoRepository.update(todo);

        return todo;
    }

    @Override
    public void delete(String todoId) {
        Todo todo = findOne(todoId);
        this.todoRepository.delete(todo);
    }

    @Override
    public void deleteAll() {
        this.todoRepository.deleteAll();
    }
}
