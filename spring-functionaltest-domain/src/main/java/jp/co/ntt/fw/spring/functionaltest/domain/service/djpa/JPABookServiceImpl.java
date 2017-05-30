/*
 * Copyright(c) 2014-2017 NTT Corporation.
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

        Page<JPABook> page = jpaBookRepository.findAll(new PageRequest(pageable
                .getOffset(), pageable.getPageNumber()));
        return page;
    }

    @Override
    public JPABook findById(String id) {
        return jpaBookRepository.findOne(id);
    }

    @Override
    public JPABook addBook(JPABook jpaBook) {
        jpaBook.setBlobCode(jpaBook.getClobCode().getBytes(
                Charset.forName("UTF-8")));
        // return jpaBookRepository.saveAndFlush(jpaBook);
        return jpaBookRepository.save(jpaBook);
    }

    @Transactional(value = "jpaTransactionManager")
    @Override
    public JPABook addBookWithRollback(JPABook jpaBook) throws Exception {

        jpaBook.setBlobCode(jpaBook.getClobCode().getBytes(
                Charset.forName("UTF-8")));
        // return jpaBookRepository.saveAndFlush(jpaBook);
        JPABook book = jpaBookRepository.save(jpaBook);
        if (null != book) {
            throw new RuntimeException(rollbackMsg);
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
        return jpaBookRepository.exists(bookId);
    }

    @Override
    public void delete(String bookId) {
        jpaBookRepository.delete(bookId);

    }

    @Override
    public void deleteInBatch(Iterable<String> iterable) {
        Iterable<JPABook> bookList = jpaBookCRUDRepository.findAll(iterable);
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
        return jpaBookRepository.findAll(new Sort(direction, sortBy));
    }

    @Override
    public void delete(Iterable<? extends JPABook> entities) {
        jpaBookRepository.delete(entities);

    }

    @Override
    public void delete(JPABook jpaBook) {
        jpaBookRepository.delete(jpaBook);

    }

}
