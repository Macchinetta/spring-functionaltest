/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.djpa;

import jp.co.ntt.fw.spring.functionaltest.domain.model.JPABookLZ;

public interface JPABookLZService {

    JPABookLZ findById(Integer id);

    JPABookLZ addBook(JPABookLZ bookLZ);

}
