/*
 * Copyright(c) 2024 NTT Corporation.
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

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import jp.co.ntt.fw.spring.functionaltest.domain.cmmn.validation.AllowedSortProperty;
import jp.co.ntt.fw.spring.functionaltest.domain.model.Celebrity;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.pgnt.CelebritySearchCriteria;

@Validated
public interface CelebrityService {

    Page<Celebrity> getNames(CelebritySearchCriteria criteria,
            @AllowedSortProperty({ "celebrity_id" }) Pageable pageable);

}
