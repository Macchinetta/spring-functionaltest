/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.djpa;

import java.util.List;

import jp.co.ntt.fw.spring.functionaltest.domain.model.JPABookEG;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface JPABookEGService {

    JPABookEG findById(Integer id);

    JPABookEG findByIdUsingJPARepository(Integer id);

    JPABookEG addBook(JPABookEG jpaBookEG);

    Page<JPABookEG> getPaginatedBooks(Pageable pageable);

    JPABookEG saveAndFlush(JPABookEG jpaBookEG);

    JPABookEG flush(JPABookEG jpaBookEG);

    List<JPABookEG> saveMultiple();

    List<JPABookEG> createJPAEGBooks();

    JPABookEG saveUsingCustomRepo(JPABookEG bookEntity);

    JPABookEG findUsingCustomRepo(Integer bookId);

    JPABookEG saveUsingNoIntfInheriRepo(JPABookEG bookEntity);

    JPABookEG findUsingNoIntfInheriRepo(Integer bookId);

    void deleteAll();

    long count();

    JPABookEG findByTitle(String bookTitle);

    JPABookEG updateUsingMyProjectRepo(JPABookEG jpaBookEG);

    JPABookEG findOneForUpdateLckTmeOutPessismisticLocking(Integer bookId,
            Integer sleepTime);

    JPABookEG findOneForUpdateLckTmeOutPessismisticLockingNoExcp(
            Integer bookId, Integer sleepTime);

}
