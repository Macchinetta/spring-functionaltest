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
package jp.co.ntt.fw.spring.functionaltest.domain.repository.djpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import jp.co.ntt.fw.spring.functionaltest.domain.model.JPACategoryEG;

public interface JPACategoryEGCRUDRepository extends JpaRepository<JPACategoryEG, Integer> {

    @Query("SELECT a FROM JPACategoryEG a WHERE a.categoryName = :categoryName")
    JPACategoryEG findByCategoryName(@Param("categoryName") String categoryName);

}
