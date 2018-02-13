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
package jp.co.ntt.fw.spring.functionaltest.domain.service.djpa;

import javax.inject.Inject;

import jp.co.ntt.fw.spring.functionaltest.domain.model.JPABookLZ;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.djpa.JPABookRepositoryLZ;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(value = "jpaTransactionManager")
public class JPABookLZServiceImpl implements JPABookLZService {

    @Inject
    JPABookRepositoryLZ jpaBookRepositoryLZ;

    @Override
    public JPABookLZ findById(Integer id) {
        return jpaBookRepositoryLZ.findOne(id);
    }

    @Override
    public JPABookLZ addBook(JPABookLZ bookLZ) {
        return jpaBookRepositoryLZ.saveAndFlush(bookLZ);
    }

}
