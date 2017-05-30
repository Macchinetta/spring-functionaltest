/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.repository.djpa;

import jp.co.ntt.fw.spring.functionaltest.domain.model.JPABook;

import org.springframework.data.repository.CrudRepository;

public interface JPABookCrudRepository extends CrudRepository<JPABook, String> {

}
