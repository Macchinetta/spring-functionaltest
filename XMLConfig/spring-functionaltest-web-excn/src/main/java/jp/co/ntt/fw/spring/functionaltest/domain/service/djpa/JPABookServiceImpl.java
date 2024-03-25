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
package jp.co.ntt.fw.spring.functionaltest.domain.service.djpa;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.terasoluna.gfw.common.exception.SystemException;

import jakarta.inject.Inject;
import jp.co.ntt.fw.spring.functionaltest.domain.model.JPABook;
import jp.co.ntt.fw.spring.functionaltest.domain.model.JPACategory;
import jp.co.ntt.fw.spring.functionaltest.domain.model.JPACategoryEG;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.djpa.JPABookCrudRepository;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.djpa.JPABookRepository;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.djpa.JPACategoryEGCRUDRepository;

@Service
@Transactional(value = "jpaTransactionManager")
public class JPABookServiceImpl implements JPABookService {

    @Inject
    JPABookRepository jpaBookRepository;

    @Inject
    JPACategoryEGCRUDRepository jpaCategoryEGCRUDRepository;

    @Inject
    JPABookCrudRepository jpaBookCRUDRepository;

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

    @Override
    @Transactional(value = "jpaTransactionManager")
    public List<JPABook> saveMultipleFlush() {
        List<JPABook> entities = new ArrayList<JPABook>();

        Calendar cal = Calendar.getInstance();
        cal.set(2013, 11, 24);
        Date utilDate = cal.getTime();
        java.sql.Date releaseDate = new java.sql.Date(utilDate.getTime());

        JPABook book = new JPABook();
        book.setBookId("0000000001");
        book.setCategory(new JPACategory());
        book.getCategory().setCategoryId("0000000001");
        book.setTitle("TitleA011");
        book.setPrice(10000);
        book.setReleaseDate(releaseDate);
        book.setClobCode("CodeA011");
        book.setBlobCode("CodeA011".getBytes());
        entities.add(book);

        book = new JPABook();
        book.setBookId("0000000002");
        book.setCategory(new JPACategory());
        book.getCategory().setCategoryId("0000000001");
        book.setTitle("TitleA012");
        book.setPrice(10000);
        book.setReleaseDate(releaseDate);
        book.setClobCode("CodeA012");
        book.setBlobCode("CodeA012".getBytes());
        entities.add(book);

        book = new JPABook();
        book.setBookId("0000000003");
        book.setCategory(new JPACategory());
        book.getCategory().setCategoryId("0000000001");
        book.setTitle("TitleA013");
        book.setPrice(10000);
        book.setReleaseDate(releaseDate);
        book.setClobCode("CodeA013");
        book.setBlobCode("CodeA013".getBytes());
        entities.add(book);

        book = new JPABook();
        book.setBookId("0000000004");
        book.setCategory(new JPACategory());
        book.getCategory().setCategoryId("0000000002");
        book.setTitle("TitleB011");
        book.setPrice(10000);
        book.setReleaseDate(releaseDate);
        book.setClobCode("CodeB011");
        book.setBlobCode("CodeB011".getBytes());
        entities.add(book);

        return jpaBookRepository.saveAllAndFlush(entities);
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
        jpaBookRepository.deleteAllInBatch(bookList);
    }

    @Override
    public void deleteAllById(Iterable<String> iterable) {
        jpaBookRepository.deleteAllById(iterable);
    }

    @Override
    public void deleteAllByIdInBatch(Iterable<String> iterable) {
        jpaBookRepository.deleteAllByIdInBatch(iterable);
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
