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
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import jp.co.ntt.fw.spring.functionaltest.domain.model.AsClauseTodoMB3;
import jp.co.ntt.fw.spring.functionaltest.domain.model.AutoMapTodoMB3;
import jp.co.ntt.fw.spring.functionaltest.domain.model.CategoryMB3;
import jp.co.ntt.fw.spring.functionaltest.domain.model.TodoCriteria;
import jp.co.ntt.fw.spring.functionaltest.domain.model.TodoCriteria2;
import jp.co.ntt.fw.spring.functionaltest.domain.model.TodoMB3;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.dam3.CategoryMB3Repository;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.dam3.TodoBatchRepository;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.dam3.TodoReUseRepository;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.dam3.TodoRepository;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.dam3.TodoSearchCriteria;

import org.apache.commons.io.IOUtils;
import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import com.github.dozermapper.core.Mapper;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.terasoluna.gfw.common.exception.ResourceNotFoundException;
import org.terasoluna.gfw.common.exception.SystemException;
import org.terasoluna.gfw.common.message.ResultMessages;

@Transactional
@Service
public class TodoMB3ServiceImpl implements TodoMB3Service {

    private static final Logger logger = LoggerFactory.getLogger(
            TodoMB3ServiceImpl.class);

    @Inject
    TodoRepository todoRepository;

    @Inject
    CategoryMB3Repository categoryMB3Repository;

    @Inject
    TodoReUseRepository todoReUseRepositry;

    @Inject
    @Named("todoBatchRepository")
    TodoBatchRepository todoBatchRepository;

    @Inject
    Mapper beanMapper;

