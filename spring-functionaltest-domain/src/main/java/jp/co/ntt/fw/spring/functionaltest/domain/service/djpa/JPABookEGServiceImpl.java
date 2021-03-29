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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.ntt.fw.spring.functionaltest.domain.model.JPABookEG;
import jp.co.ntt.fw.spring.functionaltest.domain.model.JPACategoryEG;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.djpa.JPABookEGCrudRepository;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.djpa.JPABookEGMyProjectRepository;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.djpa.JPABookEGPaginationRepository;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.djpa.JPABookEGRepository;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.djpa.JPABookNoInterfaceInheritanceRepository;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.djpa.JPABookRepositoryCustom;

@Service
public class JPABookEGServiceImpl implements JPABookEGService {

    private static final Logger logger = LoggerFactory.getLogger(
            JPABookEGServiceImpl.class);

    @Inject
    JPABookEGCrudRepository jpaBookEGCrudRepository;

    @Inject
    JPABookEGPaginationRepository jpaBookEGPaginationRepository;

    @Inject
    JPABookEGRepository jpaBookEGRepository;

    @Inject
    JPABookRepositoryCustom jpaBookRepositoryCustom;

    @Inject
    JPABookNoInterfaceInheritanceRepository jpaBookNoInterfaceInheritanceRepository;

    @Inject
    JPABookEGMyProjectRepository jpaBookEGMyProjectRepository;

    @Override
    public JPABookEG findById(Integer id) {
        return jpaBookEGCrudRepository.findById(id).orElse(null);
    }

    @Transactional(value = "jpaTransactionManager")
    @Override
    public JPABookEG addBook(JPABookEG jpaBookEG) {
        return jpaBookEGCrudRepository.save(jpaBookEG);
    }

    @Override
    public Page<JPABookEG> getPaginatedBooks(Pageable pageable) {
        Page<JPABookEG> pagedBookList = jpaBookEGPaginationRepository.findAll(
                pageable);
        for (JPABookEG jpaBookEG : pagedBookList) {
            jpaBookEG.setBlobCodeHex(new String(Hex.encode(jpaBookEG
                    .getBlobCode())).toUpperCase());
        }
        return pagedBookList;
    }

    @Override
    @Transactional(value = "jpaTransactionManager")
    public List<JPABookEG> saveMultiple() {
        List<JPABookEG> entities = createJPAEGBooks();

        // delete all the books previously added.
        // jpaBookEGRepository.deleteAll();

        return jpaBookEGRepository.saveAll(entities);

    }

    @Transactional(value = "jpaTransactionManager")
    @Override
    public void deleteAll() {
        jpaBookEGRepository.deleteAll();

    }

    @Transactional(value = "jpaTransactionManager")
    @Override
    public long count() {
        return jpaBookEGRepository.count();
    }

    @Transactional(value = "jpaTransactionManager")
    @Override
    public JPABookEG saveAndFlush(JPABookEG jpaBookEG) {
        return jpaBookEGRepository.saveAndFlush(jpaBookEG);
    }

    @Override
    public List<JPABookEG> createJPAEGBooks() {
        List<JPABookEG> bookList = new ArrayList<JPABookEG>();
        for (int i = 4; i <= 18; i++) {
            JPABookEG bookEG = new JPABookEG();
            JPACategoryEG categoryEG = new JPACategoryEG();
            if (i % 2 == 0) {
                categoryEG.setCategoryId(2);
                categoryEG.setCategoryName("B01");
            } else {
                categoryEG.setCategoryId(7);
                categoryEG.setCategoryName("Z07");
            }
            bookEG.setCategory(categoryEG);
            String clobCode = "BX5D" + i;
            bookEG.setClobCode(clobCode);
            bookEG.setBlobCode(clobCode.getBytes());
            bookEG.setTitle("Manual Title " + i);

            Calendar cal = Calendar.getInstance();
            cal.set(2015, 10, 23);
            Date utilDate = cal.getTime();
            bookEG.setReleaseDate(new java.sql.Date(utilDate.getTime()));
            bookEG.setPrice(400 + i);

            bookList.add(bookEG);
        }
        return bookList;
    }

    @Transactional(value = "jpaTransactionManager")
    @Override
    public JPABookEG flush(JPABookEG jpaBookEG) throws InterruptedException {
        JPABookEG jpaBookEGManaged = jpaBookEGRepository.save(jpaBookEG);

        jpaBookEGRepository.flush();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            logger.debug(e.getMessage());
            throw e;
        }
        logger.debug("Debug:: after flush");
        return jpaBookEGManaged;
    }

    @Transactional(value = "jpaTransactionManager")
    @Override
    public JPABookEG saveUsingCustomRepo(JPABookEG bookEntity) {
        return jpaBookRepositoryCustom.save(bookEntity);
    }

    @Override
    public JPABookEG findUsingCustomRepo(Integer bookId) {
        return jpaBookRepositoryCustom.findById(bookId).orElse(null);
    }

    @Transactional(value = "jpaTransactionManager")
    @Override
    public JPABookEG saveUsingNoIntfInheriRepo(JPABookEG bookEntity) {
        return jpaBookNoInterfaceInheritanceRepository.save(bookEntity);
    }

    @Override
    public JPABookEG findUsingNoIntfInheriRepo(Integer bookId) {
        return jpaBookNoInterfaceInheritanceRepository.findById(bookId).orElse(
                null);
    }

    @Override
    public JPABookEG findByIdUsingJPARepository(Integer id) {
        return jpaBookEGRepository.findById(id).orElse(null);
    }

    @Override
    public JPABookEG findByTitle(String bookTitle) {
        return jpaBookEGRepository.findByTitle(bookTitle);
    }

    @Transactional(value = "jpaTransactionManager")
    @Override
    public JPABookEG updateUsingMyProjectRepo(JPABookEG jpaBookEG) {

        // Calling method
        JPABookEG jpaBookEGOrg = jpaBookEGMyProjectRepository
                .findByIdWithValidVersion(jpaBookEG.getBookId(), jpaBookEG
                        .getVersion());

        jpaBookEGOrg.setTitle(jpaBookEG.getTitle());

        return jpaBookEGMyProjectRepository.save(jpaBookEGOrg);
    }

    @Transactional(value = "jpaTransactionManager")
    @Override
    public JPABookEG findOneForUpdateLckTmeOutPessismisticLocking(
            Integer bookId, Integer sleepTime) throws InterruptedException {

        JPABookEG jpaBookEG = jpaBookEGRepository.findOneForUpdate(bookId);
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            logger.debug(e.getMessage());
            throw e;
        }
        return jpaBookEG;
    }

    @Transactional(value = "jpaTransactionManager")
    @Override
    public JPABookEG findOneForUpdateLckTmeOutPessismisticLockingNoExcp(
            Integer bookId, Integer sleepTime) throws InterruptedException {

        JPABookEG jpaBookEG = jpaBookEGRepository.findOneForUpdateNoExcp(
                bookId);
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            logger.debug(e.getMessage());
            throw e;
        }
        return jpaBookEG;
    }

}
