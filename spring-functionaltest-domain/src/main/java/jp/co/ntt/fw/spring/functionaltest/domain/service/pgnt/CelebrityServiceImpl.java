/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.pgnt;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import jp.co.ntt.fw.spring.functionaltest.domain.model.Celebrity;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.pgnt.CelebrityRepository;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.pgnt.CelebritySearchCriteria;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.pgnt.PageableCelebritySearchCriteria;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class CelebrityServiceImpl implements CelebrityService {

    @Inject
    CelebrityRepository celebrityRepository;

    @Override
    public Page<Celebrity> getNames(CelebritySearchCriteria criteria,
            Pageable pageable) {

        List<Celebrity> names = null;
        PageableCelebritySearchCriteria celebritySearchCriteria = new PageableCelebritySearchCriteria(criteria, pageable);

        long total = celebrityRepository
                .countByCriteria(celebritySearchCriteria);

        if (0 < total) {
            names = celebrityRepository.findPage(celebritySearchCriteria);
        } else {
            names = new ArrayList<Celebrity>();
        }

        return new PageImpl<Celebrity>(names, pageable, total);
    }

}
