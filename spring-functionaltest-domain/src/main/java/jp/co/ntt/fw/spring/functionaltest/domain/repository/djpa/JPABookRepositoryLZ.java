/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.repository.djpa;

import java.io.Serializable;

import jp.co.ntt.fw.spring.functionaltest.domain.model.JPABookLZ;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JPABookRepositoryLZ extends
                                    JpaRepository<JPABookLZ, Serializable> {

}
