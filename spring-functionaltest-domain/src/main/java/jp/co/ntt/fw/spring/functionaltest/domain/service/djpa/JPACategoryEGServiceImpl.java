/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.djpa;

import javax.inject.Inject;

import jp.co.ntt.fw.spring.functionaltest.domain.model.JPACategoryEG;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.djpa.JPACategoryEGCRUDRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(value = "jpaTransactionManager")
public class JPACategoryEGServiceImpl implements JPACategoryEGService {

    @Inject
    JPACategoryEGCRUDRepository jpaCategoryEGCRUDRepository;

    @Override
    public JPACategoryEG getCategoryDetails(String categoryName) {
        return jpaCategoryEGCRUDRepository.findByCategoryName(categoryName);
    }

}
