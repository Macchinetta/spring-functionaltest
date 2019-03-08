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
package jp.co.ntt.fw.spring.functionaltest.domain.service.djpa;

import java.nio.charset.Charset;
import java.util.List;

import javax.inject.Inject;

import jp.co.ntt.fw.spring.functionaltest.domain.model.JPABook;
import jp.co.ntt.fw.spring.functionaltest.domain.model.JPACategoryEG;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.djpa.JPABookCrudRepository;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.djpa.JPABookEGCrudRepository;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.djpa.JPABookRepository;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.djpa.JPACategoryEGCRUDRepository;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.djpa.JPACategoryRepository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.terasoluna.gfw.common.exception.SystemException;

@Service
@Transactional(value = "jpaTransactionManager")
public class JPABookServiceImpl implements JPABookService {

    @Inject
    JPABookRepository jpaBookRepository;

    @Inject
    JPACategoryRepository jpaCategoryRepository;

    @Inject
    JPACategoryEGCRUDRepository jpaCategoryEGCRUDRepository;

    @Inject
    JPABookCrudRepository jpaBookCRUDRepository;

    @Inject
    JPABookEGCrudRepository jpaBookCrudRepository;

    @Value("${rollback.msg}")
    protected String rollbackMsg;

    @Transactional(value = "jpaTransactionManager")
    public List<JPABook> findAll() {
        List<JPABook> bookList = jpaBookRepository.findAll();
        return bookList;
    }

    @Override
    public Iterable<JPACategoryEG> getAllCrudCategories() {
        return jpaCategoryEGCRUDRepository.findAll();
    }

    @Override
    public Page<JPABook> getItems(Pageable pageable) {

        Page<JPABook> page = jpaBookRepository.findAll(pageable);
        return page;
    }

    @Override
    public JPABook findById(String id) {
        return jpaBookRepository.findById(id).orElse(null);
    }

    @Override
    public JPABook addBook(JPABook jpaBook) {
        jpaBook.setBlobCode(jpaBook.getClobCode().getBytes(Charset.forName(
                "UTF-8")));
        // return jpaBookRepository.saveAndFlush(jpaBook);
        return jpaBookRepository.save(jpaBook);
    }

    @Transactional(value = "jpaTransactionManager")
    @Override
    public JPABook addBookWithRollback(JPABook jpaBook) throws SystemException {

        jpaBook.setBlobCode(jpaBook.getClobCode().getBytes(Charset.forName(
                "UTF-8")));
        // return jpaBookRepository.saveAndFlush(jpaBook);
        JPABook book = jpaBookRepository.save(jpaBook);
        if (null != book) {
            throw new SystemException("e.sf.djpa.9001", rollbackMsg);
        }
        return book;
    }

    @Transactional(value = "jpaTransactionManager")
    @Override
    public void deleteAllInBatch() {

        jpaBookRepository.deleteAllInBatch();

    }

    @Override
    public boolean isPresent(String bookId) {
        return jpaBookRepository.existsById(bookId);
    }

    @Override
    public void delete(String bookId) {
        jpaBookRepository.deleteById(bookId);

    }

    @Override
    public void deleteInBatch(Iterable<String> iterable) {
        Iterable<JPABook> bookList = jpaBookCRUDRepository.findAllById(
                iterable);
        jpaBookRepository.deleteInBatch(bookList);
    }

    @Override
    public long getBookCount() {
        return jpaBookRepository.count();
    }

    @Override
    public List<JPABook> getSortedBookList(String sortBy, String sortOrder) {
        Direction direction = null;
        if ("ASC".equals(sortOrder)) {
            direction = Direction.ASC;
        } else if ("DESC".equals(sortOrder)) {
            direction = Direction.DESC;
        }
        return jpaBookRepository.findAll(Sort.by(direction, sortBy));
    }

    @Override
    public void delete(Iterable<? extends JPABook> entities) {
        jpaBookRepository.deleteAll(entities);

    }

    @Override
    public void delete(JPABook jpaBook) {
        jpaBookRepository.delete(jpaBook);

    }

}
