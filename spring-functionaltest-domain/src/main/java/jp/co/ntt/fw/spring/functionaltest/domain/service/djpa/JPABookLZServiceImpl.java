/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.djpa;

import javax.inject.Inject;

import jp.co.ntt.fw.spring.functionaltest.domain.model.JPABookLZ;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.djpa.JPABookRepositoryLZ;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(value = "jpaTransactionManager")
public class JPABookLZServiceImpl implements JPABookLZService {

    @Inject
    JPABookRepositoryLZ jpaBookRepositoryLZ;

    @Override
    public JPABookLZ findById(Integer id) {
        return jpaBookRepositoryLZ.findOne(id);
    }

    @Override
    public JPABookLZ addBook(JPABookLZ bookLZ) {
        return jpaBookRepositoryLZ.saveAndFlush(bookLZ);
    }

}
