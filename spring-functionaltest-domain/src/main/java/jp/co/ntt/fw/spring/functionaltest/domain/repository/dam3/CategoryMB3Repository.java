/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.repository.dam3;

import jp.co.ntt.fw.spring.functionaltest.domain.model.CategoryMB3;

public interface CategoryMB3Repository {

    CategoryMB3 findOneByName(String name);

    CategoryMB3 selectTodoCategoryIdAutoMap(String categoryId);

}
