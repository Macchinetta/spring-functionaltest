/*
 * Copyright 2014-2017 NTT Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
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

        long total = celebrityRepository.countByCriteria(
                celebritySearchCriteria);

        if (0 < total) {
            names = celebrityRepository.findPage(celebritySearchCriteria);
        } else {
            names = new ArrayList<Celebrity>();
        }

        return new PageImpl<Celebrity>(names, pageable, total);
    }

}
