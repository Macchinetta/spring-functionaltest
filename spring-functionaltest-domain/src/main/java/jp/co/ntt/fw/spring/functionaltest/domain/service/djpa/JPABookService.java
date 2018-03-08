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
package jp.co.ntt.fw.spring.functionaltest.domain.service.djpa;

import java.util.List;

import jp.co.ntt.fw.spring.functionaltest.domain.model.JPABook;
import jp.co.ntt.fw.spring.functionaltest.domain.model.JPACategoryEG;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.terasoluna.gfw.common.exception.SystemException;

public interface JPABookService {

    List<JPABook> findAll();

    JPABook findById(String id);

    Iterable<JPACategoryEG> getAllCrudCategories();

    Page<JPABook> getItems(Pageable pageable);

    JPABook addBook(JPABook jpaBook);

    JPABook addBookWithRollback(JPABook jpaBook) throws SystemException;

    void deleteAllInBatch();

    void delete(String bookId);

    void deleteInBatch(Iterable<String> iterable);

    void delete(Iterable<? extends JPABook> entities);

    void delete(JPABook jpaBook);

    long getBookCount();

    List<JPABook> getSortedBookList(String sortBy, String sortOrder);

    boolean isPresent(String bookId);

}
