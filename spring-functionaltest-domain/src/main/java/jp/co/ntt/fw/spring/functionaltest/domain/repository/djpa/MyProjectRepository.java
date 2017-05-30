/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.repository.djpa;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface MyProjectRepository<T, ID extends Serializable>
                                                                 extends
                                                                 JpaRepository<T, ID> {

    T findOneWithValidVersion(ID id, Long version);
}
