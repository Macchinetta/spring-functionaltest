/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.djpa;

import jp.co.ntt.fw.spring.functionaltest.domain.model.JPACategoryLZ;

public interface JPACategoryLzService {

    JPACategoryLZ getCategoryDetails(String categoryName);

}