    @Value("${rollback.msg}")
    protected String rollbackMsg;

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormat
            .forPattern("yyyy/MM/dd");

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
            }
        }
        return normDesc1;
    }

    @Override
    public TodoMB3 findOneByTodoId(String todoId) {
        TodoMB3 todoMB3 = todoRepository.findOneByTodoId(todoId);
        try (InputStream inputStream = todoMB3.getDesc1();
                Reader reader = todoMB3.getDesc2()) {
            todoMB3.setNormDesc1(normalizeDesc1(inputStream));
            todoMB3.setNormDesc2(normalizeDesc2(reader));
        } catch (IOException e) {
        }
        return todoMB3;
    }

    @Override
    public TodoMB3 addTodo(TodoMB3 todoMB3) {
        CategoryMB3 categoryMB3 = categoryMB3Repository.findOneByName(todoMB3
                .getCategory().getName());
        todoMB3.setFinished(false);
        todoMB3.setCreatedAt(DateTimeFormat.forPattern("yyyy/MM/dd")
                .parseDateTime("2016/12/28").toDate());

        todoMB3.setCategory(categoryMB3);
        todoRepository.insert(todoMB3);

        return todoMB3;
    }

    @Override
    public TodoMB3 addTodoForRollback(TodoMB3 todoMB3) {
        CategoryMB3 categoryMB3 = categoryMB3Repository.findOneByName(todoMB3
                .getCategory().getName());
        todoMB3.setFinished(false);
        todoMB3.setCreatedAt(DateTimeFormat.forPattern("yyyy/MM/dd")
                .parseDateTime("2016/12/28").toDate());

        todoMB3.setCategory(categoryMB3);
        todoRepository.insert(todoMB3);

        throw new SystemException("e.sf.dam3.9002", rollbackMsg);
    }

    @Override
    public void addBulkTodo() {

        int numOfTodos = 1000;

        TodoMB3 todoMB3;
        CategoryMB3 cat;
        List<TodoMB3> todoList = new ArrayList<TodoMB3>();
        for (int i = 11; i <= numOfTodos; i++) {
            cat = new CategoryMB3();
            cat.setCategoryId("0000000005");
            todoMB3 = new TodoMB3();
            todoMB3.setCreatedAt(DateTimeFormat.forPattern("yyyy/MM/dd")
                    .parseDateTime("2016/12/29").toDate());
            todoMB3.setFinished(true);
            todoMB3.setCompleteAt(DateTimeFormat.forPattern("yyyy/MM/dd")
                    .parseDateTime("2016/12/30"));
            todoMB3.setCategory(cat);
            todoMB3.setTodoId(String.format("%010d", i));
            todoMB3.setTodoTitle("TT" + i);
            todoMB3.setVersion(1);
            todoList.add(todoMB3);
        }

        for (TodoMB3 todoMB3Obj : todoList) {
            todoRepository.insert(todoMB3Obj);
        }

    }

    @Override
    public void addTodoReUseMode() {

        List<TodoMB3> todoList = new ArrayList<TodoMB3>();

        TodoMB3 todoMB3 = new TodoMB3();
        CategoryMB3 cat = new CategoryMB3();
        for (int i = 11; i <= 1000; i++) {
            cat = new CategoryMB3();
            cat.setCategoryId("0000000005");
            todoMB3 = new TodoMB3();
            todoMB3.setCreatedAt(DateTimeFormat.forPattern("yyyy/MM/dd")
                    .parseDateTime("2016/12/29").toDate());
            todoMB3.setFinished(true);
            todoMB3.setCompleteAt(DateTimeFormat.forPattern("yyyy/MM/dd")
                    .parseDateTime("2016/12/30"));
            todoMB3.setCategory(cat);
            todoMB3.setTodoId(String.format("%010d", i));
            todoMB3.setTodoTitle("TT" + i);
            todoMB3.setVersion(1);
            todoList.add(todoMB3);
        }

        for (TodoMB3 todoMB3Obj : todoList) {
            todoReUseRepositry.insert(todoMB3Obj);
        }

    }

    @Override
    public TodoMB3 addTodoWithNullTitle(TodoMB3 todoMB3) {
        CategoryMB3 categoryMB3 = categoryMB3Repository.findOneByName(todoMB3
                .getCategory().getName());
        todoMB3.setFinished(false);
        todoMB3.setTodoTitle(null);
        todoMB3.setCreatedAt(DateTimeFormat.forPattern("yyyy/MM/dd")
                .parseDateTime("2016/12/28").toDate());

        todoMB3.setCategory(categoryMB3);
        todoRepository.insert(todoMB3);

        return todoMB3;
    }

    @Override
    public TodoMB3 update(TodoMB3 todoMB3) {
        todoRepository.update(todoMB3);
        return todoMB3;
    }

    @Override
    public void deleteByTodoId(String todoId) {
        todoRepository.delete(todoId);

    }

    @Override
    public void deleteAll() {
        todoRepository.deleteAll();

    }

    @Override
    public TodoMB3 selectByAutoMap(String todoId) {
        AutoMapTodoMB3 todoMB3Auto = todoRepository.selectTodoByAutoMap(todoId);
        CategoryMB3 categoryMB3 = categoryMB3Repository
                .selectTodoCategoryIdAutoMap(todoMB3Auto.getCategoryId());
        TodoMB3 todoMB3 = beanMapper.map(todoMB3Auto, TodoMB3.class);
        todoMB3.setCategory(categoryMB3);
        return todoMB3;
    }

    @Override
    public TodoMB3 selectByAsClause(String todoId) {
        AsClauseTodoMB3 asClauseTodoMB3 = todoRepository.selectTodoByAsClause(
                todoId);
        CategoryMB3 categoryMB3 = categoryMB3Repository
                .selectTodoCategoryIdAutoMap(asClauseTodoMB3.getCatCode());

        TodoMB3 todoMB3 = new TodoMB3();
        todoMB3.setCategory(categoryMB3);
        todoMB3.setTodoId(asClauseTodoMB3.getTodoNum());
        todoMB3.setTodoTitle(asClauseTodoMB3.getTodoName());
        todoMB3.setFinished(asClauseTodoMB3.isStatus());
        todoMB3.setCreatedAt(asClauseTodoMB3.getOriginDate());
        todoMB3.setVersion(asClauseTodoMB3.getVer());
        return todoMB3;
    }

    /**
     * No Mapping Specified for search in mapper
     * @param todoId
     * @return
     */
    @Transactional(readOnly = true)
    @Override
    public TodoMB3 findOne(String todoId) {
        AutoMapTodoMB3 todoMB3Auto = todoRepository.findOne(todoId);
        if (todoMB3Auto == null) {
            throw new ResourceNotFoundException(ResultMessages.error().add(
                    "e.ex.td.5001", todoId));
        }
        CategoryMB3 categoryMB3 = categoryMB3Repository
                .selectTodoCategoryIdAutoMap(todoMB3Auto.getCategoryId());
        TodoMB3 todoMB3 = beanMapper.map(todoMB3Auto, TodoMB3.class);
        todoMB3.setCategory(categoryMB3);
        return todoMB3;
    }

    @Override
    public TodoMB3 findOneUsingCompositeKey(String todoId,
            String categoryName) {
        CategoryMB3 category = categoryMB3Repository.findOneByName(
                categoryName);
        if (category == null) {
            throw new ResourceNotFoundException(ResultMessages.error().add(
                    "e.ex.td.5001", todoId));
        }
        AutoMapTodoMB3 todoMB3Auto = todoRepository.findOneUsingCompositeKey(
                todoId, category.getCategoryId());
        if (todoMB3Auto == null) {
            throw new ResourceNotFoundException(ResultMessages.error().add(
                    "e.ex.td.5001", todoId));
        }

        TodoMB3 todoMB3 = beanMapper.map(todoMB3Auto, TodoMB3.class);
        todoMB3.setCategory(category);
        return todoMB3;
    }

    @Override
    public TodoMB3 findOneUsingCompositeKeyNoParamAnnot(String todoId,
            String categoryName) {
        CategoryMB3 category = categoryMB3Repository.findOneByName(
                categoryName);
        if (category == null) {
            throw new ResourceNotFoundException(ResultMessages.error().add(
                    "e.ex.td.5001", todoId));
        }
        AutoMapTodoMB3 todoMB3Auto = todoRepository
                .findOneUsingCompositeKeyNoParamAnnot(todoId, category
                        .getCategoryId());
        if (todoMB3Auto == null) {
            throw new ResourceNotFoundException(ResultMessages.error().add(
                    "e.ex.td.5001", todoId));
        }
        TodoMB3 todoMB3 = beanMapper.map(todoMB3Auto, TodoMB3.class);
        todoMB3.setCategory(category);
        return todoMB3;
    }

    @Override
    public List<TodoMB3> findAllByCriteria(TodoCriteria criteria) {
        List<AutoMapTodoMB3> todoList = todoRepository.findAllByCriteria(
                criteria);
        List<TodoMB3> todoMB3s = new ArrayList<TodoMB3>();
        for (AutoMapTodoMB3 autoMapTodoMB3 : todoList) {
            CategoryMB3 categoryMB3 = categoryMB3Repository
                    .selectTodoCategoryIdAutoMap(autoMapTodoMB3
                            .getCategoryId());
            TodoMB3 todoMB3 = beanMapper.map(autoMapTodoMB3, TodoMB3.class);
            todoMB3.setCategory(categoryMB3);
            todoMB3s.add(todoMB3);
        }

        return todoMB3s;
    }

    @Override
    public Map<String, AutoMapTodoMB3> findAllByCriteriaRetMap(
            TodoCriteria criteria) {
        return todoRepository.findAllByCriteriaRetMap(criteria);
    }

    @Override
    public long getTodoCountForGivenStatus(boolean finished) {
        return todoRepository.countByFinished(finished);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<TodoMB3> searchTodos(TodoCriteria criteria, Pageable pageable) {
        long total = todoRepository.countByCriteria(criteria);
        List<TodoMB3> todos;
        if (0 < total) {
            RowBounds rowBounds = new RowBounds((int) pageable
                    .getOffset(), pageable.getPageSize());
            todos = todoRepository.findPageByCriteria(criteria, rowBounds);
        } else {
            todos = Collections.emptyList();
        }
        return new PageImpl<>(todos, pageable, total);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<TodoMB3> findPageByCriteriaSqLRef(TodoCriteria criteria,
            Pageable pageable) {
        long total = todoRepository.countByCriteria(criteria);
        List<TodoMB3> todos;
        if (0 < total) {
            todos = todoRepository.findPageByCriteriaSqLRef(criteria, pageable);
        } else {
            todos = Collections.emptyList();
        }
        return new PageImpl<>(todos, pageable, total);
    }

    @Override
    public boolean createAndReturnBoolean(TodoMB3 todoMB3) {
        boolean result = false;
        CategoryMB3 categoryMB3 = categoryMB3Repository.findOneByName(todoMB3
                .getCategory().getName());
        todoMB3.setFinished(false);
        todoMB3.setCreatedAt(DateTimeFormat.forPattern("yyyy/MM/dd")
                .parseDateTime("2016/12/28").toDate());

        todoMB3.setCategory(categoryMB3);
        try {
            result = todoRepository.createAndReturnBoolean(todoMB3);
        } catch (Exception e) {
            logger.debug(e.getMessage());
        }
        return result;
    }

    @Override
    public int createAndReturnInt(TodoMB3 todoMB3) {
        int result = 0;
        CategoryMB3 categoryMB3 = categoryMB3Repository.findOneByName(todoMB3
                .getCategory().getName());
        todoMB3.setFinished(false);
        todoMB3.setCreatedAt(DateTimeFormat.forPattern("yyyy/MM/dd")
                .parseDateTime("2016/12/28").toDate());

        todoMB3.setCategory(categoryMB3);
        try {
            result = todoRepository.createAndReturnInt(todoMB3);
        } catch (Exception e) {
            logger.debug(e.getMessage());
        }
        return result;
    }

    @Override
    public TodoMB3 createUsingAutoIncreament(TodoMB3 todoMB3) {
        CategoryMB3 categoryMB3 = categoryMB3Repository.findOneByName(todoMB3
                .getCategory().getName());
        todoMB3.setFinished(false);
        todoMB3.setCreatedAt(DateTimeFormat.forPattern("yyyy/MM/dd")
                .parseDateTime("2016/12/28").toDate());
        todoMB3.setCategory(categoryMB3);
        todoRepository.createUsingAutoIncreament(todoMB3);
        return todoMB3;
    }

    @Override
    public int createInBatch() {

        int numOfTodos = 10;

        TodoMB3 todoMB3;
        CategoryMB3 cat;
        List<TodoMB3> todoList = new ArrayList<TodoMB3>();
        for (int i = 1; i <= numOfTodos; i++) {
            cat = new CategoryMB3();

            todoMB3 = new TodoMB3();
            todoMB3.setCreatedAt(DateTimeFormat.forPattern("yyyy/MM/dd")
                    .parseDateTime("2016/12/29").toDate());
            if (i % 2 == 0) {
                cat.setCategoryId("0000000005");
                todoMB3.setCompleteAt(DateTimeFormat.forPattern("yyyy/MM/dd")
                        .parseDateTime("2016/12/30"));
                todoMB3.setFinished(true);
            } else {
                cat.setCategoryId("0000000003");
                todoMB3.setFinished(false);
            }
            todoMB3.setCategory(cat);
            todoMB3.setTodoId(String.format("%010d", i));
            todoMB3.setTodoTitle("TT" + i);
            todoMB3.setVersion(1);
            todoList.add(todoMB3);
        }
        return todoRepository.createInBatch(todoList);
    }

    @Override
    public TodoMB3 updateTodo(TodoMB3 todoMB3) {

        TodoMB3 currentTodo = todoRepository.findOneByTodoId(todoMB3
                .getTodoId());
        if (currentTodo == null || currentTodo.getVersion() != todoMB3
                .getVersion()) {
            throw new ObjectOptimisticLockingFailureException(TodoMB3.class, todoMB3
                    .getTodoId());
        }

        currentTodo.setTodoTitle(todoMB3.getTodoTitle());
        currentTodo.setFinished(todoMB3.isFinished());

        int updated = todoRepository.updateTodo(currentTodo);
        if (updated == 0) {
            throw new ObjectOptimisticLockingFailureException(TodoMB3.class, currentTodo
                    .getTodoId());
        }
        currentTodo.setVersion(todoMB3.getVersion() + 1);

        return currentTodo;
    }

    @Override
    public int updateFinishedByTodIds(boolean finished, List<String> todoIds) {
        return todoRepository.updateFinishedByTodIds(finished, todoIds);
    }

    @Override
    public boolean delete(TodoMB3 todo) {
        return todoRepository.deleteByTodo(todo);
    }

    @Override
    public int deleteOlderFinishedTodo(Date date) {
        return todoRepository.deleteOlderFinishedTodo(date);
    }

    @Override
    public List<TodoMB3> findAllByCriteriaIFEle(TodoCriteria todoCriteria) {
        return todoRepository.findAllByCriteriaIFEle(todoCriteria);
    }

    @Override
    public List<TodoMB3> findAllByCriteriaOSGNL(TodoCriteria todoCriteria) {
        return todoRepository.findAllByCriteriaOSGNL(todoCriteria);
    }

    @Override
    public List<TodoMB3> findAllByCriteriaEscapeSrch(
            TodoCriteria todoCriteria) {
        return todoRepository.findAllByCriteriaEscapeSrch(todoCriteria);
    }

    @Override
    public void downloadTodos(TodoCriteria criteria,
            final BufferedWriter downloadWriter) {

        ResultHandler<TodoMB3> handler = new ResultHandler<TodoMB3>() {
            @Override
            public void handleResult(ResultContext<? extends TodoMB3> context) {
                TodoMB3 todo = context.getResultObject();
                StringBuilder sb = new StringBuilder();
                try {
                    sb.append(todo.getTodoId());
                    sb.append(",");
                    sb.append(todo.getTodoTitle());
                    sb.append(",");
                    sb.append(todo.isFinished());
                    sb.append(",");
                    sb.append(DATE_FORMATTER.print(todo.getCreatedAt()
                            .getTime()));
                    sb.append(",");
                    sb.append(todo.getVersion());
                    downloadWriter.write(sb.toString());
                    downloadWriter.newLine();
                } catch (IOException e) {
                    throw new SystemException("e.xx.fw.9001", e);
                }
            }
        };

        todoRepository.collectAllByCriteria(criteria, handler);

    }

    @Override
    public AutoMapTodoMB3 findOneBySP(String todoId) {
        AutoMapTodoMB3 autoMapTodoMB3 = todoRepository.findOneBySP(todoId);
        return autoMapTodoMB3;
    }

    @Override
    public CategoryMB3 getCategory(String categoryId) {
        return categoryMB3Repository.selectTodoCategoryIdAutoMap(categoryId);
    }

    @Override
    public List<AutoMapTodoMB3> findByUsingClassTypeAlias(
            TodoSearchCriteria todoSearchCriteria) {
        return todoRepository.findByUsingClassTypeAlias(todoSearchCriteria);
    }

    @Override
    public List<AutoMapTodoMB3> findByUsingOverwrittenDefltTypeAliasName(
            TodoCriteria2 todoSearchCriteria) {
        return todoRepository.findByUsingOverwrittenDefltTypeAliasName(
                todoSearchCriteria);
    }

    @Override
    public int updateUsingBatchFinishedByTodIds(boolean finished,
            List<String> todoIds) {
        return todoBatchRepository.updateFinishedByTodIds(finished, todoIds);
    }

    @Override
    public List<TodoMB3> findUsingChooseEle(TodoCriteria todoCriteria) {
        List<TodoMB3> todoList = new ArrayList<TodoMB3>();
        todoList = todoRepository.findUsingChooseEle(todoCriteria);

        formatTodoList(todoList);
        return todoList;
    }

    /**
     * This method converts the blob / clob related data to String and set it in the ToDo
     * @param todoList
     */
    private void formatTodoList(List<TodoMB3> todoList) {
        for (TodoMB3 todoMB3 : todoList) {
            try (InputStream inputStream = todoMB3.getDesc1();
                    Reader reader = todoMB3.getDesc2()) {
                String normDesc1 = normalizeDesc1(inputStream);
                String normDesc2 = normalizeDesc2(reader);
                todoMB3.setNormDesc1(normDesc1);
                todoMB3.setNormDesc2(normDesc2);
            } catch (IOException e) {
            }
        }
    }

}
