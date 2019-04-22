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
package jp.co.ntt.fw.spring.functionaltest.domain.service.dam3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import jp.co.ntt.fw.spring.functionaltest.domain.model.DateMB3;
import jp.co.ntt.fw.spring.functionaltest.domain.model.TodoMB3;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.dam3.CategoryMB3Repository;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.dam3.ShowDateMB3Repository;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.dam3.TodoBatchRepository;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.dam3.TodoReUseRepository;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.dam3.TodoRepository;

import org.apache.commons.io.IOUtils;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.terasoluna.gfw.common.exception.SystemException;

@Transactional
@Service
public class TodoMB3ForJSR310ServiceImpl implements TodoMB3ForJSR310Service {

    private static final Logger logger = LoggerFactory.getLogger(
            TodoMB3ForJSR310ServiceImpl.class);

    @Inject
    TodoRepository todoRepository;

    @Inject
    CategoryMB3Repository categoryMB3Repository;

    @Inject
    TodoReUseRepository todoReUseRepositry;

    @Inject
    ShowDateMB3Repository showDateMB3Repository;

    @Inject
    @Named("todoBatchRepository")
    TodoBatchRepository todoBatchRepository;

    @Inject
    Mapper beanMapper;

    @Value("${rollback.msg}")
    protected String rollbackMsg;

    @Override
    public List<TodoMB3> findAllTodos() {
        List<TodoMB3> todoList = new ArrayList<TodoMB3>();
        todoList = todoRepository.findAllTodos();

        formatTodoList(todoList);
        return todoList;
    }

    private String normalizeDesc2(Reader reader) {
        String normDesc2 = "";
        if (null != reader) {
            try (BufferedReader in4 = new BufferedReader(reader)) {
                normDesc2 = in4.readLine();
            } catch (IOException e) {
                throw new SystemException("e.sf.dam3.9001", "input/output error.", e);
            }
        }
        return normDesc2;
    }

    private String normalizeDesc1(InputStream inputStream) {
        String normDesc1 = "";
        if (null != inputStream) {
            try (StringWriter writer = new StringWriter()) {
                IOUtils.copy(inputStream, writer, "UTF-8");
                normDesc1 = writer.toString();
            } catch (IOException e) {
                throw new SystemException("e.sf.dam3.9001", "input/output error.", e);
            } finally {
                IOUtils.closeQuietly(inputStream);
            }
        }
        return normDesc1;
    }

    @Override
    public DateMB3 findCreatedAtOne(String todoId) {
        DateMB3 dateMB3 = showDateMB3Repository.selectDate(todoId);
        return dateMB3;

    }

    /**
     * This method converts the blob / clob related data to String and set it in the ToDo
     * @param todoList
     */
    private void formatTodoList(List<TodoMB3> todoList) {
        for (TodoMB3 todoMB3 : todoList) {
            InputStream inputStream = todoMB3.getDesc1();

            String normDesc1 = normalizeDesc1(inputStream);

            Reader reader = todoMB3.getDesc2();
            String normDesc2 = normalizeDesc2(reader);
            todoMB3.setNormDesc1(normDesc1);
            todoMB3.setNormDesc2(normDesc2);
        }
    }

}
