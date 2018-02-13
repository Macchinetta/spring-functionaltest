/*
 * Copyright 2014-2017 NTT Corporation.
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
 *
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.dam3;

import java.io.BufferedWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;

import jp.co.ntt.fw.spring.functionaltest.domain.model.AutoMapTodoMB3;
import jp.co.ntt.fw.spring.functionaltest.domain.model.CategoryMB3;
import jp.co.ntt.fw.spring.functionaltest.domain.model.TodoCriteria;
import jp.co.ntt.fw.spring.functionaltest.domain.model.TodoCriteria2;
import jp.co.ntt.fw.spring.functionaltest.domain.model.TodoMB3;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.dam3.TodoSearchCriteria;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TodoMB3Service {

    List<TodoMB3> findAllTodos();

    /**
     * mapping is specified in Mapper
     * @param todoId
     * @return
     */
    TodoMB3 findOneByTodoId(String todoId);

    TodoMB3 addTodo(TodoMB3 todoMB3);

    TodoMB3 addTodoWithNullTitle(TodoMB3 todoMB3);

    TodoMB3 addTodoForRollback(TodoMB3 todoMB3);

    void addBulkTodo();

    void addTodoReUseMode();

    TodoMB3 update(TodoMB3 todoMB3);

    void deleteByTodoId(String todoId);

    void deleteAll();

    TodoMB3 selectByAutoMap(String todoId);

    TodoMB3 selectByAsClause(String todoId);

    TodoMB3 findOne(String todoId);

    TodoMB3 findOneUsingCompositeKey(String todoId, String categoryName);

    TodoMB3 findOneUsingCompositeKeyNoParamAnnot(String todoId,
            String categoryName);

    List<TodoMB3> findAllByCriteria(TodoCriteria criteria);

    Map<String, AutoMapTodoMB3> findAllByCriteriaRetMap(TodoCriteria criteria);

    long getTodoCountForGivenStatus(boolean finished);

    public Page<TodoMB3> searchTodos(TodoCriteria criteria, Pageable pageable);

    Page<TodoMB3> findPageByCriteriaSqLRef(TodoCriteria criteria,
            Pageable pageable);

    boolean createAndReturnBoolean(TodoMB3 todoMB3);

    int createAndReturnInt(TodoMB3 todoMB3);

    TodoMB3 createUsingAutoIncreament(TodoMB3 todoMB3);

    int createInBatch();

    TodoMB3 updateTodo(TodoMB3 todoMB3);

    int updateFinishedByTodIds(boolean finished, List<String> todoIds);

    boolean delete(TodoMB3 todo);

    int deleteOlderFinishedTodo(Date date);

    List<TodoMB3> findAllByCriteriaIFEle(TodoCriteria todoCriteria);

    List<TodoMB3> findAllByCriteriaOSGNL(TodoCriteria todoCriteria);

    List<TodoMB3> findAllByCriteriaEscapeSrch(TodoCriteria todoCriteria);

    void downloadTodos(TodoCriteria criteria,
            final BufferedWriter downloadWriter);

    AutoMapTodoMB3 findOneBySP(String todoId);

    CategoryMB3 getCategory(String categoryId);

    List<AutoMapTodoMB3> findByUsingClassTypeAlias(
            TodoSearchCriteria todoSearchCriteria);

    List<AutoMapTodoMB3> findByUsingOverwrittenDefltTypeAliasName(
            TodoCriteria2 todoSearchCriteria);

    int updateUsingBatchFinishedByTodIds(boolean finished,
            List<String> todoIds);

    List<TodoMB3> findUsingChooseEle(TodoCriteria todoCriteria);

}
