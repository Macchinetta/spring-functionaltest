/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.djpa;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.ntt.fw.spring.functionaltest.domain.model.JPACategory;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.djpa.JPACategoryRepository;

@Service
@Transactional(value = "jpaTransactionManager")
public class JPACategorySeviceImpl implements JPACategoryService {

    @Inject
    JPACategoryRepository JPACategoryRepository;

    @Override
    public JPACategory getCategoryDetails(String categoryName) {
        return JPACategoryRepository.findByCategoryName(categoryName);
    }

}
