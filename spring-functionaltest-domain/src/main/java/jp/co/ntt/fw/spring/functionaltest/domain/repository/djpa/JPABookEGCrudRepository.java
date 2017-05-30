/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.repository.djpa;

import jp.co.ntt.fw.spring.functionaltest.domain.model.JPABookEG;

import org.springframework.data.repository.CrudRepository;

public interface JPABookEGCrudRepository extends
                                        CrudRepository<JPABookEG, Integer> {

}
