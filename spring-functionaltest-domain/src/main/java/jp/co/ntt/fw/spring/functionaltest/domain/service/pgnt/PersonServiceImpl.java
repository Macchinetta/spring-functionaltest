/*
 * Copyright(c) 2014 NTT Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.pgnt;

import java.util.Collections;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.inject.Inject;
import jp.co.ntt.fw.spring.functionaltest.domain.model.Person;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.pgnt.PageablePersonSearchCriteria;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.pgnt.PersonRepository;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.pgnt.PersonSearchCriteria;

@Service
public class PersonServiceImpl implements PersonService {

    @Inject
    protected PersonRepository personRepostiroy;

    @Override
    public Page<Person> getPersons(PersonSearchCriteria criteria,
            Pageable pageable) {
        List<Person> persons = null;
        PageablePersonSearchCriteria pageablePersonSearchCriteria = new PageablePersonSearchCriteria(criteria, pageable);

        long total = personRepostiroy.countByCriteria(
                pageablePersonSearchCriteria);

        if (0 < total) {
            persons = personRepostiroy.findPage(pageablePersonSearchCriteria);
        } else {
            persons = Collections.emptyList();
        }

        return new PageImpl<Person>(persons, pageable, total);
    }

}
