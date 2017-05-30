/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.repository.djpa;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;

import jp.co.ntt.fw.spring.functionaltest.domain.model.JPABookEG;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;

public interface JPABookEGRepository extends JpaRepository<JPABookEG, Integer> {

    @Query("SELECT book FROM JPABookEG book WHERE book.title = :bTitle")
    JPABookEG findByTitle(@Param("bTitle") String bookTitle);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT book FROM JPABookEG book WHERE book.bookId = :bookId ORDER BY book.bookId")
    @QueryHints(value = { @QueryHint(name = "javax.persistence.lock.timeout", value = "0") })
    JPABookEG findOneForUpdate(@Param("bookId") Integer bookId);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT book FROM JPABookEG book WHERE book.bookId = :bookId ORDER BY book.bookId")
    @QueryHints(value = { @QueryHint(name = "javax.persistence.lock.timeout", value = "12000") })
    JPABookEG findOneForUpdateNoExcp(@Param("bookId") Integer bookId);

}
