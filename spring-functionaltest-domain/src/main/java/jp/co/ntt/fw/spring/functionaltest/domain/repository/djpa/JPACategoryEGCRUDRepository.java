/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.repository.djpa;

import jp.co.ntt.fw.spring.functionaltest.domain.model.JPACategoryEG;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JPACategoryEGCRUDRepository
                                            extends
                                            JpaRepository<JPACategoryEG, Integer> {

    @Query("SELECT a FROM JPACategoryEG a WHERE a.categoryName = :categoryName")
    JPACategoryEG findByCategoryName(@Param("categoryName") String categoryName);

}
