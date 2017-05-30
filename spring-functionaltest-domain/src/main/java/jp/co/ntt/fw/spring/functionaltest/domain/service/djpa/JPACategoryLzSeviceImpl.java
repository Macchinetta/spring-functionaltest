/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.djpa;

import javax.inject.Inject;

import jp.co.ntt.fw.spring.functionaltest.domain.model.JPACategoryLZ;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.djpa.JPACategoryLzRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(value = "jpaTransactionManager")
public class JPACategoryLzSeviceImpl implements JPACategoryLzService {

    @Inject
    JPACategoryLzRepository jpaCategoryLzRepository;

    @Override
    public JPACategoryLZ getCategoryDetails(String categoryName) {
        return jpaCategoryLzRepository.findByCategoryName(categoryName);
    }

}
