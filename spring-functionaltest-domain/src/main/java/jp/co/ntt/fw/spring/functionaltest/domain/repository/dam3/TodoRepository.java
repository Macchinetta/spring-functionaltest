/*
 * Copyright 2014-2018 NTT Corporation.
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
 */
package jp.co.ntt.fw.spring.functionaltest.domain.repository.dam3;

import java.util.Date;
import java.util.List;
import java.util.Map;

import jp.co.ntt.fw.spring.functionaltest.domain.model.AsClauseTodoMB3;
import jp.co.ntt.fw.spring.functionaltest.domain.model.AutoMapTodoMB3;
import jp.co.ntt.fw.spring.functionaltest.domain.model.TodoCriteria;
import jp.co.ntt.fw.spring.functionaltest.domain.model.TodoCriteria2;
import jp.co.ntt.fw.spring.functionaltest.domain.model.TodoMB3;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.data.domain.Pageable;

public interface TodoRepository {

    List<TodoMB3> findAllTodos();

    void insert(TodoMB3 todoMB3);

    int delete(String todoId);

    int update(TodoMB3 todoMB3);

    TodoMB3 findOneByTodoId(String todoId);

    void deleteAll();

    AutoMapTodoMB3 selectTodoByAutoMap(String todoId);

    AsClauseTodoMB3 selectTodoByAsClause(String todoId);

    AutoMapTodoMB3 findOne(String todoId);

    AutoMapTodoMB3 findOneUsingCompositeKey(@Param("todoId") String todoId,
            @Param("categoryId") String categoryId);

    AutoMapTodoMB3 findOneUsingCompositeKeyNoParamAnnot(String todoId,
            String categoryId);

    List<AutoMapTodoMB3> findAllByCriteria(TodoCriteria criteria);

    @MapKey("todoId")
    Map<String, AutoMapTodoMB3> findAllByCriteriaRetMap(TodoCriteria criteria);

    long countByFinished(boolean finished);

    long countByCriteria(TodoCriteria criteria);

    List<TodoMB3> findPageByCriteria(TodoCriteria criteria,
            RowBounds rowBounds);

    List<TodoMB3> findPageByCriteriaSqLRef(
            @Param("criteria") TodoCriteria criteria,
            @Param("pageable") Pageable pageable);

    boolean createAndReturnBoolean(TodoMB3 todoMB3);

    int createAndReturnInt(TodoMB3 todoMB3);

    void createUsingAutoIncreament(TodoMB3 todoMB3);

    void createUsingUseGeneratedKeys(TodoMB3 todoMB3);

    int createInBatch(List<TodoMB3> todoMB3s);

    int updateTodo(TodoMB3 todoMB3);

    int updateFinishedByTodIds(@Param("finished") boolean finished,
            @Param("todoIds") List<String> todoIds);

    boolean deleteByTodo(TodoMB3 todo);

    int deleteOlderFinishedTodo(Date date);

    List<TodoMB3> findAllByCriteriaIFEle(TodoCriteria todoCriteria);

    List<TodoMB3> findAllByCriteriaOSGNL(TodoCriteria todoCriteria);

    List<TodoMB3> findAllByCriteriaEscapeSrch(TodoCriteria todoCriteria);

    void collectAllByCriteria(TodoCriteria todoCriteria,
            ResultHandler<TodoMB3> handler);

    AutoMapTodoMB3 findOneBySP(String todoId);

    List<AutoMapTodoMB3> findByUsingClassTypeAlias(
            TodoSearchCriteria todoSearchCriteria);

    List<AutoMapTodoMB3> findByUsingOverwrittenDefltTypeAliasName(
            TodoCriteria2 todoSearchCriteria);

    List<TodoMB3> findUsingChooseEle(TodoCriteria todoCriteria);

}
