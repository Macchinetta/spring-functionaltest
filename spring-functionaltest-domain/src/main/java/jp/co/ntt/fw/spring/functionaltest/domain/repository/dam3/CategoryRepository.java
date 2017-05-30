/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.repository.dam3;

import jp.co.ntt.fw.spring.functionaltest.domain.model.Category;

public interface CategoryRepository {

    Category findOneByName(String name);

}
