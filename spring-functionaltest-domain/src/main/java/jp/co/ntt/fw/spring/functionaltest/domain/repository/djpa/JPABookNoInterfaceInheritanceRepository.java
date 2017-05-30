/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.repository.djpa;

import org.springframework.data.repository.RepositoryDefinition;

import jp.co.ntt.fw.spring.functionaltest.domain.model.JPABookEG;

@RepositoryDefinition(domainClass = JPABookEG.class, idClass = Integer.class)
public interface JPABookNoInterfaceInheritanceRepository {
    JPABookEG findOne(Integer id);

    JPABookEG save(JPABookEG entity);
}
