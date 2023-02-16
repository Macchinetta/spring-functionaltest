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
package jp.co.ntt.fw.spring.functionaltest.domain.repository.djpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;

import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import jp.co.ntt.fw.spring.functionaltest.domain.model.JPABookEG;

public interface JPABookEGRepository extends JpaRepository<JPABookEG, Integer> {

    @Query("SELECT book FROM JPABookEG book WHERE book.title = :bTitle")
    JPABookEG findByTitle(@Param("bTitle") String bookTitle);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT book FROM JPABookEG book WHERE book.bookId = :bookId ORDER BY book.bookId")
    @QueryHints(value = {
            @QueryHint(name = "jakarta.persistence.lock.timeout", value = "0") })
    JPABookEG findByBookIdForUpdate(@Param("bookId") Integer bookId);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT book FROM JPABookEG book WHERE book.bookId = :bookId ORDER BY book.bookId")
    @QueryHints(value = {
            @QueryHint(name = "jakarta.persistence.lock.timeout", value = "12000") })
    JPABookEG findByBookIdForUpdateNoExcp(@Param("bookId") Integer bookId);

}
