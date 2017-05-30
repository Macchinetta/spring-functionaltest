/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.repository.djpa;

import java.io.Serializable;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

@NoRepositoryBean
public interface CommonProjectRepository<T, ID extends Serializable>
                                                                     extends
                                                                     Repository<T, ID> {

    T findOne(ID id);

    T save(T entity);

}
