/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.djpa;

import java.util.List;

import jp.co.ntt.fw.spring.functionaltest.domain.model.JPABook;
import jp.co.ntt.fw.spring.functionaltest.domain.model.JPACategoryEG;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface JPABookService {

    List<JPABook> findAll();

    JPABook findById(String id);

    Iterable<JPACategoryEG> getAllCrudCategories();

    Page<JPABook> getItems(Pageable pageable);

    JPABook addBook(JPABook jpaBook);

    JPABook addBookWithRollback(JPABook jpaBook) throws Exception;

    void deleteAllInBatch();

    void delete(String bookId);

    void deleteInBatch(Iterable<String> iterable);

    void delete(Iterable<? extends JPABook> entities);

    void delete(JPABook jpaBook);

    long getBookCount();

    List<JPABook> getSortedBookList(String sortBy, String sortOrder);

    boolean isPresent(String bookId);

}
